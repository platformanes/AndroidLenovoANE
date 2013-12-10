AndroidLenovoANE
================

联想登录ID 与 支付集合ANE
## 官方文档信息
* [传送门](http://developer.passport.lenovo.com/sdkdownload/index.html#)
* 官方登录`IDSDK` 与 `支付SDK` 分开，而此ANE把它们整合

## 编写ANE过程

* 参照我博客的教程[传送门](http://www.shadowkong.com/archives/1090)

## 打包APK过程
* 执行`BuildAPK1`文件夹下的`lenovo_apk.bat`打包得`lenovo.apk`
* 把`lenovo.apk`放到`BuildAPK2`执行`jardo.bat`命令得到`v_new.apk`
* 把`v_new.apk`放到`BuildAPK3`执行`c-writekey.bat`，`d-alg.bat`得`lenovoDemo.apk`
* `lenovoDemo.apk`便是最终的APK

## 作者

* [platformANEs](https://github.com/platformanes)由 [zrong](http://zengrong.net) 和 [rect](http://www.shadowkong.com/) 共同发起并完成。
