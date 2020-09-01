package org.jlleitschuh.gradle.ktlint

import org.assertj.core.api.Assertions.assertThat
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class KtlintPluginVersionTest : AbstractPluginTest() {

    private fun File.buildScriptUsingKtlintVersion(version: String) {
        buildFile().writeText(
            """
                ${pluginsBlockWithMainPluginAndKotlinJvm()}

                repositories {
                    gradlePluginPortal()
                }

                buildDir = file("directory with spaces")

                ktlint {
                    version = "$version"
                }
            """.trimIndent()
        )
    }

    @BeforeEach
    fun setup() {
        projectRoot.withCleanSources()
    }

    @Test
    fun `with ktlint version equal to 0_22`() {
        projectRoot.buildScriptUsingKtlintVersion("0.22.0")
        build("ktlintCheck").apply {
            assertThat(task(":ktlintMainSourceSetCheck")!!.outcome).isEqualTo(TaskOutcome.SUCCESS)
        }
    }

    @Test
    fun `with ktlint version less than 0_22`() {
        projectRoot.buildScriptUsingKtlintVersion("0.21.0")
        buildAndFail("ktlintCheck").apply {
            assertThat(task(":ktlintMainSourceSetCheck")!!.outcome).isEqualTo(TaskOutcome.FAILED)
        }
    }
}
