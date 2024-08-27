<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Web di Chat</title>
    <link rel="stylesheet" href="styles/chat.css">
    <script type="text/javascript" src="scripts/chat.js"></script>
</head>
<body>
    <div class="container">
        <div class="chat-list">
            <div class="chat-item">Evento: Claudio Bisio</div>
            <div class="chat-item">Evento: Sapobully 4L</div>

            <div class="home-button-container">
                <button class="home-button"><a href="WelcomeView.html">Schermata Home</a></button>
            </div>
            <!-- Aggiungi altre chat se necessario -->
        </div>
        <div class="chat-window">
            <div class="chat-header">
                <h2>Evento: Claudio Bisio</h2>
            </div>
            <div class="chat-messages">
                <div class="message sent">
                    Ciao, a che ora ci incontriamo per il concerto?
                    <div class="username">Marco Pisellonio</div>
                </div>
                <div class="message received">
                    Facciamo alle 12 in centro?
                    <div class="username">Lilith</div>
                </div>
                <div class="message received">
                    Si, per me è perfetto
                    <div class="username">aragorn</div>
                </div>
                <div class="message sent">
                    Benissimo, a domani!!
                    <div class="username">Marco Pisellonio</div>
                </div>
                <!-- Aggiungi altri messaggi se necessario -->
            </div>
            <div class="chat-input">
                <input type="text" placeholder="Scrivi un messaggio...">
                <button onclick="myFunction();">Invia</button>
            </div>
        </div>
    </div>
</body>
</html>