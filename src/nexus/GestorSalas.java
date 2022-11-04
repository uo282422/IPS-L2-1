package nexus;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import logic.Sala;
import logic.cita.Cita;
import util.DataBase;

public class GestorSalas {
	private List<Sala>listaSalas;
	private DataBase bd;
	private GestorCitas gC;
	public GestorSalas(GestorCitas gC) {
		this.bd=new DataBase();
		listaSalas=bd.cargarSalas();
		this.gC=gC;
	}
	
	public Sala cargarSala(int id) {
        return bd.cargarSalaPorId(id);
    }
	
	public List<Sala> getListaSalas(){
		return listaSalas;
	}

	public boolean comprobarSala(int sala, String fecha, String horaE, String horaS) {
		
		Date fechaCandidato = fechaToDate(fecha);
		Date horaECandidato = horaToDate(horaE);
		Date horaSCandidato = horaToDate(horaS);
		
		for(Cita c: gC.getListaCitas()) {
			if(c.getSala()==sala) {//la sala esta ocupada en algun moento
				if(fechaToDate(c.getFecha()).equals(fechaCandidato)) {//si la fecha es la de hoy,ocupada hoy, pasamos a comprobar la hora
					if ((horaECandidato.after(horaToDate(c.getHoraS())) || horaSCandidato
							.before(horaToDate(c.getHoraE())) == false)){
						
						return false;
					}
						
				}
			}else return true;//la sala esta libre
		}
		
		return false;
	}
	
	
	
	
	
	
	
	public Date fechaToDate(String fecha) {
		String[] sep = fecha.split("/");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sep[0]));
		c.set(Calendar.MONTH, Integer.parseInt(sep[1]) - 1);
		c.set(Calendar.YEAR, Integer.parseInt(sep[2]));

		Date f = c.getTime();
		return f;
	}

	/*
	 * 
	 * HH:MM 0 1
	 */
	public Date horaToDate(String e) {
		String[] sep = e.split(":");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sep[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(sep[1]));
		Date h = c.getTime();
		return h;
	}
}
	
	
