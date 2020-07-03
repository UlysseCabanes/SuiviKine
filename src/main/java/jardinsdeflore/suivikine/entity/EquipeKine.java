package jardinsdeflore.suivikine.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Equipe_kine", uniqueConstraints = {@UniqueConstraint(columnNames="login"), @UniqueConstraint(columnNames="mdp")})
public class EquipeKine implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_equipe_kine")
    private int idEquipeKine;
    
    @Column(name = "nom", unique = true)
    private String nom;
    
    @Column(name = "login", unique = true)
    private String login;
    
    @Column(name = "mdp", unique = true)
    private String mdp;

    //Constructeurs
    public EquipeKine() {
    }

    public EquipeKine(String nom, String login, String mdp) {
        this.nom = nom;
        this.login = login;
        this.mdp = mdp;
    }

    //Getter
    public int getIdEquipeKine() {
        return idEquipeKine;
    }
    
    public String getNom() {
        return nom;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    //Setter
    public void setIdEquipeKine(int idEquipeKine) {
        this.idEquipeKine = idEquipeKine;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }  
}