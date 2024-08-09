package com.hechimdemo.dashboard.changePassword

import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.changePassword.viewmodel.ChangePasswordViewModel
import com.hechimdemo.dashboard.util.BaseMockkTest
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.UiText
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
@OptIn(ExperimentalCoroutinesApi::class)
internal class ChangePasswordViewModelTest: BaseMockkTest<ChangePasswordViewModel>() {

    @MockK
    lateinit var hechimAuthentication: HechimAuthentication
    @MockK
    lateinit var credentialsValidator: CredentialsValidator

    override fun createSut(): ChangePasswordViewModel = ChangePasswordViewModel(
        hechimAuthentication, credentialsValidator
    )

    @BeforeEach
    override fun setUp() {
        coEvery { hechimAuthentication.changePassword(
            ChangePasswordTestResponse.OLD_PASSWORD, ChangePasswordTestResponse.NEW_PASSWORD) } returns HechimResource.Success(true)
        coEvery { hechimAuthentication.changePassword(
            ChangePasswordTestResponse.NEW_PASSWORD, ChangePasswordTestResponse.OLD_PASSWORD) } returns HechimResource.Error(error = HechimError(message = ""))
        every { credentialsValidator.validatePassword(ChangePasswordTestResponse.VALID_PASSWORD) } returns true
        every { credentialsValidator.validatePassword(ChangePasswordTestResponse.NEW_PASSWORD) } returns true
        every { credentialsValidator.validatePassword(ChangePasswordTestResponse.INVALID_PASSWORD) } returns false
    }


    @Test
    fun `initial values`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        assert(stub.oldPassword.text == "")
        assert(stub.oldPassword.error == UiText.StringValue(""))

        assert(stub.newPassword.text == "")
        assert(stub.newPassword.error == UiText.StringValue(""))

        assert(stub.confirmedPassword.text == "")
        assert(stub.confirmedPassword.error == UiText.StringValue(""))
    }

    @Test
    fun `on text change - all three fields`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        val old = "test old"
        val new = "test old"
        val confirmed = "test confirmed"
        stub.onOldChanged(old)
        assert(stub.oldPassword.text == old)
        assert(stub.oldPassword.error == UiText.StringValue(""))
        stub.onNewChanged(new)
        assert(stub.newPassword.text == new)
        assert(stub.newPassword.error == UiText.StringValue(""))
        stub.onConfirmedChanged(confirmed)
        assert(stub.confirmedPassword.text == confirmed)
        assert(stub.confirmedPassword.error == UiText.StringValue(""))
    }

    @Test
    fun `text field errors`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        val oldAndNewIdentical = "Hechim123!"
        stub.onOldChanged(oldAndNewIdentical)
        stub.onNewChanged(oldAndNewIdentical)
        stub.changePassword()
        advanceUntilIdle()
        //set new password same as old one
        assert((stub.newPassword.error as UiText.StringResource).resourceId
            == UiText.StringResource(R.string.new_password_same_as_old).resourceId)

        stub.onOldChanged("")
        stub.onNewChanged(ChangePasswordTestResponse.INVALID_PASSWORD)
        stub.changePassword()
        advanceUntilIdle()

        //password invalid error text
        assert((stub.newPassword.error as UiText.StringResource).resourceId
            == UiText.StringResource(R.string.new_password_invalid).resourceId)

        //test after any change that error disappears
        stub.onNewChanged("")
        assert(stub.newPassword.error == UiText.StringValue(""))

        stub.onNewChanged(ChangePasswordTestResponse.VALID_PASSWORD)
        stub.onConfirmedChanged(ChangePasswordTestResponse.VALID_PASSWORD.plus("1"))
        stub.changePassword()
        advanceUntilIdle()
        assert((stub.confirmedPassword.error as UiText.StringResource).resourceId
           == UiText.StringResource(R.string.passwords_not_equal).resourceId)
    }


    @Test
    fun `endpoint call - success`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        stub.onOldChanged(ChangePasswordTestResponse.OLD_PASSWORD)
        stub.onNewChanged(ChangePasswordTestResponse.NEW_PASSWORD)
        stub.onConfirmedChanged(ChangePasswordTestResponse.NEW_PASSWORD)
        stub.changePassword()
        //test that response state is in loading state
        assert(stub.resource is HechimResource.Loading)
        advanceUntilIdle()
        assert(stub.resource is HechimResource.Success)
        //check that fields are empty after successful change
        assert(stub.newPassword.text.isEmpty())
        assert(stub.oldPassword.text.isEmpty())
        assert(stub.confirmedPassword.text.isEmpty())
    }

    @Test
    fun `endpoint call - error`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        stub.onOldChanged(ChangePasswordTestResponse.NEW_PASSWORD)
        stub.onNewChanged(ChangePasswordTestResponse.OLD_PASSWORD)
        stub.onConfirmedChanged(ChangePasswordTestResponse.OLD_PASSWORD)
        stub.changePassword()
        //test that response state is in loading state
        assert(stub.resource is HechimResource.Loading)
        advanceUntilIdle()
        assert(stub.resource is HechimResource.Error)
        //check that fields are empty after successful change
    }
}
