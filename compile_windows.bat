@echo off
REM Script de compilación mejorado para Windows
REM Compila SudokuJ sin necesidad de Maven

setlocal enabledelayedexpansion

echo ========================================
echo    Compilando SudokuJ v2.0.0
echo ========================================
echo.

REM Configuración
set SRC=src
set OUT=target\classes
set JAR=target\sudokuj-2.0.0.jar
set MAIN_CLASS=sudoku.Main
set ERRORS=compile_errors.txt

REM Verificar Java
where javac >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java JDK no encontrado en el PATH
    echo.
    echo Por favor:
    echo 1. Instala Java JDK 11 o superior
    echo 2. Agrega javac al PATH
    echo 3. Reinicia esta ventana
    echo.
    echo Descarga desde: https://adoptium.net/
    pause
    exit /b 1
)

REM Mostrar versión de Java
echo [INFO] Versión de Java:
javac -version 2>&1
java -version 2>&1 | findstr "version"
echo.

REM Crear directorios
echo [1/4] Creando directorios...
if not exist "%OUT%" mkdir "%OUT%"
if not exist "target" mkdir "target"
if exist "%ERRORS%" del "%ERRORS%"
echo [OK] Directorios creados
echo.

REM Compilar
echo [2/4] Compilando archivos Java...
echo Esto puede tardar unos momentos...
echo.

set COUNT=0
set ERR_COUNT=0

for /r "%SRC%" %%f in (*.java) do (
    set /a COUNT+=1
    echo [!COUNT!] Compilando: %%f
    javac -d "%OUT%" -sourcepath "%SRC%" -encoding UTF-8 -cp "%OUT%" "%%f" >>"%ERRORS%" 2>&1
    if errorlevel 1 (
        set /a ERR_COUNT+=1
        echo        [ERROR] Fallo al compilar
    )
)

echo.
echo [INFO] Archivos compilados: %COUNT%
if %ERR_COUNT% GTR 0 (
    echo [WARNING] Errores encontrados: %ERR_COUNT%
    echo Revisa %ERRORS% para más detalles
) else (
    echo [OK] Compilación exitosa
)
echo.

REM Verificar si hay errores críticos
if %ERR_COUNT% GTR 0 (
    echo [INFO] Revisando errores...
    findstr /C:"error:" "%ERRORS%" >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo [ERROR] Errores críticos encontrados. Abortando.
        type "%ERRORS%" | more
        pause
        exit /b 1
    )
)

REM Copiar recursos
echo [3/4] Copiando recursos...
if exist "resources" (
    xcopy /E /I /Y "resources\*" "%OUT%\" >nul 2>&1
    if errorlevel 1 (
        echo [WARNING] Algunos recursos no se pudieron copiar
    ) else (
        echo [OK] Recursos copiados
    )
) else (
    echo [WARNING] Directorio resources no encontrado
)
echo.

REM Crear JAR
echo [4/4] Creando JAR...
jar cfe "%JAR%" %MAIN_CLASS% -C "%OUT%" . >nul 2>&1

if exist "%JAR%" (
    echo [OK] JAR creado exitosamente
    echo.
    echo ========================================
    echo    Compilación Completada
    echo ========================================
    echo.
    echo Archivo: %JAR%
    echo Tamaño: 
    for %%A in ("%JAR%") do echo        %%~zA bytes
    echo.
    echo Para ejecutar:
    echo   java -jar %JAR%
    echo.
    echo O doble clic en el archivo JAR
    echo.
) else (
    echo [ERROR] No se pudo crear el JAR
    echo Verifica los errores en %ERRORS%
    pause
    exit /b 1
)

REM Limpiar archivo de errores si está vacío
if exist "%ERRORS%" (
    for %%A in ("%ERRORS%") do (
        if %%~zA EQU 0 del "%ERRORS%"
    )
)

echo Presiona cualquier tecla para salir...
pause >nul
