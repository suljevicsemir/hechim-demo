import com.android.build.gradle.LibraryExtension
import com.semirsuljevic.hechimdemo.configureBuildType
import com.semirsuljevic.hechimdemo.configureProjectFlavor
import com.semirsuljevic.hechimdemo.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("drivewellapp.android.library")
                apply("drivewellapp.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureBuildType(this)
                configureProjectFlavor(this)
            }

            dependencies {
                // add("implementation", libs.findLibrary("lifecycle.runtimeCompose").get())
                // add("implementation", libs.findLibrary("lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
                add("implementation", libs.findLibrary("datastore").get())
                add("implementation", libs.findLibrary("kotlinx.collections.immutable").get())
            }
        }
    }
}