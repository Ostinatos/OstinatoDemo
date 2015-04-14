package ostinato;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class Droner {

	private long dronerId;
	
	@NotNull
	@Size(min=3, max=16)
	private String dronerName;
	
	@NotNull
	@Size(min=6, max=30)
	private String dronerCredential;
	
	private MultipartFile profileAvatar;
	
	public MultipartFile getProfileAvatar() {
		return profileAvatar;
	}

	public void setProfileAvatar(MultipartFile profileAvatar) {
		this.profileAvatar = profileAvatar;
	}

	public Droner(){}
	
	public Droner(long dronerId, String name, String password){
		this.dronerId = dronerId;
		this.dronerCredential = password;
		this.dronerName = name;
	}
	
	public Droner(String name, String password){
		this.dronerCredential = password;
		this.dronerName = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dronerCredential == null) ? 0 : dronerCredential.hashCode());
		result = prime * result + (int) (dronerId ^ (dronerId >>> 32));
		result = prime * result
				+ ((dronerName == null) ? 0 : dronerName.hashCode());
		return result;
	}

	@Override
	public String toString(){
		return this.dronerId + " " + this.dronerName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Droner other = (Droner) obj;
		if (dronerCredential == null) {
			if (other.dronerCredential != null)
				return false;
		} else if (!dronerCredential.equals(other.dronerCredential))
			return false;
		if (dronerId != other.dronerId)
			return false;
		if (dronerName == null) {
			if (other.dronerName != null)
				return false;
		} else if (!dronerName.equals(other.dronerName))
			return false;
		return true;
	}

	public long getDronerId() {
		return dronerId;
	}
	public void setDronerId(long dronerId) {
		this.dronerId = dronerId;
	}
	public String getDronerName() {
		return dronerName;
	}
	public void setDronerName(String dronerName) {
		this.dronerName = dronerName;
	}
	public String getDronerCredential() {
		return dronerCredential;
	}
	public void setDronerCredential(String dronerCredential) {
		this.dronerCredential = dronerCredential;
	}
	
	
}
