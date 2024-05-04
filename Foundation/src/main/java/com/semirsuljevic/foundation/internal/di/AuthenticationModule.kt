package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.internal.authentication.HechimAuthenticationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {
    @Binds
    abstract fun bindAuthenticationApi(authenticationImpl: HechimAuthenticationImpl) : HechimAuthentication
}
