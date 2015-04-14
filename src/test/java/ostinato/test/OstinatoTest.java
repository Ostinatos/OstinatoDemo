package ostinato.test;

import java.util.Date;

import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import ostinato.Ostinato;
import ostinato.data.OstinatoRepository;
import ostinato.web.DronerController;
import ostinato.web.OstinatoController;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

public class OstinatoTest {

	@Test
	public void showRecentOstinato(){}
	
	@Test
	public void testSingleOstinato() throws Exception{
		Ostinato ost = new Ostinato("hi boy!", new Date());
		OstinatoRepository mockRepo = mock(OstinatoRepository.class);
		when(mockRepo.findOne(1234L)).thenReturn(ost);
		
		OstinatoController controller = new OstinatoController();
		controller.setOstinatoRepository(mockRepo);
		
		MockMvc mockMVC = standaloneSetup(controller).build();
		
		mockMVC.perform(get("/ostinatos/1234"))
		.andExpect(view().name("ostinato"))
		.andExpect(model().attributeExists("ostinato"))
		.andExpect(model().attribute("ostinato", ost));
	}
	
	@Test
	public void showRegisterForm() throws Exception{
		DronerController controller = new DronerController();
		
		MockMvc mockMVC = standaloneSetup(controller).build();
		
		mockMVC.perform(get("/droner/register"))
		.andExpect(view().name("registerForm"));
	}
}
