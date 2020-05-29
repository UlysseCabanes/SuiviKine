package jardinsdeflore.suivikine.repository;

import jardinsdeflore.suivikine.entity.Equipe_kine;
import org.springframework.data.repository.CrudRepository;

public interface Equipe_kineRepository extends CrudRepository<Equipe_kine, Integer> {
    Equipe_kine findByLogin(String login);
}