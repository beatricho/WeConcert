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
    <title>WeConcert - Pubblica</title>
    <link rel="stylesheet" href="styles/pubblica.css">
</head>
<body>
    <div class="pubblica-page">
        <header>
            <img src="images/logo.png" alt="WeConcert Logo" class="logo">
        </header>
        <main>
            <h2>Pubblica un Nuovo Post</h2>
            <form action="pubblica" method="post">             
                
                <label for="evento">Seleziona Evento:</label>
                <select id="evento" name="evento" required>
                    <% 
                        List<Evento> eventi = (List<Evento>) application.getAttribute("eventi");
                        if (eventi != null && !eventi.isEmpty()) {
                            for (Evento evento : eventi) {
                    %>
                                <option value="<%= evento.getDescrizione() %>"><%= evento.getDescrizione() %> - <%= evento.getData() %> @ <%= evento.getLuogo() %></option>
                    <% 
                            }
                        } else {
                    %>
                        <option value="Scegli_successivamente">Scegli successivamente</option>
                    <% } %>
                </select>
                
                <label for="descrizione">Descrizione:</label>
                <textarea id="descrizione" name="descrizione" required></textarea>
				
                <label for="disponibilitaMezzo">Disponibilità Mezzo:</label>
                <select id="disponibilitaMezzo" name="disponibilitaMezzo" required>
                    <option value="HO">Ho un mezzo</option>
                    <option value="NON_HO_E_CERCO">Non ho un mezzo e cerco</option>
                    <option value="NON_HO_E_NON_CERCO">Non ho un mezzo e non cerco</option>
                </select>

                <label for="etaMin">Età Minima:</label>
                <input type="number" id="etaMin" name="etaMin" required min="1" max="120">

                <label for="etaMax">Età Massima:</label>
                <input type="number" id="etaMax" name="etaMax" required min="1" max="120">

                <label for="genereGruppo">Genere del Gruppo:</label>
                <select id="genereGruppo" name="genereGruppo" required>
                    <option value="MISTO">Misto</option>
                    <option value="MASCHILE">Maschile</option>
                    <option value="FEMMINILE">Femminile</option>
                </select>

                <label for="partecipantiMax">Partecipanti Massimi:</label>
                <input type="number" id="partecipantiMax" name="partecipantiMax" required min="1">

                <button type="submit">Pubblica</button>
            </form>
        </main>

        <footer>
            <button class="btn-back" onclick="window.location.href='WelcomeView.html'">Torna alla pagina iniziale</button>
        </footer>
    </div>

    <script src="scripts/pubblica.js"></script>
</body>
</html>
