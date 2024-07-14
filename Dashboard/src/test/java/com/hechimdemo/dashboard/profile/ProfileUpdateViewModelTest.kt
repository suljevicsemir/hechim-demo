package com.hechimdemo.dashboard.profile

import com.hechimdemo.dashboard.api.profile.viewmodel.ProfileUpdateViewModel
import com.hechimdemo.dashboard.changePassword.ChangePasswordTestResponse
import com.hechimdemo.dashboard.util.BaseMockkTest
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.foundation.api.user.model.HechimUser
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileUpdateViewModelTest: BaseMockkTest<ProfileUpdateViewModel>() {

    @MockK
    lateinit var profile: Profile
    @MockK
    lateinit var profileProvider: ProfileProvider

    override fun createSut(): ProfileUpdateViewModel = ProfileUpdateViewModel(profile, profileProvider)

    @BeforeEach
    override fun setUp() {
        coEvery { profileProvider.profileFlow } returns flowOf(ProfileTestResponse.user)
        val newUser = ProfileTestResponse.newUser
        coEvery { profile.updateName(newUser.firstName, newUser.lastName) } returns HechimResource.Success(true)
    }

    @Test
    fun `initial states`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        assert(stub.firstName == ProfileTestResponse.user.firstName)
        assert(stub.lastName == ProfileTestResponse.user.lastName)
        //implicitly test _user field via button getters
        assert(!stub.firstNameButton)
        assert(!stub.lastNameButton)
        assert(stub.state is HechimResource.Nothing)
    }

    @Test
    fun `text field setters and buttons enabled`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        stub.setFirstName(ProfileTestResponse.user.firstName.plus("X"))
        assert(stub.firstNameButton)
        stub.setLastName(ProfileTestResponse.user.lastName.plus("X"))
        assert(stub.lastNameButton)
        stub.setFirstName(ProfileTestResponse.user.firstName)
        assert(!stub.firstNameButton)
        stub.setLastName(ProfileTestResponse.user.lastName)
        assert(!stub.lastNameButton)
    }

    @Test
    fun `update call`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        stub.setFirstName(ProfileTestResponse.newUser.firstName)
        stub.updateName()
        //test loader
        assert(stub.state is HechimResource.Loading)
        advanceUntilIdle()
        assert(stub.state is HechimResource.Success)
    }
}
