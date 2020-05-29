package jardinsdeflore.suivikine.service;



import jardinsdeflore.suivikine.entity.Medecin;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import jardinsdeflore.suivikine.repository.MedecinRepository;

@Service 
public class MedecinService {
	private static final Logger LOGGER = Logger.getLogger(MedecinService.class.getName());
	private MedecinRepository medecinRepository;

	public MedecinService(MedecinRepository medecinRepository) { 
		this.medecinRepository = medecinRepository;
	}

	public Iterable<Medecin> findAll() {
		return medecinRepository.findAll();
	}
        
        public Medecin findByNom(String nom) {
		return medecinRepository.findByNom(nom);
	}

	public long count() {
		return medecinRepository.count();
	}

	public void delete(Medecin medecin) {
		medecinRepository.delete(medecin);
	}

	public void save(Medecin medecin) {
		if (medecin == null) { 
			LOGGER.log(Level.SEVERE,
					"medecin is null. Are you sure you have connected your form to the application?");
			return;
		}
		medecinRepository.save(medecin);
	}
}