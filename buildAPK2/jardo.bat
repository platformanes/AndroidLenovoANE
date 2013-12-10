@echo off
echo 解压APK
jar -xf lenovo.apk
echo 删除签名文件 
del META-INF\CERT.RSA 
del META-INF\CERT.SF 
del META-INF\MANIFEST.MF
rd /s /q META-INF
del lenovo.apk
echo 生成APK
jar -cf v_new.apk .
echo =========== over ==============
echo 再点一下 --Rect - - www.shadowkong.com
pause