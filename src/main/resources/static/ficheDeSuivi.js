//Récupérer toutes les "textarea"
let textarea = document.getElementsByTagName('textarea');
//Attribuer la fonction "autosize" à l'appui d'une touche sur toutes les "textaea"
for(let t of textarea) {
    t.addEventListener('keydown', autosize);
}
//Fonction pour ajuster la taille d'un élément à son contenu textuel          
function autosize(){
  let el = this;
  setTimeout(function(){ 
    el.style.cssText = 'height:auto; padding:0';
    el.style.cssText = 'height:' + el.scrollHeight + 'px';
  },0);
}

//Attribuer la fonction "masquer" au click sur tous les boutons "+" et "-"
for(let i = 2; i <= 4; i++) {
    document.getElementById("masquer" + i).addEventListener("click", masquer);
}
//Fonction pour masquer une colonne du tableau afin d'améliorer la lisibilité
function masquer(event) {
    //Savoir quel bouton a déclenché la fonction
    let id = event.target.id;
    let bouton = document.getElementById(id);
    //Chaque bouton servant à masquer
    let bouton2 = document.getElementById("masquer2");
    let bouton3 = document.getElementById("masquer3");
    let bouton4 = document.getElementById("masquer4");
    //Vérifier si c'est un bouton "-" (= la colonne a une taille normale)
    if(bouton.value === "-") {
        //Savoir quelle colonne doit être masquée
        if(id === "masquer2") {
            //Rétrécir la colonne choisie et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "10%";
            document.getElementById("col" + 3).style.width = "45%";
            document.getElementById("col" + 4).style.width = "45%";
            //Attribuer la valeur "-" aux boutons des colonnes agrandies et "+" au bouton de la colonne rétrécie
            bouton2.value="+";
            bouton3.value="-";
            bouton4.value="-";
        }
        if(id === "masquer3") {
            //Rétrécir la colonne choisie et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "45%";
            document.getElementById("col" + 3).style.width = "10%";
            document.getElementById("col" + 4).style.width = "45%";
            //Attribuer la valeur "-" aux boutons des colonnes agrandies et "+" au bouton de la colonne rétrécie
            bouton2.value="-";
            bouton3.value="+";
            bouton4.value="-";
        }
        if(id === "masquer4") {
            //Rétrécir la colonne choisie et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "45%";
            document.getElementById("col" + 3).style.width = "45%";
            document.getElementById("col" + 4).style.width = "10%";
            //Attribuer la valeur "-" aux boutons des colonnes agrandies et "+" au bouton de la colonne rétrécie
            bouton2.value="-";
            bouton3.value="-";
            bouton4.value="+";
        } 
    }
    //Sinon, c'est un bouton "+" (la colonne est rétrécie donc il faut l'agrandir et rétrécir les autres)
    else{
        //Donner aux trois colonnes une largeur standard
        document.getElementById("col" + 2).style.width = "33%";
        document.getElementById("col" + 3).style.width = "33%";
        document.getElementById("col" + 4).style.width = "34%";
        //Attribuer la valeur "-" aux boutons (car toutes les colonnes ont une largeur standard)
        bouton2.value="-";
        bouton3.value="-";
        bouton4.value="-";
    }
}
document.getElementById("boutonImpression").addEventListener("click", imprimer);

function imprimer() {
    //Donner aux trois colonnes une largeur standard
    document.getElementById("col" + 2).style.width = "33%";
    document.getElementById("col" + 3).style.width = "33%";
    document.getElementById("col" + 4).style.width = "34%";
    //Imprimer la fiche
    window.print();
}