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
import it.unibo.tw.web.beans.Utente;

public class Registrazione extends HttpServlet {

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);

        //inizializzazione dell'applicazione da ripetere sia nel login che nella registrazione
        if (this.getServletContext().getAttribute("utenti") == null) {

            //UTENTI REGISTRATI
            List<Utente> utenti = new ArrayList<Utente>();
            //amministratore è l'unico utente inserito all'inizializzazione (è un utente con solo username e password)
            Utente amministratore = new Utente();
            amministratore.setUsername("admin");
            amministratore.setPassword("admin");
            utenti.add(amministratore);
            this.getServletContext().setAttribute("utenti", utenti);

            //EVENTI REGISTRATI
            List<Evento> eventi = new ArrayList<Evento>();
            //inserimento di 2 eventi server-side
            Evento primo = new Evento();
            primo.setId("evento1");
            primo.setData(LocalDate.of(2024, 12, 20));
            primo.setOrario(LocalTime.of(20, 0));
            primo.setLuogo("Mantova");
            primo.setDescrizione("Claudio Bisio");
            eventi.add(primo);

            Evento secondo = new Evento();
            secondo.setId("evento2");
            secondo.setData(LocalDate.of(2025, 2, 10));
            primo.setOrario(LocalTime.of(21, 0));
            primo.setLuogo("Bologna");
            primo.setDescrizione("Tedua");
            eventi.add(secondo);

            this.getServletContext().setAttribute("eventi", eventi);
        } 
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
