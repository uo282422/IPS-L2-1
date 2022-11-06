package nexus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import logic.Baja;
import logic.Baja.BajaTipos;
import logic.Medico;
import util.DataBase;

public class GestorBajas {

	private Baja baja;
	private DataBase db = new DataBase();
	private boolean update = false;
	private String idColision = "";

	public List<Medico> cargarMedicos() {
		return db.cargarMedicos();
	}

	public boolean checkColisiones(Medico m, Date inicio, Date fin,
			String hInicio, String hFin) {
		List<Baja> bajas = db.getBajas(m);
		if (bajas.isEmpty())
			return true;
		for (Baja b : bajas) {
			try {
				Date bInicio = new SimpleDateFormat("dd/MM/yyyy;HH:mm")
						.parse(b.getfInicio() + ";" + b.gethInicio());
				Date bFin = new SimpleDateFormat("dd/MM/yyyy;HH:mm")
						.parse(b.getfFin() + ";" + b.gethFin());
				String sinicio = new SimpleDateFormat("dd/MM/yyyy")
						.format(inicio);
				String sfin = new SimpleDateFormat("dd/MM/yyyy").format(fin);
				Date dInicio = new SimpleDateFormat("dd/MM/yyyy;HH:mm")
						.parse(sinicio + ";" + hInicio);
				Date dFin = new SimpleDateFormat("dd/MM/yyyy;HH:mm")
						.parse(sfin + ";" + hFin);
				if (dInicio.before(bFin) && dInicio.after(bInicio)
						|| dFin.before(bFin) && dFin.after(bInicio)) {
					idColision = b.getId();
					return false; // colisión
				}

			} catch (ParseException e) {
			}

		}
		return true;
	}

	public void save(Medico m, Date inicio, Date fin, String horaInicio,
			String horaFin, String tipo, String observaciones) {
		baja = new Baja();
		baja.setId(generarId());
		baja.setfInicio(new SimpleDateFormat("dd/MM/yyyy").format(inicio));
		baja.setfFin(new SimpleDateFormat("dd/MM/yyyy").format(fin));
		baja.sethInicio(horaInicio);
		baja.sethFin(horaFin);
		baja.setTipo(parseTipo(tipo));
		baja.setObservaciones(observaciones);
		baja.setMedico(m);
		if (update) {
			db.actualizarBaja(baja);
			update = false;
		} else
			db.guardarBaja(baja);
	}

	private BajaTipos parseTipo(String t) {
		switch (t.toLowerCase()) {
		case "baja":
			return BajaTipos.BAJA;
		case "vacaciones":
			return BajaTipos.VACACIONES;
		case "moscosos":
			return BajaTipos.MOSCOSOS;
		default:
			return BajaTipos.OTROS;
		}
	}

	private String generarId() {
		if (update)
			return idColision;
		else
			return db.generarIdBaja();
	}

	public void update(boolean update) {
		this.update = update;
	}
}