@echo off
echo ��ѹAPK
jar -xf lenovo.apk
echo ɾ��ǩ���ļ� 
del META-INF\CERT.RSA 
del META-INF\CERT.SF 
del META-INF\MANIFEST.MF
rd /s /q META-INF
del lenovo.apk
echo ����APK
jar -cf v_new.apk .
echo =========== over ==============
echo �ٵ�һ�� --Rect - - www.shadowkong.com
pause