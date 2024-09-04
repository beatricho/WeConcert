<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<!-- pagina per la gestione di errori -->
<%@ page errorPage="./errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->

<%@ page import="java.util.*"%>
<%@ page import="it.unibo.tw.web.beans.*"%>

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WeConcert - Eliminazione Post</title>
    <link rel="stylesheet" href="styles/adesioni.css">
</head>
<body>
<div class="container">
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Id Post</th>
                        <th>Evento</th>
                        <th>Utente Pubblicante</th>
                        <th>Descrizione</th>
                        <th>Rimozione Post</th>
                    </tr>
                </thead>
                <tbody>
                <% 
                
                	List<Utente> utenti = (List<Utente>) application.getAttribute("utenti");
                
                	//Codice nel caso di rimozione di adesione
                	if (request.getParameter("remove") != null && request.getParameter("remove").equals("ok")){
                		String idPost = request.getParameter("description"); //idPost da rimuovere
                		Post postDaRimuovere = new Post();
                		Utente userPubblicante = new Utente(); //utente che ha pubblicato il post da rimuovere
                
                		for (Utente utente: utenti){
                			for(Post post: utente.getPostPubblicati()){
	                			if(post.getId().equals(idPost)){ //identifico post da rimuovere
	                				postDaRimuovere = post;
	                				userPubblicante = utente; 
	                				break;
	                			}
                			}
                		}
                		
                		userPubblicante.getAdesioni().remove(postDaRimuovere);
                	}
                
                	for(Utente utente: utenti){
                		for(Post post: utente.getPostPubblicati()){
                %>
                     	<tr>
	                        <td> <%= post.getId() %></td>
	                        <td>
	                            <div><%= post.getEvento().getDescrizione() %></div>
	                            <div><%= post.getEvento().getData() %></div>
	                            <div> <%= post.getEvento().getOrario() %></div>
	                            <div> <%= post.getEvento().getLuogo() %></div>
	                        </td>
	                        <td> <%= post.getUtentePubblicante().getUsername() %></td>
	                        <td> <%= post.getDescrizione() %> </td>
	                        <td>
	                        	<a href="?remove=ok&description=<%=post.getId()%>">
								<img src="./images/remove.gif" alt="remove"/></a>
							</td>
                    	</tr>   
                <% 
                		}
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="home-button-container">
            <button class="home-button"><a href="adminPage.html">Schermata Home</a></button>
        </div>
</body>
</html>