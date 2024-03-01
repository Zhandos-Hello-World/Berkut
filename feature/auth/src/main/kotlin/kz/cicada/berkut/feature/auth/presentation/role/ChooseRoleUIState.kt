package kz.cicada.berkut.feature.auth.presentation.role

import kz.cicada.berkut.feature.auth.presentation.model.RoleDvo

data class ChooseRoleUIState(
    val roles: List<RoleDvo>,
    val selected: RoleDvo? = null,
)