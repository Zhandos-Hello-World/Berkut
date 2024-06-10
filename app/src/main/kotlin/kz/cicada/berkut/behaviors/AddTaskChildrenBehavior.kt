package kz.cicada.berkut.behaviors

import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.children.navigation.ChildrenScreens
import kz.cicada.berkut.feature.children.presentation.childs.Child
import kz.cicada.berkut.feature.children.presentation.childs.ChildrenBehavior
import kz.cicada.berkut.feature.children.presentation.operation.ChildrenOperationLauncher
import kz.cicada.berkut.feature.tasks.navigation.TasksScreen
import kz.cicada.berkut.feature.tasks.presentation.list.TaskListLauncher
import kz.cicada.berkut.lib.core.ui.event.ActionEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent

@Parcelize
class AddTaskChildrenBehavior : ChildrenBehavior {

    override fun onClickNavigate(child: Child): List<ActionEvent> {
        return listOf(
            OpenScreenEvent(
                ChildrenScreens.ChildOperationScreen(
                    launcher = ChildrenOperationLauncher(
                        childrenId = child.id.toString(),
                        imageUrl = child.imageUrl,
                        phoneNumber = "+7" + child.phoneNumber,
                        username = child.name,
                        coins = child.coins,
                    )
                )
            )
        )
    }
}