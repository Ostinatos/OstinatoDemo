package ostinato.data;

import ostinato.Droner;

public interface DronerRepository {
	
	Droner save(Droner droner);
	
	Droner findByUsername(String username);
}
