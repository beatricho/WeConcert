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
import it.unibo.tw.web.beans.GenereGruppo;
import it.unibo.tw.web.beans.Post;
import it.unibo.tw.web.beans.Evento;
import it.unibo.tw.web.beans.Utente;

public class Pubblica extends HttpServlet {

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Inoltra alla pagina JSP per la pubblicazione
        getServletContext().getRequestDispatcher("/pubblica.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera i dati dal form
        String descrizione = request.getParameter("descrizione");
        String disponibilitaMezzo = request.getParameter("disponibilitaMezzo");
        String etaMin = request.getParameter("etaMin");
        String etaMax = request.getParameter("etaMax");
        String genereGruppo = request.getParameter("genereGruppo");
        int partecipantiMax = Integer.parseInt(request.getParameter("partecipantiMax"));
        String descrizioneEvento = request.getParameter("evento");
       
        // Ottiene l'utente corrente dal contesto
        Utente utenteCorrente = (Utente) this.getServletContext().getAttribute("utenteCorrente");

        
        Evento eventoSelezionato = null;
        // Recupera la lista degli eventi dal contesto
        if(! descrizioneEvento.equals("Scegli_successivamente")) {
        	
	        List<Evento> eventi = (List<Evento>) this.getServletContext().getAttribute("eventi");
	        if(eventi != null) {
		        for (Evento evento : eventi) {
		            if (evento.getDescrizione().equals(descrizioneEvento)) {
		                eventoSelezionato = evento;
		                break;
		            }
		        }
	        }
        }
        else {	// Non sono disponibili eventi al momento -> scegli successivamente
	        eventoSelezionato = new Evento();
	       	eventoSelezionato.setDescrizione("Evento fittizio momentaneo");
        }
        
        // Crea il nuovo post
        Post nuovoPost = new Post();
        nuovoPost.setId("post-" + System.currentTimeMillis()); // Usa timestamp per ID univoco
        nuovoPost.setDescrizione(descrizione);
        nuovoPost.setUtentePubblicante(utenteCorrente);
        nuovoPost.setDisponibilitaMezzo(DisponibilitaMezzo.valueOf(disponibilitaMezzo));
        EtaGruppo etaGruppo = new EtaGruppo();
        etaGruppo.setSogliaInferiore(Integer.parseInt(etaMin));
        etaGruppo.setSogliaSuperiore(Integer.parseInt(etaMax));
        nuovoPost.setEtaGruppo(etaGruppo);
        nuovoPost.setGenereGruppo(GenereGruppo.valueOf(genereGruppo));
        nuovoPost.setPartecipantiMax(partecipantiMax);
        nuovoPost.setEvento(eventoSelezionato);
       
        // Recupera la lista dei post pubblicati e aggiunge il nuovo post
        List<Post> postPubblicati = (List<Post>) this.getServletContext().getAttribute("postPubblicati");
        if (postPubblicati == null) {
            postPubblicati = new ArrayList<>();
        }
        postPubblicati.add(nuovoPost);

        // Aggiorna la lista nel contesto dell'applicazione
        this.getServletContext().setAttribute("postPubblicati", postPubblicati);

        // Reindirizza alla pagina del profilo o ad una conferma
        response.sendRedirect("profilo.jsp");
    }
}
