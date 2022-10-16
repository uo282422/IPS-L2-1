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

	private static final String QUERY_CITA_CON_ID = "SELECT * FROM cita WHERE cita_id = ?";
	private static final String QUERY_NOMBRE_PACIENTE_CON_ID = "SELECT paciente_nombre FROM paciente WHERE paciente_id = ?";
	private static final String GUARDAR_CITA = "INSERT INTO cita VALUES ('%d', '%d', '%s', '%s', '%s', '%d', '%d', '%d', '%s', '%s', '%d', '%s')";
	private static final String GUARDAR_JORNADA = "INSERT INTO jornada VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
	private static final String ACTUALIZAR_CITA_ACUDE = "UPDATE cita SET cita_acudio = '%d' WHERE cita_id = '%d'";
	private static final String ACTUALIZAR_CITA_CAUSAS = "UPDATE cita SET cita_causa = '%s' WHERE cita_id = '%d'";
	private static final String QUERY_ID_JORNADA = "SELECT jornada_id FROM jornada";

	/**
	 * Realiza una consulta a la base de datos para obtener todos los médicos.
	 * 
	 * @return List<Medico> conteniendo todos los médicos en la base de datos.
	 */
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

	public List<Jornada> getJornadasDeMedico(String medId) {

		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from jornada where jornada_medico_id='" + medId + "'");
				while (rs.next()) {
					String id = rs.getString("jornada_id");
					String dias = rs.getString("jornada_dias");
					String horaI = rs.getString("jornada_hora_inicio");
					String horaF = rs.getString("jornada_hora_fin");
					String diaI = rs.getString("jornada_dia_inicio");
					String diaF = rs.getString("jornada_dia_fin");

					jornadas.add(new Jornada(id, dias, horaI, horaF, diaI, diaF, medId));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar jornada", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para jornada", e);
		}
		return jornadas;
	}

	public List<Sala> cargarSalas() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from sala");
				while (rs.next()) {
					String id = rs.getString("sala_id");
					String nombre = rs.getString("sala_nombre");

					salas.add(new Sala(Integer.parseInt(id), nombre));
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
		return salas;
	}

	public ArrayList<Paciente> cargarPacientes() {
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from paciente");
				while (rs.next()) {
					boolean valido = true;
					String id = rs.getString("paciente_id");
					String nombre = rs.getString("paciente_nombre");
					String apellido = rs.getString("paciente_apellido");
					String telefono = rs.getString("paciente_telefono");
					String correo = rs.getString("paciente_correo");
					String otros = rs.getString("paciente_otros");

//					if(!apellido.isBlank() && !apellido.isEmpty())
//						apellido= rs.getString("paciente_apellido");
//					else valido=false;
//					if(!nombre.isBlank() && !nombre.isEmpty())
//						nombre= rs.getString("paciente_nombre");
//					else valido=false;
//					if(!telefono.isBlank() && !telefono.isEmpty())
//						telefono= rs.getString("paciente_telefono");
//					else valido=false;
//					if(!correo.isBlank() && !correo.isEmpty())
//						correo= rs.getString("paciente_correo");
//					else valido=false;

					// System.out.println(id + nombre + apellido + telefono +
					// correo + otros);

					if (valido)
						pacientes.add(new Paciente(Integer.parseInt(id), nombre, apellido, Integer.parseInt(telefono),
								correo, otros));
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
		return pacientes;
	}

	public void crearCita(Cita cita, ArrayList<Medico> medicos) {

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement("insert into cita values (?,?,?,?,?,?,?,?,?,?,?,?)");
			try {
				pst.setString(1, cita.getIdCita() + "");
				pst.setString(2, cita.getIdPaciente() + "");
				pst.setString(3, cita.getFecha());
				pst.setString(4, cita.getHoraE());
				pst.setString(5, cita.getHoraS());
				pst.setBoolean(6, cita.isUrgente());
				pst.setString(7, cita.getSala() + "");
				pst.setString(8, cita.getTelefonoCita() + "");
				pst.setString(9, cita.getCorreoCita());
				pst.setString(10, cita.getOtrosCita());
				pst.setBoolean(11, cita.isAcudio());
				pst.setString(12, cita.getCausaCita());
				pst.execute();

			} catch (SQLException e) {
				throw new Error("Error al meter la cita", e);
			}

			pst = conn.prepareStatement("insert into medico_cita values(?,?)");
			try {

				for (Medico m : medicos) {
					pst.setString(1, m.getId());
					pst.setString(2, cita.getIdCita() + "");
					pst.execute();
				}

			} catch (SQLException e) {
				throw new Error("Error al linkear medico-cita", e);
			} finally {
				pst.close();
				conn.close();
			}

		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
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

	/**
	 * Guarda la jornada en base de datos.
	 * 
	 * @param j Jornada conteniendo la jornada a guardar.
	 */
	public void guardarJornada(Jornada j) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				s.executeUpdate(String.format(GUARDAR_JORNADA, j.getId(), j.getDias(), j.getHoraComienzo(),
						j.getHoraFinal(), j.getDiaInicio(), j.getDiaFinal(), j.getMedico().getId()));
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

	/**
	 * Dado el id de una cita devuelve el objeto Cita de la misma.
	 * 
	 * @param id String conteniendo el id a consultar.
	 * @return Cita con la cita conveniente.
	 */
	public Cita getCita(String id) {
		Cita c = null;
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = conn.prepareStatement(QUERY_CITA_CON_ID);
			try {
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int idI = Integer.parseInt(id);
					int idPaciente = Integer.parseInt(rs.getString("cita_paciente_id"));
					String fecha = rs.getString("cita_fecha");
					String hora_inicio = rs.getString("cita_hora_inicio");
					String hora_fin = rs.getString("cita_hora_fin");
					boolean urgente = rs.getBoolean("cita_urgente");
					int sala = Integer.parseInt(rs.getString("cita_sala_id"));
					String nombrePaciente = getNombrePaciente(idPaciente);
					int telefonoCita = Integer.parseInt(rs.getString("cita_telefono"));
					String correoCita = rs.getString("cita_correo");
					String otrosCita = rs.getString("cita_otros");
					boolean acudio = rs.getBoolean("cita_acudio");
					String causa = rs.getString("cita_causa");
					c = new Cita(idI, idPaciente, fecha, hora_inicio, hora_fin, urgente, sala, telefonoCita, correoCita,
							otrosCita, acudio, causa);
					c.setNombrePaciente(nombrePaciente);
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

	/**
	 * Devuelve el nombre de un paciente dado su id
	 * 
	 * @param id int conteniendo el id a consultar.
	 * @return String con el nombre a devolver.
	 */
	private String getNombrePaciente(int id) {
		String nombrePaciente = "";
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = conn.prepareStatement(QUERY_NOMBRE_PACIENTE_CON_ID);
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

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, Integer.parseInt(telefono),
							correo, otros, acudio, causa));
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

	public int generarIdCita() {
		int id = 400;
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select max(cita_id) from cita");
				if (rs.next()) {
					id = Integer.parseInt(rs.getString(1));
				}
			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
		return ++id;
	}

	public List<Cita> getCitasPorMedicoYFecha(String idMedico, String fecha) {

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

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, Integer.parseInt(telefono),
							correo, otros, acudio, causa));
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

	/**
	 * Dado un objeto Cita, lo guarda en base de datos.
	 * 
	 * @param c Cita conteniendo el objeto a guardar.
	 */
	public void guardarCita(Cita c) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				s.executeUpdate(String.format(GUARDAR_CITA, c.getIdCita(), c.getIdPaciente(), c.getFecha(),
						c.getHoraE(), c.getHoraS(), boolToBit(c.isUrgente()), c.getSala(), c.getTelefonoCita(),
						c.getCorreoCita(), c.getOtrosCita(), boolToBit(c.isAcudio()), c.getCausaCita()));
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

	/**
	 * Método llamado para hacer set de las causas y si el paciente acudió a la
	 * cita.
	 * 
	 * @param c Cita que se quiere actualizar.
	 */
	public void actualizarCita(Cita c) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				s.executeUpdate(String.format(ACTUALIZAR_CITA_ACUDE, boolToBit(c.isAcudio()), c.getIdCita()));
				s.executeUpdate(String.format(ACTUALIZAR_CITA_CAUSAS, c.getCausaCita(), c.getIdCita()));
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

	/**
	 * Método que devuelve todos los ids de cada jornada en la base de datos.
	 * 
	 * @return List<String> con dichos ids.
	 */
	public List<String> cargarJornadaId() {
		ArrayList<String> ids = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery(QUERY_ID_JORNADA);
				while (rs.next()) {
					ids.add(rs.getString("jornada_id"));
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
		return ids;
	}

	private int boolToBit(boolean b) {
		return b ? 1 : 0;
	}
}
