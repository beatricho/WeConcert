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
		String[] temp = request.getParameterValues("genereGruppo");
		List<GenereGruppo> genere = new ArrayList<>();
		if (temp != null)
			for (String s : temp) {
				genere.add(GenereGruppo.valueOf(s.toUpperCase()));
			}
		temp = request.getParameterValues("disponibilitaMezzo");
		List<DisponibilitaMezzo> disp = new ArrayList<>();
		if (temp != null)
			for (String s : temp) {
				disp.add(DisponibilitaMezzo.valueOf(s.toUpperCase()));
			}
		String citta = request.getParameter("citta");
		HttpSession sessione = request.getSession();
		Utente pubblicante = (Utente) sessione.getAttribute("utenteCorrente");

		List<Post> risultato = new ArrayList<>();
		List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");
		String action = request.getParameter("tipo");

		if ("ricerca".equals(action)) {
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
							if (p.getEvento().getDescrizione().toLowerCase().contains(ricerca.toLowerCase())
									&& !p.getUtentePubblicante().getUsername().equals(pubblicante.getUsername())) {
								risultato.add(p);
							}
						}
					}
				}

				if (risultato.isEmpty()) {
					sessione.setAttribute("err", 1);
				} else {

					boolean trovato = false;
					List<Post> toRemove = new ArrayList<>();
					if (!genere.isEmpty() && !risultato.isEmpty()) {
						for (Post p : risultato) {
							for (GenereGruppo g : genere) {
								if (p.getGenereGruppo().getNumero() == g.getNumero()) {
									trovato = true;
									break;
								}
							}
							if (!trovato) {
								toRemove.add(p);
							} else
								trovato = false;
						}
						risultato.removeAll(toRemove);
					}
					
					toRemove.clear();
					if (request.getParameter("etaMinima") != "" && !risultato.isEmpty()) {
						int etaMin = Integer.valueOf(request.getParameter("etaMinima"));
						for (Post p : risultato) {
							if (p.getEtaGruppo().getSogliaInferiore() < etaMin) {
								toRemove.add(p);
							}
						}
						risultato.removeAll(toRemove);
					}

					toRemove.clear();
					if (request.getParameter("etaMassima") != "" && !risultato.isEmpty()) {
						int etaMax = Integer.valueOf(request.getParameter("etaMassima"));
						for (Post p : risultato) {
							if (p.getEtaGruppo().getSogliaSuperiore() > etaMax) {
								toRemove.add(p);
							}
						}
						risultato.removeAll(toRemove);
					}

					toRemove.clear();
					if (citta != "" && !risultato.isEmpty()) {
						for (Post p : risultato) {
							if (p.getCitta() != citta) {
								toRemove.add(p);
							}
						}
						risultato.removeAll(toRemove);
					}

					toRemove.clear();
					trovato = false;
					if (!disp.isEmpty() && !risultato.isEmpty()) {
						for (Post p : risultato) {
							for (DisponibilitaMezzo g : disp) {
								if (p.getDisponibilitaMezzo().getNumero() == g.getNumero()) {
									trovato = true;
									break;
								}
							}
							if (!trovato) {
								toRemove.add(p);
							} else
								trovato = false;
						}
						risultato.removeAll(toRemove);
					}

					if (!risultato.isEmpty()) {
						sessione.setAttribute("err", 0);
						sessione.setAttribute("risultatoRicerca", risultato);
					} else
						sessione.setAttribute("err", 1);
				}
			} else {
				sessione.setAttribute("err", 2);
			}
		} else {
			String postId = request.getParameter("idPost");
			List<Post> pubblicati = null;
			Utente aderente = null;
			Post target = null;

			for (Utente u : utenti) {
				for (Post p : u.getPostPubblicati()) {
					if (p.getId().equals(postId)) {
						target = p;
						pubblicante = target.getUtentePubblicante();
						break;
					}
				}
			}

			pubblicati = pubblicante.getPostPubblicati();
			aderente = (Utente) sessione.getAttribute("utenteCorrente");

			utenti.remove(pubblicante);
			pubblicati.remove(target);
			target.setUtentiAderenti(aderente);
			pubblicati.add(target);
			aderente.getAdesioni().add(target);
			pubblicante.setPostPubblicati(pubblicati);
			utenti.add(pubblicante);

			this.getServletContext().setAttribute("utenti", utenti);
			sessione.setAttribute("utenteCorrente", aderente);
		}

		response.sendRedirect("ricerca.jsp");
	}
}