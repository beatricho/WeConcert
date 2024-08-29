// Funzione per modificare la descrizione di un post
function modificaPost(postId) {
    const nuovaDescrizione = prompt('Inserisci nuova descrizione per il post:');
    if (nuovaDescrizione) {
        fetch('profilo', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ action: 'modificaDescrizionePost', postId: postId, nuovaDescrizione: nuovaDescrizione })
        }).then(response => {
            if (response.ok) {
                alert('Descrizione modificata con successo!');
                location.reload(); // Ricarica la pagina per mostrare i dati aggiornati
            } else {
				response.text().then(text => alert('Errore nella modifica del post: ' + text));
            }
        });
    }
}

// Funzione per eliminare un post
function eliminaPost(postId) {
    if (confirm('Sei sicuro di voler eliminare questo post?')) {
        fetch('profilo', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ action: 'eliminaPost', postId: postId })
        }).then(response => {
            if (response.ok) {
                alert('Post eliminato con successo!');
                location.reload(); // Ricarica la pagina per mostrare i dati aggiornati
            } else {
				response.text().then(text => alert('Errore nella modifica del post: ' + text));
            }
        });
    }
}

// Funzione per eliminare un'adesione
function eliminaAdesione(postId, username) {
    if (confirm(`Sei sicuro di voler eliminare l'adesione di ${username}?`)) {
        fetch('profilo', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ action: 'eliminaAdesione', postId: postId, username: username })
        }).then(response => {
            if (response.ok) {
                alert('Adesione eliminata con successo!');
                location.reload(); // Ricarica la pagina per mostrare i dati aggiornati
            } else {
				response.text().then(text => alert('Errore nella modifica del post: ' + text));
            }
        });
    }
}
