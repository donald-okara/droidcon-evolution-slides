plugins {
    alias(libs.plugins.kotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatformPlugin)
}

compose {
    resources {
        publicResClass = true // generates Res.*
        packageOfResClass = "ski.shared.resources.generated.resources" // pinned so the rootProject.name rename doesn't move the generated package
    }
}

kotlin{
    sourceSets{
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}