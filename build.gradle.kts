plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

// -- Redirect build output out of OneDrive (Windows local dev only) --
// OneDrive's file watcher holds files inside build/ open, which makes Gradle's
// clean phase fail intermittently with "Unable to delete directory". On
// Windows we redirect every subproject's buildDir to a local-disk scratch path.
if (System.getProperty("os.name").startsWith("Windows", ignoreCase = true)) {
    val buildRoot = file("C:/GradleTmp/watchdevtools-build")
    subprojects {
        layout.buildDirectory.set(file("$buildRoot/${project.name}"))
    }
}
