package org.jlleitschuh.gradle.ktlint.sample.kotlin.multiplatform

/**
 * JavaScript platform implementation of demo class.
 */
actual class Sample {
    actual fun doPlatformThing(): CharSequence {
        return "Hello from JavaScript platform"
    }
}
