package com.hechimdemo.dashboard.profile

import com.hechimdemo.dashboard.api.profile.viewmodel.UserProfileViewModel
import com.hechimdemo.dashboard.util.BaseMockkTest
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.ui.api.navigation.Navigator
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
class UserProfileViewModelTest: BaseMockkTest<UserProfileViewModel>() {

    @MockK
    lateinit var profileProvider: ProfileProvider
    @MockK
    lateinit var profile: Profile
    @MockK
    lateinit var navigator: Navigator

    override fun createSut(): UserProfileViewModel = UserProfileViewModel(profileProvider, profile, navigator)

    @BeforeEach
    override fun setUp() {
        coEvery { profileProvider.profileFlow } returns flowOf(ProfileTestResponse.user)
    }

    @Test
    fun `initial states`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        assert(stub.user is HechimResource.Loading)
        assert(stub.userContent.email == "")
    }

    @Test
    fun `main test - test all fields`() = runTest(UnconfinedTestDispatcher()){
        val stub = createSut()
        stub.collectProfile()
        advanceUntilIdle()
        assert((stub.user as HechimResource.Success).data.firstName == ProfileTestResponse.user.firstName)
        assert((stub.user as HechimResource.Success).data.lastName == ProfileTestResponse.user.lastName)

        assert(stub.userContent.firstName == ProfileTestResponse.user.firstName)
        assert(stub.userContent.lastName == ProfileTestResponse.user.lastName)
        stub.navigateToLastName()
        stub.navigateToFirstName()
    }
}
