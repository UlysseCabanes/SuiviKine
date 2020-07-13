package jardinsdeflore.suivikine.repository;

import jardinsdeflore.suivikine.entity.Medecin;
import org.springframework.data.repository.CrudRepository;

public interface MedecinRepository extends CrudRepository<Medecin, Integer> {
    Iterable<Medecin> findByNom(String nom);
    
    Iterable<Medecin> findAllByOrderByNomAsc();
}