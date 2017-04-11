package plittr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import plittr.entity.Plitter;
import plittr.service.PlitterService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private PlitterService plitterService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPage(){
		return "index";
	}
	

}