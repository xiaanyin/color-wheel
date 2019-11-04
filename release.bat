@echo off
call gradle jlink
if exist "release" (
    rmdir "release" /s /q
)

mkdir "release"
mkdir "release\application"
mkdir "release\application\image"

xcopy /y /s application release\application
xcopy /y /s build\image release\application\image

echo @echo off > release\startup.bat
echo set DIR=%%~dp0 >> release\startup.bat
echo call application\image\bin\color-wheel.bat ROOT_PATH=%%DIR%% >> release\startup.bat

REM echo start application\image\bin\color-wheel.bat ROOT_PATH=%%DIR%% >> release\startup.bat
REM echo exit >> release\startup.bat
