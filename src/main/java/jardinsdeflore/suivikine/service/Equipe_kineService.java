package jardinsdeflore.suivikine.service;


import jardinsdeflore.suivikine.entity.Equipe_kine;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import jardinsdeflore.suivikine.repository.Equipe_kineRepository;

@Service 
public class Equipe_kineService {
	private static final Logger LOGGER = Logger.getLogger(Equipe_kineService.class.getName());
	private Equipe_kineRepository equipeKineRepository;


	public Equipe_kineService(Equipe_kineRepository equipeKineRepository) { 
		this.equipeKineRepository = equipeKineRepository;
	}

	public Iterable<Equipe_kine> findAll() {
		return equipeKineRepository.findAll();
	}
        
        public Equipe_kine findByLogin(String login) {
		return equipeKineRepository.findByLogin(login);
	}

	public long count() {
		return equipeKineRepository.count();
	}

	public void delete(Equipe_kine equipeKine) {
		equipeKineRepository.delete(equipeKine);
	}

	public void save(Equipe_kine equipeKine) {
		if (equipeKine == null) { 
			LOGGER.log(Level.SEVERE,
					"equipeKine is null. Are you sure you have connected your form to the application?");
			return;
		}
		equipeKineRepository.save(equipeKine);
	}
}