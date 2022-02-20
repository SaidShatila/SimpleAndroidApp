# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class com.example.simpleandroidapp.** { *; }
-keep class com.example.simpleandroidapp.data.remote.response.Todos{ *; }
-keep class com.App
-keep class com.example.simpleandroidapp.utils.**{*;}
-keepattributes *Annotations, Exception*
-optimizations !method/removal/parameter
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#Retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-dontwarn retrofit2.-KotlinExtensions
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

-dontwarn kotlin.Unit
-dontwarn javax.annotation.**

# datastore
-keep class androidx.datastore.* {*;}

# Keep class names of Hilt injected ViewModels since their name are used as a multibinding map key.
-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel

-keep,allowobfuscation,allowshrinking @dagger.hilt.android.EarlyEntryPoint class *

-keep class androidx.lifecycle.** { *; }

# Navigation
-keep,allowobfuscation,allowshrinking class * extends androidx.navigation.Navigator

# Android data binding
-dontwarn androidx.databinding.**
-keep class androidx.databinding.** { *; }

# Android data binding
-dontwarn com.xxxx.xx.databinding.**
-keep class com.xxxx.xx.databinding.** { *; }
-keepclassmembers class com.xxxx.xx.databinding.** { *; }

-keep class com.xxxx.xx.BindingHelpers.** { *; }
-keepclassmembers class com.xxxx.xx.BindingHelpers.** { *; }


#AndroidX View Model
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keepclassmembers class * extends androidx.lifecycle.ViewModel { *; }

#AndroidX AttributeSet
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#AndroidX Annotation
-keepattributes Annotation

#AndroidX Lifecycle
-keepclassmembers enum androidx.lifecycle.Lifecycle$Event {
    <fields>;
}
-keep !interface * implements androidx.lifecycle.LifecycleObserver {
}

-keep class * implements androidx.lifecycle.GeneratedAdapter {
    <init>(...);
}
-keepclassmembers class ** {
    @androidx.lifecycle.OnLifecycleEvent *;
}


# ServiceLoader support(coroutines)
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}


-dontwarn okhttp3.**
-dontwarn javax.annotation.**

