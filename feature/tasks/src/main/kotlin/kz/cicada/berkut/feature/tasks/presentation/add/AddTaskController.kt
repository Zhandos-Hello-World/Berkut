package kz.cicada.berkut.feature.tasks.presentation.add

interface AddTaskController {
    fun onNavigateBack()
    fun addTask()
    fun inputCoins(coins: String)
    fun inputDescription(description: String)
    fun inputName(name: String)
}