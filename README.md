# 介绍

在 donut 平台中,提供 pda(HIKROBOT) 的功能强化:

1. pda 扫码触发
2. pda 语言播报

只提供的核心文件, 你可以新建个多端插件, 然后把文件拷贝到插件目录下即可

# pda 扫码触发

1. 必要条件: pda 需要设置扫描服务(广播模式)
2. 由于 pda 的广播参数可能不同, 所以你需要自己检查下 pda 广播参数是否正确
   1. com.service.scanner.data
   2. ScanCode

![20240825-110606](https://github.com/user-attachments/assets/46a2cb75-f469-4426-bb2a-87e208887908)

由于 sendMiniPluginEvent 在 beta 版本，真机下有 bug，无法触发. 所以使用了异步回调触发

# pda 语言播报

1. 必要条件: pda 需要 tts 功能才能播报
   1. 我提供了个 讯飞 3.0 tts.apk(网上找的 🥲, 慎重使用)
   2. 谷歌的 tts 工具, 你需要自己装中文语言包(不推荐)
   3. 你也可以用其他 sdk / 在线服务 来实现这个功能(在线不方便,离线收费)

# 踩坑

1. 安卓代码改变之后，一直未生效
   ![image](https://github.com/YBFACC/blog/assets/47051132/4c6d394a-403d-4d31-8426-c61de12a434e)
   尝试重新构建
   ![image](https://github.com/YBFACC/blog/assets/47051132/ef115d46-a60b-44fb-8c0a-890db3cce0f2)
   重新编译

2. 示例代码无法启动
   尝试开梯子
   检查本地环境 java 版本等，是否符合要求(java:11,gradle:6.7.1)

3. 上传的插件去哪里找
   https://dev.weixin.qq.com/console/crossPlatform

4. 如果本机中安装多个 java 环境,可能会导致编译失败. 可以在微信开发工具中配置环境变量
   ![image](https://github.com/user-attachments/assets/41c1d0cc-0a6d-40de-9b7a-4e7dd23037fa)

```
FAILURE: Build failed with an exception. \* What went wrong:
Execution failed for task ':plugin:compileDebugJavaWithJavac'.

> java.lang.IllegalAccessError: class org.gradle.internal.compiler.java.ClassNameCollector (in unnamed module @0x13532e55) cannot access class com.sun.tools.javac.code.Symbol$TypeSymbol (in module jdk.compiler) because module jdk.compiler does not export com.sun.tools.javac.code to unnamed module @0x13532e55 \* Try: Run with --stacktrace option to get the stack trace.
```

5. pda 是 android 11 ,在 AndroidManifest 需要写点配置

