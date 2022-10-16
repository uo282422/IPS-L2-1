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
import logic.Enfermedad;
import logic.Jornada;
import logic.Medico;
import logic.Paciente;
import logic.Sala;
import logic.Vacuna;

public class DataBase {
	private String url = "jdbc:hsqldb:hsql://localhost";
	private String user = "sa";
	private String pass = "";

	public List<Medico> cargarMedicos() {
		ArrayList<Medico> medicos = new ArrayList<Medico>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from medico");
				while (rs.next()) {
					String id = rs.getString("medico_id");
					String nombre = rs.getString("medico_nombre");
					String apellido = rs.getString("medico_apellido");
					String email = rs.getString("medico_email");
					medicos.add(new Medico(id, nombre, apellido, email));
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

	public List<Cita> cargarCitasPorMedicoYFecha(String idMedico, String fecha) {

		ArrayList<Cita> citas = new ArrayList<Cita>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement(
					"select * from cita c, medico_cita m where c.cita_id = m.cita_id and m.medico_id = ? and c.cita_fecha = ?");
			try {
				pst.setString(1, idMedico);
				pst.setString(2, fecha);

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					int id = Integer.parseInt(rs.getString("CITA_ID"));
					int pacienteId = Integer.parseInt(rs.getString("CITA_PACIENTE_ID"));
					String horaI = rs.getString("CITA_HORA_INICIO");
					String horaF = rs.getString("CITA_HORA_FIN");
					boolean urgente = rs.getBoolean("CITA_URGENTE");
					int salaId = Integer.parseInt(rs.getString("CITA_SALA_ID"));
					String telefono = rs.getString("CITA_TELEFONO");
					String correo = rs.getString("CITA_CORREO");
					String otros = rs.getString("CITA_OTROS");
					boolean acudio = rs.getBoolean("CITA_ACUDIO");
					String causa = rs.getString("CITA_CAUSA");

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, telefono, correo, otros,
							acudio, causa));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				pst.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		return citas;
	}

	public Paciente cargarPacientePorId(int idPaciente) {

		Paciente paciente = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			PreparedStatement pst = conn.prepareStatement("select * from paciente where paciente_id = ?");

			try {

				pst.setString(1, idPaciente + "");
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					int id = Integer.parseInt(rs.getString("PACIENTE_ID"));
					String nombre = rs.getString("PACIENTE_NOMBRE");
					String apellido = rs.getString("PACIENTE_APELLIDO");
					int telefono = Integer.parseInt(rs.getString("PACIENTE_TELEFONO"));
					String correo = rs.getString("PACIENTE_CORREO");
					String otros = rs.getString("PACIENTE_OTROS");

					paciente = new Paciente(id, nombre, apellido, telefono, correo, otros);
				}

				rs.close();

			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				pst.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		return paciente;
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
				s.executeUpdate(String.format("insert into jornada values ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
						j.getId(), j.getDias(), j.getHoraComienzo(), j.getHoraFinal(), j.getDiaInicio(),
						j.getDiaFinal(), j.getMedico().getId()));
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

	public Sala cargarSalaPorId(int idSala) {

		Sala sala = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			PreparedStatement pst = conn.prepareStatement("select * from sala where sala_id = ?");

			try {

				pst.setString(1, idSala + "");
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					int id = Integer.parseInt(rs.getString("SALA_ID"));
					String nombre = rs.getString("SALA_NOMBRE");

					sala = new Sala(id, nombre);
				}

				rs.close();

			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				pst.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		return sala;
	}

	public List<Cita> cargarCitasPorPaciente(int idPaciente) {
		ArrayList<Cita> citas = new ArrayList<Cita>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement("select * from cita c where c.cita_paciente_id = ?");
			try {
				pst.setString(1, idPaciente + "");

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					int id = Integer.parseInt(rs.getString("CITA_ID"));
					int pacienteId = Integer.parseInt(rs.getString("CITA_PACIENTE_ID"));
					String fecha = rs.getString("CITA_FECHA");
					String horaI = rs.getString("CITA_HORA_INICIO");
					String horaF = rs.getString("CITA_HORA_FIN");
					boolean urgente = rs.getBoolean("CITA_URGENTE");
					int salaId = Integer.parseInt(rs.getString("CITA_SALA_ID"));
					String telefono = rs.getString("CITA_TELEFONO");
					String correo = rs.getString("CITA_CORREO");
					String otros = rs.getString("CITA_OTROS");
					boolean acudio = rs.getBoolean("CITA_ACUDIO");
					String causa = rs.getString("CITA_CAUSA");

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, telefono, correo, otros,
							acudio, causa));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				pst.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		return citas;
	}

	public List<Medico> cargarMedicosPorCita(int idCita) {
		ArrayList<Medico> medicos = new ArrayList<Medico>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement(
					"select * from medico m, medico_cita c where c.cita_id = ? and m.medico_id = c.medico_id");
			try {
				pst.setString(1, idCita + "");

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					String id = rs.getString("MEDICO_ID");
					String nombre = rs.getString("MEDICO_NOMBRE");
					String apellido = rs.getString("MEDICO_APELLIDO");
					String email = rs.getString("MEDICO_EMAIL");

					medicos.add(new Medico(id, nombre, apellido, email));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				pst.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		return medicos;
	}

	public List<Enfermedad> cargarEnfermedadesPorPaciente(int idPaciente) {
		ArrayList<Enfermedad> enfermedades = new ArrayList<Enfermedad>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement("select * from enfermedad where enfermedad_paciente_id = ?");
			try {
				pst.setString(1, idPaciente + "");

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					int id = Integer.parseInt(rs.getString("ENFERMEDAD_ID"));
					String nombre = rs.getString("ENFERMEDAD_NOMBRE");
					String descripcion = rs.getString("ENFERMEDAD_DESCRIPCION");
					boolean enCita = rs.getBoolean("ENFERMEDAD_EN_CITA");
					int idCita = enCita ? Integer.parseInt(rs.getString("ENFERMEDAD_CITA_ID")) : 0;

					enfermedades.add(new Enfermedad(id, nombre, descripcion, idPaciente, enCita, idCita));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				pst.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		return enfermedades;
	}

	public List<Vacuna> cargarVacunasPorPaciente(int idPaciente) {
		ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement("select * from vacuna where vacuna_paciente_id = ?");
			try {
				pst.setString(1, idPaciente + "");

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					int id = Integer.parseInt(rs.getString("VACUNA_ID"));
					String nombre = rs.getString("VACUNA_NOMBRE");
					String descripcion = rs.getString("VACUNA_DESCRIPCION");
					boolean enCita = rs.getBoolean("VACUNA_EN_CITA");
					int idCita = enCita ? Integer.parseInt(rs.getString("VACUNA_CITA_ID")) : 0;

					vacunas.add(new Vacuna(id, nombre, descripcion, idPaciente, enCita, idCita));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				pst.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}

		return vacunas;
	}
}
