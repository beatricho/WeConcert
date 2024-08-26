package it.unibo.tw.web.beans;

public class Recensione {
    private String id;
    private Valutazione valutazione;
    private String commento;

    public Recensione(){
        this.id="";
        this.valutazione=null;
        this.commento="";
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Valutazione getValutazione(){
        return valutazione;
    }

    public void setValutazione(Valutazione valutazione){
        this.valutazione = valutazione;
    }

    public String getCommento(){
        return commento;
    }

    public void setCommento(String commento){
        this.commento = commento;
    }
}
