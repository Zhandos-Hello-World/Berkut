package kz.cicada.berkut.feature.auth.presentation.role

import kz.cicada.berkut.feature.auth.presentation.model.RoleDvo


interface ChooseRoleController {
    fun onConfirmClick()
    fun onNavigateBack()
    fun onSelectRole(role: RoleDvo)
}