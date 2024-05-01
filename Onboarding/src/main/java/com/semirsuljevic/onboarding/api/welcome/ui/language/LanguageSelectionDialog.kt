package com.semirsuljevic.onboarding.api.welcome.ui.language

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hechimdemo.onboarding.R
import com.semirsuljevic.ui.api.dialog.HechimDialog
import com.semirsuljevic.ui.api.dialog.HechimDialogConfig

@Composable
fun LanguageSelectionDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    visible: Boolean,
    language: String
) {

    if(visible) {
        HechimDialog(
            config = HechimDialogConfig(
                onConfirm = onConfirm,
                confirmButton = stringResource(id = R.string.common_yes),
                onDismiss = onDismiss,
                dismissButton = stringResource(id = R.string.common_no),
                title = stringResource(id = R.string.language_selection_alert_title),
                description = stringResource(id = R.string.language_selection_alert_content, language),
                visible = visible,
            )
        )
    }
}

@Preview
@Composable
fun LanguageSelectionDialogPreview() {
    LanguageSelectionDialog(
        onConfirm = {},
        onDismiss = {},
        visible = true,
        language = "English"
    )
}
