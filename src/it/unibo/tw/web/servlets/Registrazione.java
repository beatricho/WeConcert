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

import it.unibo.tw.web.beans.DisponibilitaMezzo;
import it.unibo.tw.web.beans.EtaGruppo;
import it.unibo.tw.web.beans.Evento;
import it.unibo.tw.web.beans.Genere;
import it.unibo.tw.web.beans.GenereGruppo;
import it.unibo.tw.web.beans.Post;
import it.unibo.tw.web.beans.Recensione;
import it.unibo.tw.web.beans.Utente;
import it.unibo.tw.web.beans.Valutazione;

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

            //EVENTI REGISTRATI
            List<Evento> eventi = new ArrayList<Evento>();
            //inserimento di 2 eventi server-side
            Evento primo = new Evento();
            primo.setId("evento1");
            primo.setData(LocalDate.of(2024, 12, 20));
            primo.setOrario(LocalTime.of(20, 0));
            primo.setLuogo("Mantova");
            primo.setDescrizione("Claudio Bisio");
            primo.setURLLocandina("https://www.sorrisi.com/wp-content/uploads/2015/03/DOC_2010_1149_757674_1.jpg");
            eventi.add(primo);

            Evento secondo = new Evento();
            secondo.setId("evento2");
            secondo.setData(LocalDate.of(2025, 2, 10));
            secondo.setOrario(LocalTime.of(21, 0));
            secondo.setLuogo("Bologna");
            secondo.setDescrizione("Tedua");
            secondo.setURLLocandina("https://www.polverinihairacademia.com/wp-content/uploads/2023/09/tedua-capelli-lunghi-uomo.webp");
            eventi.add(secondo);

            this.getServletContext().setAttribute("eventi", eventi);
            
            // Creazione utenti già registrati
            Utente effe = new Utente();
            effe.setUsername("effe");
            effe.setPassword("effe");
            effe.setNome("Francesco");
            effe.setCognome("Baldassarre");
            effe.setGenere(Genere.UOMO);
            
            // Creazione di post di effe
            EtaGruppo eta = new EtaGruppo();
            eta.setSogliaInferiore(25);
            eta.setSogliaSuperiore(40);

            Utente utenteAderente = new Utente();
            
            Post post = new Post();
            post.setId("post-1");
            post.setEvento(eventi.get(0));
            post.setDescrizione("Qualcuno che venga con me a vedere il grande CLAUDIO BISIO??????");
            post.setUtentePubblicante(effe);
            post.setDisponibilitaMezzo(DisponibilitaMezzo.HO);
            post.setEtaGruppo(eta);
            post.setGenereGruppo(GenereGruppo.MISTO);
            post.setPartecipantiMax(10);

            // Creazione di adesioni al primo post
            Utente filo = new Utente();
            filo.setUsername("the_real_Filippo");
            filo.setGenere(Genere.UOMO);
            Utente michew = new Utente();
            michew.setUsername("Michela89");
            michew.setGenere(Genere.DONNA);
            utenti.add(filo);
            utenti.add(michew);
            post.setUtentiAderenti(filo);
            post.setUtentiAderenti(michew);
            
            effe.pubblicaPost(post);
            
            // Secondo post
            post = new Post();
            eta = new EtaGruppo();
            eta.setSogliaInferiore(18);
            eta.setSogliaSuperiore(24);
            post.setId("post-2");
            post.setEvento(eventi.get(1));
            post.setDescrizione("Cerco degli omies per proxare il nuovo drop di Tedua ueue");
            post.setUtentePubblicante(effe);
            post.setDisponibilitaMezzo(DisponibilitaMezzo.NON_HO_E_CERCO);
            post.setEtaGruppo(eta);
            post.setGenereGruppo(GenereGruppo.MASCHILE);
            post.setPartecipantiMax(5);

            // Creazione di adesioni al secondo post
            Utente sfera = new Utente();
            sfera.setUsername("sfera-ebbasta");
            sfera.setGenere(Genere.UOMO);
            Utente tony = new Utente();
            tony.setUsername("tony_effe_baby");
            tony.setGenere(Genere.UOMO);
            Utente sosa = new Utente();
            sosa.setUsername("sosa");
            sosa.setGenere(Genere.UOMO);
            utenti.add(sfera);
            utenti.add(tony);
            utenti.add(sosa);
            post.setUtentiAderenti(sfera);
            post.setUtentiAderenti(tony);
            post.setUtentiAderenti(sosa);

            effe.pubblicaPost(post);
            
            // Creazione di recensioni fittizie fatte a effe
            if (this.getServletContext().getAttribute("recensioni") == null) {
                List<Recensione> recensioni = new ArrayList<Recensione>();
                Recensione recensione = new Recensione();
                recensione.setId("rec1");
                recensione.setValutazione(Valutazione.ECCELLENTE);
                recensione.setCommento("Compagno favoloso!!");
                recensioni.add(recensione);

                recensione = new Recensione();
                recensione.setId("rec2");
                recensione.setValutazione(Valutazione.BUONO);
                recensione.setCommento("Tutto apposto");
                recensioni.add(recensione);

                recensione = new Recensione();
                recensione.setId("rec3");
                recensione.setValutazione(Valutazione.DISCRETO);
                recensione.setCommento("Mi ha fatto aspettare sotto la pioggia per 5 minuti");
                recensioni.add(recensione);
                effe.setRecensioni(recensioni);
            }
            
            utenti.add(effe);
            this.getServletContext().setAttribute("utenti", utenti);            
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
