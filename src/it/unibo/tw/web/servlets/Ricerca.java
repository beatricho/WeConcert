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
import it.unibo.tw.web.beans.Evento;
import it.unibo.tw.web.beans.Genere;
import it.unibo.tw.web.beans.GenereGruppo;
import it.unibo.tw.web.beans.Post;
import it.unibo.tw.web.beans.Utente;

public class Ricerca extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ricerca = request.getParameter("ricercaString");
		String tipo = request.getParameter("ricercaTipo");
		GenereGruppo genere = GenereGruppo.valueOf(request.getParameter("genereGruppo"));
		DisponibilitaMezzo disp = DisponibilitaMezzo.valueOf(request.getParameter("disponibilitaMezzo"));
		String citta = request.getParameter("citta");
		int etaMin = Integer.valueOf(request.getParameter("etaMinima"));
		int etaMax = Integer.valueOf(request.getParameter("etaMassima"));
        HttpSession sessione = request.getSession();

		List<Post> risultato = new ArrayList<>();
		List<Utente> utenti = (List<Utente>) this.getServletContext().getAttribute("utenti");

		if (tipo.equals("Utente")) {
			for (Utente u : utenti) {
				if (u.getUsername().equals(ricerca)) {
					risultato = u.getPostPubblicati();
					break;
				}
			}
		} else {
			for (Utente u : utenti) {
				for (Post p : u.getPostPubblicati()) {
					if (p.getEvento().getDescrizione().contains(ricerca)) {
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

			if (etaMin != 0 || etaMax != 100) {
				for (Post p : risultato) {
					if (p.getEtaGruppo().getSogliaInferiore() < etaMin
							|| p.getEtaGruppo().getSogliaSuperiore() > etaMax) {
						risultato.remove(p);
					}
				}
			}

			if (citta != "") {

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

		response.sendRedirect("ricerca.jsp");
	}
}