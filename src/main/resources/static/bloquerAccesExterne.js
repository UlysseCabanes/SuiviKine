////------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Bloquer l'accès à la page si l'utilisateur n'est pas passé par la page de login
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
docref = document.referrer;
if( docref.length === 0 ){
    // rediriger
    window.location.replace( "http://localhost:8080/login" );
}