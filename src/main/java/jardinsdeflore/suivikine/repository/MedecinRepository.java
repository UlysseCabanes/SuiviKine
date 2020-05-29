package jardinsdeflore.suivikine.repository;

import jardinsdeflore.suivikine.entity.Medecin;
import org.springframework.data.repository.CrudRepository;

public interface MedecinRepository extends CrudRepository<Medecin, Integer> {
    Medecin findByNom(String nom);
}