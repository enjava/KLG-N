# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidStudioSDK/tools/proguard/proguard-android.txt
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
#proguard.cfg 配置如下：
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-keepattributes *Annotation*
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#忽略警告 也可以用-ignorewarnings
-ignorewarning
#保持哪些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.support.v7.app.AppCompatActivity
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.** #如果有引用v4或者v7包，需添加
-keepattributes Signature        #不混淆泛型
-keepnames class * implements java.io.Serializable #不混淆Serializable
#不混淆第三方jar包中的类
-keep class android.support.** {*;}
-keep class com.zhy.** {*;}
-keep class android.net.http.** {*;}
-keep class com.readystatesoftware.** {*;}
-keep class com.lidroid.xutils.** {*;}
-keep class com.google.gson.** {*;}
-keep class com.google.zxing.** {*;}
-keepclasseswithmembernames class * { # 保持 native 方法不被混淆
native <methods>;
}
-keepclasseswithmembers class * { # 保持自定义控件类不被混淆
public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {  # 保持自定义控件类不被混淆
public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
public void *(android.view.View);
}
-keepclassmembers enum * { # 保持枚举 enum 类不被混淆
public static **[] values();
public static ** valueOf(Java.lang.String);
}
-keep class * implements android.os.Parcelable {  # 保持 Parcelable 不被混淆
public static final android.os.Parcelable$Creator *;
}

#生成日志数据，gradle build时在本项目根目录输出
-dump class_files.txt            #apk包内所有class的内部结构
-printseeds seeds.txt            #未混淆的类和成员
-printusage unused.txt           #打印未被使用的代码
-printmapping mapping.txt        #混淆前后的映射
