package it.unibo.tw.web.beans;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento {
    private String id;
    private String descrizione;
    private LocalDate data;
    private LocalTime orario;
    private String luogo;
    private String URLLocandina;

    public Evento() {
        this.id = "";
        this.data = null;
        this.orario = null;
        this.luogo = "";
        this.descrizione="";
        this.URLLocandina="";
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getOrario() {
        return orario;
    }
    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }
    public String getLuogo() {
        return luogo;
    }
    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }
    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getURLLocandina() {
        return URLLocandina;
    }
    public void setURLLocandina(String url) {
        this.URLLocandina = url;
    }
}