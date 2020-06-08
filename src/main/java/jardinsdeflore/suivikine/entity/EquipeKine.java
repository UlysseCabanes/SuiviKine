package jardinsdeflore.suivikine.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Equipe_kine", uniqueConstraints = {@UniqueConstraint(columnNames="login"), @UniqueConstraint(columnNames="mdp")})
public class EquipeKine implements Serializable{
    
    @Id
    @Column(name = "id_equipe_kine")
    private int idEquipeKine;
    
    @Column(unique = true)
    private String login;
    
    @Column(unique = true)
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