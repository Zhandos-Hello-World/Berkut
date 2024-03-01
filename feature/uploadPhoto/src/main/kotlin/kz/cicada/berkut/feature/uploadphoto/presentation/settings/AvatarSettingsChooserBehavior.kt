package kz.cicada.berkut.feature.uploadphoto.presentation.settings

import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserBehavior
import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import kz.cicada.berkut.feature.uploadPhoto.R
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import kz.cicada.berkut.lib.core.ui.compose.R as composeR

@Parcelize
internal class AvatarSettingsChooserBehavior : SimpleChooserBehavior {
    override fun getHeaderOrNull(): VmRes<CharSequence> = VmRes.StrRes(R.string.choose_photo)

    override suspend fun getItemList(): List<ChooserDvo> {
        return listOf(
            ChooserDvo.SecondaryButton(
                id = AvatarSettings.OPEN_GALLERY.name,
                text = VmRes.StrRes(R.string.from_gallery),
                dividerVisible = true,
                leadingIcon = composeR.drawable.ic_gallery,
            ),
            ChooserDvo.SecondaryButton(
                id = AvatarSettings.OPEN_CAMERA.name,
                text = VmRes.StrRes(R.string.take_picture),
                dividerVisible = false,
                leadingIcon = composeR.drawable.ic_photo,
            ),
        )
    }

    override suspend fun onSecondaryButtonClick(item: ChooserDvo.SecondaryButton): List<SystemEvent> {
        return listOf(
            AvatarSettingsResultEvent(
                when (item.id) {
                    AvatarSettings.OPEN_CAMERA.name -> AvatarSettings.OPEN_CAMERA
                    else -> AvatarSettings.OPEN_GALLERY
                },
            ),
        )
    }
}
