package kz.cicada.berkut.feature.tasks.navigation

import kz.cicada.berkut.feature.tasks.presentation.add.AddTaskFragment
import kz.cicada.berkut.feature.tasks.presentation.add.AddTaskLauncher
import kz.cicada.berkut.feature.tasks.presentation.details.TaskDetailsFragment
import kz.cicada.berkut.feature.tasks.presentation.details.TaskDetailsLauncher
import kz.cicada.berkut.feature.tasks.presentation.list.TaskListFragment
import kz.cicada.berkut.feature.tasks.presentation.list.TaskListLauncher
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutFragmentScreen

object TasksScreen {

    fun AddTask(launcher: AddTaskLauncher): BerkutDialogFragmentScreen =
        BerkutDialogFragmentScreen {
            AddTaskFragment(launcher)
        }

    fun TaskDetails(launcher: TaskDetailsLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        TaskDetailsFragment(launcher)
    }

    fun TaskList(launcher: TaskListLauncher): BerkutFragmentScreen = BerkutFragmentScreen {
        TaskListFragment(launcher)
    }
}