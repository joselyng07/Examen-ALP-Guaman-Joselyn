# Sistema AULALOGIC — Examen ALP (Java)

Proyecto de consola para registrar, consultar, procesar y almacenar calificaciones de estudiantes.

## Datos de personalización
- Estudiante: ______________________________
- Variante asignada: ______
- Límite de aprobación: ______ / 20

**Configuración inicial:** `Main.java` usa límite de aprobación 13 como ejemplo (variante C). Modifica `LIMITE_APROBACION` según la variante asignada:
A=11, B=12, C=13, D=14, E=15, F=16, G=17, H=18.

## Requisitos implementados
- Registro de estudiantes con código único.
- Validación de código y nombre: no vacíos ni con punto y coma.
- Notas enteras de 0 a 20, re-pregunta ante valores inválidos.
- Tres notas almacenadas en arreglo `int[]`.
- Colección de estudiantes con `ArrayList`.
- Consulta por código, listado y cálculos por estudiante.
- Reporte: total, aprobados, reprobados, promedio general, porcentaje aprobado, promedio mayor/menor, estudiantes bajo promedio general y listado ordenado descendente.
- Persistencia usando `FileWriter`/`BufferedWriter` y `FileReader`/`BufferedReader`.
- Carga automática desde `datos/estudiantes.txt`; guardado al salir.

## Estructura
```text
Examen-ALP-AULALOGIC-Completo/
├── README.md
├── src/
│   ├── Main.java
│   ├── Estudiante.java
│   ├── GestionAcademica.java
│   └── GestorArchivos.java
├── datos/
│   └── estudiantes.txt
├── docs/
│   ├── Analisis.md
│   └── CasosDePrueba.md
└── evidencias/
    └── README.md
```

## Compilar y ejecutar
Desde la carpeta raíz del proyecto:

```bash
javac -encoding UTF-8 src/*.java
java -cp src Main
```

Si `javac` no está reconocido, instala/configura un JDK y revisa la variable PATH.

## Formato de archivo
Una línea por estudiante:
`codigo;nombre;nota1;nota2;nota3`

Ejemplo:
`E001;Ana Torres;15;18;12`

## Commits sugeridos
Haz los commits de manera progresiva y real (no falsifiques historial):
1. `Análisis y estructura inicial`
2. `Implementación de clases y registro`
3. `Procesamiento y persistencia`
4. `Pruebas y documentación final`
