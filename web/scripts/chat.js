const socket = new WebSocket("ws://localhost:8080/WeConcert/actions");

socket.onmessage =  function (event){
	var message = JSON.parse(event.data);
	if(message.valid){ //Il messaggio è valido
		
    }else{//messaggio non valido
		alert("hai superato il limite massimo di richieste per sessione");
	}
	
}

socket.onopen = function (event) {
	console.log("connessione aperta");
}


function send( data) {
    var json = JSON.stringify(data);
    socket.send(json);
}

function myFunction(){
	var operationReq = {};
	operationReq.valore = valore;
	operationReq.posizione = posizione;
	send(operationReq);
}