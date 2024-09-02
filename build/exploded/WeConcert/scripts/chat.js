const socket = new WebSocket("ws://localhost:8080/WeConcert/actions");myFunction
var username;

socket.onmessage =  function (event){
	var message = JSON.parse(event.data); //ricavo il messaggio dal formato JSON
	var newMess = document.createElement("div"); //creo l'elemento che deve essere inserito all'interno della chat
	newMess.innerText = message.testo;
	
	//distinzione necessaria per la grafica della chat
	if(message.sender === username){	//messaggio inviato
		newMess.className ="message sent";
		document.getElementById(message.chatId).querySelector("#container").appendChild(newMess);	//inserimento dell'elemento all'interno del riquadro della chat
	}else{	//messaggio ricevuto
		newMess.className ="message received";
		document.getElementById(message.chatId).querySelector("#container").appendChild(newMess);	//inserimento dell'elemento all'interno del riquadro della chat
		
		var sender = document.createElement("div"); //creo l'elemento che mostra l'username dell'utente nel caso di un messaggio ricevuto
		sender.innerText = message.sender;
		sender.className ="username";
		newMess.appendChild(sender);	//inserimento del sender all'interno del riquadro appena aggiunto
	}
}

socket.onopen = function (event) {
	console.log("connessione aperta");
}

function send( data) {
    var json = JSON.stringify(data);
    console.log(json);
    socket.send(json); //mando il messaggio in formato JSON
}

function myFunction(usernameCorrente, chatId){
	var messaggio = {};
	messaggio.testo = document.getElementById("messaggio").value;
	messaggio.sender = usernameCorrente;
	username = usernameCorrente;
	messaggio.chatId = chatId;
	send(messaggio);
}

function apparizione(chatId) {
    // Nasconde tutte le finestre di chat
    var chatWindows = document.getElementsByClassName("chat-window");
    for (var i = 0; i < chatWindows.length; i++) {
        chatWindows[i].style.display = "none";
    }
    
    // Mostra solo la finestra di chat selezionata
    var selectedChat = document.getElementById(chatId);
    if (selectedChat) {
        selectedChat.style.display = "block";
    }
}