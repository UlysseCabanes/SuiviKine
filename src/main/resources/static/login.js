//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Emp�cher l'utilisateur de revenir en arri�re depuis la page de login
//Permet de bloquer l'acc�s aux autres sessions par l'utilisateur
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Ajouter la page actuelle (login) � l'historique de navigation
    history.pushState(null, null, location.href);
    //Lorsque l'on clique sur la fl�che de retour, le navigateur va chercher la derni�re page 
    //de l'historique (qui est maintenant la page actuelle) et la charge
    window.onpopstate = function () {
        history.go(1);
    };