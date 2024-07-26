package exam2;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="JpaMember2")
public class Member2 {
	@Id
	@SequenceGenerator (
		name = "mySequence01",
		sequenceName = "JpaMember2_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
	@GeneratedValue (generator = "mySequence01")
	private Long id;
	@Access(AccessType.FIELD)
	private String username;
	@Access(AccessType.PROPERTY)
	private String password;
	@Transient
	private long timestamp1;
	transient private long timestamp2;
	
	protected Member2() {}
	
	public Member2(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
