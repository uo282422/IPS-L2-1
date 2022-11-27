package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.Baja;
import logic.Enfermedad;
import logic.Medico;
import logic.Paciente;
import logic.Prescripcion;
import logic.Sala;
import logic.Seguimiento;
import logic.Vacuna;
import logic.cita.Cita;
import logic.cita.Enum_acudio;
import logic.diagnostico.Diagnostico;
import logic.diagnostico.DiagnosticoCapitulo;
import logic.diagnostico.DiagnosticoGrupo;
import logic.diagnostico.DiagnosticoSubgrupo;
import logic.diagnostico.DiagnosticoTabla;
import logic.jornada.Calendario;
import logic.jornada.Jornada;
import logic.procedimiento.Procedimiento;
import logic.procedimiento.ProcedimientoSeccion;
import logic.procedimiento.ProcedimientoSistema;
import logic.procedimiento.ProcedimientoTipo;
import ui.Especialidad;

public class DataBase {
	private String url = "jdbc:hsqldb:hsql://localhost";
	private String user = "sa";
	private String pass = "";

	private static final String QUERY_CITA_CON_ID = "SELECT * FROM cita WHERE cita_id = ?";
	private static final String QUERY_NOMBRE_PACIENTE_CON_ID = "SELECT paciente_nombre FROM paciente WHERE paciente_id = ?";
	private static final String GUARDAR_CITA = "INSERT INTO cita VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GUARDAR_JORNADA = "INSERT INTO jornada VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String ACTUALIZAR_CITA_ACUDE = "UPDATE cita SET cita_acudio = ? WHERE cita_id = ?";
	private static final String QUERY_ID_JORNADA = "SELECT jornada_id FROM jornada";
	private static final String QUERY_CAUSAS = "SELECT causa FROM causacita WHERE cita_id = ?";
	private static final String ACTUALIZAR_CAUSAS = "INSERT INTO causacita (cita_id, causa) VALUES(?,?)";
	private static final String QUERY_ALL_CAUSAS = "SELECT causa FROM causa";
	private static final String NUEVA_CAUSA = "INSERT INTO causa VALUES(?)";
	private static final String LISTAR_PRESC = "SELECT prescripcion_id, prescripcion_nombre from prescripcion";
	private static final String BUSCAR_BAJAS_PORMEDICO = "SELECT * FROM baja WHERE baja_medico_id = ?";
	private static final String BUSCAR_BAJAS_ID = "SELECT baja_id FROM baja";
	private static final String GUARDAR_BAJA = "INSERT INTO baja VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ACTUALIZAR_BAJA = "UPDATE baja SET baja_tipo = ?, baja_fecha_inicio = ?, baja_fecha_fin = ?, baja_hora_inicio = ?, baja_hora_fin = ?, baja_observaciones = ?, baja_medico_id = ? WHERE baja_id = ?";
	private static final String CARGAR_CALENDARIOS = "SELECT * FROM CALENDARIO";
	private static final String BUSCAR_CALENDARIOS_ID = "SELECT CALENDARIO_ID FROM CALENDARIO";
	private static final String GUARDAR_CALENDARIO = "INSERT INTO CALENDARIO VALUES(?, ?)";
	private static final String GUARDAR_MEDICO_CALENDARIO = "INSERT INTO MEDICO_CALENDARIO VALUES(?, ?)";
	private static final String ELIMINAR_CALENDARIO = "DELETE FROM CALENDARIO WHERE CALENDARIO_ID = ?";
	private static final String JORNADAS_CARGAR = "SELECT * FROM JORNADA";
	private static final String CARGAR_JORNADAS_PARA_CALENDARIO = "SELECT * FROM JORNADA WHERE JORNADA_ID IN (SELECT JORNADA_ID FROM JORNADA_CALENDARIO WHERE CALENDARIO_ID = ?)";
	private static final String CARGAR_JORNADAS_PARA_MEDICO = "SELECT * FROM JORNADA WHERE JORNADA_ID IN (SELECT JORNADA_ID FROM JORNADA_CALENDARIO WHERE CALENDARIO_ID IN (SELECT CALENDARIO_ID FROM MEDICO_CALENDARIO WHERE MEDICO_ID = ?))";
	private static final String GUARDAR_JORNADA_PARA_CALENDARIO = "INSERT INTO JORNADA_CALENDARIO VALUES (?, ?)";
	private static final String EDITAR_CALENDARIO = "UPDATE CALENDARIO SET CALENDARIO_NOMBRE = ? WHERE CALENDARIO_ID = ?";
	private static final String BORRAR_JORNADAS_PARA_CALENDARIO = "DELETE FROM JORNADA_CALENDARIO WHERE CALENDARIO_ID = ?";
	private static final String CARGAR_TABLAS = "SELECT * FROM DIAGNOSTICO_TABLA";
	private static final String CARGAR_GRUPOS_POR_TABLA = "SELECT * FROM DIAGNOSTICO_GRUPO WHERE TABLA_ID = ?";
	private static final String CARGAR_SUBGRUPOS_POR_GRUPO = "SELECT * FROM DIAGNOSTICO_SUBGRUPO WHERE GRUPO_ID = ?";
	private static final String CARGAR_CAPITULOS_POR_SUBGRUPO = "SELECT * FROM DIAGNOSTICO_CAPITULO WHERE SUBGRUPO_ID = ?";
	private static final String GUARDAR_DIAGNOSTICOS = "INSERT INTO DIAGNOSTICO VALUES(?,?,?,?,?)";
	private static final String CARGAR_SECCIONES = "SELECT * FROM PROCEDIMIENTO_SECCION";
	private static final String CARGAR_SISTEMAS_POR_SECCION = "SELECT * FROM PROCEDIMIENTO_SISTEMA WHERE SECCION_ID = ?";
	private static final String CARGAR_TIPOS_POR_SISTEMA = "SELECT * FROM PROCEDIMIENTO_TIPO WHERE SISTEMA_ID = ?";
	private static final String GUARDAR_PROCEDIMIENTOS = "INSERT INTO PROCEDIMIENTO VALUES(?,?,?,?)";

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
					String esp = rs.getString("medico_especialidad_id");

					String dni = rs.getString("medico_dni");
					String coleg = rs.getString("medico_colegiado");

					medicos.add(new Medico(id, nombre, apellido, email, esp, dni, coleg));
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

	public List<Diagnostico> cargarDiagnosticos() {
		ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from diagnostico");
				while (rs.next()) {
					String citaid = rs.getString("cita_id");
					String apartadoid = rs.getString("capitulo_id");
					String fecha = rs.getString("diagnostico_fecha");
					String hora = rs.getString("diagnostico_hora");
					boolean seg = rs.getBoolean("seguimiento");

					diagnosticos.add(new Diagnostico(citaid, apartadoid, fecha, hora, seg));
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
		return diagnosticos;
	}

	public List<Seguimiento> cargarSeguimientos() {
		ArrayList<Seguimiento> seguimientos = new ArrayList<Seguimiento>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from seguimiento");
				while (rs.next()) {
					String id = rs.getString("seguimiento_id");
					String cita = rs.getString("cita_id");
					String esta = rs.getString("seguimiento_estado");
					String com = rs.getString("seguimiento_comentarios");

					seguimientos.add(new Seguimiento(id, cita, esta, com));
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
		return seguimientos;
	}

	public List<Jornada> getJornadasDeMedico(String medId) {

		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery(CARGAR_JORNADAS_PARA_MEDICO);
				while (rs.next()) {
					String id = rs.getString("jornada_id");
					String dias = rs.getString("jornada_dias");
					String horaI = rs.getString("jornada_hora_inicio");
					String horaF = rs.getString("jornada_hora_fin");
					String inicio = rs.getString("JORNADA_INCIO");
					String fin = rs.getString("JORNADA_FIN");

					jornadas.add(new Jornada(id, dias, horaI, horaF, medId, inicio, fin));

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

					String dni = rs.getString("paciente_dni");
					String nhc = rs.getString("paciente_nhc");
					String tarjeta = rs.getString("paciente_tarjeta");

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
								correo, otros, dni, nhc, tarjeta));
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

	public void crearCita(Cita cita, ArrayList<Medico> medicos, ArrayList<Especialidad> especialidades) {

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			PreparedStatement pst = conn
					.prepareStatement("insert into cita values (?,?,?,?,?,?,?,?,?,?,?,?,NULL, NULL)");

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
				pst.setInt(11, serializeAcudio(cita.isAcudio()));
				pst.setString(12, cita.getMotivosIniciales());

				pst.execute();

			} catch (SQLException e) {
				throw new Error("Error al meter la cita", e);
			}
			setCausas(cita.getIdCita(), cita.getCausas());
			pst = conn.prepareStatement("insert into medico_cita values(?,?)");
			try {

				for (Medico m : medicos) {
					pst.setString(1, m.getId());
					pst.setString(2, cita.getIdCita() + "");
					pst.execute();
				}

			} catch (SQLException e) {
				throw new Error("Error al linkear medico-cita", e);
			}

			pst = conn.prepareStatement("insert into especialidad_cita values(?,?,?)");
			try {

				for (Especialidad e : especialidades) {
					pst.setString(1, cita.getIdCita() + "");
					pst.setString(2, e.getId_esp());
					pst.setInt(3, e.getUnidades());
					pst.execute();
				}

			} catch (SQLException e) {
				throw new Error("Error al linkear epecialidad-cita", e);

			} finally {
				pst.close();
				conn.close();
			}
			
			pst = conn.prepareStatement(GUARDAR_DIAGNOSTICOS);
			try {

				for (Diagnostico d : cita.getDiagnosticos()) {
					pst.setString(1, String.valueOf(cita.getIdCita()));
					pst.setString(2, d.getcapitulo_id());
					pst.setString(3, d.getFecha());
					pst.setString(4, d.getHora());
					pst.setBoolean(5, d.getSeguimiento());
					pst.execute();
				}

			} catch (SQLException e) {
				throw new Error("Error al crear diagnosticos", e);

			} finally {
				pst.close();
				conn.close();
			}
			
			pst = conn.prepareStatement(GUARDAR_PROCEDIMIENTOS);
			try {

				for (Procedimiento p : cita.getProcedimientos()) {
					pst.setString(1, String.valueOf(cita.getIdCita()));
					pst.setString(2, p.getTipoId());
					pst.setString(3, p.getFecha());
					pst.setString(4, p.getHora());
					pst.execute();
				}

			} catch (SQLException e) {
				throw new Error("Error al crear procedimientos", e);

			} finally {
				pst.close();
				conn.close();
			}

		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
	}

	private void setCausas(int idCita, List<String> causas) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement(ACTUALIZAR_CAUSAS);
			for (String c : causas) {
				try {
					pst.setString(1, idCita + "");
					pst.setString(2, c);
					pst.execute();

				} catch (SQLException e) {
					throw new Error("Error al meter la cita", e);
				}
			}

		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
	}

	private int serializeAcudio(Enum_acudio acudio) {
		switch (acudio) {
		case ACUDE:
			return 1;
		case NO_ACUDE:
			return 2;
		default:
			return 0;
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

					String dni = rs.getString("paciente_dni");
					String nhc = rs.getString("paciente_nhc");
					String tarjeta = rs.getString("paciente_tarjeta");

					paciente = new Paciente(id, nombre, apellido, telefono, correo, otros, dni, nhc, tarjeta);
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
			PreparedStatement s = conn.prepareStatement(GUARDAR_JORNADA);
			try {
				s.setString(1, j.getId());
				s.setString(2, j.getNombre());
				s.setString(3, j.getDias());
				s.setString(4, j.getHoraComienzo());
				s.setString(5, j.getHoraFinal());
				s.setString(6, j.getInicio());
				s.setString(7, j.getFin());
				s.executeUpdate();
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
					String motivos = rs.getString("cita_motivos");
					int acudio = rs.getInt("cita_acudio");
					List<String> causas = getCausas(id);
					c = new Cita(idI, idPaciente, fecha, hora_inicio, hora_fin, urgente, sala, telefonoCita, correoCita,
							otrosCita, parseAcudio(acudio), causas, motivos);
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

	private List<String> getCausas(String id) {
		List<String> causas = new ArrayList<String>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = conn.prepareStatement(QUERY_CAUSAS);
			try {
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					causas.add(rs.getString("causa"));
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
		return causas;
	}

	private Enum_acudio parseAcudio(int acudio) {
		switch (acudio) {
		case 1:
			return Enum_acudio.ACUDE;
		case 2:
			return Enum_acudio.NO_ACUDE;
		default:
			return Enum_acudio.NO_FIGURA;
		}
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
					int acudio = rs.getInt("CITA_ACUDIO");
					List<String> causas = getCausas(rs.getString("cita_id"));
					String motivos = rs.getString("cita_motivos");

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, Integer.parseInt(telefono),
							correo, otros, parseAcudio(acudio), causas, motivos));
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
					String esp = rs.getString("MEDICO_ESPECIALIDAD_ID");

					String dni = rs.getString("medico_dni");
					String coleg = rs.getString("medico_colegiado");

					medicos.add(new Medico(id, nombre, apellido, email, esp, dni, coleg));
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
					int acudio = rs.getInt("CITA_ACUDIO");
					List<String> causas = getCausas(rs.getString("cita_id"));
					String motivos = rs.getString("cita_motivos");

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, Integer.parseInt(telefono),
							correo, otros, parseAcudio(acudio), causas, motivos));
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
			PreparedStatement s = conn.prepareStatement(GUARDAR_CITA);
			try {
				s.setString(1, c.getIdCita() + "");
				s.setString(2, c.getIdPaciente() + "");
				s.setString(3, c.getFecha());
				s.setString(4, c.getHoraE());
				s.setString(5, c.getHoraS());
				s.setInt(6, boolToBit(c.isUrgente()));
				s.setString(7, c.getSala() + "");
				s.setString(8, c.getTelefonoCita() + "");
				s.setString(9, c.getCorreoCita());
				s.setString(10, c.getOtrosCita());
				s.setInt(11, serializeAcudio(c.isAcudio()));
				s.executeUpdate();
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
			PreparedStatement s = conn.prepareStatement(ACTUALIZAR_CITA_ACUDE);
			PreparedStatement s2 = conn.prepareStatement(ACTUALIZAR_CAUSAS);
			try {
				s.setInt(1, serializeAcudio(c.isAcudio()));
				s.setString(2, c.getIdCita() + "");
				s.executeUpdate();
				s2.setString(1, c.getIdCita() + "");
				for (String causa : c.getCausas()) {
					s2.setString(2, causa);
					s2.executeUpdate();
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

	public List<Especialidad> cargarEspecialidades() {
		ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn
					.prepareStatement("select especialidad_id, especialidad_nombre from especialidad");

			try {

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					especialidades.add(
							new Especialidad(rs.getString("especialidad_id"), rs.getString("especialidad_nombre")));

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

		return especialidades;
	}

	public List<String> cargarTodasCausas() {
		ArrayList<String> causas = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery(QUERY_ALL_CAUSAS);
				while (rs.next()) {
					causas.add(rs.getString("causa"));
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
		return causas;
	}

	public List<Prescripcion> cargarPrescripciones() {
		ArrayList<Prescripcion> prescripciones = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery(LISTAR_PRESC);
				while (rs.next()) {
					prescripciones.add(new Prescripcion(rs.getString(1), rs.getString(2)));
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
		return prescripciones;
	}

	public void nuevaCausa(String causa) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(NUEVA_CAUSA);
			try {
				s.setString(1, causa);
				s.execute();
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
	 * Devuelve una lista con las bajas asignadas a un medico
	 * 
	 * @param m Medico para el que se buscan las bajas
	 * @return List<Baja> conteniendo las bajas del medico buscado
	 */
	public List<Baja> getBajas(Medico m) {
		List<Baja> bajas = new ArrayList<Baja>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(BUSCAR_BAJAS_PORMEDICO);
			ResultSet rs = null;
			try {
				s.setString(1, m.getId());
				rs = s.executeQuery();
				while (rs.next()) {
					bajas.add(parseBaja(rs, m));
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
		return bajas;
	}

	private Baja parseBaja(ResultSet rs, Medico m) throws SQLException {
		Baja b = new Baja();
		b.setMedico(m);
		b.setId(rs.getString("baja_id"));
		b.setfInicio(rs.getString("baja_fecha_inicio"));
		b.setfFin(rs.getString("baja_fecha_fin"));
		b.sethInicio(rs.getString("baja_hora_inicio"));
		b.sethFin(rs.getString("baja_hora_fin"));
		b.setObservaciones(rs.getString("baja_observaciones"));
		return b;
	}

	public String generarIdBaja() {
		String id = "";
		List<String> ids = new ArrayList<String>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(BUSCAR_BAJAS_ID);
			ResultSet rs = null;
			try {
				rs = s.executeQuery();
				while (rs.next()) {
					ids.add(rs.getString("baja_id"));
				}
				if (ids.isEmpty())
					id = "100";
				else
					id = (Integer.parseInt(ids.get(ids.size() - 1)) + 1) + "";

			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
		return id;
	}

	public void guardarBaja(Baja b) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(GUARDAR_BAJA);
			try {
				s.setString(1, b.getId());
				s.setString(2, b.getTipo().toString());
				s.setString(3, b.getfInicio());
				s.setString(4, b.getfFin());
				s.setString(5, b.gethInicio());
				s.setString(6, b.gethFin());
				s.setString(7, b.getObservaciones());
				s.setString(8, b.getMedico().getId());
				s.executeUpdate();
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

	public void actualizarBaja(Baja b) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(ACTUALIZAR_BAJA);
			try {
				s.setString(1, b.getTipo().toString());
				s.setString(2, b.getfInicio());
				s.setString(3, b.getfFin());
				s.setString(4, b.gethInicio());
				s.setString(5, b.gethFin());
				s.setString(6, b.getObservaciones());
				s.setString(7, b.getMedico().getId());
				s.setString(8, b.getId());
				s.executeUpdate();
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

	public List<Calendario> cargarCalendarios() {
		ArrayList<Calendario> cs = new ArrayList<Calendario>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(CARGAR_CALENDARIOS);
			ResultSet rs = null;
			try {
				rs = s.executeQuery();
				while (rs.next()) {
					Calendario c = new Calendario(rs.getString("CALENDARIO_ID"), rs.getString("CALENDARIO_NOMBRE"));
					List<Jornada> jornadas = cargarJornadasParaCalendario(c);
					c.setJornadas(jornadas);
					cs.add(c);
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
		return cs;
	}

	private List<Jornada> cargarJornadasParaCalendario(Calendario c) {
		ArrayList<Jornada> js = new ArrayList<Jornada>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(CARGAR_JORNADAS_PARA_CALENDARIO);
			ResultSet rs = null;
			try {
				s.setString(1, c.getId());
				rs = s.executeQuery();
				while (rs.next()) {
					String id = rs.getString("JORNADA_ID");
					String nombre = rs.getString("JORNADA_NOMBRE");
					String dias = rs.getString("JORNADA_DIAS");
					String horaInicio = rs.getString("JORNADA_HORA_INICIO");
					String horaFin = rs.getString("JORNADA_HORA_FIN");
					String inicio = rs.getString("JORNADA_INICIO");
					String fin = rs.getString("JORNADA_FIN");
					js.add(new Jornada(id, nombre, dias, horaInicio, horaFin, inicio, fin));
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
		return js;
	}

	public String generarIdCalendario() {
		String id = "";
		List<String> ids = new ArrayList<String>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(BUSCAR_CALENDARIOS_ID);
			ResultSet rs = null;
			try {
				rs = s.executeQuery();
				while (rs.next()) {
					ids.add(rs.getString("calendario_id"));
				}
				if (ids.isEmpty())
					id = "800";
				else
					id = (Integer.parseInt(ids.get(ids.size() - 1)) + 1) + "";

			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
		return id;
	}

	public void guardarCalendario(Calendario c) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(GUARDAR_CALENDARIO);
			try {
				s.setString(1, c.getId());
				s.setString(2, c.getNombre());
				s.executeUpdate();
				for (Jornada j : c.getJornadas()) {
					s = conn.prepareStatement(GUARDAR_JORNADA_PARA_CALENDARIO);
					s.setString(1, j.getId());
					s.setString(2, c.getId());
					s.executeUpdate();
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
	}

	public void eliminarCalendario(Calendario c) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(ELIMINAR_CALENDARIO);
			try {
				s.setString(1, c.getId());
				s.executeUpdate();
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

	public ArrayList<Cita> cargarCitas() {
		ArrayList<Cita> citas = new ArrayList<Cita>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement("select * from cita");
			try {

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
					int acudio = rs.getInt("CITA_ACUDIO");
					List<String> causas = getCausas(rs.getString("cita_id"));
					String motivos = rs.getString("cita_motivos");

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, Integer.parseInt(telefono),
							correo, otros, parseAcudio(acudio), causas, motivos));
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

	public List<Jornada> cargarJornadas() {
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement pst = conn.prepareStatement(JORNADAS_CARGAR);
			try {

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					String id = rs.getString("JORNADA_ID");
					String nombre = rs.getString("JORNADA_NOMBRE");
					String dias = rs.getString("JORNADA_DIAS");
					String horaInicio = rs.getString("JORNADA_HORA_INICIO");
					String horaFin = rs.getString("JORNADA_HORA_FIN");
					String inicio = rs.getString("JORNADA_INICIO");
					String fin = rs.getString("JORNADA_FIN");

					jornadas.add(new Jornada(id, nombre, dias, horaInicio, horaFin, inicio, fin));
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

		return jornadas;
	}

	public void guardarCalendarioParaMedicos(Calendario c, List<Medico> meds) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(GUARDAR_MEDICO_CALENDARIO);
			try {
				for (Medico m : meds) {
					s.setString(1, m.getId());
					s.setString(2, c.getId());
					s.executeUpdate();
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
	}

	public void editarCalendario(Calendario c) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(EDITAR_CALENDARIO);
			try {
				s.setString(2, c.getId());
				s.setString(1, c.getNombre());
				s.executeUpdate();
				s = conn.prepareStatement(BORRAR_JORNADAS_PARA_CALENDARIO);
				s.setString(1, c.getId());
				s.executeUpdate();
				for (Jornada j : c.getJornadas()) {
					s = conn.prepareStatement(GUARDAR_JORNADA_PARA_CALENDARIO);
					s.setString(1, j.getId());
					s.setString(2, c.getId());
					s.executeUpdate();
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
	}

	public String generarIdSeguimiento() {
		String id = "";
		List<String> ids = new ArrayList<String>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement("SELECT SEGUIMIENTO_ID FROM SEGUIMIENTO");
			ResultSet rs = null;
			try {
				rs = s.executeQuery();
				while (rs.next()) {
					ids.add(rs.getString("seguimiento_id"));
				}
				if (ids.isEmpty())
					id = "1000";
				else
					id = (Integer.parseInt(ids.get(ids.size() - 1)) + 1) + "";

			} catch (SQLException e) {
				throw new Error("Problem", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
		return id;
	}

	public void insertarSeguimiento(Seguimiento seg) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement("INSERT INTO seguimiento VALUES (?, ?, ?, ?)");
			try {
				s.setString(1, seg.getId_seguimiento());
				s.setString(2, seg.getId_cita());
				s.setString(3, seg.getEstado());
				s.setString(4, seg.getComentarios());

				s.execute();
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

	public void actualizarEstadoSeguimiento(String estado, String com, String citaId) {
		System.out.println(estado + " " + com + " " + citaId);
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement(
					"UPDATE seguimiento SET seguimiento_estado = ?, seguimiento_comentarios = ? WHERE cita_id = ?");
			try {
				s.setString(1, estado);
				s.setString(2, com);
				s.setString(3, citaId);
				s.executeUpdate();

			} catch (SQLException e) {
				throw new Error("Problem1", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem2", e);
		}

	}

	public void actualizarSeguimientoDiagnostico(boolean b, String citaId) {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement s = conn.prepareStatement("UPDATE diagnostico SET seguimiento = ? WHERE cita_id = ?");
			try {
				s.setBoolean(1, b);
				s.setString(2, citaId);
				s.executeUpdate();

			} catch (SQLException e) {
				throw new Error("Problem1", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problem2", e);
		}

	}

	public List<DiagnosticoTabla> cargarTablas() {

		ArrayList<DiagnosticoTabla> tablas = new ArrayList<DiagnosticoTabla>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery(CARGAR_TABLAS);
				while (rs.next()) {

					String id = rs.getString("tabla_id");
					String nombre = rs.getString("tabla_descripcion");

					tablas.add(new DiagnosticoTabla(id, nombre));

				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar tablas", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para tablas", e);
		}

		return tablas;

	}

	public List<DiagnosticoGrupo> cargarGrupoPorTabla(String idTabla) {

		ArrayList<DiagnosticoGrupo> grupos = new ArrayList<DiagnosticoGrupo>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(CARGAR_GRUPOS_POR_TABLA);
				ps.setString(1, idTabla);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {

					String id = rs.getString("grupo_id");
					String nombre = rs.getString("grupo_descripcion");

					grupos.add(new DiagnosticoGrupo(id, nombre));

				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar grupos", e);
			} finally {
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para grupos", e);
		}

		return grupos;
	}

	public List<DiagnosticoSubgrupo> cargarSubgrupoPorGrupo(String idGrupo) {
		ArrayList<DiagnosticoSubgrupo> subgrupos = new ArrayList<DiagnosticoSubgrupo>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(CARGAR_SUBGRUPOS_POR_GRUPO);
				ps.setString(1, idGrupo);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {

					String id = rs.getString("subgrupo_id");
					String nombre = rs.getString("subgrupo_descripcion");
					boolean ignorar = rs.getBoolean("subgrupo_ignorar");
					
					if (!ignorar) {
						subgrupos.add(new DiagnosticoSubgrupo(id, nombre));
					}
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar subgrupos", e);
			} finally {
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para subgrupos", e);
		}

		return subgrupos;
	}

	public List<DiagnosticoCapitulo> cargarCapitulosPorSubgrupo(String idSubgrupo) {
		ArrayList<DiagnosticoCapitulo> capitulos = new ArrayList<DiagnosticoCapitulo>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(CARGAR_CAPITULOS_POR_SUBGRUPO);
				ps.setString(1, idSubgrupo);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {

					String id = rs.getString("capitulo_id");
					String nombre = rs.getString("capitulo_descripcion");
					
					capitulos.add(new DiagnosticoCapitulo(id, nombre));
				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar capitulos", e);
			} finally {
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para capitulos", e);
		}

		return capitulos;
	}

	public List<ProcedimientoSeccion> cargarSecciones() {
		ArrayList<ProcedimientoSeccion> secciones = new ArrayList<ProcedimientoSeccion>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery(CARGAR_SECCIONES);
				while (rs.next()) {

					String id = rs.getString("seccion_id");
					String nombre = rs.getString("seccion_descripcion");

					secciones.add(new ProcedimientoSeccion(id, nombre));

				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar secciones", e);
			} finally {
				s.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para secciones", e);
		}

		return secciones;
	}

	public List<ProcedimientoSistema> cargarSistemasPorSeccion(String idSeccion) {
		ArrayList<ProcedimientoSistema> sistemas = new ArrayList<ProcedimientoSistema>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(CARGAR_SISTEMAS_POR_SECCION);
				ps.setString(1, idSeccion);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {

					String id = rs.getString("sistema_id");
					String nombre = rs.getString("sistema_descripcion");

					sistemas.add(new ProcedimientoSistema(id, nombre));

				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar sistemas", e);
			} finally {
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para sistemas", e);
		}

		return sistemas;
	}

	public List<ProcedimientoTipo> cargarTiposPorSistema(String idSistema) {
		ArrayList<ProcedimientoTipo> tipos = new ArrayList<ProcedimientoTipo>();

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(CARGAR_TIPOS_POR_SISTEMA);
				ps.setString(1, idSistema);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {

					String id = rs.getString("tipo_id");
					String nombre = rs.getString("tipo_descripcion");

					tipos.add(new ProcedimientoTipo(id, nombre));

				}
				rs.close();
			} catch (SQLException e) {
				throw new Error("Problema al cargar tipos", e);
			} finally {
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new Error("Problema conexion para tipos", e);
		}

		return tipos;
	}

}
