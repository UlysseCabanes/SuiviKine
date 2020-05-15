//Récupérer toutes les "textarea"
let textarea = document.getElementsByTagName('textarea');
//Attribuer la fonction à l'appui d'une touche sur toutes les "textaea"
for(let t of textarea) {
    t.addEventListener('keydown', autosize);
}
//Fonction pour ajuster la taille d'un élément à son contenu textuel          
function autosize(){
  let el = this;
  setTimeout(function(){ 
    el.style.cssText = 'height:auto; padding:0';
    // for box-sizing other than "content-box" use:
    // el.style.cssText = '-moz-box-sizing:content-box';
    el.style.cssText = 'height:' + el.scrollHeight + 'px';
  },0);
}