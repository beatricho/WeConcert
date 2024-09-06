<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>
<%@ page import="it.unibo.tw.web.beans.*"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WeConcert - Profilo</title>
    <link rel="stylesheet" href="styles/profilo.css">
</head>
<body>
    <div class="profilo-page">
        <header>
            <img src="images/logo.png" alt="WeConcert Logo" class="logo">
        </header>
        <main>
            <h2>Profilo Utente</h2>

            <!-- Visualizzazione dei Post Pubblicati con Adesioni -->
            <section class="section" id="post-pubblicati-section">
                <h3>Post Pubblicati</h3>
                <ul id="post-pubblicati">
                    <% 	
                    	Utente utente = (Utente) session.getAttribute("utenteCorrente");
                        List<Post> postPubblicati = utente.getPostPubblicati();
                        if (postPubblicati != null && !postPubblicati.isEmpty()) {
                            for (Post post : postPubblicati) {
                                Evento eventoRelativo = post.getEvento();
                                String URLLocandina = "";
                                LocalDate data = null;
                                LocalTime orario = null;
                                String luogo = "";
                                if(eventoRelativo != null){   
                                	URLLocandina = eventoRelativo.getURLLocandina();
                                    data = eventoRelativo.getData();
                                    orario = eventoRelativo.getOrario();
                                    luogo = eventoRelativo.getLuogo();
                                }
                    %>
                                <li class="post-item">
                                    <div class="post-info">
                                        <!-- Immagine della Locandina dell'Evento -->
                                        <img src="<%= URLLocandina %>" alt="Locandina Evento" class="post-locandina">
                                        <div class="post-header">
                                            <span class="post-descrizione"><%= post.getDescrizione() %></span>
                                            <div class="post-buttons">
                                                <button class="btn-modifica" onclick="modificaPost('<%= post.getId() %>')">Modifica</button>
                                                <button class="btn-elimina-post" onclick="eliminaPost('<%= post.getId() %>')">Elimina</button>
                                            </div>
                                        </div>
                                        <div class="post-dettagli">
                                            <p><strong>Evento:</strong><%= data %> - <%= orario %> @ <%= luogo %></p>
                                            <p><strong>Disponibilità Mezzo:</strong> <%= post.getDisponibilitaMezzo() %></p>
                                            <p><strong>Partecipanti Max:</strong> <%= post.getPartecipantiMax() %></p>
                                            <p><strong>Età Gruppo:</strong> <%= post.getEtaGruppo().getSogliaInferiore() %> - <%= post.getEtaGruppo().getSogliaSuperiore() %> anni</p>
                                            <p><strong>Genere Gruppo:</strong> <%= post.getGenereGruppo() %></p>
                                        </div>

                                        <!-- Adesioni degli utenti -->
                                        <ul class="adesioni-list">
                                            <h4>Adesioni:</h4>
                                            <% 
                                                List<Utente> adesioni = post.getUtentiAderenti();
                                                if (adesioni != null && !adesioni.isEmpty()) {
                                                    for (Utente adesione : adesioni) {
                                            %>
                                                        <li class="adesione-item">
                                                            <span>• <%= adesione.getUsername() %></span>
                                                            <button class="btn-elimina-adesione" onclick="eliminaAdesione('<%= post.getId() %>', '<%= adesione.getUsername() %>')">Elimina Adesione</button>
                                                        </li>
                                            <% 
                                                    }
                                                } else {
                                            %>
                                                <li>Nessuna adesione presente.</li>
                                            <% } %>
                                        </ul>
                                    </div>
                                </li>
                    <% 
                            }
                        } else {
                    %>
                        <li>Nessun post pubblicato disponibile.</li>
                    <% } %>
                </ul>
            </section>

            <!-- Visualizzazione dello Storico delle Recensioni -->
            <section class="section" id="storico-recensioni-section">
                <h3>Recensioni</h3>
                <ul id="storico-recensioni">
                    <% 
                        List<Recensione> storicoRecensioni = utente.getRecensioni();
                        if (storicoRecensioni != null && !storicoRecensioni.isEmpty()) {
                            for (Recensione rec : storicoRecensioni) {
                    %>
                                <li class="recensione-item">
                                    <strong><%= rec.getValutazione() %>:</strong> <%= rec.getCommento() %>
                                </li>
                    <% 
                            }
                        } else {
                    %>
                        <li>Nessuna recensione disponibile.</li>
                    <% } %>
                </ul>
            </section>
        </main>

        <!-- Pulsante per tornare alla pagina iniziale -->
        <footer>
            <button class="btn-back" onclick="window.location.href='WelcomeView.html'">Torna alla pagina iniziale</button>
        </footer>
    </div>

    <script src="scripts/profilo.js"></script>
</body>
</html>
