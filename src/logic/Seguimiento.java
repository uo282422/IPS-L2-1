package logic;

public class Seguimiento {
	private String id_seguimiento;
	private String id_cita;
	private String estado;
	private String comentarios;
	
	public Seguimiento(String id, String dia, String est, String com) {
		this.id_seguimiento=id;
		this.id_cita=dia;
		this.estado=est;
		this.comentarios=com;
	}

	public void abrir() {
		setEstado("ABIERTO");
	}
	public void cerrar() {
		setEstado("CERRADO");
	}
	public String getId_seguimiento() {
		return id_seguimiento;
	}

	public void setId_seguimiento(String id_seguimiento) {
		this.id_seguimiento = id_seguimiento;
	}

	public String getId_cita() {
		return id_cita;
	}

	public void setId_cita(String id_cita) {
		this.id_cita = id_cita;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	
}
