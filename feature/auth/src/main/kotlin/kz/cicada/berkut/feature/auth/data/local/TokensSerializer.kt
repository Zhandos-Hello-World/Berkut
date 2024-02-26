package kz.cicada.berkut.feature.auth.data.local

//import am.strongte.auth.TokensPreferences
//import am.strongte.core.error.handling.UnknownException
//import androidx.datastore.core.CorruptionException
//import androidx.datastore.core.Serializer
//import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
//import java.io.InputStream
//import java.io.OutputStream
//
//object TokensSerializer : Serializer<TokensPreferences> {
//
//    override val defaultValue: TokensPreferences = TokensPreferences.getDefaultInstance()
//
//    override suspend fun readFrom(input: InputStream): TokensPreferences {
//        try {
//            return TokensPreferences.parseFrom(input)
//        } catch (exception: InvalidProtocolBufferException) {
//            throw UnknownException(CorruptionException("Cannot read proto.", exception))
//        }
//    }
//
//    override suspend fun writeTo(t: TokensPreferences, output: OutputStream) = t.writeTo(output)
//
//}