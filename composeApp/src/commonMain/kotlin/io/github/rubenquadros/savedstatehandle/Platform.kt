package io.github.rubenquadros.savedstatehandle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform