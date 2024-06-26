package kz.cicada.berkut.lib.core.ui.base

sealed class LoadingType {
    object Progress : LoadingType()
    object Skeleton : LoadingType()
    object BlockingDialog : LoadingType()
}
