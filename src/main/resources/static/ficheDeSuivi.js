//R�cup�rer toutes les "textarea"
let textarea = document.getElementsByTagName('textarea');
//Attribuer la fonction "autosize" � l'appui d'une touche sur toutes les "textaea"
for(let t of textarea) {
    t.addEventListener('keydown', autosize);
}
//Fonction pour ajuster la taille d'un �l�ment � son contenu textuel          
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
//Fonction pour masquer une colonne du tableau afin d'am�liorer la lisibilit�
function masquer(event) {
    //Savoir quel bouton a d�clench� la fonction
    let id = event.target.id;
    let bouton = document.getElementById(id);
    //Chaque bouton servant � masquer
    let bouton2 = document.getElementById("masquer2");
    let bouton3 = document.getElementById("masquer3");
    let bouton4 = document.getElementById("masquer4");
    //V�rifier si c'est un bouton "-" (= la colonne a une taille normale)
    if(bouton.value === "-") {
        //Savoir quelle colonne doit �tre masqu�e
        if(id === "masquer2") {
            //R�tr�cir la colonne choisie et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "10%";
            document.getElementById("col" + 3).style.width = "45%";
            document.getElementById("col" + 4).style.width = "45%";
            //Attribuer la valeur "-" aux boutons des colonnes agrandies et "+" au bouton de la colonne r�tr�cie
            bouton2.value="+";
            bouton3.value="-";
            bouton4.value="-";
        }
        if(id === "masquer3") {
            //R�tr�cir la colonne choisie et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "45%";
            document.getElementById("col" + 3).style.width = "10%";
            document.getElementById("col" + 4).style.width = "45%";
            //Attribuer la valeur "-" aux boutons des colonnes agrandies et "+" au bouton de la colonne r�tr�cie
            bouton2.value="-";
            bouton3.value="+";
            bouton4.value="-";
        }
        if(id === "masquer4") {
            //R�tr�cir la colonne choisie et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "45%";
            document.getElementById("col" + 3).style.width = "45%";
            document.getElementById("col" + 4).style.width = "10%";
            //Attribuer la valeur "-" aux boutons des colonnes agrandies et "+" au bouton de la colonne r�tr�cie
            bouton2.value="-";
            bouton3.value="-";
            bouton4.value="+";
        } 
    }
    //Sinon, c'est un bouton "+" (la colonne est r�tr�cie donc il faut l'agrandir et r�tr�cir les autres)
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