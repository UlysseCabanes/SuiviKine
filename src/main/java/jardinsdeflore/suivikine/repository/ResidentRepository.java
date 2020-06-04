package jardinsdeflore.suivikine.repository;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import jardinsdeflore.suivikine.entity.Resident;
import org.springframework.data.repository.CrudRepository;

public interface ResidentRepository extends CrudRepository<Resident, ResidentId> {
    Iterable<Resident> findByEquipeKine(int idEquipe);
}