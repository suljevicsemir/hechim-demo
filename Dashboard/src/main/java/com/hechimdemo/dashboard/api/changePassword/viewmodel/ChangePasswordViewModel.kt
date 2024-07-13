package com.hechimdemo.dashboard.api.changePassword.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hechimdemo.dashboard.R
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.TextFieldConfig
import com.semirsuljevic.foundation.api.common.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val hechimAuthentication: HechimAuthentication,
    private val credentialsValidator: CredentialsValidator
): ViewModel() {
    private val _resource = mutableStateOf<HechimResource<Boolean>>(HechimResource.Nothing())
    val resource get() = _resource.value

    fun resetResource() {
        _resource.value = HechimResource.Nothing()
    }

    private val _oldPassword = mutableStateOf(TextFieldConfig())
    val oldPassword get() = _oldPassword.value

    private val _newPassword = mutableStateOf(TextFieldConfig())
    val newPassword get() = _newPassword.value

    private val _confirmedPassword = mutableStateOf(TextFieldConfig())
    val confirmedPassword get() = _confirmedPassword.value

    fun onOldChanged(value: String) { onTextChanged(_oldPassword, value) }
    fun onNewChanged(value: String) { onTextChanged(_newPassword, value) }
    fun onConfirmedChanged(value: String) { onTextChanged(_confirmedPassword, value) }

    private fun onTextChanged(state: MutableState<TextFieldConfig>, value: String) {
        state.value = state.value.copy(
            text = value,
            error = UiText.StringValue("")
        )
    }


    fun changePassword() {
        if(!validatePasswords()) {
            return
        }
        viewModelScope.launch {
            _resource.value = HechimResource.Loading("")
            delay(1000)
            _resource.value = hechimAuthentication.changePassword(
                _oldPassword.value.text.trim(),
                _newPassword.value.text.trim()
            )
            if(_resource.value is HechimResource.Success) {
                onTextChanged(_oldPassword, "")
                onTextChanged(_newPassword, "")
                onTextChanged(_confirmedPassword, "")
            }
        }

    }

    private fun validatePasswords(): Boolean{
        //new password equal to old one, show error
        if(_newPassword.value.text.trim() == _oldPassword.value.text.trim()) {
            _newPassword.value = _newPassword.value.copy(
                error = UiText.StringResource(R.string.new_password_same_as_old)
            )
            return false
        }
        //
        if(!credentialsValidator.validatePassword(_newPassword.value.text.trim())) {
            _newPassword.value = _newPassword.value.copy(
                error = UiText.StringResource(R.string.new_password_invalid)
            )
            return false
        }
        if(_newPassword.value != _confirmedPassword.value) {
            _confirmedPassword.value = _confirmedPassword.value.copy(
                error = UiText.StringResource(R.string.passwords_not_equal)
            )
            return false
        }
        return true
    }




}
