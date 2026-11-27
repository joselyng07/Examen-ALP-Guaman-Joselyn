# Proyecto-AulaLogic

Sistema de seguimiento del rendimiento academico de una asignatura, desarrollado en **Java** como **aplicacion de consola**.

## Descripcion del problema
Se necesita registrar, consultar, procesar y almacenar las calificaciones de los estudiantes de una asignatura. El sistema conserva
los registros despues de cerrar el programa y genera indicadores del rendimiento del curso (aprobados, reprobados, promedio general,
mejor y peor promedio, etc.).

## Variante de aprobacion configurada
**Variante C - promedio minimo de aprobacion: 13.**
Un estudiante es `APROBADO` si su promedio es **mayor o igual a 13**; en caso contrario es `REPROBADO`.
La constante se encuentra en `Estudiante.LIMITE_APROBACION`. Los datos de prueba incluyen a **Carlos Mena (E004)** con notas 12, 13, 14,
cuyo promedio es exactamente **13.00** (APROBADO).

## Requisitos
- JDK 11 o superior (desarrollado y probado con Java 21).

## Compilacion y ejecucion
Desde la carpeta raiz del proyecto (`Proyecto-AulaLogic/`), porque el archivo de datos se busca en la ruta relativa `datos/estudiantes.txt`:

```bash
# Compilar (genera las clases en la carpeta out/)
javac -encoding UTF-8 -d out src/*.java

# Ejecutar
java -cp out Main
```

En Windows (PowerShell o CMD) los mismos comandos funcionan igual. Si los acentos se ven mal en consola, ejecute antes `chcp 65001`.

## Estructura del repositorio
```text
Proyecto-AulaLogic/
|-- README.md
|-- src/
|   |-- Main.java             # menu y flujo principal
|   |-- Estudiante.java       # datos y calculos de cada estudiante
|   |-- GestionAcademica.java # coleccion, validaciones e indicadores
|   `-- GestorArchivos.java   # lectura y escritura de archivos
|-- datos/
|   `-- estudiantes.txt       # persistencia (codigo;nombre;n1;n2;n3)
|-- docs/
|   |-- Analisis.md           # analisis entrada-proceso-salida y diseno
|   `-- CasosDePrueba.md      # CP01-CP08 con resultados reales
`-- evidencias/
    `-- Ejecucion.png         # captura de una ejecucion
```

## Funcionalidades implementadas
- **Registro de estudiantes** (arreglo `int[3]` de notas + `ArrayList<Estudiante>`), con validacion de codigo unico, codigo/nombre no vacios
  y sin `;`, y calificaciones enteras entre 0 y 20 (se vuelven a pedir si son incorrectas).
- **Procesamiento:** promedio real (sin division entera), nota maxima, nota minima y estado academico segun el limite 13.
- **Consulta por codigo** con aviso si el estudiante no existe.
- **Reporte academico:** total, aprobados, reprobados, promedio general, porcentaje de aprobados, mejor y peor promedio,
  estudiantes bajo el promedio general y listado ordenado de mayor a menor promedio (ordenamiento por seleccion).
- **Persistencia** en `datos/estudiantes.txt` con `FileWriter`/`BufferedWriter` y `FileReader`/`BufferedReader`; recuperacion automatica al iniciar,
  guardado manual (opcion 5) y guardado automatico al salir (opcion 6). El archivo se reescribe completo, sin duplicar registros, y los promedios
  y estados se recalculan desde las notas.
- **Menu** repetitivo hasta elegir la opcion 6, con control de opciones incorrectas.
- Manejo de excepciones (`try-catch`) y tratamiento de archivo inexistente o lineas mal formadas.

Formato del archivo:
```text
E001;Ana Torres;15;18;12
E002;Luis Pérez;8;10;12
E003;Marta Ruiz;20;18;19
E004;Carlos Mena;12;13;14
```

## Pruebas
Los casos CP01 a CP08 estan documentados en [`docs/CasosDePrueba.md`](docs/CasosDePrueba.md) con entrada, resultado esperado,
resultado obtenido y estado. La captura de una ejecucion esta en `evidencias/Ejecucion.png`.

## Limitaciones
- Aplicacion de consola de un solo usuario: no tiene interfaz grafica, base de datos ni control de concurrencia.
- Solo gestiona **una asignatura** con exactamente **tres calificaciones enteras** por estudiante.
- No permite editar ni eliminar estudiantes (solo registrar, consultar y listar).
- Los nombres no pueden contener `;` por el formato del archivo. Las lineas invalidas del archivo se omiten (con aviso) y se perderian en el
  siguiente guardado.
- Al registrar, un codigo repetido, vacio o con `;` cancela la operacion y se vuelve al menu (no se vuelve a pedir el codigo).
- La comparacion de codigos no distingue mayusculas de minusculas (`e001` = `E001`).
- Los resultados dependen de ejecutar el programa desde la carpeta raiz del proyecto (ruta relativa).

## Control de versiones
1. Analisis y estructura inicial.
2. Implementacion de clases y registro.
3. Procesamiento y persistencia.
4. Pruebas y documentacion final.
