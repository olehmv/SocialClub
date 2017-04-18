package plittr.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import plittr.entity.Plitter;
import plittr.exception.PlitterNotFoundException;
import plittr.exception.error.ErrorInfo;
import plittr.service.PlitterService;

@RestController
@RequestMapping(value = "/plitters")
public class PlitterController {
	@Autowired
	private PlitterService plitterService;
	@Autowired
	ServletContext servletContext; 

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Plitter> getProfiles() {
		return plitterService.getPlitters();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Plitter getProfile(@PathVariable String id) {
		Plitter entity = plitterService.getPlitter(Long.valueOf(id));
		if (entity == null) {
			throw new PlitterNotFoundException();
		}
		return entity;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Plitter addProfile(@RequestBody Plitter plitter) {
		return plitterService.savePlitter(plitter);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Plitter deleteProfile(@PathVariable long id) {
		Plitter entity = plitterService.getPlitter(id);
		if (entity == null) {
			throw new PlitterNotFoundException();
		} else {
			plitterService.deletePlitter(entity);
		}
		return entity;
	}
	@RequestMapping(value="/{id}",method = RequestMethod.PUT,consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Plitter updateProfile(@PathVariable long id, @RequestBody Plitter plitter){
		System.out.println(plitter);
		Plitter entity=plitterService.getPlitter(id);
		if (entity==null){
			throw new PlitterNotFoundException();
		}else{
			entity.setUsername(plitter.getUsername());
			entity.setEmail(plitter.getEmail());
			plitterService.updatePlitter(entity);
		}
		return entity;
	}
	

	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PlitterNotFoundException.class)
	ErrorInfo
	handleBadRequest(HttpServletRequest req, Exception ex) {
	    return new ErrorInfo(req.getRequestURL(), ex);
	} 
	
	@RequestMapping(value = "/profilePicture/{profilePicture}",method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] plitterImage(@PathVariable String profilePicture) throws IOException {
		System.out.println(profilePicture);
	    InputStream in = servletContext.getResourceAsStream("/WEB-INF/img/"+profilePicture);
	    System.out.println(in.toString());
	    return IOUtils.toByteArray(in);
	}
}
