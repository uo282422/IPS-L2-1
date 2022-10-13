package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.Cita;
import logic.Jornada;
import logic.Medico;

public class DataBase {
	private String url = "jdbc:hsqldb:hsql://localhost";
	private String user = "sa";
	private String pass = "";

	private static final String QUERY_CITA_CON_ID = "SELECT * FROM cita WHERE cita_id = ?";
	private static final String QUERY_NOMBRE_PACIENTE_CON_ID = "SELECT paciente_nombre FROM paciente WHERE paciente_id = ?";

	public List<Medico> cargarMedicos() {
		ArrayList<Medico> medicos = new ArrayList<Medico>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from medico");
				while (rs.next()) {
					String id = rs.getString("medico_id");
					String email = rs.getString("medico_email");
					medicos.add(new Medico(id, email));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
		return medicos;
	}

	public void cargarDatosDePrueba() {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				// DATOS A INSERTAR
				s.executeUpdate("commit");
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
	}

	public void guardarJornada(Jornada j) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				s.executeUpdate(String.format(
						"insert into jornada values ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
						j.getId(), j.getDias(), j.getHoraComienzo(),
						j.getHoraFinal(), j.getDiaInicio(), j.getDiaFinal(),
						j.getMedico().getId()));
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
	}

	public Cita getCita(String id) {
		Cita c = null;
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = conn.prepareStatement(QUERY_CITA_CON_ID);
			try {
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int idPaciente = Integer
							.parseInt(rs.getString("cita_paciente_id"));
					String fecha = rs.getString("cita_fecha");
					String hora_inicio = rs.getString("cita_hora_inicio");
					boolean urgente = rs.getBoolean("cita_urgente");
					int sala = Integer.parseInt(rs.getString("cita_sala_id"));
					String nombrePaciente = getNombrePaciente(idPaciente);
					c = new Cita(idPaciente, nombrePaciente, fecha, hora_inicio,
							sala, urgente);
					c.setIdCita(Integer.parseInt(id));
				}

				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
		return c;
	}

	private String getNombrePaciente(int id) {
		String nombrePaciente = "";
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = conn
					.prepareStatement(QUERY_NOMBRE_PACIENTE_CON_ID);
			try {
				ps.setString(1, "" + id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					nombrePaciente = rs.getString("paciente_nombre");
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
		return nombrePaciente;
	}
}
