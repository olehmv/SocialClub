package plittr.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableWebMvc
@ComponentScan("plittr")
public class WebConfig extends WebMvcConfigurerAdapter {
//	@Autowired
//	private PlitterConverter plitterConverter;

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate5Module());
        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(messageConverter);
		converters.add(jacksonMessageConverter());
	}
//	<mvc:resources mapping="/images/**" location="file:${catalina.home}/images/" cache-period="3600" />

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	    registry.addResourceHandler("/images/**").addResourceLocations("file:${catalina.home}/images/");
	    
	}
//	 @Override
//	    public void addFormatters(FormatterRegistry registry) {
//	        registry.addConverter(plitterConverter);
//	    }
	 /**
	     * Configure MessageSource to lookup any validation/error message in internationalized property files
	     */
	    @Bean
	    public MessageSource messageSource() {
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("messages");
	        return messageSource;
	    }     
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	//"variable:.+"
	 @Override
	    public void configurePathMatch(PathMatchConfigurer matcher) {
	        matcher.setUseRegisteredSuffixPatternMatch(true);
	    }
	
}
//"variable:.+" doesn't work when there's more than one dot in the variable. 
//eg putting emails at the end of restful paths like /path/abc@server.com.au. 
//The controller doesn't even get called, but it works when there's only one dot /path/abc@server.com
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {    
///somepath/param.value.anything will result in a param with value param.value
//        configurer.favorPathExtension(true);
//        configurer.favorParameter(false);
//        configurer.useJaf(false);
//        configurer.defaultContentType(MediaType.APPLICATION_JSON);
//    }