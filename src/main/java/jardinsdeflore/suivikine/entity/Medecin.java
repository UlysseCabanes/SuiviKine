package jardinsdeflore.suivikine.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medecin")
public class Medecin implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_medecin")
    private int idMedecin;
    
    @Column(name = "nom")
    private String nom;

    //Constructeurs
    public Medecin() {
    }

    public Medecin(String nom) {
        this.nom = nom;
    }

    //Getter
    public int getIdMedecin() {
        return idMedecin;
    }

    public String getNom() {
        return nom;
    }

    //Setter
    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}