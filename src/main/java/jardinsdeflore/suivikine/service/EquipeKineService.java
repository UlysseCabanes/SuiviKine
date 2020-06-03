package jardinsdeflore.suivikine.service;


import jardinsdeflore.suivikine.entity.EquipeKine;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;

@Service 
public class EquipeKineService {
	private static final Logger LOGGER = Logger.getLogger(EquipeKineService.class.getName());
	private EquipeKineRepository equipeKineRepository;


	public EquipeKineService(EquipeKineRepository equipeKineRepository) { 
		this.equipeKineRepository = equipeKineRepository;
	}

	public Iterable<EquipeKine> findAll() {
		return equipeKineRepository.findAll();
	}
        
        public EquipeKine findByLogin(String login) {
		return equipeKineRepository.findByLogin(login);
	}

	public long count() {
		return equipeKineRepository.count();
	}

	public void delete(EquipeKine equipeKine) {
		equipeKineRepository.delete(equipeKine);
	}

	public void save(EquipeKine equipeKine) {
		if (equipeKine == null) { 
			LOGGER.log(Level.SEVERE,
					"equipeKine is null. Are you sure you have connected your form to the application?");
			return;
		}
		equipeKineRepository.save(equipeKine);
	}
}