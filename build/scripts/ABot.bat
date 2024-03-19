@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  ABot startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and A_BOT_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ABot.jar;%APP_HOME%\lib\mcprotocollib-1.20-1.jar;%APP_HOME%\lib\opennbt-1.4.jar;%APP_HOME%\lib\mcauthlib-6f3d6aada5.jar;%APP_HOME%\lib\adventure-api-4.9.3.jar;%APP_HOME%\lib\adventure-nbt-4.9.3.jar;%APP_HOME%\lib\adventure-key-4.9.3.jar;%APP_HOME%\lib\adventure-text-serializer-gson-legacy-impl-4.9.3.jar;%APP_HOME%\lib\adventure-text-serializer-gson-4.9.3.jar;%APP_HOME%\lib\api-2.0.jar;%APP_HOME%\lib\immutable-2.0.jar;%APP_HOME%\lib\fastutil-object-int-maps-8.5.2.jar;%APP_HOME%\lib\netty-all-4.1.66.Final.jar;%APP_HOME%\lib\netty-incubator-transport-native-io_uring-0.0.8.Final-linux-x86_64.jar;%APP_HOME%\lib\fastutil-int-object-maps-8.5.2.jar;%APP_HOME%\lib\gson-2.8.2.jar;%APP_HOME%\lib\fastutil-object-sets-8.5.2.jar;%APP_HOME%\lib\fastutil-int-sets-8.5.2.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.65.Final.jar;%APP_HOME%\lib\netty-transport-4.1.65.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.65.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.65.Final.jar;%APP_HOME%\lib\netty-common-4.1.65.Final.jar;%APP_HOME%\lib\examination-string-1.3.0.jar;%APP_HOME%\lib\examination-api-1.3.0.jar;%APP_HOME%\lib\fastutil-object-common-8.5.2.jar;%APP_HOME%\lib\fastutil-int-common-8.5.2.jar;%APP_HOME%\lib\fastutil-common-8.5.2.jar


@rem Execute ABot
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %A_BOT_OPTS%  -classpath "%CLASSPATH%"  %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable A_BOT_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%A_BOT_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
