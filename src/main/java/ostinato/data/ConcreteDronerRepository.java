package ostinato.data;

import java.util.logging.Logger;

import javax.inject.Named;

import ostinato.Droner;

@Named
public class ConcreteDronerRepository implements DronerRepository {

	Logger logger = Logger.getAnonymousLogger();
	
	public Droner save(Droner droner) {
		logger.info("saving droner " + droner.toString());
		return droner;
	}

	public Droner findByUsername(String username) {
		logger.info("query droner by user name: " + username);
		return new Droner(1234L, username, "123456");
	}

}
