package ostinato;

import java.util.Date;

public class Ostinato {

	private final Date timestamp;
	private final String message;
	
	public Ostinato(String message, Date timestamp){
		this.message=message;
		this.timestamp=timestamp;
	}
	
	public Ostinato(String message){
		this.message=message;
		this.timestamp=new Date();
	}
	
	public Ostinato(){
		this.message = "";
		this.timestamp=new Date();
	}
	
	@Override
	public String toString(){
		return this.message + " " + this.timestamp.toString();
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ostinato other = (Ostinato) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
	
	
}
