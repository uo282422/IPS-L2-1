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
import logic.Paciente;
import logic.Sala;

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
	
	
	public List<Jornada> getJornadasDeMedico(String medId) {
		
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs = s.executeQuery("select * from jornada where jornada_medico_id='"+medId+"'");
				while (rs.next()) {
					String id = rs.getString("jornada_id");
					String dias = rs.getString("jornada_dias");
					String horaI = rs.getString("jornada_hora_inicio");
					String horaF = rs.getString("jornada_hora_fin");
					String diaI= rs.getString("jornada_dia_inicio");
					String diaF= rs.getString("jornada_dia_fin");
					
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
	
	
	
	
	
	
	public List<Sala>cargarSalas(){
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

					//System.out.println(id + nombre + apellido + telefono + correo + otros);

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
				pst.setString(1,cita.getIdCita()+"");
				pst.setString(2, cita.getIdPaciente()+"");
				pst.setString(3,cita.getFecha());
				pst.setString(4,cita.getHoraE());
				pst.setString(5,cita.getHoraS());
				pst.setBoolean(6,cita.isUrgente());
				pst.setString(7,cita.getSala()+"");
				pst.setString(8,cita.getTelefonoCita()+"");
				pst.setString(9,cita.getCorreoCita());
				pst.setString(10,cita.getOtrosCita());
				pst.setBoolean(11,cita.isAcudio());
				pst.setString(12,cita.getCausaCita());
				pst.execute();
				
				
				
			} catch (SQLException e) {
				throw new Error("Error al meter la cita", e);
			} 
			
			pst=conn.prepareStatement("insert into medico_cita values(?,?)");
			try {
				
				for(Medico m:medicos) {
					pst.setString(1, m.getId());
					pst.setString(2, cita.getIdCita()+"");
					pst.execute();
				}
				
				
				
			}catch(SQLException e) {
				throw new Error("Error al linkear medico-cita", e);
			}finally {
				pst.close();
				conn.close();
			}
			
			
		} catch (SQLException e) {
			throw new Error("Problem", e);
		}
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

	public int generarIdCita() {
		int id=400;
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			Statement s = conn.createStatement();
			try {
				ResultSet rs=s.executeQuery("select max(cita_id) from cita");
				if(rs.next()) {
					id=Integer.parseInt(rs.getString(1));
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
			PreparedStatement pst = conn.prepareStatement("select * from cita c, medico_cita m where c.cita_id = m.cita_id and m.medico_id = ? and c.cita_fecha = ?");
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

					citas.add(new Cita(id, pacienteId, fecha, horaI, horaF, urgente, salaId, Integer.parseInt(telefono), correo, otros,
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
	
}
