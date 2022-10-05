package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import logic.GestorJornada;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AsignarJornada extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel medPn;
	private JPanel jornadaPn;
	private JLabel diaFLb;
	private JLabel diaILb;
	private JLabel medLb;
	private JLabel hInicioLb;
	private JLabel hFinalLb;
	private JLabel diasLb;
	private GestorJornada gestorJornada = new GestorJornada();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AsignarJornada dialog = new AsignarJornada();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AsignarJornada() {
		setBounds(100, 100, 952, 558);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			medPn = new JPanel();
		}
		{
			jornadaPn = new JPanel();
		}
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPanel.add(medPn);
		medPn.setLayout(new GridLayout(8, 0, 0, 0));
		{
			medLb = new JLabel("Médico:");
			medPn.add(medLb);
		}
		{
			JComboBox<String> medCmb = new JComboBox<>();
			medLb.setLabelFor(medCmb);
			medPn.add(medCmb);
		}
		{
			hInicioLb = new JLabel("Hora de inicio: ");
			medPn.add(hInicioLb);
		}
		{
			JComboBox<String> hInicioCmb = new JComboBox<>();
			hInicioLb.setLabelFor(hInicioCmb);
			hInicioCmb.setModel(setUpComboModel(generateTimeList(0, 23)));
			medPn.add(hInicioCmb);
		}
		{
			hFinalLb = new JLabel("Hora de fin:");
			medPn.add(hFinalLb);
		}
		{
			JComboBox<String> hFinCmb = new JComboBox<>();
			hFinalLb.setLabelFor(hFinCmb);
			hFinCmb.setModel(setUpComboModel(generateTimeList(0, 23)));
			medPn.add(hFinCmb);
		}
		{
			diasLb = new JLabel("Días:");
			medPn.add(diasLb);
		}
		{
			JPanel semanaPn = new JPanel();
			diasLb.setLabelFor(semanaPn);
			medPn.add(semanaPn);
			semanaPn.setLayout(new GridLayout(0, 7, 0, 0));
			{
				JToggleButton lunesTgl = new JToggleButton("L");
				semanaPn.add(lunesTgl);
			}
			{
				JToggleButton martesTgl = new JToggleButton("M");
				semanaPn.add(martesTgl);
			}
			{
				JToggleButton miercolesTgl = new JToggleButton("X");
				semanaPn.add(miercolesTgl);
			}
			{
				JToggleButton juevesTgl = new JToggleButton("J");
				semanaPn.add(juevesTgl);
			}
			{
				JToggleButton viernesTgl = new JToggleButton("V");
				semanaPn.add(viernesTgl);
			}
			{
				JToggleButton sabadoTgl = new JToggleButton("S");
				semanaPn.add(sabadoTgl);
			}
			{
				JToggleButton domingoTgl = new JToggleButton("D");
				semanaPn.add(domingoTgl);
			}
		}
		contentPanel.add(jornadaPn);
		jornadaPn.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel pnCalendarios = new JPanel();
			jornadaPn.add(pnCalendarios);
			pnCalendarios.setLayout(new GridLayout(4, 0, 0, 0));
			{
				diaILb = new JLabel("Día inicio jornada:");
				diaILb.setHorizontalAlignment(SwingConstants.CENTER);
				pnCalendarios.add(diaILb);
			}
			{
				JCalendar calendarI = new JCalendar();
				diaILb.setLabelFor(calendarI);
				pnCalendarios.add(calendarI);
			}
			{
				diaFLb = new JLabel("Día final jornada:");
				diaFLb.setHorizontalAlignment(SwingConstants.CENTER);
				pnCalendarios.add(diaFLb);
			}
			{
				JCalendar calendarF = new JCalendar();
				diaFLb.setLabelFor(calendarF);
				pnCalendarios.add(calendarF);
			}
		}
		{
			JPanel btnPn = new JPanel();
			btnPn.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPn, BorderLayout.SOUTH);
			{
				JButton confirmarBtn = new JButton("Confirmar");
				confirmarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (comprobarCampos()) {
							guardarDatos();
							dispose();
							//Mover de ventana (abrir nueva o volver atrás, no se
							//cierra la aplicación)
						}
					}
				});
				confirmarBtn.setActionCommand("OK");
				btnPn.add(confirmarBtn);
				getRootPane().setDefaultButton(confirmarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						//Volver a la ventana previa ??
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				btnPn.add(cancelarBtn);
			}
		}
	}
	
	/**
	 * Este método genera una lista llena con Strings de horas con una 
	 * separación de 1 hora entre las mismas.
	 * 
	 * Es utilizado para generar el modelo de las combos dedicadas
	 * a asignar las horas de la jornada
	 * @return List<String> Conteniendo las horas.
	 */
	private List<String> generateTimeList(int inicio, int fin) {
		ArrayList<String> times = new ArrayList<>();
		for (int i = inicio; i <= fin; i++) {
			times.add(i + ":00");
		}
		return times;
	}
	
	/**
	 * Este método recibe una lista y devuelve un modelo de Combo lleno con la 
	 * lista recibida.
	 * @param l List<String> con la que se quiere generar el modelo de combo
	 * @return DefaultComboBoxModel<String> conteniendo la lista recibida.
	 */
	private DefaultComboBoxModel<String> setUpComboModel(List<String> l){
		String[] array = l.toArray(new String[l.size()]);
		return new DefaultComboBoxModel<String>(array);
	}
	
	private boolean comprobarCampos() {
		return false;
	}
	
	private void guardarDatos() {
		
	}
}
