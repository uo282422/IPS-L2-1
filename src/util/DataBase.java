package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.Jornada;
import logic.Medico;

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
}
