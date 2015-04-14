package ostinato.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ostinato.data.OstinatoRepository;
import ostinato.web.exception.OstinatoNotFoundException;
import ostinato.Ostinato;

@Controller
@RequestMapping("/ostinatos")
public class OstinatoController {

	@Inject
	private OstinatoRepository concreteOstinatoRepository;

	private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);
	
	public void setOstinatoRepository(OstinatoRepository ostinatoRepository) {
		this.concreteOstinatoRepository = ostinatoRepository;
	}

//	@RequestMapping(method=RequestMethod.GET)
//	public String ostinatos(Model model){
//		model.addAttribute(concreteOstinatoRepository.findOstinatos(Long.MAX_VALUE, 20));
//		model.addAttribute("testAttr", "hello boy!");
//		return "ostinatos";
//	}
	
	//the view name returned is inferred from the request path, namely "ostinatos"
	@RequestMapping(method=RequestMethod.GET)
	public List<Ostinato> ostinatos(
			@RequestParam(value="max", defaultValue="1024") long max,
			@RequestParam(value="count", defaultValue="20") int count,
			Model model){
		model.addAttribute("testAttr", "hello boy!");
		return concreteOstinatoRepository.findOstinatos(max, count); 
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{ostinatoId}")
	public String ostinato(
			@PathVariable("ostinatoId")long ostinatoId, //if the variable name is the same as the one in path place holder
			Model model){
		model.addAttribute("ostinato",concreteOstinatoRepository.findOne(ostinatoId));
		//imaginary exception
		if(ostinatoId > 99)
		{
			throw new OstinatoNotFoundException();
		}
		
		return "ostinato";
	}
}
