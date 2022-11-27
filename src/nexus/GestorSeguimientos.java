package nexus;

import java.util.ArrayList;

import logic.Seguimiento;
import logic.cita.Cita;
import logic.diagnostico.Diagnostico;
import util.DataBase;

public class GestorSeguimientos {
	private DataBase bd;
	private ArrayList<Seguimiento> listaSeguimientos = new ArrayList<Seguimiento>();
	public GestorSeguimientos() {
		this.bd = new DataBase();
		
		listaSeguimientos = (ArrayList<Seguimiento>) bd.cargarSeguimientos();
		
	}
	
	public ArrayList<Seguimiento> getListaSeguimientos() {
		return listaSeguimientos;
	}

	public Seguimiento getSeguimientoCita(String citaId) {
		for (Seguimiento s : listaSeguimientos) {
			if (s.getId_cita() == citaId) {
				return s;
			}
		}
		return null;
	}

	public void insertarNuevoSeguimiento(Diagnostico d,String estado, String str) {
		Cita c=new GestorCitas().getCita(d.getCitaId());
		Seguimiento s =new Seguimiento(bd.generarIdSeguimiento(), String.valueOf(c.getIdCita()), estado, str);
		bd.insertarSeguimiento(s);
		
	}

	public void actualizarSeguimiento(Diagnostico d, String res) {
		
		for(Seguimiento seg: listaSeguimientos) {
			if(seg.getId_cita().equals(d.getCitaId())) {
				bd.actualizarEstadoSeguimiento("ABIERTO",res, seg.getId_cita());
			}
		}
		
	}

	public void cerrarSeguimiento(Seguimiento s) {
		bd.actualizarEstadoSeguimiento("CERRADO", s.getComentarios(), s.getId_cita());
		
	}
}
