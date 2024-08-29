package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.web.beans.DisponibilitaMezzo;
import it.unibo.tw.web.beans.EtaGruppo;
import it.unibo.tw.web.beans.Evento;
import it.unibo.tw.web.beans.GenereGruppo;
import it.unibo.tw.web.beans.Post;
import it.unibo.tw.web.beans.Utente;
import it.unibo.tw.web.beans.Recensione;

public class Profilo extends HttpServlet {
    
    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);

        Utente utenteCorrente = (Utente) this.getServletContext().getAttribute("utenteCorrente");

        if (utenteCorrente == null) {
            utenteCorrente = new Utente();
            utenteCorrente.setNome("Mario");
            utenteCorrente.setCognome("Rossi");
            this.getServletContext().setAttribute("utenteCorrente", utenteCorrente);
        }

        // Inizializzazione dei post dell'utente se non presenti
        if (this.getServletContext().getAttribute("postUtente") == null) {
            List<Post> postPubblicati = new ArrayList<Post>();
            
            // Creazione del primo post
            List<Evento> eventi = (List<Evento>) this.getServletContext().getAttribute("eventi");
            EtaGruppo eta = new EtaGruppo();
            eta.setSogliaInferiore(25);
            eta.setSogliaSuperiore(40);

            Utente utenteAderente = new Utente();
            
            Post post = new Post();
            post.setId("post-1");
            post.setEvento(eventi.get(0));
            post.setDescrizione("Qualcuno che venga con me a vedere il grande CLAUDIO BISIO??????");
            post.setUtentePubblicante(utenteCorrente);
            post.setDisponibilitaMezzo(DisponibilitaMezzo.HO);
            post.setEtaGruppo(eta);
            post.setGenereGruppo(GenereGruppo.MISTO);
            post.setPartecipantiMax(10);
            
            // Creazione di adesioni al primo post
            utenteAderente.setUsername("the_real_Filippo");
            post.setUtentiAderenti(utenteAderente);
            utenteAderente = new Utente();
            utenteAderente.setUsername("Michela89");
            post.setUtentiAderenti(utenteAderente);
                        
            postPubblicati.add(post);
            
            // Secondo post
            post = new Post();
            eta = new EtaGruppo();
            eta.setSogliaInferiore(18);
            eta.setSogliaSuperiore(24);
            post.setId("post-2");
            post.setEvento(eventi.get(1));
            post.setDescrizione("Cerco degli omies per proxare il nuovo drop di Tedua ueue");
            post.setUtentePubblicante(utenteCorrente);
            post.setDisponibilitaMezzo(DisponibilitaMezzo.NON_HO_E_CERCO);
            post.setEtaGruppo(eta);
            post.setGenereGruppo(GenereGruppo.MASCHILE);
            post.setPartecipantiMax(5);
            postPubblicati.add(post);

            // Creazione di adesioni al secondo post
            utenteAderente = new Utente();
            utenteAderente.setUsername("sfera-ebbasta");
            post.setUtentiAderenti(utenteAderente);
            utenteAderente = new Utente();
            utenteAderente.setUsername("tony_effe_baby");
            post.setUtentiAderenti(utenteAderente);
            utenteAderente = new Utente();
            utenteAderente.setUsername("sosa");
            post.setUtentiAderenti(utenteAderente);
            utenteAderente = new Utente();
            utenteAderente.setUsername("gue");
            post.setUtentiAderenti(utenteAderente);
                        
            postPubblicati.add(post);

            this.getServletContext().setAttribute("postUtente", postPubblicati);
        }
      

        // Creazione di recensioni fittizie fatte all'utente corrente
        if(this.getServletContext().getAttribute("recensioni") == null){
            List<Recensione> recensioni = new ArrayList<Recensione>();
            for (int i = 1; i <= 3; i++) {
                Recensione recensione = new Recensione();
                recensione.setId("rec" + i);
                recensione.setCommento(i % 2 == 0 ? "Pollice in su" : "Pollice in giù");
                recensione.setCommento("Commento recensione " + i);
                recensioni.add(recensione);
            }
            this.getServletContext().setAttribute("recensioni", recensioni);
        }
    }


    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        List<Post> postPubblicati = (List<Post>) this.getServletContext().getAttribute("postPubblicati");

        if ("modificaDescrizionePost".equals(action)) {
            String postId = request.getParameter("postId");
            String nuovaDescrizione = request.getParameter("nuovaDescrizione");

            for (Post post : postPubblicati) {
                if (post.getId().equals(postId)) {
                    post.setDescrizione(nuovaDescrizione); // Modifica la descrizione del post
                    break;
                }
            }
        }
        
        else if ("eliminaPost".equals(action)) {
            String postId = request.getParameter("postId");
            postPubblicati.removeIf(post -> post.getId().equals(postId)); // Rimuovi il post con l'id specificato
        }
        
        else if ("eliminaAdesione".equals(action)){
            String postId = request.getParameter("postId");
            String username = request.getParameter("username");
            for (Post post : postPubblicati) {
                if (post.getId().equals(postId)) {
                    List<Utente> utentiAderenti = post.getUtentiAderenti();
                    for(Utente adesione : utentiAderenti){
                        if(adesione.getUsername().equals(username)){
                            utentiAderenti.remove(adesione);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        this.getServletContext().setAttribute("postPubblicati", postPubblicati);
        response.sendRedirect("profilo.jsp");        
    }
}
