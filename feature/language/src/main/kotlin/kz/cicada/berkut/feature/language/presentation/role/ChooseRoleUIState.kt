package kz.cicada.berkut.feature.language.presentation.role

import kz.cicada.berkut.feature.language.presentation.model.RoleDvo

data class ChooseRoleUIState(
    val roles: List<RoleDvo>,
    val selected: RoleDvo? = null,
)