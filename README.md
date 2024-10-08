# Conversor de Divisas en Java

Este proyecto es un **Conversor de Divisas** que permite realizar conversiones entre varias monedas utilizando la API de [ExchangeRate-API](https://www.exchangerate-api.com/). El programa se ejecuta en la consola de comandos y permite elegir entre varias opciones de conversión de divisas como Dólar a Peso argentino, Peso colombiano a Dólar, entre otras.

## Características

- Conversión entre varias monedas (USD, ARS, BRL, COP).
- Uso de la API de ExchangeRate-API para obtener los tipos de cambio actualizados.
- Interfaz de usuario en consola con un menú de opciones.
- Validación de entradas para garantizar que solo se ingresen números.
- Muestra el resultado de la conversión en formato destacado.
- Pausa de 1 segundo antes de limpiar la pantalla y regresar al menú principal.

## Monedas disponibles para conversión

1. Dólar (USD) => Peso argentino (ARS)
2. Peso argentino (ARS) => Dólar (USD)
3. Dólar (USD) => Real brasileño (BRL)
4. Real brasileño (BRL) => Dólar (USD)
5. Dólar (USD) => Peso colombiano (COP)
6. Peso colombiano (COP) => Dólar (USD)

## Requisitos

- Java JDK 11 o superior.
- Clave API de ExchangeRate-API para obtener los tipos de cambio en tiempo real.
- Conexión a Internet para poder consultar la API de tipos de cambio.

## Configuración del Proyecto

### Paso 1: Clonar el repositorio

Clona el repositorio a tu máquina local:

```bash
git clone https://github.com/tu_usuario/conversor-divisas-java.git

### Paso 2: Configurar la API Key

Debes obtener una clave API gratuita desde ExchangeRate-API para obtener los tipos de cambio.

Una vez obtenida la clave API, reemplaza la constante API_KEY en el archivo ConversorDivisas.java con tu clave personal:

private static final String API_KEY = "TU_CLAVE_API";

Paso 3: Compilar y ejecutar el programa

1. Compila el programa con el siguiente comando:
javac ConversorDivisas.java

2.Ejecuta el programa:
java ConversorDivisas

###Cómo usar el conversor

Selecciona una opción de conversión: Después de ejecutar el programa, se te mostrará un menú con las opciones de conversión disponibles. Ingresa el número de la opción que deseas elegir.

Ingresa la cantidad: Luego, se te pedirá que ingreses la cantidad de dinero que deseas convertir.

Ver el resultado: El programa te mostrará el resultado de la conversión en un formato destacado y luego esperará 1 segundo antes de limpiar la consola y volver al menú.

Salir: Si deseas salir del programa, selecciona la opción "7) Salir" en el menú.
