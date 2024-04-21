object Util {
    private const val jodaTimeVersion = "2.12.7"
    const val jodaTime = "net.danlew:android.joda:$jodaTimeVersion"

    private const val timberVersion = "5.0.1"
    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    private const val chuckerVersion = "4.0.0"
    const val chuckerLibrary = "com.github.chuckerteam.chucker:library:$chuckerVersion"
    const val chuckerLibraryNoOp = "com.github.chuckerteam.chucker:library-no-op:$chuckerVersion"

    object Hyperion {
        private const val hyperionVersion = "0.9.34"
        private const val hyperionNemironVersion = "0.3.3"

        const val hyperionCore = "com.willowtreeapps.hyperion:hyperion-core:$hyperionVersion"
        const val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:$hyperionVersion"
        const val hyperionDisk = "com.willowtreeapps.hyperion:hyperion-disk:$hyperionVersion"
        const val hyperionPreferences = "com.willowtreeapps.hyperion:hyperion-shared-preferences:$hyperionVersion"
        const val hyperionTimber = "com.willowtreeapps.hyperion:hyperion-timber:$hyperionVersion"

        const val hyperionNetworkEmulation = "me.nemiron.hyperion:network-emulation:$hyperionNemironVersion"
        const val hyperionChucker = "me.nemiron.hyperion:chucker:$hyperionNemironVersion"

        val all = listOf(
            hyperionCore,
//            hyperionCrash,
            hyperionDisk,
            hyperionPreferences,
            hyperionTimber,
            hyperionNetworkEmulation,
            hyperionChucker,
        )
    }

}