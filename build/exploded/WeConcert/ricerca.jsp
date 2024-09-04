<!DOCTYPE html>

<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="it.unibo.tw.web.beans.*"%>

<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Feed Ricerca</title>
<link rel="stylesheet" href="styles/profilo.css">
<link rel="stylesheet" href="styles/post.css">

</head>

<body>

	<jsp:useBean id="utenteCorrente" class="it.unibo.tw.web.beans.Utente"
		scope="session"></jsp:useBean>

	<script>
		function validateForm() {
			// Ottieni il valore del campo di testo
			var par = document.getElementById("ricercaString").value;
			var tipo = document.getElementById("ricercaTipo").value;
			// Controlla se il campo ÃƒÂ¨ vuoto
			if (par.trim() === "" || tipo === null) {
				// Mostra un alert
				alert("Inserire un evento o un utente!");
				return false; // Previene l'invio del form
			}

			return true; // Permette l'invio del form
		}
	</script>

	<nav>
		<div class="title">
			<h2 class="logo">Ricerca Post</h2>
		</div>
		<div class="container">
			<div class="top">
				<a class="profile">
					<div class="handle">
						<h4><%=utenteCorrente.getNome()%>
							<%=utenteCorrente.getCognome()%></h4>
						<p class="text-muted">
							@<%=utenteCorrente.getUsername()%></p>
					</div>
				</a>
				<div class="sidebar">
            		<button class="btn-back" onclick="window.location.href='WelcomeView.html'">Torna alla pagina iniziale</button>
				</div>
			</div>
			<form id="fileform" action="<%=request.getContextPath()%>/ricerca"
				method="post" onsubmit="return validateForm()">
				 <input type="hidden" name="tipo" value="ricerca">
				<div class="search-container"> <input id="ricercaString"
						name="ricercaString" type="text" class="search-input"
						placeholder="Inserire il nome di un utente o di un artista" />
					<br>
					<!-- Bottone per inviare il form -->
					<button class="search-button" type="submit">Cerca</button>
				</div>

				<div class="filters">
					<!-- Filtro per la ricerca -->
					<fieldset>
						<b><legend>Tipo di ricerca</legend></b> <label> <input id="ricercaTipo"
							type="radio" name="ricercaTipo" value="utente">
							Utente
						</label> <br> <label> <input id="ricercaTipo"
							type="radio" name="ricercaTipo" value="evento">
							Evento
						</label>
					</fieldset>

					<!-- Filtro per genere del gruppo -->
					<fieldset>
						<b><legend>Genere del gruppo</legend></b> <label> <input
							type="checkbox" name="genereGruppo" value="femminile">
							Femminile
						</label> <br> <label> <input type="checkbox"
							name="genereGruppo" value="maschile"> Maschile
						</label> <br> <label> <input type="checkbox"
							name="genereGruppo" value="misto"> Misto
						</label>
					</fieldset>

					<!-- Filtro per disponibilitÃƒÂ  del mezzo -->
					<fieldset>
						<b><legend>Disponibilità  del mezzo</legend></b> <label> <input
							type="checkbox" name="disponibilitaMezzo" value="ho"> Ho
						</label> <br> <label> <input type="checkbox"
							name="disponibilitaMezzo" value="nonHoCerco"> Non ho e
							cerco
						</label> <br> <label> <input type="checkbox"
							name="disponibilitaMezzo" value="nonHoNonCerco"> Non ho e
							non cerco
						</label>
					</fieldset>

					<!-- Filtro per cittÃƒÂ  di provenienza -->
					<fieldset>
						<b><legend>Città </legend></b> <label for="citta"> Città
							di provenienza: <input type="text" id="citta" name="citta"
							placeholder="Inserisci la cittÃ ">
						</label>
					</fieldset>

					<!-- Filtri per etÃƒÂ  minima e massima -->
					<fieldset>
						<b><legend>Età </legend></b> <label for="etaMinima"> Età
							minima: <input type="text" id="etaMinima" name="etaMinima"
							placeholder="Inserisci l'etÃ  minima">
						</label> <br> <label for="etaMassima"> Età  massima: <input
							type="text" id="etaMassima" name="etaMassima"
							placeholder="Inserisci l'età massima">
						</label>
					</fieldset>

				</div>
			</form>
		</div>
	</nav>

	<main class="main">
		<div class="container">

			<div class="middle">
				<div class="feed">
					<%
					List<Post> risultatoRicerca = (List<Post>) session.getAttribute("risultatoRicerca");
					Integer err = (Integer) session.getAttribute("err");
					if (err != null) {
						if (err == 0) {
							for (Object obj : risultatoRicerca) {
						Post post = (Post) obj;
						Evento eventoRelativo = post.getEvento();
						String URLLocandina = "";
						LocalDate data = null;
						LocalTime orario = null;
						String luogo = "";
						if (eventoRelativo != null) {
							URLLocandina = eventoRelativo.getURLLocandina();
							data = eventoRelativo.getData();
							orario = eventoRelativo.getOrario();
							luogo = eventoRelativo.getLuogo();
						}
					%>
					<li class="post-item">
						<div class="post-info">
							<!-- Immagine della Locandina dell'Evento -->
							<img src="<%=URLLocandina%>" alt="Locandina Evento"
								class="post-locandina">
							<div class="post-header">
								<span class="post-descrizione"><%=post.getDescrizione()%></span>
								<div class="post-buttons"></div>
							</div>
							<div class="post-dettagli">
								<p>
									<strong>Evento:</strong><%=data%>
									-
									<%=orario%>
									@
									<%=luogo%></p>
								<p>
									<strong>Disponibilità Mezzo:</strong>
									<%=post.getDisponibilitaMezzo()%></p>
								<p>
									<strong>Partecipanti Max:</strong>
									<%=post.getPartecipantiMax()%></p>
								<p>
									<strong>Età  Gruppo:</strong>
									<%=post.getEtaGruppo().getSogliaInferiore()%>
									-
									<%=post.getEtaGruppo().getSogliaSuperiore()%>
									anni
								</p>
								<p>
									<strong>Genere Gruppo:</strong>
									<%=post.getGenereGruppo()%></p>
							</div>

							<!-- Adesioni degli utenti -->
							<div class="adesioni">
								<%
								if (post.getUtentiAderenti().size() != post.getPartecipantiMax()) {
								%>
								<form action="<%=request.getContextPath()%>/ricerca" method="post">
								<button class="btn-inserisci-adesione" type="submit">
									Aderisci!</button>
								    <input type="hidden" name="tipo" value="adesione">
								    <input type="hidden" name="idPost" value="<%=post.getId()%>">
								    <input type="hidden" name="utenteAderente" value="<%=utenteCorrente.getUsername()%>">
								</form>
								<%
								}
								%>
								<ul class="adesioni-list">
									<h4>Adesioni:</h4>
									<%
									List<Utente> adesioni = post.getUtentiAderenti();
									if (adesioni != null && !adesioni.isEmpty()) {
										for (Utente adesione : adesioni) {
									%>
									<li class="adesione-item"><span> <%=adesione.getUsername()%></span>
									</li>
									<%
									}
									} else {
									%>
									<li>Nessuna adesione presente.</li>
									<%
									}
									%>
								</ul>
							</div>
							<%
							}
							} else if (err == 1){
							%>
							<br> La ricerca non ha prodotto risultati... <br> <br>
							Riprova! <br> <br>
							<%
							} else if (err == 2) {
								%>
							<br> Inserire il tipo di ricerca! <br> <br>
							<%
							}

							session.removeAttribute("err");
							}
							%>
						</div>
				</div>
			</div>
		</div>
		</div>
		</div>
</body>
</main>