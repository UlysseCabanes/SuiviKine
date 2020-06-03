package jardinsdeflore.suivikine.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Equipe_kine")
public class EquipeKine implements Serializable{
    
    @Id
    @Column(name = "id_equipe_kine")
    private int idEquipeKine;
    
    private String login;
    
    private String mdp;

    //Constructeurs
    public EquipeKine() {
    }

    public EquipeKine(int idEquipeKine, String login, String mdp) {
        this.idEquipeKine = idEquipeKine;
        this.login = login;
        this.mdp = mdp;
    }

    //Getter
    public int getIdEquipeKine() {
        return idEquipeKine;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }  
}