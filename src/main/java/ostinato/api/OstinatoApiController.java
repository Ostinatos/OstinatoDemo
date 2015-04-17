package ostinato.api;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ostinato.Ostinato;
import ostinato.data.OstinatoRepository;

@Controller
@RequestMapping("/api/ostinatos")
public class OstinatoApiController {

	@Inject
	private OstinatoRepository concreteOstinatoRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Ostinato> ostinatos(
			@RequestParam(value="max", defaultValue="1024") long max,
			@RequestParam(value="count", defaultValue="20") int count,
			Model model){
		return concreteOstinatoRepository.findOstinatos(max, count); 
	}
	
	//add @ResponseBody to bypass view resolver
	//add @RequestBody to accept json object request
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody void saveOstinato(@RequestBody Ostinato ost){
		concreteOstinatoRepository.saveOne(ost);
	}
}
