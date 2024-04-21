package kz.cicada.berkut.feature.profile.presentation.logout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.profile.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton

@Composable
fun LogoutConfirmContent(
    controller: LogoutConfirmController,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                ),
            )
            .background(MaterialTheme.additionalColors.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .size(
                    width = 40.dp,
                    height = 5.dp,
                ),
            backgroundColor = MaterialTheme.additionalColors.backgroundAccent,
            content = { },
        )

        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = R.drawable.ic_person_remove),
            contentDescription = null,
            tint = MaterialTheme.additionalColors.backgroundAccent,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Are you sure you want to proceed with deleting your account?",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.additionalColors.elementsLowContrast,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(24.dp))
        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.additionalColors.elementsError,
            ),
            text = "Yes",
            onClick = controller::onLogout,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonSecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            text = "No",
            onClick = controller::onCancelLogout,
        )
        Spacer(modifier = Modifier.height(24.dp))
    }

}

@Preview
@Composable
fun LogoutConfirmPreview() {
    AppTheme {
        LogoutConfirmContent(
            controller = object : LogoutConfirmController {
                override fun onLogout() = Unit
                override fun onCancelLogout() = Unit
            },
        )
    }
}