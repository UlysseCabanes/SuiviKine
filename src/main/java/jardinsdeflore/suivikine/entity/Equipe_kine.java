package jardinsdeflore.suivikine.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipe_kine implements Serializable{
    
    @Id
    private int id_equipe_kine;
    
    private String login;
    
    private String mdp;

    //Constructeurs
    public Equipe_kine() {
    }

    public Equipe_kine(int id_equipe_kine, String login, String mdp) {
        this.id_equipe_kine = id_equipe_kine;
        this.login = login;
        this.mdp = mdp;
    }

    //Getter
    public int getId_equipe_kine() {
        return id_equipe_kine;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    //Setter
    public void setId_equipe_kine(int id_equipe_kine) {
        this.id_equipe_kine = id_equipe_kine;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }  
}