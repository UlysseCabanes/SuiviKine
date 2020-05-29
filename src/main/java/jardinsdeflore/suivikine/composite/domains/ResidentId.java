/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jardinsdeflore.suivikine.composite.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ulyss
 */
public class ResidentId implements Serializable{
    
    private String nom;
    private String prenom;
    private Date date_naissance;
    
    public ResidentId() {
    }

    public ResidentId(String nom, String prenom, Date date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nom);
        hash = 79 * hash + Objects.hashCode(this.prenom);
        hash = 79 * hash + Objects.hashCode(this.date_naissance);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResidentId other = (ResidentId) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.date_naissance, other.date_naissance)) {
            return false;
        }
        return true;
    }
    
    
}
