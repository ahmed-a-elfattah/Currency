// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application")  version BuildPlugins.application apply false
    id ("com.android.library") version BuildPlugins.library apply false
    id ("org.jetbrains.kotlin.android") version BuildPlugins.kotlin apply false
}
buildscript {
    dependencies{
        classpath("com.google.dagger:hilt-android-gradle-plugin:${BuildPlugins.hilt}")
    }
}
tasks.register(name= "type",type= Delete::class){
    delete (rootProject.buildDir)
}