//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Empêcher l'utilisateur de revenir en arrière depuis la page de login
//Permet de bloquer l'accès aux autres sessions par l'utilisateur
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Ajouter la page actuelle (login) à l'historique de navigation
    history.pushState(null, null, location.href);
    //Lorsque l'on clique sur la flèche de retour, le navigateur va chercher la dernière page 
    //de l'historique (qui est maintenant la page actuelle) et la charge
    window.onpopstate = function () {
        history.go(1);
    };