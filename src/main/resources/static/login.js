//Empêcher l'utilisateur de revenir en arrière depuis la page e login
//Permet de bloquer l'accès aux autres sessions par l'utilisateur
history.pushState(null, null, location.href);
window.onpopstate = function () {
    history.go(1);
};