package it.unibo.tw.web.beans;

public enum DisponibilitaMezzo {
    HO(1),
    NON_HO_E_CERCO(2),
    NON_HO_E_NON_CERCO(3);

    private final int numero;

    // Costruttore dell'enum
    DisponibilitaMezzo(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
