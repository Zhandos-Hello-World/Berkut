package kz.cicada.berkut.behaviors

import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.children.presentation.childs.ChildrenBehavior
import kz.cicada.berkut.feature.tasks.navigation.TasksScreen
import kz.cicada.berkut.feature.tasks.presentation.list.TaskListLauncher
import kz.cicada.berkut.lib.core.ui.event.ActionEvent
import kz.cicada.berkut.lib.core.ui.event.ReplaceScreenEvent

@Parcelize
class AddTaskChildrenBehavior : ChildrenBehavior {

    override fun onClickNavigate(childId: Int): List<ActionEvent> {
        return listOf(
            ReplaceScreenEvent(
                TasksScreen.TaskList(
                    TaskListLauncher(childId)
                )
            )
        )
    }
}