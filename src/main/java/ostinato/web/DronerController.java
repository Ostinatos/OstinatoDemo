package ostinato.web;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ostinato.Droner;
import ostinato.data.DronerRepository;

@Controller
@RequestMapping("/droner")
public class DronerController {

	@Inject
	private DronerRepository concreteDronerRepository;
	
	private Logger logger = Logger.getAnonymousLogger();
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegisterForm(){
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(
			@Valid Droner droner,
			Errors errors,
			RedirectAttributes model
			){
		logger.info("processing registration for droner: "
				+ "dronerName:" + droner.getDronerName() + 
				", droner password:" + droner.getDronerCredential());
		
		if(errors.hasErrors()){
			logger.severe("form validation failed!");
			return "registerForm";
		}
		
		concreteDronerRepository.save(droner);
		
		model.addFlashAttribute("droner", droner);//add the newly saved object to flash attribute
		logger.info("droner object saved to flash attributes");
		return "redirect:/droner/"+droner.getDronerName();
	}
	
	@RequestMapping(value="/{dronerName}", method=RequestMethod.GET)
	public String dronerDetail(
			@PathVariable String dronerName,
			Model model){
		//if the object already exists in model, do not fire query
		if(!model.containsAttribute("droner")){
			logger.info("droner object not exists, fire query.");
			model.addAttribute("droner", concreteDronerRepository.findByUsername(dronerName));
		}
		
		return "dronerDetail";
		
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.GET)
	public String dronerSettings(
			Model model
			){
		model.addAttribute("droner", concreteDronerRepository.findByUsername("christina"));
		return "dronerSettings";
	}
	
	@RequestMapping(value="/settings", method=RequestMethod.POST)
	public String processSettings(
//			@RequestPart("profileAvatar")byte[] profileAvatar,
			@Valid Droner droner,
			Errors errors
			) throws Exception, IOException{
		if(errors.hasErrors()){
			logger.severe("form validation failed!");
			return "dronerSettings";
		}
		
		MultipartFile profileAvatar = droner.getProfileAvatar();
		if(null != profileAvatar){
			profileAvatar.transferTo(
					new File("/droner/"+droner.getDronerName()+
							profileAvatar.getOriginalFilename().substring(profileAvatar.getOriginalFilename().lastIndexOf(".")) ));
		}
		
		return "redirect:/droner/"+droner.getDronerName();
		
		
	}
	

	public void setDronerRepository(DronerRepository concreteDronerRepository) {
		this.concreteDronerRepository = concreteDronerRepository;
	}
}
