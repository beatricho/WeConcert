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
import it.unibo.tw.web.beans.Valutazione;
import it.unibo.tw.web.beans.Recensione;

public class Profilo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Log di controllo per assicurare che GET funzioni
        System.out.println("GET request ricevuta in Profilo.");
        getServletContext().getRequestDispatcher("/profilo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");
        Utente pubblicante = null;
        Utente aderente = null;
        Post target = null;
        String postId = request.getParameter("postId");
        
        List<Post> postPubblicati = new ArrayList<Post>();
        for(Utente u : utenti) {
        	for(Post p : u.getPostPubblicati()) {
        		postPubblicati.add(p);
        	}
        }
        
        for(Post post : postPubblicati) {
        	if(post.getId().equals(postId)) {
        		pubblicante = post.getUtentePubblicante();
        		target = post;
        	}
        }
        
        List<Post> pubblicati = pubblicante.getPostPubblicati();

        if ("modificaDescrizionePost".equals(action)) {
            String nuovaDescrizione = request.getParameter("nuovaDescrizione");          
                        
            utenti.remove(pubblicante);
            pubblicati.remove(target);
            target.setDescrizione(nuovaDescrizione);
            pubblicati.add(target);
            pubblicante.setPostPubblicati(pubblicati);
            
            utenti.add(pubblicante);             
        }

        else if ("eliminaPost".equals(action)) {
        	utenti.remove(pubblicante);
            pubblicati.remove(target);
            pubblicante.setPostPubblicati(pubblicati);
        }

        else if ("eliminaAdesione".equals(action)) {
            String username = request.getParameter("username");

            for(Utente u : utenti) {
            	if(u.getUsername().equals(username)) {
            		aderente = u;
            		break;
            	}
            }
            
            utenti.remove(pubblicante);
            pubblicati.remove(target);
            target.getUtentiAderenti().remove(aderente);
            pubblicati.add(target);
            pubblicante.setPostPubblicati(pubblicati);
        }

        else if ("inserisciAdesione".equals(action)) {
            String username = request.getParameter("username");

            for (Utente u : utenti) {
            	if(u.getUsername().equals(username)) {
            		aderente=u;
            		break;
            	}
            }
            
            for(Post post : postPubblicati) {
            	if(post.getId().equals(postId)) {
            		pubblicante = post.getUtentePubblicante();
            		target = post;
            	}
            }
            
            utenti.remove(pubblicante);
            pubblicati.remove(target);
            target.setUtentiAderenti(aderente);
            pubblicati.add(target);
            pubblicante.setPostPubblicati(pubblicati);
            utenti.add(pubblicante);           
            
            this.getServletContext().setAttribute("utenti", utenti);
            
            response.sendRedirect("ricerca.jsp");
        }
        
        utenti.add(pubblicante);           
        this.getServletContext().setAttribute("utenti", utenti);
        
        response.sendRedirect("profilo.jsp");
    }
}
