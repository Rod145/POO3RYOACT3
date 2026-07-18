package org.example;

public interface Transacciones {
    void retirar(double monto);
    void transferir(double monto, Tarjeta destino);
    void depositar(double monto);
    void pagar(double monto, Tarjeta origen);
    void consultar();
    void bloquearDesbloquear();
    void extender();
    void reportarRobo();
    void comprar(double monto);
}