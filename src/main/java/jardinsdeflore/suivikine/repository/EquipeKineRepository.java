package jardinsdeflore.suivikine.repository;

import jardinsdeflore.suivikine.entity.EquipeKine;
import org.springframework.data.repository.CrudRepository;

public interface EquipeKineRepository extends CrudRepository<EquipeKine, Integer> {
    EquipeKine findByLogin(String login);
}