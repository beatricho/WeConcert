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
    <title>WeConcert - Eliminazione Utenti</title>
    <link rel="stylesheet" href="styles/adesioni.css">
</head>
<body>
<div class="container">
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Nome</th>
                        <th>Cognome</th>
                        <th>Data Nascita</th>
                        <th>Genere</th>
                        <th>Rimozione Utente</th>
                    </tr>
                </thead>
                <tbody>
                <% 
                
                	List<Utente> utenti = (List<Utente>) application.getAttribute("utenti");
                
                	//Codice nel caso di rimozione di adesione
                	if (request.getParameter("remove") != null && request.getParameter("remove").equals("ok")){
                		String username = request.getParameter("description"); //username dell'utente da rimuovere
                		Utente utenteDaRimuovere = new Utente();
                
                		for (Utente utente: utenti){
                			if(utente.getUsername().equals(username)){ //identifico utente da rimuovere
                				utenteDaRimuovere = utente;
                				break;
                			}
                		}
                		
                		utenti.remove(utenteDaRimuovere); //rimozione
                	}
                
                	for(Utente utente: utenti){
                		if(!utente.getUsername().equals("admin")){
                %>
                     	<tr>
	                        <td> <%= utente.getUsername() %></td>
	                        <td> <%= utente.getNome() %></td>
	                        <td> <%= utente.getCognome() %> </td>
	                        <td> <%= utente.getNascita() %></td>
	                        <td> <%= utente.getGenere().toString() %></td>
	                        <td>
	                        	<a href="?remove=ok&description=<%=utente.getUsername()%>">
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