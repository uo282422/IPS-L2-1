package nexus;

import java.util.ArrayList;

import logic.Diagnostico;
import logic.Seguimiento;
import util.DataBase;

public class GestorDiagnosticos {
	private DataBase bd;
	private ArrayList<Diagnostico> listaDiagnosticos = new ArrayList<Diagnostico>();
	public GestorDiagnosticos() {
		this.bd = new DataBase();
		
		listaDiagnosticos = (ArrayList<Diagnostico>) bd.cargarDiagnosticos();
		
	}
	
	public ArrayList<Diagnostico> getListaDiagnosticos() {
		return listaDiagnosticos;
	}
	public Diagnostico getDiagnosticoCita(String citaId) {
		for (Diagnostico d : listaDiagnosticos) {
			if (d.getCitaId() == citaId) {
				return d;
			}
		}
		return null;
	}

	public void actualizarSeguimientoDiagnostico(Diagnostico d, boolean b) {
		bd.actualizarSeguimientoDiagnostico(true, d.getCitaId());
		
	}
}
