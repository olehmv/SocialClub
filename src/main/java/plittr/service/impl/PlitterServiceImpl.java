package plittr.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Data;

import plittr.entity.Plitter;
import plittr.entity.Plittle;
import plittr.entity.Role;
import plittr.entity.Status;
import plittr.geo.ServerLocation;
import plittr.geo.ServerLocationBo;
import plittr.repository.PlitterRepository;
import plittr.repository.PlittleRepository;
import plittr.service.PlitterService;

@Service("userDetailsService")
@Transactional
public class PlitterServiceImpl implements PlitterService, UserDetailsService {
	@Autowired
	private ServerLocationBo serverLocation;
	@Autowired
	private PlitterRepository plitterRepository;
	@Autowired
	private PlittleRepository plittleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	static final Logger logger = LoggerFactory.getLogger(PlitterServiceImpl.class);

	@Override
	public List<Plitter> getPlitters() {
		return plitterRepository.findAll();
	}

	@Override
	public Plitter getPlitter(long id) {
		return plitterRepository.findOne(id);
	}

	@Override
	public Plitter savePlitter(String ipAddress, Plitter plitter) {
		ServerLocation sl = serverLocation.getLocation(ipAddress);
		plitter.setPassword(passwordEncoder.encode(plitter.getPassword()));
		// plitter.setProfilePicture(plitter.getUsername()+".jpg");
		plitter.setRole(Role.ROLE_USER);
		plitter.setStatus(plitter.getStatus());
		plitter.setLatitude(sl.getLatitude());
		plitter.setLongitude(sl.getLongitude());
		plitter.setRegion(sl.getRegion());
		plitter.setRegionName(sl.getRegionName());
		plitter.setPostalCode(sl.getPostalCode());
		plitter.setCity(sl.getCity());
		plitter.setCountryCode(sl.getCountryCode());
		plitter.setCountryName(sl.getCountryName());
		plitter.setRegistrationDate(new Date());
		return plitterRepository.save(plitter);
	}

	@Override
	public void deletePlitter(Plitter entity) {
		plitterRepository.delete(entity);
	}

	@PostConstruct
	private void admin() throws IOException, URISyntaxException {
		ServerLocation cl = serverLocation.getLocation("151.38.39.114");
		System.out.println(cl);

		for (int i = 0; i < 5; i++) {
			Plitter plitter = new Plitter();
			plitter.setUsername("oleg" + i);
			plitter.setEmail("oleg@gmail.com");
			plitter.setPassword(passwordEncoder.encode("oleg"));
			plitter.setRole(Role.ROLE_USER);
			plitter.setStatus(Status.BASIC);
			plitter.getPhotos().add("dell-streak-7.1.jpg");
			plitter.getPhotos().add("dell-streak-7.2.jpg");
			plitter.getPhotos().add("dell-streak-7.3.jpg");
			plitter.getPhotos().add("dell-streak-7.4.jpg");
			plitter.setProfilePicture("dell-streak-7.1.jpg");
			savePlitter(plitter);
			Plittle plittle = new Plittle();
			plittle.setLatitude(cl.getLatitude());
			plittle.setLongitude(cl.getLongitude());
			plittle.setPlitter(plitter);
			plittle.setTime(new Date());
			plittleRepository.save(plittle);
			plitter.getPlittles().add(plittle);
			savePlitter(plitter);
			// plittleRepository.save(plittle);
			// Plitter entity = plitterRepository.findOne((long) i);
			// System.out.println(entity);
		}
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		Plitter plitter = plitterRepository.findByUsername(username);
		logger.info("User : {}", plitter);
		if (plitter == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(plitter.getUsername(), plitter.getPassword(),
				true, true, true, true, getGrantedAuthorities(plitter));
	}

	@Override
	public Plitter savePlitter(Plitter plitter) {
		return savePlitter("151.38.39.114", plitter);
	}

	@Override
	public Plitter updatePlitter(Plitter entity) {
		return savePlitter(entity);
	}

	private List<GrantedAuthority> getGrantedAuthorities(Plitter plitter) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(plitter.getRole().name()));
		logger.info("authorities : {}", authorities);
		return authorities;
	}

	@Override
	public Plitter findById(Integer id) {
				return getPlitter(id);
	}
	
	
	public boolean isPlitterUsernameUnique(Long id, String username) {
		Plitter plitter =plitterRepository.findByUsername(username);
		return ( plitter == null || ((id != null) && (plitter.getId() == id)));
	}

	@Override
	public Plitter findByUsername(String username) {
		return plitterRepository.findByUsername(username);
	}

}
