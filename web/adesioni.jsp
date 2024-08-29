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
    <title>WeConcert - Adesioni</title>
    <link rel="stylesheet" href="styles/adesioni.css">
</head>
<body>
<div class="container">
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Evento</th>
                        <th>Utente Pubblicante</th>
                        <th>Descrizione</th>
                        <th>Età Gruppo</th>
                        <th>Genere Gruppo</th>
                        <th>Disponibilità Mezzo</th>
                        <th>Rimozione Adesione</th>
                    </tr>
                </thead>
                <tbody>
                <% 
                
                	Utente utente = (Utente) session.getAttribute("utenteCorrente");
                
                	//Codice nel caso di rimozione di adesione
                	if (request.getParameter("remove") != null && request.getParameter("remove").equals("ok")){
                		String idPostDaRimuovere = request.getParameter("description"); //id del post nella richiesta
                		Post postDaRimuovere = new Post();
                
                		for (Post post: utente.getAdesioni()){
                			if(post.getId().equals(idPostDaRimuovere)){ //identifico il post da rimuovere
                				postDaRimuovere = post;
                				break;
                			}
                		}
                		
                		utente.getAdesioni().remove(postDaRimuovere); //rimozione
                	}
                
                	for(Post post: utente.getAdesioni()){
                %>
                     	<tr>
	                        <td>
	                            <div><%= post.getEvento().getDescrizione() %></div>
	                            <div><%= post.getEvento().getData() %></div>
	                            <div> <%= post.getEvento().getOrario() %></div>
	                            <div> <%= post.getEvento().getLuogo() %></div>
	                        </td>
	                        <td> <%= post.getUtentePubblicante().getUsername() %></td>
	                        <td> <%= post.getDescrizione() %></td>
	                        <td> <%= post.getEtaGruppo().getSogliaInferiore() %> - <%= post.getEtaGruppo().getSogliaSuperiore() %> </td>
	                        <td> <%= post.getGenereGruppo().toString() %></td>
	                        <td> <%= post.getDisponibilitaMezzo().toString() %></td>
	                        <td>
	                        	<a href="?remove=ok&description=<%=post.getId()%>">
								<img src="./images/remove.gif" alt="remove"/></a>
							</td>
                    	</tr>   
                <% 
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="home-button-container">
            <button class="home-button"><a href="WelcomeView.html">Schermata Home</a></button>
        </div>
</body>
</html>