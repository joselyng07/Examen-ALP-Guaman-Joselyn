# Casos de prueba - AulaLogic (Variante C: aprobacion >= 13)

Las pruebas se ejecutaron sobre el programa compilado con Java 21, partiendo de los datos iniciales de `datos/estudiantes.txt` (E001 a E004, ver mas abajo), salvo que el caso indique otra cosa. Las salidas mostradas son transcripciones reales de la ejecucion (se omite el recuadro del menu repetido para abreviar).

**Datos iniciales:**
```text
E001;Ana Torres;15;18;12
E002;Luis Pérez;8;10;12
E003;Marta Ruiz;20;18;19
E004;Carlos Mena;12;13;14
```

| Caso | Situacion | Resultado | Estado |
|---|---|---|---|
| CP01 | Registro correcto de un estudiante | Coincide con lo esperado | CUMPLE |
| CP02 | Intento de registrar un codigo duplicado | Coincide con lo esperado | CUMPLE |
| CP03 | Calificacion fuera del rango permitido | Coincide con lo esperado | CUMPLE |
| CP04 | Promedio igual al limite de aprobacion configurado (Variante C = 13) | Coincide con lo esperado | CUMPLE |
| CP05 | Busqueda de un estudiante existente | Coincide con lo esperado | CUMPLE |
| CP06 | Busqueda de un estudiante inexistente | Coincide con lo esperado | CUMPLE |
| CP07 | Reporte sin estudiantes registrados | Coincide con lo esperado | CUMPLE |
| CP08 | Guardado y recuperacion de datos al reiniciar | Coincide con lo esperado | CUMPLE |

## CP01 - Registro correcto de un estudiante

**Entrada:** Opcion 1 -> codigo `E005`, nombre `Sofia Vega`, notas `14`, `16`, `15`; luego opcion 2

**Resultado esperado:** Mensaje de registro correcto, promedio 15.00 y estado APROBADO; E005 aparece en el listado

**Resultado obtenido:**

```text
Registros recuperados: 4

Seleccione una opcion: 1
Codigo del estudiante: E005
Nombre completo: Sofia Vega
Calificacion 1 (0-20): 14
Calificacion 2 (0-20): 16
Calificacion 3 (0-20): 15
Estudiante registrado correctamente.
Promedio: 15.00 - Estado: APROBADO

Seleccione una opcion: 2

COD    NOMBRE                  N1  N2  N3     PROM  MAX MIN  ESTADO
E001   Ana Torres              15  18  12    15.00   18  12  APROBADO
E002   Luis Pérez               8  10  12    10.00   12   8  REPROBADO
E003   Marta Ruiz              20  18  19    19.00   20  18  APROBADO
E004   Carlos Mena             12  13  14    13.00   14  12  APROBADO
E005   Sofia Vega              14  16  15    15.00   16  14  APROBADO

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## CP02 - Intento de registrar un codigo duplicado

**Entrada:** Opcion 1 -> codigo `E001` (ya existe)

**Resultado esperado:** Mensaje de error indicando que el codigo ya esta registrado; no se crea el registro

**Resultado obtenido:**

```text
Registros recuperados: 4

Seleccione una opcion: 1
Codigo del estudiante: E001
Error: el codigo 'E001' ya esta registrado.

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## CP03 - Calificacion fuera del rango permitido

**Entrada:** Opcion 1 -> codigo `E006`, nombre `Pedro Lima`, nota 1: `25`, luego `-3`, luego `abc`, luego `10`; notas 2 y 3: `11`, `12`

**Resultado esperado:** Se vuelve a solicitar la nota 1 hasta recibir un valor valido (0-20); finalmente se registra con promedio 11.00

**Resultado obtenido:**

```text
Registros recuperados: 4

Seleccione una opcion: 1
Codigo del estudiante: E006
Nombre completo: Pedro Lima
Calificacion 1 (0-20): 25
Error: la calificacion debe estar entre 0 y 20.
Calificacion 1 (0-20): -3
Error: la calificacion debe estar entre 0 y 20.
Calificacion 1 (0-20): abc
Error: ingrese un numero entero entre 0 y 20.
Calificacion 1 (0-20): 10
Calificacion 2 (0-20): 11
Calificacion 3 (0-20): 12
Estudiante registrado correctamente.
Promedio: 11.00 - Estado: REPROBADO

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## CP04 - Promedio igual al limite de aprobacion configurado (Variante C = 13)

**Entrada:** Opcion 3 -> buscar `E004` (notas 12, 13, 14); opcion 1 -> `E007`, `Rosa Cruz`, notas `13`, `13`, `13`; opcion 3 -> buscar `E007`

**Resultado esperado:** Ambos estudiantes con promedio exactamente 13.00 y estado APROBADO (el limite es inclusivo)

**Resultado obtenido:**

```text
Registros recuperados: 4

Seleccione una opcion: 3
Codigo a buscar: E004

Codigo       : E004
Nombre       : Carlos Mena
Calificaciones: 12, 13, 14
Promedio     : 13.00
Nota maxima  : 14
Nota minima  : 12
Estado       : APROBADO

Seleccione una opcion: 1
Codigo del estudiante: E007
Nombre completo: Rosa Cruz
Calificacion 1 (0-20): 13
Calificacion 2 (0-20): 13
Calificacion 3 (0-20): 13
Estudiante registrado correctamente.
Promedio: 13.00 - Estado: APROBADO

Seleccione una opcion: 3
Codigo a buscar: E007

Codigo       : E007
Nombre       : Rosa Cruz
Calificaciones: 13, 13, 13
Promedio     : 13.00
Nota maxima  : 13
Nota minima  : 13
Estado       : APROBADO

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## CP05 - Busqueda de un estudiante existente

**Entrada:** Opcion 3 -> codigo `E001`

**Resultado esperado:** Se muestran codigo, nombre, calificaciones, promedio 15.00, nota maxima 18, nota minima 12 y estado APROBADO

**Resultado obtenido:**

```text
Registros recuperados: 4

Seleccione una opcion: 3
Codigo a buscar: E001

Codigo       : E001
Nombre       : Ana Torres
Calificaciones: 15, 18, 12
Promedio     : 15.00
Nota maxima  : 18
Nota minima  : 12
Estado       : APROBADO

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## CP06 - Busqueda de un estudiante inexistente

**Entrada:** Opcion 3 -> codigo `E999`

**Resultado esperado:** Mensaje informativo indicando que no existe el estudiante; el programa continua

**Resultado obtenido:**

```text
Registros recuperados: 4

Seleccione una opcion: 3
Codigo a buscar: E999
No existe un estudiante con el codigo 'E999'.

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## CP07 - Reporte sin estudiantes registrados

**Entrada:** Archivo `datos/estudiantes.txt` vacio; opcion 4

**Resultado esperado:** Mensaje informativo de que no hay estudiantes; sin errores ni division entre cero

**Resultado obtenido:**

```text
Registros recuperados: 0

Seleccione una opcion: 4

No hay estudiantes registrados. No es posible generar el reporte.

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## CP08 - Guardado y recuperacion de datos al reiniciar

**Entrada:** Sesion 1: opcion 1 -> `E010`, `Julia Andrade`, notas `17`, `19`, `18`; opcion 6 (guardado automatico al salir). Sesion 2 (reinicio del programa): opcion 2.

**Resultado esperado:** Al salir se escribe el archivo con el nuevo registro (sin duplicar los anteriores). Al reiniciar se recuperan 5 registros y se recalculan promedios y estados.

**Resultado obtenido:**

Sesion 1:
```text
Registros recuperados: 4

Seleccione una opcion: 1
Codigo del estudiante: E010
Nombre completo: Julia Andrade
Calificacion 1 (0-20): 17
Calificacion 2 (0-20): 19
Calificacion 3 (0-20): 18
Estudiante registrado correctamente.
Promedio: 18.00 - Estado: APROBADO

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

Contenido de `datos/estudiantes.txt` tras la sesion 1:
```text
E001;Ana Torres;15;18;12
E002;Luis Pérez;8;10;12
E003;Marta Ruiz;20;18;19
E004;Carlos Mena;12;13;14
E010;Julia Andrade;17;19;18
```

Sesion 2 (reinicio):
```text
Registros recuperados: 5

Seleccione una opcion: 2

COD    NOMBRE                  N1  N2  N3     PROM  MAX MIN  ESTADO
E001   Ana Torres              15  18  12    15.00   18  12  APROBADO
E002   Luis Pérez               8  10  12    10.00   12   8  REPROBADO
E003   Marta Ruiz              20  18  19    19.00   20  18  APROBADO
E004   Carlos Mena             12  13  14    13.00   14  12  APROBADO
E010   Julia Andrade           17  19  18    18.00   19  17  APROBADO

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

**Estado:** CUMPLE

## Pruebas adicionales

### Opcion de menu incorrecta (la aplicacion no termina)
```text
Registros recuperados: 4

Seleccione una opcion: 9
Opcion incorrecta. Elija un numero del 1 al 6.

Seleccione una opcion: abc
Opcion incorrecta. Elija un numero del 1 al 6.

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

### Codigo invalido (con `;` o vacio)
```text
Registros recuperados: 4

Seleccione una opcion: 1
Codigo del estudiante: E;1
Error: el codigo no puede estar vacio ni contener ';'.

Seleccione una opcion: 1
Codigo del estudiante: 
Error: el codigo no puede estar vacio ni contener ';'.

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```

### Archivo inexistente al iniciar
```text
Aviso: no existe 'datos/estudiantes.txt'. Se inicia sin registros.
Registros recuperados: 0

Seleccione una opcion: 2

No hay estudiantes registrados.

Seleccione una opcion: 6
Guardando registros antes de salir...
Registros guardados. Hasta pronto.
```
