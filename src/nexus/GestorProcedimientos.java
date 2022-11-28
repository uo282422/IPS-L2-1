package nexus;

import java.util.List;

import logic.procedimiento.Procedimiento;
import logic.procedimiento.ProcedimientoSeccion;
import logic.procedimiento.ProcedimientoSistema;
import logic.procedimiento.ProcedimientoTipo;
import util.DataBase;

public class GestorProcedimientos {
	
	private DataBase bd;
	
	public GestorProcedimientos() {
		this.bd = new DataBase();
	}

	public List<ProcedimientoSeccion> getSecciones() {
		return bd.cargarSecciones();
	}

	public List<ProcedimientoSistema> getSistemasPorSeccion(String idSeccion) {
		return bd.cargarSistemasPorSeccion(idSeccion);
	}

	public List<ProcedimientoTipo> getTiposPorSistema(String idSistema) {
		return bd.cargarTiposPorSistema(idSistema);
	}

	public List<Procedimiento> cargarProcedimientosOrdenados(int idPaciente) {
		return bd.cargarProcedimientosOrdenados(idPaciente);
	}

}
