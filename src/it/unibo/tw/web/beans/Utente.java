package it.unibo.tw.web.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utente {
    private String username;
    private String nome;
    private String cognome;
    private String password;
    private LocalDate nascita;
    private Genere genere;
    public List<Post> getPostPubblicati() {
		return postPubblicati;
	}

	public void setPostPubblicati(List<Post> postPubblicati) {
		this.postPubblicati = postPubblicati;
	}

	public List<Post> getAdesioni() {
		return adesioni;
	}

	public void setAdesioni(List<Post> adesioni) {
		this.adesioni = adesioni;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	private List<Post> postPubblicati;
    private List<Post> adesioni;         //rappresenta i post a cui l'utente ha aderito
    private List<Recensione> recensioni; //rappresenta le recensioni che l'utente riceve, non quelle che fa (questo perchè nel profilo si devono vedere le recensioni che l'utente ha ricevuto dagli altri utenti con cui ha partecipato a degli eventi)

    public Utente(){
        this.username="";
        this.nome="";
        this.cognome="";
        this.password="";
        this.nascita=null;
        this.genere=null;
        this.postPubblicati = new ArrayList<Post>();
        this.adesioni = new ArrayList<Post>();
        this.recensioni = new ArrayList<Recensione>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCognome(){
        return cognome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public Genere getGenere(){
        return genere;
    }

    public void setGenere(Genere genere){
        this.genere = genere;
    }

    public LocalDate getNascita(){
        return nascita;
    }

    public void setNascita(LocalDate nascita){
        this.nascita = nascita;
    }

    public boolean pubblicaPost(Post post){
        //ritorno risultato dell'aggiunta, false in caso di errore
        return this.postPubblicati.add(post);
    }

    public boolean aderisci(Post post){
        return this.adesioni.add(post);
    }

    public boolean recensisci(Recensione recensione){
        return this.recensioni.add(recensione);
    }
}
