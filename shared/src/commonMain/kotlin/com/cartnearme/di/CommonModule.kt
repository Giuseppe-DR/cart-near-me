package com.cartnearme.di

import com.cartnearme.data.DatabaseDriverFactory
import com.cartnearme.db.CartDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val commonModule = module {
    // We expect the platform application (Android/iOS) to inject the DatabaseDriverFactory instance
    single<CartDatabase> {
        val driverFactory = get<DatabaseDriverFactory>()
        CartDatabase(driverFactory.createDriver())
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}
