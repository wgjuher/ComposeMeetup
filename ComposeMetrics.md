* [Interpreting Compose Compiler Metrics](https://github.com/androidx/androidx/blob/androidx-main/compose/compiler/design/compiler-metrics.md)
* [Composable metrics](https://chris.banes.dev/composable-metrics/)
```
subprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
        kotlinOptions {
            if (project.findProperty("app.enableComposeCompilerReports") == "true") {
                freeCompilerArgs += [
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics"
                ]
                freeCompilerArgs += [
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                                project.buildDir.absolutePath + "/compose_metrics"
                ]
            }
        }
    }
}
```
```
./gradlew assembleRelease -Papp.enableComposeCompilerReports=true 
```
