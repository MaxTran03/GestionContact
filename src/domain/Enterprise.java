package domain;

//import org.eclipse.jdt.annotation.Nullable;

public class Enterprise extends Contact {
	/*@Nullable */long numSiret = -1;

	public Enterprise(){
		
	}
	
	public Enterprise(String firstname, String lastname, String email, Address add, long num) {
		super(firstname, lastname, email, add);
		this.numSiret = num;
	}
	
	public Enterprise(long id, String firstname, String lastname, String email, Address add, long num) {
		super(id, firstname, lastname, email, add);
		this.numSiret = num;
	}

	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}

}
