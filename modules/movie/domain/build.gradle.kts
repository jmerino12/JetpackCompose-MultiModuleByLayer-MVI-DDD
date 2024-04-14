plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.corrutines)
    implementation ("com.google.dagger:dagger:2.49")
    ksp("com.google.dagger:dagger-compiler:2.48")

}