package plittr.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profilePicture")
public class PlitterImageController {
	
	@Autowired
    ServletContext servletContext; 
	@RequestMapping(value = "/{profilePicture}",method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] plitterImage(@PathVariable String profilePicture) throws IOException {
		System.out.println(profilePicture);
	    InputStream in = servletContext.getResourceAsStream("/WEB-INF/img/"+profilePicture);
	    System.out.println(in.toString());
	    return IOUtils.toByteArray(in);
	}
}

//File[] files = new File(System.getProperty("catalina.home") + "\\wtpwebapps\\social\\static\\img\\phones")
//.listFiles();
//System.out.println(files[0].getAbsolutePath());
//byte[] bytes = Files.readAllBytes(files[0].toPath());
//System.out.println(servletContext.toString());
//String path =servletContext.getRealPath("/WEB-INF/img/dell-streak-7.1.jpg");
//System.out.print(path);// this prints correct path
//File f=new File(path); if(!f.exists()){System.out.println("\n\n\nFILE DOES NOT EXISTS\n\n\n");}