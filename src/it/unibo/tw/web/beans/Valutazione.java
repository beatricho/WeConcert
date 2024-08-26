package it.unibo.tw.web.beans;

public enum Valutazione {
    PESSIMO(1),
    INSUFFICIENTE(2),
    DISCRETO(3),
    BUONO(4),
    ECCELLENTE(5);

    private final int numero;

    // Costruttore dell'enum
    Valutazione(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
