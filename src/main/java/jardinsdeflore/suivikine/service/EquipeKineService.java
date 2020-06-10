package jardinsdeflore.suivikine.service;

import jardinsdeflore.suivikine.entity.EquipeKine;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import java.util.ArrayList;

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
        
        public EquipeKine findLast(EquipeKineRepository equipeKineRepository) {
            //Compter le nombre d'�quipes dans la BDD
            long taille = equipeKineRepository.count();
            //R�cup�rer toutes les �quipes
            ArrayList<EquipeKine> lesEquipes = (ArrayList) equipeKineRepository.findAll();
            //R�cup�rer la derni�re �quipe
            EquipeKine derniere = lesEquipes.get((int) taille - 1);
            
            return derniere;
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
			LOGGER.log(Level.SEVERE, "EquipeKine = null");
			return;
		}
		equipeKineRepository.save(equipeKine);
	}
}