@echo off
REM Script para crear directorios de release listos para ZIP y repo
REM Genera: "SudokuJ 2.0.0 Java" (JAR) y "SudokuJ 2.0.0 Windows" (EXE)
REM
REM ANTES de ejecutar: compila con compile_windows.bat y crear_exe.bat

setlocal enabledelayedexpansion

set VERSION=2.0.0
set DIR_JAR=SudokuJ 2.0.0 Java
set DIR_EXE=SudokuJ 2.0.0 Windows
set JAR_SOURCE=target\sudokuj-2.0.0.jar
set EXE_SOURCE=dist\SudokuJ.exe

echo ========================================
echo    Build Release SudokuJ %VERSION%
echo ========================================
echo.

REM === PASO 1: Verificar JAR ===
if not exist "%JAR_SOURCE%" (
    echo [ERROR] JAR no encontrado: %JAR_SOURCE%
    echo.
    echo Ejecuta primero: compile_windows.bat
    pause
    exit /b 1
)

REM === PASO 2: Crear directorio "SudokuJ 2.0.0 Java" para ZIP ===
echo [1/2] Creando directorio "%DIR_JAR%"...
if exist "%DIR_JAR%" rmdir /s /q "%DIR_JAR%"
mkdir "%DIR_JAR%"

copy "%JAR_SOURCE%" "%DIR_JAR%\sudokuj-2.0.0.jar" >nul
copy "release\LICENCE.txt" "%DIR_JAR%\LICENCE.txt" >nul
copy "release\README.md" "%DIR_JAR%\README.md" >nul

echo [OK] Directorio creado: %DIR_JAR%\
echo      - sudokuj-2.0.0.jar
echo      - LICENCE.txt
echo      - README.md
echo.

REM === PASO 3: Crear directorio EXE ===
echo [2/2] Creando directorio "%DIR_EXE%"...
if exist "%DIR_EXE%" rmdir /s /q "%DIR_EXE%"
mkdir "%DIR_EXE%"

set EXE_FOUND=0

REM Caso 1: Launch4j - exe suelto en dist\
if exist "%EXE_SOURCE%" (
    copy "%EXE_SOURCE%" "%DIR_EXE%\SudokuJ.exe" >nul
    set EXE_FOUND=1
)

REM Caso 2: jpackage app-image - carpeta dist\SudokuJ\
if %EXE_FOUND%==0 if exist "dist\SudokuJ\SudokuJ.exe" (
    xcopy /E /I /Y "dist\SudokuJ\*" "%DIR_EXE%\" >nul
    set EXE_FOUND=1
)

if %EXE_FOUND%==1 (
    copy "release\LICENCE.txt" "%DIR_EXE%\LICENCE.txt" >nul
    copy "release\README-Windows.md" "%DIR_EXE%\README.md" >nul 2>&1
    if not exist "%DIR_EXE%\README.md" copy "release\README.md" "%DIR_EXE%\README.md" >nul
    echo [OK] Directorio creado: %DIR_EXE%\
    echo      - SudokuJ.exe (y archivos)
    echo      - LICENCE.txt
    echo      - README.md
) else (
    copy "release\LICENCE.txt" "%DIR_EXE%\LICENCE.txt" >nul
    copy "release\README.md" "%DIR_EXE%\README.md" >nul
    echo [WARNING] SudokuJ.exe no encontrado en dist\
    echo           Ejecuta crear_exe.bat para generarlo.
    echo           Por ahora se han copiado LICENCE y README.
)

echo.
echo ========================================
echo    Release listo
echo ========================================
echo.
echo Para crear los ZIP:
echo   1. Comprime "%DIR_JAR%" como SudokuJ-2.0.0-Java.zip
echo   2. Comprime "%DIR_EXE%" como SudokuJ-2.0.0-Windows.zip
echo.
echo Sube ambos ZIP al repo (releases de GitHub)
echo.
pause
