package jardinsdeflore.suivikine.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medecin implements Serializable{
    
    @Id
    private int id_medecin;
    
    private String nom;

    //Constructeurs
    public Medecin() {
    }

    public Medecin(String nom) {
        this.nom = nom;
    }

    //Getter
    public int getId_medecin() {
        return id_medecin;
    }

    public String getNom() {
        return nom;
    }

    //Setter
    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
