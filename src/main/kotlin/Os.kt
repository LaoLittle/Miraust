package org.laolittle

enum class Os {
    Windows,
    Linux,
    MacOS,
    Unknown,
}

val PlatformOs: Os = run {
    val osName = System.getProperty("os.name")

    when {
        osName.startsWith("Windows") -> Os.Windows
        osName == "Linux" -> Os.Linux
        osName.startsWith("Mac") -> Os.MacOS
        else -> Os.Unknown
    }
}

val librarySuffix = when(PlatformOs) {
    Os.Windows -> "dll"
    Os.MacOS -> "dylib"
    else -> "so"
}