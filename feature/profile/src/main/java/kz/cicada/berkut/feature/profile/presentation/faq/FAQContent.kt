package kz.cicada.berkut.feature.profile.presentation.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.profile.presentation.model.FAQDvo
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.expandable.ExpandableColumn
import kz.cicada.berkut.lib.core.ui.compose.widgets.expandable.ExpandedItem
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun FAQContent(
    uiState: FAQUIState,
    controller: FAQController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundDark),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Toolbar(
            title = "FAQ",
            navigateUp = controller::navigateUp,
        )

        when (uiState) {
            is FAQUIState.Loading -> {
                Spacer(modifier = Modifier.weight(1F))
                CustomProgressBar(
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.CenterHorizontally),
                    color = MaterialTheme.additionalColors.backgroundAccent,
                )
                Spacer(modifier = Modifier.weight(1F))

            }

            is FAQUIState.Data -> {
                ExpandableColumn(
                    items = uiState.list.map {
                        ExpandedItem(
                            title = it.question,
                            answer = it.answer,
                        )
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun FAQDataContentPreview() {
    AppTheme {
        FAQContent(
            uiState = FAQUIState.Data(
                list = listOf(
                    FAQDvo(
                        question = "First Question",
                        answer = "First answer",
                    ),
                    FAQDvo(
                        question = "First Question",
                        answer = "First answer",
                    ),
                    FAQDvo(
                        question = "First Question",
                        answer = "First answer",
                    ),
                    FAQDvo(
                        question = "First Question",
                        answer = "First answer",
                    ),
                    FAQDvo(
                        question = "First Question",
                        answer = "First answer",
                    ),
                )
            ),
            controller = object : FAQController {
                override fun navigateUp() = Unit
            },
        )
    }
}


@Preview
@Composable
fun FAQLoadingContentPreview() {
    AppTheme {
        FAQContent(
            uiState = FAQUIState.Loading,
            controller = object : FAQController {
                override fun navigateUp() = Unit
            },
        )
    }
}