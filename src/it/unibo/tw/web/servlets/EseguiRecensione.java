package it.unibo.tw.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.unibo.tw.web.beans.Recensione;
import it.unibo.tw.web.beans.Utente;
import it.unibo.tw.web.beans.Valutazione;

public class EseguiRecensione extends HttpServlet {

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	// Leggi i dati inviati dal client
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        // Converte la stringa JSON in un oggetto JSON usando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(sb.toString());

        // Estrai i valori dal JSON
        String utenteRecensito = jsonNode.get("utente").asText();
        Valutazione valutazione = Valutazione.valueOf(jsonNode.get("valutazione").asText());
        String commento = jsonNode.get("commento").asText();
        Recensione recensione = new Recensione();
        recensione.setValutazione(valutazione);
        recensione.setCommento(commento);
        recensione.setId("rec-"+System.currentTimeMillis());
        List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");
        
        for(Utente u: utenti){
        	if(u.getUsername().equals(utenteRecensito)) {
        		u.getRecensioni().add(recensione);
        		break;
        	}
        }
        
        // Invia una risposta al client
        response.setContentType("text/plain");
        response.getWriter().write("Recensione ricevuta con successo!");
    }

}
