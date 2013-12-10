AndroidLenovoANE
================

联想登录ID 与 支付集合ANE
## 官方文档信息
* [传送门](http://developer.passport.lenovo.com/sdkdownload/index.html#)
* 官方登录`IDSDK` 与 `支付SDK` 分开，而此ANE把它们整合

## 编写ANE过程

* 参照我博客的教程[传送门](http://www.shadowkong.com/archives/1090)
* 支付URL并没有在前端配 若要配请自行修改[LenovoPay](android/src/com/lenovo/func/LenovoPay.java)第63行

## 打包APK过程
* 配置`BuildAPK1`下的`lenovo_apk.bat`的`ADT` 工具路径（该工具在AIRSDK中）
* 执行`BuildAPK1`下的`lenovo_apk.bat`打包得`lenovo.apk`
* 配置`BuildAPK2`下的`jardo.bat`的`jar`工具路径（该工具在JDK的JRE中）
* 把`lenovo.apk`放到`BuildAPK2`执行`jardo.bat`命令得到`v_new.apk`
* 配置`BuildAPK3`下的`c-writekey.bat`的`jarsigner`工具路径（该工具在JDK中）
* 把`v_new.apk`放到`BuildAPK3`执行`c-writekey.bat`得 `lenovo_sign.apk`
* 配置`d-alg.bat`的 `zipalign`工具路径（该工具在androidSDK中）
* 若要替换签名文件 则替换`BuildAPK3`下的`a.keystore`
* 执行`d-alg.bat`得`lenovoDemo.apk`
* `lenovoDemo.apk`便是最终的APK

## 作者

* [platformANEs](https://github.com/platformanes)由 [zrong](http://zengrong.net) 和 [rect](http://www.shadowkong.com/) 共同发起并完成。
