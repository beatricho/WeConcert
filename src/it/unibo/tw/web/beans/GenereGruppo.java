package it.unibo.tw.web.beans;

public enum GenereGruppo {
    MASCHILE(1),
    FEMMINILE(2),
    MISTO(3);

    private final int numero;

    // Costruttore dell'enum
    GenereGruppo(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
