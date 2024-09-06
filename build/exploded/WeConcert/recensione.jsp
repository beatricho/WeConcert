<!DOCTYPE>

<!-- pagina per la gestione di errori -->
<%@ page errorPage="./errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="it.unibo.tw.web.beans.*"%>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page import="com.fasterxml.jackson.datatype.jsr310.JavaTimeModule"%>
<%@ page import="com.fasterxml.jackson.core.JsonProcessingException"%>

<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recensioni</title>
    <link rel="stylesheet" href="styles/recensione.css">
</head>

<body>
    <div class="container">
        <div class="left-banner">
            <div class="eventi">
                <h2>Eventi</h2>
                <ul id="eventi-list">
                    <%
                    	Utente utente = (Utente) session.getAttribute("utenteCorrente");
	                    ObjectMapper objectMapper = new ObjectMapper();
	                    objectMapper.registerModule(new JavaTimeModule()); // Per gestire LocalDate
                    	for(Post p: utente.getPostPubblicati()){ 
                    		String utentiJson = objectMapper.writeValueAsString(p.getUtentiAderenti());
                    %>
                    		<li class="evento" onclick='mostraUtenti("<%= p.getId() %>", <%= utentiJson %>)'><%= p.getEvento().getDescrizione() %></li>
                    <%
                    	}                    
                    %>
                    <%
                    	for(Post p: utente.getAdesioni()){ 
                    		String utentiJson = objectMapper.writeValueAsString(p.getUtentiAderenti());
                    %>
                    		<li class="evento" onclick='mostraUtenti("<%= p.getId() %>", <%= utentiJson %>)'><%= p.getEvento().getDescrizione() %></li>
                    <%
                    	}                    
                    %>
                </ul>
            </div>
            <div class="utenti" id="utenti">
                <!-- Utenti generati dinamicamente -->
            </div>
        </div>

        <div class="right-content">
            <div id="recensione-form">
                <h3>Recensione per <span id="utente-selezionato"></span></h3> <!-- Mostra l'utente selezionato -->
                <label for="valutazione">Valutazione:</label>
                <select id="valutazione">
                    <option value="PESSIMO">PESSIMO</option>
                    <option value="INSUFFICIENTE">INSUFFICIENTE</option>
                    <option value="DISCRETO">DISCRETO</option>
                    <option value="BUONO">BUONO</option>
                    <option value="ECCELLENTE">ECCELLENTE</option>
                </select>
                <br>
                <label for="commento">Commento:</label>
                <textarea id="commento" rows="4" cols="50"></textarea>
                <br>
                <button onclick="inviaRecensione()">Invia Recensione</button>
            </div>
        </div>
    </div>

    <div class="footer">
        <button><a href="WelcomeView.html">Schermata Home</a></button>
    </div>

    <script>
    	let utenteSelezionato = null;
    
        function mostraUtenti(eventoId, user) {
        	//Utenti che puoi recensire
        	var utenti = []; // Inizializza l'array correttamente
        	
        	for (var i = 0; i < user.length; i++) {
                utenti[i] = user[i].username;
                console.log(utenti[i]);
            }
        	
            const utentiContainer = document.getElementById('utenti');
            utentiContainer.innerHTML = '';

            utenti.forEach(utente => {
                const utenteElement = document.createElement('div');
                utenteElement.className = 'utente';
                utenteElement.textContent = utente;
                utenteElement.onclick = () => selezionaUtente(utente);
                utentiContainer.appendChild(utenteElement);
            });
        }

        function selezionaUtente(utente) {
        	utenteSelezionato = utente; // Salva il nome dell'utente selezionato
            document.getElementById('recensione-form').style.display = 'block';
            document.getElementById('utente-selezionato').textContent = utenteSelezionato; // Mostra il nome dell'utente nel form
        }
        
        function inviaRecensione() {
            const valutazione = document.getElementById('valutazione').value;
            const commento = document.getElementById('commento').value;

            if (utenteSelezionato) {
                // Creazione dell'oggetto che contiene i dati della recensione
                const recensioneData = {
                    utente: utenteSelezionato,
                    valutazione: valutazione,
                    commento: commento
                };

                // Invio dei dati alla servlet tramite una richiesta POST
                fetch('eseguiRecensione', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(recensioneData) // Converte i dati in JSON
                })
                .then(response => {
                    if (response.ok) {
                        return response.text(); // Processa la risposta della servlet
                    } else {
                        throw new Error('Errore durante l\'invio della recensione.');
                    }
                })
                .then(data => {
                    console.log('Risposta della servlet:', data);
                    alert('Recensione inviata con successo!');
                })
                .catch(error => {
                    console.error('Errore:', error);
                    alert('Si è verificato un errore durante l\'invio della recensione.');
                });

            } else {
                alert("Seleziona un utente prima di inviare una recensione.");
            }
        }
    </script>
</body>

</html>