package kz.cicada.berkut.feature.profile.presentation.privacy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun PrivacyContent(
    onNavigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary)
            .verticalScroll(rememberScrollState()),
    ) {
        Toolbar(
            navigateUp = onNavigateBack,
            title = "Privacy policy",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            text = """“Berkut” is committed to protecting the privacy of its users. This Privacy Policy outlines how we collect, use, disclose, and safeguard your information when you use our mobile application.
Information Collection
We may collect certain information from you when you use our mobile application, including but not limited to:
Personal information such as name, email address, and phone number (only if provided voluntarily).
Device information such as device model, operating system version, unique device identifiers, and mobile network information.
Usage data including app usage statistics, interactions within the app, and preferences.
Location information if you enable location services within the app.
Use of Information
We may use the information collected to:
Provide and maintain our services to you.
Improve and optimize our mobile application.
Personalize your experience and provide tailored content and features.
Respond to your inquiries, requests, and feedback.
Communicate with you about updates, promotions, and other relevant information.
Data Sharing
We do not sell, trade, or rent your personal information to third parties. However, we may share your information in the following circumstances:
With service providers and third-party vendors who assist us in operating our application and providing services to you.
In response to legal requests or to comply with applicable laws, regulations, or legal processes.
In connection with a merger, acquisition, or sale of all or a portion of our assets.
Data Retention
We will retain your information only for as long as necessary to fulfill the purposes outlined in this Privacy Policy unless a longer retention period is required or permitted by law.
Security
We take reasonable measures to protect the security and integrity of your information. However, please be aware that no security measures are impenetrable, and we cannot guarantee the absolute security of your data.
Children's Privacy
Our mobile application is not directed to children under the age of 13. We do not knowingly collect personal information from children under 13. If you believe we have collected information from a child under 13, please contact us immediately, and we will take appropriate steps to remove such information.
Changes to This Privacy Policy
We may update this Privacy Policy from time to time. Any changes will be effective immediately upon posting the revised Privacy Policy within the mobile application. We encourage you to review this Privacy Policy periodically for any updates.
Contact Us
If you have any questions or concerns about this Privacy Policy or our practices regarding your information, please contact us at [contact email/phone number/address].
By using our mobile application, you agree to the collection and use of your information as outlined in this Privacy Policy.
        """.trimMargin(),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.additionalColors.elementsLowContrast,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun PrivacyContentPreview() {
    AppTheme {
        PrivacyContent {}
    }
}