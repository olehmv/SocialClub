package plittr.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({ "plittles" })
public class Plitter implements UserDetails, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 884833278644680655L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String username;
	@NotNull
	private String email;
	@NotNull
	private String password;
	private String profilePicture;
	private String countryCode;
	private String countryName;
	private String region;
	private String regionName;
	private String city;
	private String postalCode;
	private String latitude;
	private String longitude;
	private Date registrationDate;
	@Enumerated
	private Status status;
	@Enumerated
	private Role role;
	@OneToMany
	List<Plittle>plittles=new ArrayList<>();
    @ElementCollection(targetClass=String.class)
	private List<String>photos=new ArrayList<>();
	

	public List<String> getPhotos() {
		return photos;
	
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date data) {
		this.registrationDate = data;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
	public Plitter(String userName, String email, String password, String profilePicture, Status status, Role role) {
		super();
		this.username = userName;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
		this.status = status;
		this.role = role;
	}
	
	public Plitter() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Plittle> getPlittles() {
		return plittles;
	}
	public void setPlittles(List<Plittle> plittles) {
		this.plittles = plittles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority>list=new ArrayList<>();
		list.add(new SimpleGrantedAuthority(role.name()));
		return list;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
				return true;
	}

	@Override
	public String toString() {
		return "Plitter [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", profilePicture=" + profilePicture + ", countryCode=" + countryCode + ", countryName=" + countryName
				+ ", region=" + region + ", regionName=" + regionName + ", city=" + city + ", postalCode=" + postalCode
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", registrationTime=" + registrationDate
				+ ", status=" + status + ", role=" + role + ", plittles=" + plittles + "]";
	}

	
	
	
	
	

}
