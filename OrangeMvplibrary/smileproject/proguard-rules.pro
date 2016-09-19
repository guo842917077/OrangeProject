# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\AndroidSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#尝试代码混淆
#不预校验
-dontpreverify
#不优化
#-dontoptimize
#打印详细
-verbose
#不压缩输入类型的文件
-dontshrink

#保持类的成员
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
# 表示proguard对你的代码进行迭代优化的次数
-optimizationpasses 5
#混淆时不会产各种特殊的名字
-dontusemixedcaseclassnames
#保护指定的内容 这里指定保护行号和签名
#用到反射及注解需要加上下面三个规则
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod
#内部类
-keepattributes InnerClasses
#优化时允许访问并修改有修饰符的类和类的成员混淆
#-allowaccessmodification
#指定不去忽略非公共的库类
#-dontskipnonpubliclibraryclasses
#保护指定的类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
#保护所有原生的方法
-keepclasseswithmembernames class * {
    native <methods>;
}
#保持 任意包名.R类的类成员属性。  即保护R文件中的属性名不变
-keepclassmembers class **.R$* {
    public static <fields>;
}
#不混淆Parcelable的子类，防止android.os.BadParcelableException
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#不混淆Serializable的子类
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#android-support-v4
-dontwarn android.support.v4.**
-dontwarn **CompatHoneycomb
-dontwarn **CompatHoneycombMR2
-dontwarn **CompatCreatorHoneycombMR2
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
#android-support-v7
-dontwarn android.support.v7.**
-keep public class * extends android.support.v7.**
-keep class android.support.v7.** { *; }
#保护特定的类
 #不混淆R类
-keep public class com.jph.android.R$*{
    public static final int *;
}
# Keep Butterknife stuff
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}
# square Retrofit okhttp stuff
-dontwarn com.squareup.okhttp.internal.huc.**
-dontwarn okio.**
-dontwarn com.google.appengine.api.urlfetch.**
-dontwarn rx.**
-keep class com.squareup.** { *; }
-keep class com.viselabs.aquariummanager.util.seneye.** { *; }
-keep class retrofit.http.* { *; }
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.** *;
}
-keepclassmembers class * {
    @retrofit.** *;
}
# OkHttp oddities
-dontwarn com.squareup.okhttp.*
-dontwarn com.squareup.okhttp.internal.http.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keepnames class com.levelup.http.okhttp.** { *; }
-keepnames interface com.levelup.http.okhttp.** { *; }
-keepnames class com.squareup.okhttp.** { *; }
-keepnames interface com.squareup.okhttp.** { *; }
# Keep GSON stuff
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
# Application classes that will be serialized/deserialized over Gson
#保持实体数据结构接口不被混淆(也就是被GSON注解的实体结构) 此处xxx.xxx是自己接口的包名
-keep class com.orange.smileapp.home.model.** { *; }
#nineoldandroids
-dontwarn com.nineoldandroids.**
-keep class com.nineoldandroids.** { *; }
#easy animation
-keep class com.daimajia.easing.** { *; }
-keep interface com.daimajia.easing.** { *; }


#过滤掉所有的自定义view
-keep public class * extends android.view.View {*;}
-keepclasseswithmembernames class * { # 保持 native 方法不被混淆
native <methods>;
}
-keepclasseswithmembers class * { # 保持自定义控件类不被混淆
public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
public <init>(android.content.Context, android.util.AttributeSet, int);
}
-dontwarn com.google.vending.licensing.ILicensingService
-dontwarn
