package jardinsdeflore.suivikine.repository;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import jardinsdeflore.suivikine.entity.Resident;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ResidentRepository extends CrudRepository<Resident, ResidentId> {
    Iterable<Resident> findByEquipeKine(int idEquipe);

     @Query(value = "SELECT * "
             + "FROM Resident "
             + "WHERE nom = :nom "
             + "AND prenom = :prenom "
             + "AND date_naissance = :dateNaissance", nativeQuery = true)
    Resident findResidentByNomAndPrenomAndDateNaissance(
            @Param("nom") String nom, 
            @Param("prenom") String prenom,
            @Param("dateNaissance") Date dateNaissance);
}