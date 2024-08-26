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

                <form action="autenticazione" method="post">
                    <label>
                        <span>Username</span>
                        <input type="text" name="username" />
                    </label>
                    <label>
                        <span>Password</span>
                        <input type="password" name="password"/>
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
                %>

            </div>
            <div class="sub-cont">
                <div class="img">
                    <div class="img__text m--up">
                    
                        <h3>Non hai un profilo? Registrati subito!<h3>
                    </div>
                    <div class="img__text m--in">
                    
                        <h3>Se già in possesso di un profilo, esegui il login.<h3>
                    </div>
                    <div class="img__btn">
                        <span class="m--up">Registrati</span>
                        <span class="m--in">Login</span>
                    </div>
                </div>
                <div class="form sign-up">
                    <h2>Crea il tuo profilo</h2>
                    <form action="registrazione" method="post">
                        <label>
                            <span>Nome e cognome</span>
                            <input type="text" name="nome_cognome" />
                        </label>
                        <label>
                            <span>Genere</span>
                            <select name="genere">
                                <option value='Uomo'>Uomo</option>
                                <option value='Donna'>Donna</option>
                            </select>
                        </label>
                        <label>
                            <span>Data di Nascita</span>
                            <input type="date" name="nascita"/>
                        </label>
                        <label>
                            <span>Username</span>
                            <input type="text" name="username"/>
                        </label>
                        <label>
                            <span>Password</span>
                            <input type="password" name="password" />
                        </label>
                        <button type="submit" class="submit">Registrati</button>
                    </form>

                    <%
                        Integer reg = (Integer) session.getAttribute("reg");
                        if(reg !=null){
                            switch(reg){
                                case 1:{
                                    %>
                                        Uno dei campi e' vuoto...Riprovare
                                    <% 
                                }break;

                                case 2:{
                                    %>
                                        Username gia' registrato...Riprovare
                                    <% 
                                }break;

                                case 3:{
                                    %>
                                        Registrazione avvenuta con successo
                                    <% 
                                }break;
                                
                            }
                        }
                    %>

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