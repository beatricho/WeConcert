package it.unibo.tw.web.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import it.unibo.tw.web.beans.Messaggio;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/actions")
public class Chat {
    private Session sessione;
    private Gson gson;
    private List<Session> users = new ArrayList<Session>();// attributi necessari per applicazione di gruppo
    private static final Set<Chat> endpoints = new CopyOnWriteArraySet<>();
    
    @OnOpen
    public void open(Session session) {
        this.sessione = session;
        this.gson = new Gson();
        endpoints.add(this);
        users.add(session);
        System.out.println("Connessione Aperta ");
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Connessione Chiusa ");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Errore");
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
    	Messaggio messaggio = gson.fromJson(message, Messaggio.class); //ricavo l'oggetto messaggio
    	broadcast(gson.toJson(messaggio));//invio a tutti i partecipanti della chat il messaggio inviato, in formato JSON
    }

    private static void broadcast(String message) throws IOException, EncodeException {// stampa a tutti utenti di una
                                                                                       // sessione
        endpoints.forEach(endpoint -> {
            try {
                endpoint.sessione.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
