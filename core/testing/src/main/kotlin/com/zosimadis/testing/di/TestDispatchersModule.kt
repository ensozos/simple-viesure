package com.zosimadis.testing.di

import com.zosimadis.network.Dispatcher
import com.zosimadis.network.ViesureDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [TestDispatcherModule::class],
)
internal object TestDispatchersModule {

    @Provides
    @Dispatcher(ViesureDispatchers.IO)
    fun providesIODispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher = testDispatcher

    @Provides
    @Dispatcher(ViesureDispatchers.Default)
    fun provideDefaultDispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher =
        testDispatcher
}
