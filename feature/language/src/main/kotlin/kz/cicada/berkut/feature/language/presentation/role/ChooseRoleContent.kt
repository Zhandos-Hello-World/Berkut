package kz.cicada.berkut.feature.language.presentation.role

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.auth.domain.model.UserType
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.card.IconCard
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar
import kz.cicada.berkut.feature.language.presentation.model.RoleDvo

@Composable
internal fun ChooseRoleContent(
    uiState: ChooseRoleUIState,
    controller: ChooseRoleController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary),
    ) {
        Toolbar(navigateUp = controller::onNavigateBack)
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = kz.cicada.berkut.feature.language.R.string.choice_role),
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
            Spacer(modifier = Modifier.weight(1F))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                uiState.roles.forEach { role ->
                    IconCard(
                        modifier = Modifier.weight(1F),
                        headText = role.title.resolve(),
                        iconRes = role.icon,
                        selected = role == uiState.selected,
                        onClick = {
                            controller.onSelectRole(role)
                        },
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }

            Spacer(modifier = Modifier.weight(1F))

            CommonPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, bottom = 16.dp),
                text = stringResource(id = kz.cicada.berkut.feature.language.R.string.confirm),
                onClick = controller::onConfirmClick,
            )
        }
    }
}

@Preview
@Composable
fun ChooseRoleContentContentPreview() {
    class FakeChooseRoleController : ChooseRoleController {
        override fun onConfirmClick() = Unit
        override fun onNavigateBack() = Unit
        override fun onSelectRole(role: RoleDvo) = Unit
    }
    AppTheme {
        ChooseRoleContent(
            controller = FakeChooseRoleController(),
            uiState = ChooseRoleUIState(
                roles = listOf(
                    RoleDvo(
                        title = VmRes.StrRes(kz.cicada.berkut.feature.language.R.string.child),
                        icon = R.drawable.ic_lucky_avatar,
                        type = UserType.CHILD,
                    ), RoleDvo(
                        title = VmRes.StrRes(kz.cicada.berkut.feature.language.R.string.parent),
                        icon = R.drawable.ic_lucky_motivating_yoda,
                        type = UserType.CHILD,
                    )
                ),
                selected = null,
            ),
        )
    }
}