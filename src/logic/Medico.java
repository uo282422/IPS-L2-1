package logic;

public class Medico {
	private String id;
	private String email;

	public Medico(String id, String email) {
		setId(id);
		setEmail(email);
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
