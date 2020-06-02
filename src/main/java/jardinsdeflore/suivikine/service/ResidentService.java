package jardinsdeflore.suivikine.service;


import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import java.util.Date;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service 
public class ResidentService {
	private static final Logger LOGGER = Logger.getLogger(ResidentService.class.getName());
	private ResidentRepository residentRepository;


	public ResidentService(ResidentRepository residentRepository) { 
		this.residentRepository = residentRepository;
	}

	public Iterable<Resident> findAll() {
		return residentRepository.findAll();
	}

	public long count() {
		return residentRepository.count();
	}

	public void delete(Resident resident) {
		residentRepository.delete(resident);
	}

	public void save(Resident resident) {
		if (resident == null) { 
			LOGGER.log(Level.SEVERE,
					"residet is null. Are you sure you have connected your form to the application?");
			return;
		}
		residentRepository.save(resident);
	}
}