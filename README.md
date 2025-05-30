## Reto Backend Java - TCS
## Autor: Wellington Pozo
## Descripción

La aplicación permite:

- Registrar y administrar clientes.
- Crear cuentas bancarias asociadas a clientes.
- Registrar movimientos bancarios (depósitos y retiros) con validaciones de negocio.
- Consultar todos los movimientos.
- Generar reportes de estado de cuenta por cliente y rango de fechas.
- Validar los datos de entrada y manejar errores con respuestas claras.

## Funcionalidades Implementadas

#### _F1: Generación de CRUDs_
#### _F2: Registro de Movimientos_
#### _F3: Validación de Saldo_
#### _F4: Reportes_
#### _F5: Pruebas Unitarias_
#### _F6: Pruebas de Integración_

## Herramientas y Tecnologías Utilizadas

- Java Spring Boot / .NET Core 5 o superior / ASP.NET 4.8 o inferior
- IDE de su preferencia
- Base de Datos Relacional
- Postman v9.13.2 (validador de API) / Karate DSL

## Requisitos Previos

- Java 17+
- PostgreSQL y visualizador pgAdmin 4
- Gradle instalado o wrapper (`./gradlew`)
- Postman o cualquier cliente REST

## Instrucciones para Levantar el Proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Wellin01/Reto-Java.git
   cd Reto-Java/contact-api
   
2. Configuración de la Base de Datos (application.properties):
    ```bash
    spring.application.name = retotcs
    spring.datasource.url = jdbc:postgresql://localhost:5432/retotcs
    spring.datasource.username = **** (tu_usuario)
    spring.datasource.password = **** (tu_password)
3. Ejecutar la aplicación:

- Se puede hacer corriendo directamente el Application.java.
- También se puede ejecutar en el bash:

    ```bash
    ./gradlew bootRun

## Endpoints Desplegados

- **Principales**:
    - `/cuentas`
    - `/clientes`
    - `/movimientos`
- **Extra**:
    - `/reportes?fecha=rango_fechas`

## Validaciones y Manejo de Errores

- Validaciones con anotaciones como `@NotBlank`, `@Size`, `@Pattern`, `@Min`, etc.
- Todos los errores son interceptados por `GlobalExceptionHandler`.
- Las respuestas de error siguen un formato propio:
  ```json
  {
    "timestamp": "2025-05-29T12:00:00",
    "status": 400,
    "errors": [
      "El nombre es obligatorio",
      "La identificación debe tener 10 dígitos"
    ]
  }

## Comprobación del Proyecto

### Endpoint: `/clientes`

- **POST** `localhost:8080/clientes`

❌ JSON no válido:
 ```json
{
    "nombre": "Wellin123",
    "genero": "No se",
    "edad": 17,
    "identificacion": "175123",
    "direccion": "",
    "telefono": "0101010",
    "contrasena": "12345",
    "estado": null
}
 ```
🔁 Respuesta de error:
 ```json
{
    "errors": [
        "El nombre no debe contener números",
        "La dirección es obligatoria",
        "El género debe ser Masculino, Femenino o No binario",
        "La contraseña no puede estar vacía y debe tener al menos 8 caracteres, incluyendo una letra y un número",
        "La identificación debe tener 10 dígitos numéricos",
        "La edad mínima es 18",
        "El estado del cliente no puede ser nulo (null), solo puede ser true o false",
        "El teléfono debe tener 10 dígitos numéricos"
    ],
    "timestamp": "2025-05-29T23:01:23.4504669",
    "status": 400
}
 ```
✅ JSON válido:
 ```json
{
    "nombre": " Wellin",
    "genero": " Masculino ",
    "edad": 21,
    "identificacion": "1751239888",
    "direccion": "Carcelen",
    "telefono": "0989871384",
    "contrasena": "ab1234567",
    "estado": true
}
```
- **GET (con 3 clientes)** `localhost:8080/clientes`
 ```json
[
    {
        "id": 1,
        "nombre": "Wellin",
        "genero": "Masculino",
        "edad": 21,
        "identificacion": "1751239888",
        "direccion": "Carcelen",
        "telefono": "0989871384",
        "contrasena": "ab1234567",
        "estado": true
    },
    {
        "id": 2,
        "nombre": "Maria",
        "genero": "Femenino",
        "edad": 18,
        "identificacion": "1711239000",
        "direccion": "Ferroviaria",
        "telefono": "0986415681",
        "contrasena": "12345678x",
        "estado": false
    },
    {
        "id": 3,
        "nombre": "Edison",
        "genero": "No binario",
        "edad": 45,
        "identificacion": "0101010101",
        "direccion": "Calle 13",
        "telefono": "1230004567",
        "contrasena": "abcdefg7",
        "estado": true
    }
]
```
- **PUT** `localhost:8080/clientes/2`

📥 JSON actualizado:
```json 
{
    "nombre": "Maria",
    "genero": "Femenino",
    "edad": 18,
    "identificacion": "1711239000",
    "direccion": "Condado",
    "telefono": "0986415681",
    "contrasena": "00345678x",
    "estado": true
}
```
📤 GET obtenido para verificar actualización:
```json
[
    {
        "id": 1,
        "nombre": "Wellin",
        "genero": "Masculino",
        "edad": 21,
        "identificacion": "1751239888",
        "direccion": "Carcelen",
        "telefono": "0989871384",
        "contrasena": "ab1234567",
        "estado": true
    },
    {
        "id": 3,
        "nombre": "Edison",
        "genero": "No binario",
        "edad": 45,
        "identificacion": "0101010101",
        "direccion": "Calle 13",
        "telefono": "1230004567",
        "contrasena": "abcdefg7",
        "estado": true
    },
    {
        "id": 2,
        "nombre": "Maria",
        "genero": "Femenino",
        "edad": 18,
        "identificacion": "1711239000",
        "direccion": "Condado",
        "telefono": "0986415681",
        "contrasena": "00345678x",
        "estado": true
    }
]
```
### Endpoint: `/cuentas`

- **POST** `localhost:8080/cuentas`

❌ JSON no válido:
```json
{
  "numeroCuenta": "dsadas1235",
  "tipoCuenta": "No se",
  "saldoInicial": -100,
  "estado": null,
  "cliente": {
    "id": ""
  }
}
```
🔁 Respuesta de error:
```json
{
    "errors": [
        "El tipo de cuenta debe ser 'Ahorros' o 'Corriente'",
        "El número de cuenta debe tener exactamente 7 dígitos numéricos",
        "El estado de la cuenta no puede ser nulo (null), solo puede ser true o false",
        "El saldo inicial no puede ser negativo"
    ],
    "timestamp": "2025-05-29T23:17:11.779726",
    "status": 400
}
```
✅ JSON válido:
``` json
{
  "numeroCuenta": "1234567",
  "tipoCuenta": "Ahorros",
  "saldoInicial": 100,
  "estado": true,
  "cliente": {
    "id": "1"
  }
}
```
- **GET** `localhost:8080/cuentas`
```json
[
    {
        "numeroCuenta": "1234567",
        "tipoCuenta": "Ahorros",
        "saldoInicial": 100.0,
        "saldo": 340.0,
        "estado": true,
        "cliente": {
            "id": 1,
            "nombre": "Wellin",
            "genero": "Masculino",
            "edad": 21,
            "identificacion": "1751239888",
            "direccion": "Carcelen",
            "telefono": "0989871384",
            "contrasena": "ab1234567",
            "estado": true
        }
    }
]
```
### Endpoint: `/movimientos`

- **POST** `localhost:8080/movimientos`

❌ JSON no válido:
```json
{
  "tipoMovimiento": "fsdfseg",
  "valor": 50,
  "cuenta": {
    "numeroCuenta": 10
  }
}
```
🔁 Respuesta de error (cuenta no encontrada):
```json
{
    "errors": [
        "No se encontró la cuenta con número: 10"
    ],
    "timestamp": "2025-05-29T23:23:05.5871234",
    "status": 404
}
```
🔁 Respuesta de error (tipo de cuenta inválida):
```json
{
    "errors": [
        "Tipo de movimiento inválido. Usa Retiro o Depósito."
    ],
    "timestamp": "2025-05-30T00:06:54.898262",
    "status": 400
}
```
✅ JSON válido:
``` json
{
  "tipoMovimiento": "Retiro",
  "valor": 50,
  "cuenta": {
    "numeroCuenta": 1234567
  }
}
```
- **GET (3 movimientos)** `localhost:8080/movimientos`
```json
[
  {
    "id": 1,
    "fecha": "2025-05-29",
    "tipoMovimiento": "Retiro",
    "valor": -50.0,
    "saldo": 50.0,
    "cuenta": {
      "numeroCuenta": "1234567",
      "tipoCuenta": "Ahorros",
      "saldoInicial": 100.0,
      "saldo": 340.0,
      "estado": true,
      "cliente": {
        "id": 1,
        "nombre": "Wellin",
        "genero": "Masculino",
        "edad": 21,
        "identificacion": "1751239888",
        "direccion": "Carcelen",
        "telefono": "0989871384",
        "contrasena": "ab1234567",
        "estado": true
      }
    }
  },
  {
    "id": 2,
    "fecha": "2025-05-29",
    "tipoMovimiento": "Depósito",
    "valor": 300.0,
    "saldo": 350.0,
    "cuenta": {
      "numeroCuenta": "1234567",
      "tipoCuenta": "Ahorros",
      "saldoInicial": 100.0,
      "saldo": 340.0,
      "estado": true,
      "cliente": {
        "id": 1,
        "nombre": "Wellin",
        "genero": "Masculino",
        "edad": 21,
        "identificacion": "1751239888",
        "direccion": "Carcelen",
        "telefono": "0989871384",
        "contrasena": "ab1234567",
        "estado": true
      }
    }
  },
  {
    "id": 3,
    "fecha": "2025-05-29",
    "tipoMovimiento": "Retiro",
    "valor": -10.0,
    "saldo": 340.0,
    "cuenta": {
      "numeroCuenta": "1234567",
      "tipoCuenta": "Ahorros",
      "saldoInicial": 100.0,
      "saldo": 340.0,
      "estado": true,
      "cliente": {
        "id": 1,
        "nombre": "Wellin",
        "genero": "Masculino",
        "edad": 21,
        "identificacion": "1751239888",
        "direccion": "Carcelen",
        "telefono": "0989871384",
        "contrasena": "ab1234567",
        "estado": true
      }
    }
  }
]
```
### Endpoint: `/reportes`
🔸 Ruta de prueba:
- **GET** `http://localhost:8080/reportes?clienteId=1&desde=2025-05-15&hasta=2025-06-07`

![image](https://github.com/user-attachments/assets/86353ef1-8840-4f69-a302-525cc8af3290)

📤 Respuesta esperada:
```json
[
    {
        "fecha": "2025-05-29",
        "cliente": "Wellin",
        "numeroCuenta": "1234567",
        "tipo": "Ahorros",
        "saldoInicial": 100.0,
        "estado": true,
        "movimiento": -50.0,
        "saldoDisponible": 50.0
    },
    {
        "fecha": "2025-05-29",
        "cliente": "Wellin",
        "numeroCuenta": "1234567",
        "tipo": "Ahorros",
        "saldoInicial": 100.0,
        "estado": true,
        "movimiento": 300.0,
        "saldoDisponible": 350.0
    },
    {
        "fecha": "2025-05-29",
        "cliente": "Wellin",
        "numeroCuenta": "1234567",
        "tipo": "Ahorros",
        "saldoInicial": 100.0,
        "estado": true,
        "movimiento": -10.0,
        "saldoDisponible": 340.0
    }
]
```

*Los demás casos de prueba quedan a criterio del revisor.

## Pruebas Exitosas

- Prueba Unitaria: ClienteServiceTest.java
![image](https://github.com/user-attachments/assets/c1bd5549-5e67-4707-ac61-1c0c276dd577)

- Prueba de Integración: ClienteControllerTest.java
![image](https://github.com/user-attachments/assets/a0f320bd-ffb8-47c3-b05f-87d3923222c4)

##  📝 Licencia
Este proyecto fue desarrollado con fines prácticos para TCS. Derechos reservados por el autor.
