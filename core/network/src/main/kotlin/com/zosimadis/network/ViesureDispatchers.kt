package com.zosimadis.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val viesureDispatcher: ViesureDispatchers)

enum class ViesureDispatchers {
    IO,
    Default,
}
