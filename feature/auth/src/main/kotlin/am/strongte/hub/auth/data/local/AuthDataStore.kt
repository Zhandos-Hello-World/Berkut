package am.strongte.hub.auth.data.local

//import am.strongte.auth.TokensPreferences
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.dataStore
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.SupervisorJob
//
//private const val DATA_STORE_FILE_NAME = "auth_tokens.pb"
//val Context.authDataStore: DataStore<TokensPreferences> by dataStore(
//    fileName = DATA_STORE_FILE_NAME,
//    serializer = TokensSerializer,
//    scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
//    produceMigrations = { listOf() },
//    corruptionHandler = null,
//)
