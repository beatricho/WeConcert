<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Feed Ricerca</title>
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
			// Controlla se il campo è vuoto
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
			<form id="fileform" action="../Ricerca" method="post"
				onsubmit="return validateForm()">

				<div class="search-bar">
					<i class="uil uil-search"></i> <input id="ricercaString"
						name="ricercaString" type="text"
						placeholder="Ricerca post: inserire il nome di un utente o di un artista" />
					<br>
				</div>
				<div class="radio-buttons">
					<input id="ricercaTipo" name="ricercaTipo" type="radio"
						value="utente"> Utente <br> <input name="ricercaTipo"
						type="radio" value="evento"> Evento
				</div>

				<div class="filters">
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

					<!-- Filtro per disponibilità del mezzo -->
					<fieldset>
						<b><legend>Disponibilità del mezzo</legend></b> <label> <input
							type="checkbox" name="disponibilitaMezzo" value="ho"> Ho
						</label> <br> <label> <input type="checkbox"
							name="disponibilitaMezzo" value="nonHoCerco"> Non ho e
							cerco
						</label> <br> <label> <input type="checkbox"
							name="disponibilitaMezzo" value="nonHoNonCerco"> Non ho e
							non cerco
						</label>
					</fieldset>

					<!-- Filtro per città di provenienza -->
					<fieldset>
						<b><legend>Città</legend></b> <label for="citta"> Città di
							provenienza: <input type="text" id="citta" name="citta"
							placeholder="Inserisci la città">
						</label>
					</fieldset>

					<!-- Filtri per età minima e massima -->
					<fieldset>
						<b><legend>Età</legend></b> <label for="etaMinima"> Età
							minima: <input type="text" id="etaMinima" name="etaMinima"
							placeholder="Inserisci l'età minima" value="0">
						</label> <br> <label for="etaMassima"> Età massima: <input
							type="text" id="etaMassima" name="etaMassima"
							placeholder="Inserisci l'età massima" value="100">
						</label>
					</fieldset>

					<!-- Bottone per inviare il form -->
					<button type="submit">Cerca</button>
				</div>
			</form>
		</div>
	</nav>

	<main class="main">
		<div class="container">
			<div class="left">
				<a class="profile">
					<div class="profile-pic">
						<img src="marcopisellonio.jpg" />
					</div>
					<div class="handle">
						<h4><%=utenteCorrente.getNome()%>
							<%=utenteCorrente.getCognome()%></h4>
						<p class="text-muted">
							@<%=utenteCorrente.getUsername()%></p>
					</div>
				</a>
				<div class="sidebar">
					<a class="menu-item active"> <span><i
							class="uil uil-home"></i></span>
						<h3>Torna alla home</h3>
					</a>
				</div>
			</div>

			<div class="middle">
				<div class="feeds">
					<div class="feed">
						<div class="head">
							<%
							Integer err = (Integer) session.getAttribute("err");
							if (err != null) {
								switch (err) {
								case 0: {
							%>
							<br> qua va messo tutto wiiiiii<br>
							<br>
							<%
							}
							break;

							case 1: {
							%>
							<br> La ricerca non ha prodotto risultati... <br> <br>
							Riprova! <br> <br>
							<%
							}
							break;

							}
							}

							session.removeAttribute("err");
							%>
						</div>
						<div class="user">
							<div class="profile-pic">
								<img src="mascetti.jpg" alt="" />
							</div>
							<div class="info">
								<h3>Conte Raffaello Mascetti</h3>
								<p class="text-muted">@mascetti</p>
							</div>
							<span class="edit"><i class="uil uil-ellipsis-h"></i></span>
						</div>

						<div class="photo">
							<img
								src="https://www.valmivola.com/wp-content/uploads/2024/06/Pooh.jpg"
								alt="" />
						</div>

						<div class="action-button">
							<div class="interaction-button">
								<span><i class="uil uil-thumbs-up"></i></span> <span><i
									class="uil uil-comment"></i></span> <span><i
									class="uil uil-share"></i></span>
							</div>
							<div class="bookmark">
								<span><i class="uil uil-bookmark"></i></span>
							</div>
						</div>

						<div class="liked-by">
							<span><img src="sassaroli.jpg" /></span> <span><img
								src="perozzi.jpg" /></span> <span><img src="necchi.jpg" /></span>
							<p>
								Partecipano <b>Professor Sassaroli </b>e <b>altri 3</b>
							</p>
						</div>

						<div class="genere">
							<span><img
								src="https://upload.wikimedia.org/wikipedia/commons/5/59/Gender-Symbol_Hermaprodite_Both_dark_transparent_Background.png" /></span>
							<p>
								<b>Genere</b>: misto
							</p>
						</div>

						<div class="mezzo">
							<span><img
								src="https://thumb.ac-illust.com/1e/1e3da4279e0083f304673b10804c7cc7_t.jpeg" /></span>
							<p>
								<b>Mezzo</b>: ho
							</p>
						</div>

						<div class="eta">
							<span><img
								src="https://www.svgrepo.com/show/519245/simple-calendar.svg" /></span>
							<p>
								<b>EtÃ </b>: 18-99
							</p>
						</div>

						<div class="citta">
							<span><img
								src="https://png.pngtree.com/png-vector/20191021/ourmid/pngtree-vector-location-icon-png-image_1834331.jpg" /></span>
							<p>
								<b>CittÃ  di provenienza</b>: Firenze
							</p>
						</div>

						<div class="caption">
							<p>
								<b>Conte Raffaello Mascetti</b> dice: Antani, blinda la
								supercazzola prematurata con doppio scappellamento a destra? Il
								Melandri Ã¨ sparito, quindi cerchiamo un sostituto...
							</p>
						</div>
						<div class="adesione">
							<p>
								<b>Manca un'altra persona:</b>
							</p>
							<label class="btn btn-primary" for="adesione">Aderisci!</label>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</main>