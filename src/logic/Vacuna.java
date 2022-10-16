package logic;

public class Vacuna {

	private int id;
	private String nombre;
	private String descripcion;
	private int idPaciente;
	private boolean enCita;
	private int idCita;

	public Vacuna(int id, String nombre, String descripcion, int idPaciente, boolean enCita, int idCita) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.idPaciente = idPaciente;
		this.enCita = enCita;
		this.idCita = idCita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public boolean isEnCita() {
		return enCita;
	}

	public void setEnCita(boolean enCita) {
		this.enCita = enCita;
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

}
