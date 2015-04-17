package ostinato.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("ostinato.web, ostinato.api")
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
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	//use content negotiating view manager
//	@Bean
//	public ViewResolver cnViewResolver(ContentNegotiationManager cnm){
//		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
//		cnvr.setContentNegotiationManager(cnm);
//		return cnvr;
//	}
	
	//do not use content negotiating manager
//	@Bean
//	public ViewResolver cnViewResolver(){
//		return new ContentNegotiatingViewResolver();
//	}
	
//	/**
//	 * view resolver for content negotiating view resolver to delegate to
//	 * @return
//	 */
//	@Bean
//	public ViewResolver beanNameViewResolver(){
//		return new BeanNameViewResolver();
//	}
//	
//	/**
//	 * bean name view for json renderer
//	 * @return
//	 */
//	@Bean
//	public View ostinatos(){
//		return new MappingJackson2JsonView();
//	}
	
	//required if use negotiating view manager
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
//		configurer.defaultContentType(MediaType.APPLICATION_JSON);
//	}
	
}
