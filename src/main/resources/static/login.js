//Emp�cher l'utilisateur de revenir en arri�re depuis la page e login
//Permet de bloquer l'acc�s aux autres sessions par l'utilisateur
history.pushState(null, null, location.href);
window.onpopstate = function () {
    history.go(1);
};