package ostinato.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("ostinato.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public ViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine);
	    viewResolver.setOrder(0);
//	    viewResolver.setExcludedViewNames(new String[]{"dronerDetail"});
	    return viewResolver;
	}
	
	@Bean
	  public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    return templateEngine;
	  }
	
	@Bean
	  public TemplateResolver templateResolver() {
	    TemplateResolver templateResolver = new ServletContextTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	  //set cacheable to false so that u don't need to restart tomcat while debugging templates locally.
	    templateResolver.setCacheable(false);
	    
	    return templateResolver;
	  }
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	
	@Bean
	  public MultipartResolver multipartResolver() throws IOException {
	    return new StandardServletMultipartResolver();
	  }
}
