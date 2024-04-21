package kz.cicada.berkut.feature.shareqr.presentation.share.socket


data class QRSocketModel(
    val status: Int,
    var parentInfo : ParentInfo?,
) {

    data class ParentInfo(
        val username: String?,
        val role: String?,
        val phoneNumber: String,
        val id: String,
        val links: List<Links>,
    )

    data class Links(
        var rel: String? = null,
        var href: String? = null,
    )
}