package com.semirsuljevic.storage.interval.di

import com.semirsuljevic.storage.api.secure.SecureStorage
import com.semirsuljevic.storage.interval.secure.SecureStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SecureModule {
    @Binds
    abstract fun bindSecureStorage(
        secureStorageImpl: SecureStorageImpl
    ): SecureStorage
}
