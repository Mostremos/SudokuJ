@echo off
REM Script para crear ejecutable .exe desde el JAR compilado
REM Requiere: Java JDK 14+ para jpackage, o Launch4j como alternativa

echo ========================================
echo    Creando Ejecutable .exe
echo ========================================
echo.

set JAR=target\sudokuj-2.1.0.jar
set EXE_NAME=SudokuJ
set DEST=dist

REM Verificar que el JAR existe
if not exist "%JAR%" (
    echo [ERROR] JAR no encontrado: %JAR%
    echo.
    echo Por favor, compila primero el proyecto:
    echo   compile_windows.bat
    echo   O: mvn clean package
    pause
    exit /b 1
)

echo [INFO] JAR encontrado: %JAR%
echo.

REM Metodo 1: Intentar con jpackage (Java 14+)
where jpackage >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo [METODO 1] Usando jpackage...
    echo.
    
    REM Verificar version de Java
    for /f "tokens=3" %%v in ('java -version 2^>^&1 ^| findstr /i "version"') do set JAVA_VER=%%v
    echo [INFO] Version de Java: %JAVA_VER%
    echo.
    
    REM Crear directorio de destino
    if not exist "%DEST%" mkdir "%DEST%"
    
    echo Creando ejecutable con jpackage...
    echo Esto puede tardar varios minutos...
    echo.
    
    jpackage --input target ^
      --name %EXE_NAME% ^
      --main-jar sudokuj-2.1.0.jar ^
      --main-class sudoku.Main ^
      --type exe ^
      --dest %DEST% ^
      --app-version 2.1.0 ^
      --vendor "SudokuJ Team" ^
      --description "SudokuJ - Juego de Sudoku" ^
      --win-dir-chooser ^
      --win-menu ^
      --win-shortcut ^
      --win-menu-group "Games"
    
    if exist "%DEST%\%EXE_NAME%.exe" (
        echo.
        echo [OK] Ejecutable creado exitosamente!
        echo.
        echo Archivo: %DEST%\%EXE_NAME%.exe
        echo.
        echo Puedes ejecutarlo directamente o distribuirlo.
        goto :end
    ) else (
        echo [WARNING] exe requiere WiX Toolset - wixtoolset.org
        echo Intentando app-image - no requiere WiX, incluye Java
        echo.
        
        REM app-image: carpeta con .exe y Java incluido, sin instalador
        jpackage --input target ^
          --name %EXE_NAME% ^
          --main-jar sudokuj-2.1.0.jar ^
          --main-class sudoku.Main ^
          --type app-image ^
          --dest %DEST% ^
          --app-version 2.1.0 ^
          --vendor "SudokuJ Team"
        
        if exist "%DEST%\%EXE_NAME%\%EXE_NAME%.exe" (
            echo.
            echo [OK] Ejecutable creado con app-image!
            echo.
            echo Carpeta: %DEST%\%EXE_NAME%\
            echo Ejecutar: %DEST%\%EXE_NAME%\%EXE_NAME%.exe
            echo.
            echo Java incluido - no requiere instalacion.
            goto :end
        )
        
        echo [WARNING] jpackage no pudo crear el ejecutable
        echo Continuando con metodo alternativo...
        echo.
    )
) else (
    echo [INFO] jpackage no encontrado (requiere Java 14+)
    echo.
)

REM Metodo 2: Usar Launch4j (si esta disponible)
echo [METODO 2] Usando Launch4j...
echo.

REM Buscar Launch4j (evitar ProgramFiles(x86) dentro de if - confunde el parser)
set LAUNCH4J=
set "PF=%ProgramFiles%"
set "PF86=%ProgramFiles(x86)%"
if exist "%PF%\Launch4j\launch4j.exe" (
    set "LAUNCH4J=%PF%\Launch4j\launch4j.exe"
) else if exist "%PF86%\Launch4j\launch4j.exe" (
    set "LAUNCH4J=%PF86%\Launch4j\launch4j.exe"
) else if exist "%LOCALAPPDATA%\Launch4j\launch4j.exe" (
    set "LAUNCH4J=%LOCALAPPDATA%\Launch4j\launch4j.exe"
)

if defined LAUNCH4J (
    echo [INFO] Launch4j encontrado: %LAUNCH4J%
    echo.
    echo Creando configuracion de Launch4j...
    
    REM Crear archivo de configuracion XML para Launch4j
    (
        echo ^<?xml version="1.0" encoding="UTF-8"?^>
        echo ^<launch4jConfig^>
        echo   ^<dontWrapJar^>false^</dontWrapJar^>
        echo   ^<headerType^>gui^</headerType^>
        echo   ^<jar^>%CD%\%JAR%^</jar^>
        echo   ^<outfile^>%CD%\%DEST%\%EXE_NAME%.exe^</outfile^>
        echo   ^<errTitle^>SudokuJ Error^</errTitle^>
        echo   ^<cmdLine^>^</cmdLine^>
        echo   ^<chdir^>.^</chdir^>
        echo   ^<priority^>normal^</priority^>
        echo   ^<downloadUrl^>https://adoptium.net/^</downloadUrl^>
        echo   ^<supportUrl^>^</supportUrl^>
        echo   ^<stayAlive^>false^</stayAlive^>
        echo   ^<restartOnCrash^>false^</restartOnCrash^>
        echo   ^<manifest^>^</manifest^>
        echo   ^<icon^>^</icon^>
        echo   ^<jre^>
        echo     ^<path^>^</path^>
        echo     ^<bundledJre64Bit^>false^</bundledJre64Bit^>
        echo     ^<bundledJreAsFallback^>false^</bundledJreAsFallback^>
        echo     ^<minVersion^>11.0^</minVersion^>
        echo     ^<maxVersion^>^</maxVersion^>
        echo     ^<jdkPreference^>preferJre^</jdkPreference^>
        echo     ^<runtimeBits^>64/32^</runtimeBits^>
        echo     ^<initialHeapSize^>128^</initialHeapSize^>
        echo     ^<maxHeapSize^>512^</maxHeapSize^>
        echo   ^</jre^>
        echo   ^<versionInfo^>
        echo     ^<fileVersion^>2.1.0.0^</fileVersion^>
        echo     ^<txtFileVersion^>2.1.0^</txtFileVersion^>
        echo     ^<fileDescription^>SudokuJ - Juego de Sudoku^</fileDescription^>
        echo     ^<copyright^>GPL v2^</copyright^>
        echo     ^<productVersion^>2.1.0.0^</productVersion^>
        echo     ^<txtProductVersion^>2.1.0^</txtProductVersion^>
        echo     ^<productName^>SudokuJ^</productName^>
        echo     ^<companyName^>SudokuJ Team^</companyName^>
        echo     ^<internalName^>SudokuJ^</internalName^>
        echo     ^<originalFilename^>SudokuJ.exe^</originalFilename^>
        echo   ^</versionInfo^>
        echo ^</launch4jConfig^>
    ) > launch4j_config.xml
    
    echo [INFO] Configuracion creada: launch4j_config.xml
    echo.
    echo Ejecutando Launch4j...
    "%LAUNCH4J%" launch4j_config.xml
    
    if exist "%DEST%\%EXE_NAME%.exe" (
        echo.
        echo [OK] Ejecutable creado exitosamente con Launch4j!
        echo.
        echo Archivo: %DEST%\%EXE_NAME%.exe
        echo.
        del launch4j_config.xml
        goto :end
    ) else (
        echo [ERROR] Launch4j no pudo crear el ejecutable
        echo Verifica la configuracion manualmente
    )
) else (
    echo [INFO] Launch4j no encontrado
    echo.
)

REM Metodo 3: Crear script batch como alternativa
echo [METODO 3] Creando script batch como alternativa...
echo.

if not exist "%DEST%" mkdir "%DEST%"

(
    echo @echo off
    echo REM Launcher para SudokuJ
    echo cd /d "%%~dp0.."
    echo start "" java -jar "target\sudokuj-2.1.0.jar"
) > "%DEST%\%EXE_NAME%.bat"

echo [OK] Script batch creado: %DEST%\%EXE_NAME%.bat
echo.
echo NOTA: Este es un script .bat, no un .exe real.
echo Para crear un .exe real, necesitas:
echo   1. Java JDK 14+ con jpackage, O
echo   2. Launch4j instalado
echo.
echo Instrucciones completas en: docs\compilacion\GUIA_COMPILACION_COMPLETA.md
echo.

:end
echo ========================================
pause
