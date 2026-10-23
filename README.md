# kotlin_learn 校园巡检

实验三：Android 工程结构与巡检页面整理（学号 202530224073 袁少康）。

## 运行方法

1. 用 Android Studio 打开本目录，等待 Sync Project with Gradle Files 完成。
2. 连接真机或启动模拟器（需能访问 https://httpbin.org）。
3. 点击 Run，安装并启动应用"校园巡检"。
4. 页面显示巡检员袁少康、实验室 A203、设备总数 10、已检查 3、未检查 7。
5. 点击"请求 A203"或"请求 A204"，通过 OkHttp 发起 HTTPS 请求并显示状态码与回显内容。

也可命令行构建安装：

```powershell
.\gradlew.bat assembleDebug
adb install -r app\build\outputs\apk\debug\app-debug.apk
adb shell am start -n com.example.kotlin_learn/.MainActivity
```

## 三个源码文件职责

| 文件 | 职责 |
|------|------|
| MainActivity.kt | 系统入口，设置 Compose 内容，安排请求与结果更新 |
| InspectionScreen.kt | 显示巡检数据、按钮与回调，计算未检查数量 |
| InspectionNetwork.kt | 创建 OkHttpClient，执行请求，处理响应和异常 |

三个文件同属包 `com.example.kotlin_learn`。网络依赖为 `com.squareup.okhttp3:okhttp:4.12.0`，声明在 `app/build.gradle.kts` 的 dependencies 块中。
