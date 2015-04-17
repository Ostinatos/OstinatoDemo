package ostinato.data;

import java.util.List;

import ostinato.Ostinato;

public interface OstinatoRepository {

	public List<Ostinato> findOstinatos(long max, int count);
	
	public Ostinato findOne(long ostinatoId);
	
	public Ostinato saveOne(Ostinato ost);
}
