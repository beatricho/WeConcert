package it.unibo.tw.web.beans;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String id;
    private Utente utentePubblicante;
    private List<Utente> utentiAderenti;
    private Evento evento;
    private int partecipantiMax;
    private String descrizione;
    private EtaGruppo etaGruppo;
    private DisponibilitaMezzo disponibilitaMezzo;
    private GenereGruppo genereGruppo;
    private String citta;

    public Post(){
        this.id="";
        this.utentePubblicante=null;
        this.utentiAderenti=new ArrayList<Utente>();
        this.evento=null;
        this.partecipantiMax=0;
        this.descrizione="";
        this.etaGruppo=null;
        this.disponibilitaMezzo=null;
        this.genereGruppo=null;
        this.citta=null;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Utente getUtentePubblicante() {
        return utentePubblicante;
    }
    public void setUtentePubblicante(Utente utentePubblicante) {
        this.utentePubblicante = utentePubblicante;
    }
    public List<Utente> getUtentiAderenti() {
        return utentiAderenti;
    }
    public boolean setUtentiAderenti(Utente utenteAderente) {   //l'utente aderisce al post, viene aggiunto alla lista di utenti aderenti al post
        return this.utentiAderenti.add(utenteAderente);
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public int getPartecipantiMax() {
        return partecipantiMax;
    }
    public void setPartecipantiMax(int partecipantiMax) {
        this.partecipantiMax = partecipantiMax;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public EtaGruppo getEtaGruppo() {
        return etaGruppo;
    }
    public void setEtaGruppo(EtaGruppo etaGruppo) {
        this.etaGruppo = etaGruppo;
    }
    public DisponibilitaMezzo getDisponibilitaMezzo() {
        return disponibilitaMezzo;
    }
    public void setDisponibilitaMezzo(DisponibilitaMezzo disponibilitaMezzo) {
        this.disponibilitaMezzo = disponibilitaMezzo;
    }
    public GenereGruppo getGenereGruppo() {
        return genereGruppo;
    }
    public void setGenereGruppo(GenereGruppo genereGruppo) {
        this.genereGruppo = genereGruppo;
    }
    public String getCitta() {
        return citta;
    }
    public void setCitta(String c) {
        this.citta = c;
    }
    
}
