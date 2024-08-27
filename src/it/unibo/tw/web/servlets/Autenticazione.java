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
import it.unibo.tw.web.beans.Utente;

public class Autenticazione extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username.equals("admin") && password.equals("admin")) { // amministratore
            response.sendRedirect("adminPage.html");
        } else { // utente qualsiasi
            Utente utente = new Utente();
            boolean nonRegistrato = true;
            boolean passwordSbagliata = false;
            HttpSession sessione = request.getSession();
            List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");

            for (Utente user : utenti) { // controllo se l'utente non è registrato o ha sbagliato password
                if (user.getUsername().equals(username)) {
                    nonRegistrato = false;
                    if (user.getPassword() != null && !user.getPassword().equals(password))
                        passwordSbagliata = true;
                    else
                        utente = user;
                    break;
                }
            }

            //Nei primi due casi l'applicazione mostra una pagina con l'errore che si è verificato, nell'ultimo caso mostra la pagina iniziale
            if (nonRegistrato) {
                sessione.setAttribute("log", 1);
                response.sendRedirect("login-reg.jsp");
            } else if (passwordSbagliata) {
                sessione.setAttribute("log", 2);
                response.sendRedirect("login-reg.jsp");
            } else {
                // sessione relativa all'utente che effettua l'accesso
                sessione.setAttribute("utenteCorrente", utente); // inserimento dell'user corrente 
                response.sendRedirect("WelcomeView.html");
            }

        }
    }

}