package nexus;

import java.util.ArrayList;
import java.util.List;

import logic.Seguimiento;
import logic.diagnostico.Diagnostico;
import logic.diagnostico.DiagnosticoCapitulo;
import logic.diagnostico.DiagnosticoGrupo;
import logic.diagnostico.DiagnosticoSubgrupo;
import logic.diagnostico.DiagnosticoTabla;
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
	
	public List<DiagnosticoTabla> getTablas() {
		return bd.cargarTablas();
	}

	public List<DiagnosticoGrupo> getGruposPorTabla(String idTabla) {
		return bd.cargarGrupoPorTabla(idTabla);
	}

	public List<DiagnosticoSubgrupo> getSubgruposPorGrupo(String idGrupo) {
		return bd.cargarSubgrupoPorGrupo(idGrupo);
	}

	public List<DiagnosticoCapitulo> getCapitulosPorSubgrupo(String idSubgrupo) {
		return bd.cargarCapitulosPorSubgrupo(idSubgrupo);
	}

	public List<Diagnostico> cargarDiagnosticosOrdenados(int idPaciente) {
		return bd.cargarDiagnosticosPorPaciente(idPaciente);
	}
}
