package org.example;

import java.util.Random;
public class Credito extends Tarjeta {
    private String fechaPago;
    private double anualidad;
    private double limiteCredito;

    public Credito(String titular, String nip, String banco, String fechaPago) {
        super(titular, nip, banco);
        this.fechaPago = fechaPago;
        this.anualidad = new Random().nextInt(1500) + 500;
        this.limiteCredito = 15000;
    }

    @Override
    public void retirar(double monto) {
        System.out.println("Error: no puedes retirar dinero de una tarjeta de crédito");
    }

    @Override
    public void transferir(double monto, Tarjeta destino) {
        System.out.println("Error: No puedes hacer transferencias desde una tarjeta de crédito.");
    }

    @Override
    public void depositar(double monto) {
        System.out.println("Error: Para pagar tu deuda usa la opción 'pagar'.");
    }

    @Override
    public void pagar(double monto, Tarjeta origen) {
        if (!this.getActivo()) { System.out.println("Operación denegada: La tarjeta de crédito está inactiva."); return; }

        if (origen instanceof Debito) {
            if (origen.getSaldo() >= monto) {
                origen.setSaldo(origen.getSaldo() - monto);
                this.setSaldo(this.getSaldo() - monto);
                System.out.println("Pago a tu tarjeta de crédito exitoso.");
                this.numTransacciones++;
                origen.numTransacciones++;
            } else {
                System.out.println("Tu tarjeta de débito no tiene dinero suficiente.");
            }
        } else {
            System.out.println("Solo puedes pagar el crédito usando una tarjeta de débito.");
        }
    }

    @Override
    public void comprar(double monto) {
        if (!this.getActivo()) { System.out.println("Operación denegada: La tarjeta está inactiva."); return; }

        if ((this.getSaldo() + monto) <= limiteCredito) {
            this.setSaldo(this.getSaldo() + monto);
            System.out.println("Compra a crédito exitosa.");
            this.numTransacciones++;
        } else {
            System.out.println("Compra rechazada: Superas el límite de crédito.");
        }
    }
}