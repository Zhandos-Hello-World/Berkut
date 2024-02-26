package kz.cicada.berkut.feature.language.presentation.role

import kz.cicada.berkut.feature.language.presentation.model.RoleDvo

interface ChooseRoleController {
    fun onConfirmClick()
    fun onNavigateBack()
    fun onSelectRole(role: RoleDvo)
}