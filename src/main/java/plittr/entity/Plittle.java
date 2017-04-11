package plittr.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Plittle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5364643531320288207L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String message;
	private Date time;
	private String latitude;
	private String longitude;
	@ManyToOne
	private Plitter plitter;
	public Plittle(String message, Date time, String latitude, String longitude, Plitter plitter) {
		super();
		this.message = message;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.plitter = plitter;
	}
	public Plittle() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String string) {
		this.latitude = string;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Plitter getPlitter() {
		return plitter;
	}
	public void setPlitter(Plitter plitter) {
		this.plitter = plitter;
	}
	@Override
	public String toString() {
		return "Plittle [id=" + id + ", message=" + message + ", time=" + time + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", plitter=" + plitter + "]";
	}

}
