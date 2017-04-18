package plittr.service;

import java.util.List;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import plittr.entity.Plitter;
@CacheConfig(cacheNames="plitterCache")
public interface PlitterService {
	@Cacheable(value="plitterCache")
	public List<Plitter>getPlitters();
	@Cacheable(value="plitterCache")
	public Plitter getPlitter(long id);
	@Cacheable
	public Plitter savePlitter(String ip,Plitter plitter);
	public void deletePlitter(Plitter plitter);
	@CachePut(value="plitterCache",key="#result.id")
	public Plitter savePlitter(Plitter plitter);
	public Plitter updatePlitter(Plitter entity);
	@Cacheable
	public Plitter findById(Integer id);
	public boolean isPlitterUsernameUnique(Long id, String username);
	public Plitter findByUsername(String username);

}
