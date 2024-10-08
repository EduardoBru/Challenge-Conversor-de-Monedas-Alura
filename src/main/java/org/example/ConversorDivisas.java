package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.JSONObject;

public class ConversorDivisas {

    private static final String API_KEY = "87ac6f3e0506126d727cca35";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 7) {
            mostrarMenu();
            opcion = leerOpcion(scanner);

            if (opcion >= 1 && opcion <= 6) {
                double cantidad = leerCantidad(scanner);
                manejarOpcion(opcion, cantidad);
                esperarYLimpiar();
            } else if (opcion == 7) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Por favor, elige una opción válida.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nConversor de Divisas:");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Salir");
        System.out.print("Elige una opción: ");
    }

    private static int leerOpcion(Scanner scanner) {
        int opcion = -1;
        while (true) {
            try {
                opcion = scanner.nextInt();
                break; // Salimos del bucle si la opción es válida
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // Limpiar el input incorrecto
            }
        }
        return opcion;
    }

    private static double leerCantidad(Scanner scanner) {
        double cantidad = -1;
        while (true) {
            System.out.print("Ingresa la cantidad que deseas convertir: ");
            try {
                cantidad = scanner.nextDouble();
                break; // Salimos del bucle si la cantidad es válida
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // Limpiar el input incorrecto
            }
        }
        return cantidad;
    }

    private static void manejarOpcion(int opcion, double cantidad) {
        String fromCurrency = "", toCurrency = "";

        switch (opcion) {
            case 1:
                fromCurrency = "USD";
                toCurrency = "ARS";
                break;
            case 2:
                fromCurrency = "ARS";
                toCurrency = "USD";
                break;
            case 3:
                fromCurrency = "USD";
                toCurrency = "BRL";
                break;
            case 4:
                fromCurrency = "BRL";
                toCurrency = "USD";
                break;
            case 5:
                fromCurrency = "USD";
                toCurrency = "COP";
                break;
            case 6:
                fromCurrency = "COP";
                toCurrency = "USD";
                break;
        }

        if (!fromCurrency.isEmpty() && !toCurrency.isEmpty()) {
            double tasaDeCambio = obtenerTasaDeCambio(fromCurrency, toCurrency);
            if (tasaDeCambio > 0) {
                double resultado = cantidad * tasaDeCambio;
                System.out.println("\n*********************************************");
                System.out.printf("%.2f %s equivalen a %.2f %s%n", cantidad, fromCurrency, resultado, toCurrency);
                System.out.println("*********************************************\n");
            } else {
                System.out.println("Error al obtener la tasa de cambio.");
            }
        }
    }

    private static double obtenerTasaDeCambio(String fromCurrency, String toCurrency) {
        try {
            String url = API_URL + fromCurrency + "/" + toCurrency;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            return jsonObject.getDouble("conversion_rate");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }

    // Método para esperar 1 segundo y luego limpiar la terminal
    private static void esperarYLimpiar() {
        try {
            Thread.sleep(5000); // Pausa por 1 segundo
            limpiarPantalla();  // Limpiar la terminal
        } catch (InterruptedException e) {
            System.out.println("Error al pausar el programa.");
        }
    }

    // Método para limpiar la pantalla dependiendo del sistema operativo
    private static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla.");
        }
    }
}

