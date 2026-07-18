package org.example;

import java.util.Random;

public abstract class Tarjeta implements Transacciones {
    private String titular;
    private String numero;
    private String clabe;
    private String nip;
    private String banco;
    private String expiracion;
    private String cvv;
    private double saldo;
    private boolean activo;
    public int numTransacciones;

    public Tarjeta(String titular, String nip, String banco) {
        this.titular = titular;
        this.numero = generarAleatorio(16);
        this.clabe = generarAleatorio(18);
        this.nip = nip;
        this.banco = banco;
        this.expiracion = "12/30";
        this.cvv = generarAleatorio(3);
        this.saldo = 0.0;
        this.activo = true;
        this.numTransacciones = 0;
    }
    private String generarAleatorio(int longitud) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }

    public void setTitular(String titular) { this.titular = titular; }
    public String getTitular() { return this.titular; }

    public void setNumero(String numero) { this.numero = numero; }
    public String getNumero() { return this.numero; }

    public void setClabe(String clabe) { this.clabe = clabe; }
    public String getClabe() { return this.clabe; }

    public void setNip(String nip) { this.nip = nip; }
    public String getNip() { return this.nip; }

    public void setBanco(String banco) { this.banco = banco; }
    public String getBanco() { return this.banco; }

    public void setExpiracion(String expiracion) { this.expiracion = expiracion; }
    public String getExpiracion() { return this.expiracion; }

    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getCvv() { return this.cvv; }

    public void setSaldo(double saldo) { this.saldo = saldo; }
    public double getSaldo() { return this.saldo; }

    public void setActivo(boolean activo) { this.activo = activo; }
    public boolean getActivo() { return this.activo; }

    public String getUltimos4() {
        if (numero != null && numero.length() >= 4) {
            return numero.substring(numero.length() - 4);
        }
        return "0000";
    }

    public void verEstado() {
        System.out.println("Estado de la tarjeta: " + (activo ? "Activa" : "Inactiva"));
        System.out.println("Transacciones realizadas en el año: " + numTransacciones);
    }

    public void verInfoTarjeta() {
        System.out.println("Titular: " + titular + " | Banco: " + banco);
        System.out.println("Número: " + numero + " | CLABE: " + clabe);
        System.out.println("Estado: " + (activo ? "Activo" : "Inactivo"));
        System.out.println("Vence: " + expiracion + " | CVV: " + cvv);
    }

    @Override
    public void consultar() {
        if (!getActivo()) { System.out.println("Operación denegada: La tarjeta está inactiva."); return; }
        System.out.println("Saldo actual: $" + saldo);
        numTransacciones++;
    }

    @Override
    public void bloquearDesbloquear() {
        this.activo = !this.activo;
        System.out.println("La tarjeta ahora está: " + (activo ? "Desbloqueada" : "Bloqueada"));
        numTransacciones++;
    }

    @Override
    public void extender() {
        if (!getActivo()) { System.out.println("Operación denegada: La tarjeta está inactiva."); return; }
        System.out.println("Se ha extendido el límite/vigencia de la tarjeta.");
        numTransacciones++;
    }

    @Override
    public void reportarRobo() {
        this.setActivo(false);
        System.out.println("Tarjeta reportada por robo. Bloqueada permanentemente para evitar cualquier daño.");
        numTransacciones++;
    }
}