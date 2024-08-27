package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unibo.tw.web.beans.Evento;
import it.unibo.tw.web.beans.Genere;
import it.unibo.tw.web.beans.Post;
import it.unibo.tw.web.beans.Utente;

public class Ricerca extends HttpServlet{

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String ricerca = request.getParameter("ricercaString");
        String tipo = request.getParameter("ricercaTipo");
        String genere = request.getParameter("genereGruppo");
        String disp = request.getParameter("disponibilitaMezzo");
        String citta = request.getParameter("citta");
        String etaMin = request.getParameter("etaMinima");
        String etaMax = request.getParameter("etaMassima");
        
        if(ricerca.equals("")) {
        	//errore
        }
        
        List<Post> risultato = new ArrayList<>();
        
        if(tipo.equals("Utente")) {
        	List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");
        	for (Utente u : utenti) {
        		if (u.getUsername().equals(ricerca)) {
        			risultato = u.getPostPubblicati();
        			
        			if(genere != null) {
        				for( Post p : risultato) {
        					if(...)
        				}
        			}
        		}
        	}
        }
        else {
        	
        }
    	
    	
    	
    	
    	
    	
    	//per il nome e cognome utilizzo questa procedura in quanto l'inserimento avviene nello stesso campo
        String[] nome_cognome = new String[2];
        nome_cognome = request.getParameter("nome_cognome").split(" ");
        String nome = nome_cognome[0];
        String cognome = nome_cognome[1];
        Genere genere = Genere.valueOf(request.getParameter("genere").toUpperCase());
        LocalDate nascita = LocalDate.parse(request.getParameter("nascita"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession sessione = request.getSession();
        boolean registrato = false;
        List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");
        
        for (Utente user : utenti) {
            if (user.getUsername().equals(username)) {
                registrato = true;
                break;
            }
        }

        if (registrato) {
            sessione.setAttribute("reg", 1);
        } else {
            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setGenere(genere);
            utente.setNascita(nascita);
            utente.setUsername(username);
            utente.setPassword(password);

            utenti.add(utente);
            this.getServletContext().setAttribute("utenti", utenti);

            sessione.setAttribute("reg", 2);
        }
		
        response.sendRedirect("login-reg.jsp");
    }
}