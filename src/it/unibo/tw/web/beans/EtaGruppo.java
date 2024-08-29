package it.unibo.tw.web.beans;

public class EtaGruppo {
    private int sogliaInferiore;
    private int sogliaSuperiore;

    public EtaGruppo(){
        this.sogliaInferiore=0;
        this.sogliaSuperiore=0;
    }

    public int getSogliaSuperiore(){
        return sogliaSuperiore;
    }

    public void setSogliaSuperiore(int sogliaSuperiore){
        this.sogliaSuperiore = sogliaSuperiore;
    }

    public int getSogliaInferiore(){
        return sogliaInferiore;
    }

    public void setSogliaInferiore(int sogliaInferiore){
        this.sogliaInferiore = sogliaInferiore;
    }
}
