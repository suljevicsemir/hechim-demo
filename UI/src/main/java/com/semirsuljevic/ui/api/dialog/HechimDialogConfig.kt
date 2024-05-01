package com.semirsuljevic.ui.api.dialog

data class HechimDialogConfig(
    val title: String,
    val description: String,
    val onConfirm: () -> Unit,
    val onDismiss: () -> Unit,
    val dismissButton: String,
    val confirmButton: String,
    val visible: Boolean
)
