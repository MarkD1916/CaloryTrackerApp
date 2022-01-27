apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))

    "implementation"(project(Retrofit.okHttp))
    "implementation"(project(Retrofit.retrofit))
    "implementation"(project(Retrofit.okHttpLoggingInterceptor))
    "implementation"(project(Retrofit.moshiConverter))

    "kapt"(project(Room.roomCompiler))
    "implementation"(project(Room.roomKtx))
    "implementation"(project(Room.roomRuntime))
}