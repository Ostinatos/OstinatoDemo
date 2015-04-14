package ostinato.data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;

import ostinato.Ostinato;

@Named
public class ConcreteOstinatoRepository implements OstinatoRepository {

	public List<Ostinato> findOstinatos(long max, int count) {
		List<Ostinato> result = new LinkedList<Ostinato>();
		for(int i=0;i<count;i++)
		{
			result.add(new Ostinato("Drone..." + i, new Date()));
		}
		return result;
	}

	public Ostinato findOne(long ostinatoId) {
		return new Ostinato("ONE OST", new Date());
	}

}
