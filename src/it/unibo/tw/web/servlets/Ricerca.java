package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unibo.tw.web.beans.DisponibilitaMezzo;
import it.unibo.tw.web.beans.GenereGruppo;
import it.unibo.tw.web.beans.Post;
import it.unibo.tw.web.beans.Utente;

public class Ricerca extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ricerca = request.getParameter("ricercaString");
		String tipo = request.getParameter("ricercaTipo");
		GenereGruppo genere = null;
		if(request.getParameter("genereGruppo")!= null)
		 genere = GenereGruppo.valueOf(request.getParameter("genereGruppo"));
		DisponibilitaMezzo disp = null;
		if(request.getParameter("disponibilitaMezzo")!= null)
		 disp = DisponibilitaMezzo.valueOf(request.getParameter("disponibilitaMezzo"));
		String citta = request.getParameter("citta");
		HttpSession sessione = request.getSession();
		Utente pubblicante = (Utente) sessione.getAttribute("utenteCorrente");

		List<Post> risultato = new ArrayList<>();
		List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");
		if (tipo != null) {
			if (tipo.equals("utente")) {
				for (Utente u : utenti) {
					if (u.getUsername().equals(ricerca)) {
						risultato = u.getPostPubblicati();
						break;
					}
				}
			} else {
				for (Utente u : utenti) {
					List<Post> pubblicati = u.getPostPubblicati();
					for (Post p : pubblicati) {
						if (p.getEvento().getDescrizione().toLowerCase().contains(ricerca.toLowerCase()) && !p.getUtentePubblicante().getUsername().equals(pubblicante.getUsername())) {
							risultato.add(p);
						}
					}
				}
			}

			if (risultato.isEmpty()) {
				sessione.setAttribute("err", 1);
			} else {

				if (genere != null) {
					for (Post p : risultato) {
						if (p.getGenereGruppo() != genere) {
							risultato.remove(p);
						}
					}
				}

				if (request.getParameter("etaMinima") != "") {
					int etaMin = Integer.valueOf(request.getParameter("etaMinima"));
					for (Post p : risultato) {
						if (p.getEtaGruppo().getSogliaInferiore() < etaMin) {
							risultato.remove(p);
						}
					}
				}

				if (request.getParameter("etaMassima") != "") {
					int etaMax = Integer.valueOf(request.getParameter("etaMassima"));
					for (Post p : risultato) {
						if (p.getEtaGruppo().getSogliaSuperiore() > etaMax) {
							risultato.remove(p);
						}
					}
				}

				if (citta != "") {
					for (Post p : risultato) {
						if (p.getCitta() != citta) {
							risultato.remove(p);
						}
					}
				}

				if (disp != null) {
					for (Post p : risultato) {
						if (p.getDisponibilitaMezzo() != disp) {
							risultato.remove(p);
						}
					}
				}

				sessione.setAttribute("err", 0);
				sessione.setAttribute("risultatoRicerca", risultato);
			}
		} else {
			sessione.setAttribute("err", 2);
		}

		response.sendRedirect("ricerca.jsp");
	}
}