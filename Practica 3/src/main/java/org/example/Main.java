package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Tarjeta> tarjet = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void AgregarTarjeta() {
        System.out.println("\n¿Qué tipo de tarjeta deseas agregar?");
        System.out.println("1-Credito");
        System.out.println("2-Debito");
        System.out.println("3-Salir");
        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                System.out.print("Ingresa el titular de la tarjeta: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el nip (4 dígitos): ");
                String nip = scanner.nextLine();
                while (!nip.matches("\\d{4}")) {
                    System.out.print("Ingrese un nip válido (solo 4 números): ");
                    nip = scanner.nextLine();
                }

                System.out.print("Ingrese el banco de la tarjeta: ");
                String banco = scanner.nextLine();
                Credito Tcredit = new Credito(nombre, nip, banco, "18/09/2030");
                tarjet.add(Tcredit);
                System.out.println("Tarjeta de crédito añadida exitosamente.");
                break;

            case 2:
                System.out.print("Ingresa el titular de la tarjeta: ");
                String nombreD = scanner.nextLine();

                System.out.print("Ingrese el nip (4 dígitos): ");
                String nipD = scanner.nextLine();
                while (!nipD.matches("\\d{4}")) {
                    System.out.print("Ingrese un nip válido (solo 4 números): ");
                    nipD = scanner.nextLine();
                }

                System.out.print("Ingrese el banco de la tarjeta: ");
                String bancoD = scanner.nextLine();

                Debito Tdebit = new Debito(nombreD, nipD, bancoD);
                tarjet.add(Tdebit);
                System.out.println("Tarjeta de débito añadida exitosamente.");
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    static void Tarjetero() {
        if (tarjet.isEmpty()) {
            System.out.println("No hay Tarjetas disponibles :(");
            return;
        } else {
            boolean salir = false;
            while (!salir) {
                for (int i = 0; i < tarjet.size(); i++) {
                    if (i == 0) { System.out.println("\nTarjetas:"); }
                    System.out.println("----------------------");
                    if (tarjet.get(i) instanceof Debito) {
                        System.out.println("Tarjeta de Debito");
                    } else if (tarjet.get(i) instanceof Credito) {
                        System.out.println("Tarjeta de Credito");
                    }
                    System.out.println("- Titular: " + tarjet.get(i).getTitular() + " | Banco: " + tarjet.get(i).getBanco() + " | Tarjeta (Últimos 4): " + tarjet.get(i).getUltimos4());
                }
                System.out.println("--------------------------");
                System.out.println("1-Ingresar a una tarjeta");
                System.out.println("0-Salir al menú principal");
                int opcion = leerEntero();

                switch (opcion) {
                    case 1:
                        System.out.println("------------------------------------------");
                        System.out.print("Ingresa los ultimos 4 digitos de la tarjeta: ");
                        String n1 = scanner.nextLine();
                        int C = BuscarTarjeta(n1);
                        if (C > -1) {
                            Tarjeta tarjetaActual = tarjet.get(C);
                            System.out.println("ingresa tu nip");
                            String nipc = scanner.nextLine();
                            if(nipc.equals(tarjetaActual.getNip())){
                                boolean salir2 = false;
                                while (!salir2) {
                                    System.out.println("\n------------------------------------------");
                                    System.out.println("             ¿QUÉ DESEAS HACER?            ");
                                    System.out.println("------------------------------------------");
                                    System.out.println(" 1 - Ver estado              2 - Ver información");
                                    System.out.println(" 3 - Consultar estado        4 - Transferir");
                                    System.out.println(" 5 - Retirar                 6 - Depositar");
                                    System.out.println(" 7 - Pagar (Crédito)         8 - Comprar");
                                    System.out.println(" 9 - Bloquear/Desbloquear    0 - Salir");
                                    System.out.println("------------------------------------------");
                                    System.out.print("Selecciona una opción: ");
                                    int opcion3 = leerEntero();

                                    switch (opcion3) {
                                        case 1:
                                            tarjetaActual.verEstado();
                                            break;
                                        case 2:
                                            tarjetaActual.verInfoTarjeta();
                                            break;
                                        case 3:
                                            tarjetaActual.consultar();
                                            break;
                                        case 4:
                                            System.out.print("Ingresa el monto a transferir: ");
                                            double monto = leerDouble();
                                            System.out.print("Ingresa la CLABE de la tarjeta destino: ");
                                            String clabe = scanner.nextLine();
                                            boolean Dvalido = false;
                                            Tarjeta destino = null;
                                            for (int i = 0; i < tarjet.size(); i++) {
                                                if (clabe.equals(tarjet.get(i).getClabe())) {
                                                    destino = tarjet.get(i);
                                                    Dvalido = true;
                                                    break;
                                                }
                                            }
                                            if (Dvalido) {
                                                tarjetaActual.transferir(monto, destino);
                                            } else {
                                                System.out.println("Destino no válido o no encontrado.");
                                            }
                                            break;
                                        case 5:
                                            System.out.print("Ingresa el monto a retirar: ");
                                            double monto2 = leerDouble();
                                            tarjetaActual.retirar(monto2);
                                            break;
                                        case 6:
                                            System.out.print("Ingresa el monto a depositar: ");
                                            double monto3 = leerDouble();
                                            tarjetaActual.depositar(monto3);
                                            break;
                                        case 7:
                                            System.out.print("Ingresa el monto a pagar: ");
                                            double monto4 = leerDouble();
                                            System.out.print("Ingresa los últimos 4 dígitos de tu tarjeta de DÉBITO origen: ");
                                            String digitosOrigen = scanner.nextLine();
                                            int indiceOrigen = BuscarTarjeta(digitosOrigen);

                                            if (indiceOrigen > -1) {
                                                tarjetaActual.pagar(monto4, tarjet.get(indiceOrigen));
                                            } else {
                                                System.out.println("Tarjeta no encontrada.");
                                            }
                                            break;
                                        case 8:
                                            System.out.print("Ingresa el monto para comprar: ");
                                            double monto5 = leerDouble();
                                            tarjetaActual.comprar(monto5);
                                            break;
                                        case 9:
                                            tarjetaActual.bloquearDesbloquear();
                                            break;
                                        case 0:
                                            System.out.println("Saliendo del menú de la tarjeta...");
                                            salir2 = true;
                                            break;
                                        default:
                                            System.out.println("No existe esa opción.");
                                            break;
                                    }
                                }
                            }else{
                                System.out.println("Nip incorrecto.");
                                continue;
                            }

                        } else {
                            System.out.println("No se encontró la tarjeta.");
                            System.out.println("--------------------------");
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo al menú principal....");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        }
    }

    static int BuscarTarjeta(String tarj) {
        try {
            for (int i = 0; i < tarjet.size(); i++) {
                String digitosT = tarjet.get(i).getNumero().substring(tarjet.get(i).getNumero().length() - 4);
                if (tarj.equals(digitosT)) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            System.out.println("Error al buscar: " + e.getMessage());
            return -1;
        }
    }


    public static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingresa un número entero: ");
            }
        }
    }

    public static double leerDouble() {
        while (true) {
            try {
                double valor = Double.parseDouble(scanner.nextLine());
                if (valor < 0) {
                    System.out.print("El monto no puede ser negativo. Intenta de nuevo: ");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingresa un monto válido (ej. 1500.50): ");
            }
        }
    }

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1) Crear Tarjeta");
            System.out.println("2) Tarjetero");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    AgregarTarjeta();
                    break;
                case 2:
                    Tarjetero();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}