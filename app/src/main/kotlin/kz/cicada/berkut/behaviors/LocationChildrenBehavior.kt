package kz.cicada.berkut.behaviors

import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.children.presentation.childs.ChildrenBehavior
import kz.cicada.berkut.feature.savedlocations.navigation.SavedLocationsScreens
import kz.cicada.berkut.feature.savedlocations.presentation.list.SaveLocationListLauncher
import kz.cicada.berkut.lib.core.ui.event.ActionEvent
import kz.cicada.berkut.lib.core.ui.event.ReplaceScreenEvent

@Parcelize
class LocationChildrenBehavior : ChildrenBehavior {

    override fun onClickNavigate(childId: Int): List<ActionEvent> {
        return listOf(
            ReplaceScreenEvent(
                SavedLocationsScreens.AllSaveLocations(
                    launcher = SaveLocationListLauncher(
                        childId,
                    )
                )
            )
        )
    }
}