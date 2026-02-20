@echo off
REM Script para preparar archivos de release
REM Copia el JAR compilado y ejecutable a la carpeta release/

setlocal enabledelayedexpansion

echo ========================================
echo    Preparando Release v2.0.0
echo ========================================
echo.

set JAR_SOURCE=target\sudokuj-2.0.0.jar
set JAR_DEST=release\sudokuj-2.0.0.jar
set EXE_SOURCE=dist\SudokuJ.exe
set EXE_DEST=release\SudokuJ.exe
set VERSION=2.0.0

REM Verificar que el JAR existe
if not exist "%JAR_SOURCE%" (
    echo [ERROR] JAR no encontrado: %JAR_SOURCE%
    echo.
    echo Por favor, compila primero el proyecto:
    echo   compile_windows.bat
    echo   O: mvn clean package
    echo.
    pause
    exit /b 1
)

echo [1/3] Copiando JAR...
copy "%JAR_SOURCE%" "%JAR_DEST%" >nul
if errorlevel 1 (
    echo [ERROR] No se pudo copiar el JAR
    pause
    exit /b 1
)
echo [OK] JAR copiado: %JAR_DEST%
echo.

REM Verificar si existe ejecutable
if exist "%EXE_SOURCE%" (
    echo [2/3] Copiando ejecutable...
    copy "%EXE_SOURCE%" "%EXE_DEST%" >nul
    if errorlevel 1 (
        echo [WARNING] No se pudo copiar el ejecutable
    ) else (
        echo [OK] Ejecutable copiado: %EXE_DEST%
    )
) else (
    echo [2/3] Ejecutable no encontrado (opcional)
    echo       Para crear ejecutable, ejecuta: crear_exe.bat
)
echo.

REM Verificar archivos de release
echo [3/3] Verificando archivos de release...
if not exist "release\LICENCE.txt" (
    echo [WARNING] LICENCE.txt no encontrado en release/
)
if not exist "release\README.md" (
    echo [WARNING] README.md no encontrado en release/
)

echo.
echo ========================================
echo    Release Preparado
echo ========================================
echo.
echo Archivos en release/:
dir /b release\*.jar release\*.exe 2>nul
echo.
echo Tamaños:
for %%A in ("%JAR_DEST%") do (
    if exist "%%A" (
        echo   JAR: %%~zA bytes
    )
)
for %%A in ("%EXE_DEST%") do (
    if exist "%%A" (
        echo   EXE: %%~zA bytes
    )
)
echo.
echo Para crear un ZIP de release:
echo   1. Navega a la carpeta release/
echo   2. Selecciona todos los archivos
echo   3. Crea un ZIP
echo   4. Nómbralo: SudokuJ-v%VERSION%-release.zip
echo.
pause
