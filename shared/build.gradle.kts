plugins {
  kotlin("multiplatform")
  id("com.android.library")
  id("com.google.devtools.ksp").version("1.8.0-1.0.8")
  id("com.rickclephas.kmp.nativecoroutines").version("1.0.0-ALPHA-4")
}

kotlin {
  android()

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "shared"
    }
  }

  sourceSets {
    val commonMain by getting
    val commonTest by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation(kotlin("test"))
      }
    }
    val androidMain by getting
    val androidTest by getting
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
    }
    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }

    all {
      languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
    }
  }
}

android {
  namespace = "com.fredporciuncula.nativecoroutineskotlin18"
  compileSdk = 33
  defaultConfig {
    minSdk = 26
    targetSdk = 33
  }
}
