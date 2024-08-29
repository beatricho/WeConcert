<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- pagina per la gestione di errori -->
<%@ page errorPage="./errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->

<%@ page import="java.util.*"%>
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

            <!-- Visualizzazione dei Post Pubblicati -->
            <section class="section" id="post-pubblicati-section">
                <h3>Post Pubblicati</h3>
                <ul id="post-pubblicati">
                    <% 
                        List<Post> postPubblicati = (List<Post>) application.getAttribute("postPubblicati");
                        if (postPubblicati != null && !postPubblicati.isEmpty()) {
                            for (Post post : postPubblicati) {
                    %>
                                <li class="post-item">
                                    <span class="post-descrizione"><%= post.getDescrizione() %></span>
                                    <button class="btn-modifica" onclick="modificaPost('<%= post.getId() %>')">Modifica</button>
                                    <button class="btn-elimina" onclick="eliminaPost('<%= post.getId() %>')">Elimina</button>
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
                        List<Recensione> storicoRecensioni = (List<Recensione>) application.getAttribute("recensioni");
                        if (storicoRecensioni != null && !storicoRecensioni.isEmpty()) {
                            for (Recensione rec : storicoRecensioni) {
                    %>
                                <li class="recensione-item"><strong><%= rec.getValutazione() %>:</strong> <%= rec.getCommento() %></li>
                    <% 
                            }
                        } else {
                    %>
                        <li>Nessuna recensione disponibile.</li>
                    <% } %>
                </ul>
            </section>

            <!-- Visualizzazione delle Adesioni degli Altri Utenti -->
            <section class="section" id="adesioni-altri-utenti-section">
                <h3>Adesioni di Altri Utenti</h3>
                <ul id="adesioni-altri-utenti">
                    <% 
                        if (postPubblicati != null) {
                            for (Post post : postPubblicati) {
                                List<Utente> adesioni = post.getUtentiAderenti();
                                if (adesioni != null && !adesioni.isEmpty()) {
                                    for (Utente adesione : adesioni) {
                    %>
                                        <li class="adesione-item">
                                            <span><%= adesione.getUsername() %> ha aderito al post "<%= post.getDescrizione() %>"</span>
                                            <button class="btn-elimina" onclick="eliminaAdesione('<%= post.getId() %>', '<%= adesione.getUsername() %>')">Elimina Adesione</button>
                                        </li>
                    <% 
                                    }
                                }
                            }
                        } else {
                    %>
                        <li>Nessuna adesione disponibile.</li>
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
