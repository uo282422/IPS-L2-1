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
	
}
