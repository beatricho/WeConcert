<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->

<%@ page import="java.util.*"%>
<%@ page import="it.unibo.tw.web.beans.*"%>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WeConcert - Chat</title>
    <link rel="stylesheet" href="styles/chat.css">
    <script type="text/javascript" src="scripts/chat.js"></script>
</head>
<body>
	
    <div class="container">
        <div class="chat-list">
        		
        		<!-- Post: vengono presi sia da i post pubblicati che da quelli a cui si è aderito -->
        	<%
        			Utente utente = (Utente) session.getAttribute("utenteCorrente");
        			
        			if(utente != null){
        			
                    	for(Post post: utente.getPostPubblicati()){
            %>
                    		<div class="chat-item" onclick="apparizione('<%=post.getId()%>')">Evento: <%=post.getEvento().getDescrizione()%></div>   
            <% 
                		}
		
                    	for(Post post: utente.getAdesioni()){
            %>
                    		<div class="chat-item" onclick="apparizione('<%=post.getId()%>')">Evento: <%=post.getEvento().getDescrizione()%></div>   
            <% 
                		}
        			}
		    %>
		   
            <div class="home-button-container">
                <button class="btn-back" onclick="window.location.href='WelcomeView.html'">Torna alla pagina iniziale</button>
            </div>
            
        </div>
  
  		<!-- Vengono disposti tutti i riquadri della chat ma invisibili. Diventa visibile solo quello che si clicca sul banner -->
  
        	<%
                    for(Post post: utente.getPostPubblicati()){
            %>
            		<div class="chat-window" style="display:none;" id="<%=post.getId()%>">
                    	<div class="chat-header">
                			<h2>Evento: <%=post.getEvento().getDescrizione()%></h2>
            			</div>
            			<div class="chat-messages" id="container">
                			<!-- I messaggi verranno aggiunti tramite javascript -->
            			</div>
            			<div class="chat-input">
                			<input type="text" id="messaggio" placeholder="Scrivi un messaggio...">
                			<button onclick="myFunction('<%=utente.getUsername()%>', '<%=post.getId()%>');">Invia</button>
            			</div>
        			</div>
            <% 
                	}
		
                    for(Post post: utente.getAdesioni()){
            %>
                    <div class="chat-window" style="display:none;" id="<%=post.getId()%>">
                    	<div class="chat-header">
                			<h2>Evento: <%=post.getEvento().getDescrizione()%></h2>
            			</div>
            			<div class="chat-messages" id="container">
                			<!-- I messaggi verranno aggiunti tramite javascript -->
            			</div>
            			<div class="chat-input">
                			<input type="text" id="messaggio" placeholder="Scrivi un messaggio...">
                			<button onclick="myFunction('<%=utente.getUsername()%>', '<%=post.getId()%>');">Invia</button>
            			</div>
        			</div>
            <% 
                	}
		    %>
               
    </div>
</body>
</html>