package org.example;

public class Debito extends Tarjeta {

    private final double limiteTransferencia = 10000.0;
    private final double limiteDeposito = 20000.0;

    public Debito(String titular, String nip, String banco) {
        super(titular, nip, banco);
        this.setSaldo(10000.0);
    }

    @Override
    public void retirar(double monto) {
        if (!this.getActivo()) { System.out.println("Operación denegada: La tarjeta está inactiva."); return; }

        if (this.getSaldo() >= monto) {
            this.setSaldo(this.getSaldo() - monto);
            System.out.println("Retiro de efectivo exitoso.");
            this.numTransacciones++;
        } else {
            System.out.println("Saldo insuficiente para retirar.");
        }
    }

    @Override
    public void transferir(double monto, Tarjeta destino) {
        if (!this.getActivo()) { System.out.println("Operación denegada: La tarjeta está inactiva."); return; }

        if (monto > limiteTransferencia) {
            System.out.println("Error: Supera el límite de transferencia de $" + limiteTransferencia);
            return;
        }
        if (destino instanceof Credito) {
            System.out.println("Error: No puedes transferir a una de crédito, usa la opción de 'pagar'.");
        } else if (destino == this) {
            System.out.println("Error: No te puedes transferir a ti mismo.");
        } else if (this.getSaldo() >= monto) {
            this.setSaldo(this.getSaldo() - monto);
            destino.setSaldo(destino.getSaldo() + monto);
            System.out.println("Transferencia exitosa.");
            this.numTransacciones++;
            destino.numTransacciones++;
        } else {
            System.out.println("Saldo insuficiente para transferir.");
        }
    }

    @Override
    public void depositar(double monto) {
        if (!this.getActivo()) { System.out.println("Operación denegada: La tarjeta está inactiva."); return; }

        if (monto > limiteDeposito) {
            System.out.println("Supera el límite de depósito de $" + limiteDeposito);
            return;
        }
        this.setSaldo(this.getSaldo() + monto);
        System.out.println("Depósito exitoso.");
        this.numTransacciones++;
    }

    @Override
    public void pagar(double monto, Tarjeta origen) {
        System.out.println("Error: Esta opción es solo para pagar tarjetas de crédito.");
    }

    @Override
    public void comprar(double monto) {
        if (!this.getActivo()) { System.out.println("Operación denegada: La tarjeta está inactiva."); return; }

        if (this.getSaldo() >= monto) {
            this.setSaldo(this.getSaldo() - monto);
            System.out.println("Compra con tarjeta de débito exitosa.");
            this.numTransacciones++;
        } else {
            System.out.println("Compra rechazada: Saldo insuficiente.");
        }
    }
}