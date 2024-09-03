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
                        <th>Id Recensione</th>
                        <th>Utente Recensito</th>
                        <th>Valutazione</th>
                        <th>Commento</th>
                        <th>Rimozione Recensione</th>
                    </tr>
                </thead>
                <tbody>
                <% 
                
                	List<Utente> utenti = (List<Utente>) application.getAttribute("utenti");
                
                	//Codice nel caso di rimozione di adesione
                	if (request.getParameter("remove") != null && request.getParameter("remove").equals("ok")){
                		String idRec = request.getParameter("description"); //idRec da rimuovere
                		Recensione recDaRimuovere = new Recensione();
                		Utente userRecensito = new Utente(); //utente recensito da recensione da rimuovere
                
                		for (Utente utente: utenti){
                			for(Recensione rec: utente.getRecensioni()){
	                			if(rec.getId().equals(idRec)){ //identifico post da rimuovere
	                				recDaRimuovere = rec;
	                				userRecensito= utente; 
	                				break;
	                			}
                			}
                		}
                		
                		userRecensito.getRecensioni().remove(recDaRimuovere);
                	}
                
                	for(Utente utente: utenti){
                		for(Recensione recensione: utente.getRecensioni()){
                %>
                     	<tr>
                     		<td> <%= recensione.getId() %></td>
	                        <td> <%= utente.getUsername() %></td>
	                        <td> <%= recensione.getValutazione().toString() %></td>
	                        <td> <%= recensione.getCommento() %> </td>
	                        <td>
	                        	<a href="?remove=ok&description=<%=recensione.getId()%>">
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