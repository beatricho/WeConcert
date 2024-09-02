<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html>

    <head>
        <title>Pagina login</title>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="StyleSheet" href="styles/login.css" type="text/css" media="all" />
    </head>

    <body background="images/concerto2.jpg">
        <br>
        <br>
        <div class="cont">
            <div class="form sign-in">
                <h2>Benvenuto</h2>
                
                <script>
			        function validateFormLog() {
			            // Ottieni il valore del campo di testo
			            var user = document.getElementById("username").value;
						var pwd = document.getElementById("password").value;
			            // Controlla se il campo è vuoto
			            if (user.trim() === "" || pwd.trim() === "") {
			                // Mostra un alert
			                alert("I campi testo non possono essere vuoti per eseguire il login!");
			                return false; // Previene l'invio del form
			            }
			
			            return true; // Permette l'invio del form
			        }
    			</script>

                <form action="autenticazione" method="post" onsubmit="return validateFormLog()">
                    <label>
                        <span>Username</span>
                        <input type="text" id="username" name="username" />
                    </label>
                    <label>
                        <span>Password</span>
                        <input type="password" id="password" name="password"/>
                    </label>
                    <button type="submit" class="submit">Login</button>
                </form>

                <%
                    Integer log = (Integer) session.getAttribute("log");
                    if(log !=null){
                        switch(log){
                            case 1:{
                                %>
                                    <br>
                                    Utente non registrato <br> <br>
                                    Riprovare <br> <br>
                                <% 
                            }break;

                            case 2:{
                                %>
                                    <br>
                                    Password sbagliata <br> <br>
                                    Riprovare <br> <br>
                                <% 
                            }break;
                          
                        }
                    }
                    
                    session.removeAttribute("log");
                    
                    Integer reg = (Integer) session.getAttribute("reg");
                    if(reg !=null){
                        switch(reg){

                            case 1:{
                                %>
                                    Username gia' registrato...Riprovare
                                <% 
                            }break;

                            case 2:{
                                %>
                                    Registrazione avvenuta con successo
                                <% 
                            }break;
                            
                        }
                    }
                    
                    session.removeAttribute("reg");
                %>

            </div>
            <div class="sub-cont">
                <div class="img">
                    <div class="img__text m--up">
                    
                        <h3>Non hai un profilo? Registrati subito!<h3>
                    </div>
                    <div class="img__text m--in">
                    
                        <h3>Se giÃ  in possesso di un profilo, esegui il login.<h3>
                    </div>
                    <div class="img__btn">
                        <span class="m--up">Registrati</span>
                        <span class="m--in">Login</span>
                    </div>
                </div>
                <div class="form sign-up">
                    <h2>Crea il tuo profilo</h2>
                    
                    <script>
				        function validateFormReg() {
				            // Ottieni il valore del campo di testo
				            var nome_cogn = document.getElementById("nome_cognome").value;
							var genere = document.getElementById("genere").value;
							var nascita = document.getElementById("nascita").value;
							var user = document.getElementById("usernameR").value;
							var pwd = document.getElementById("passwordR").value;
							
				            // Controlla se il campo è vuoto
				            if (nome_cogn.trim() === "" || genere.trim() === "" || nascita.trim() === "" || user.trim() === "" || pwd.trim() === "") {
				                // Mostra un alert
				                alert("I campi testo non possono essere vuoti per eseguire la registrazione!");
				                return false; // Previene l'invio del form
				            }
				
				            return true; // Permette l'invio del form
				        }
    				</script>
                    
                    <form action="registrazione" method="get" onsubmit="return validateFormReg()">
                        <label>
                            <span>Nome e cognome</span>
                            <input type="text" name="nome_cognome" id="nome_cognome" />
                        </label>
                        <label>
                            <span>Genere</span>
                            <select name="genere" id="genere">
                                <option value='Uomo'>UOMO</option>
                                <option value='Donna'>DONNA</option>
                            </select>
                        </label>
                        <label>
                            <span>Data di Nascita</span>
                            <input type="date" name="nascita" id="nascita"/>
                        </label>
                        <label>
                            <span>Username</span>
                            <input type="text" name="username" id="usernameR"/>
                        </label>
                        <label>
                            <span>Password</span>
                            <input type="password" name="password" id="passwordR"/>
                        </label>
                        <button type="submit" class="submit">Registrati</button>
                    </form>

                </div>
            </div>
        </div>

        <script>
            document.querySelector('.img__btn').addEventListener('click', function() {
                document.querySelector('.cont').classList.toggle('s--signup');
            });
        </script>
        
    </body>

</html>