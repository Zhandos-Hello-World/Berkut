package kz.cicada.berkut.feature.uploadphoto.presentation.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class AvatarSettings: Parcelable {
    OPEN_GALLERY,
    OPEN_CAMERA,
}