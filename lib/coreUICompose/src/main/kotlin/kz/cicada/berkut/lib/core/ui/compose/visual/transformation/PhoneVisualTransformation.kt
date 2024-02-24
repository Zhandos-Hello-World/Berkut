package kz.cicada.berkut.lib.core.ui.compose.visual.transformation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import kz.cicada.berkut.lib.core.empty
import kotlin.math.absoluteValue

private const val ZERO_VALUE = 0
private const val ONE_VALUE = 1
private const val TWO_VALUE = 2
private const val KZ_OR_RU_PHONE_MASK = "# ### ### ## ##"
private const val MASKED_CHAR = '#'
private const val HINT_CHAR = 'ˍ'
private const val PHONE_PREFIX = "+"

class PhoneVisualTransformation(
    private val isKzOrRuPhone: Boolean,
    private val hintColor: Color,
    private val hintChar: Char = HINT_CHAR,
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return if (isKzOrRuPhone) {
            transformKzOrRuPhone(value = text)
        } else {
            transformForeignPhone(value = text)
        }
    }

    private fun transformForeignPhone(value: AnnotatedString): TransformedText {
        val builder = AnnotatedString.Builder()
        val prefixOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset + TWO_VALUE
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    value.isEmpty() -> ZERO_VALUE
                    offset <= value.length -> offset
                    else -> value.length
                }
            }
        }

        builder.append("$PHONE_PREFIX ${value.text}")

        return TransformedText(
            builder.toAnnotatedString(),
            prefixOffsetTranslator,
        )
    }

    private fun transformKzOrRuPhone(value: AnnotatedString): TransformedText {
        val specialSymbolsIndices = KZ_OR_RU_PHONE_MASK.indices.filter {
            KZ_OR_RU_PHONE_MASK[it] != MASKED_CHAR
        }

        // Преобразование текста в соответсвии с маской
        var out = String.empty
        var maskIndex = ZERO_VALUE
        value.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += KZ_OR_RU_PHONE_MASK[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }

        val builder = AnnotatedString.Builder()
        builder.append(PHONE_PREFIX + out)

        // Добавление placeholder на остальную часть текста в соответсвии с маской
        while (maskIndex <= KZ_OR_RU_PHONE_MASK.lastIndex) {
            if (specialSymbolsIndices.contains(maskIndex)) {
                builder.append(KZ_OR_RU_PHONE_MASK[maskIndex])
            } else if (specialSymbolsIndices.isNotEmpty()) {
                builder.withStyle(style = SpanStyle(color = hintColor)) {
                    append(hintChar)
                }
            }
            maskIndex++
        }

        val transformedText = builder.toAnnotatedString()
        val prefixOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val offsetValue = offset.absoluteValue
                if (offsetValue == ZERO_VALUE) return ONE_VALUE

                var maskedCharsCount = ZERO_VALUE
                val masked = KZ_OR_RU_PHONE_MASK.takeWhile {
                    if (it == MASKED_CHAR) maskedCharsCount++
                    maskedCharsCount < offsetValue
                }

                return masked.length + TWO_VALUE
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= ONE_VALUE || value.isEmpty() -> ZERO_VALUE
                    offset <= value.length -> offset
                    else -> value.length
                }
            }
        }
        return TransformedText(transformedText, prefixOffsetTranslator)
    }
}