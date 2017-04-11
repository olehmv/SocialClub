package plittr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plittr.entity.Plitter;

public interface PlitterService {
	List<Plitter>getPlitters();
	Plitter getPlitter(long id);
	Plitter savePlitter(String ip,Plitter plitter);
	void deletePlitter(Plitter plitter);
	Plitter savePlitter(Plitter plitter);
	Plitter updatePlitter(Plitter entity);


}
