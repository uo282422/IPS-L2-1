package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import logic.GestorJornada;
import logic.Medico;
import util.DataBase;

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
	private JComboBox<String> medCmb;
	private JComboBox<String> hInicioCmb;
	private JComboBox<String> hFinCmb;
	private JCalendar calendarI = new JCalendar();
	private JCalendar calendarF = new JCalendar();
	private JPanel semanaPn;
	private GestorJornada gestorJornada = new GestorJornada();
	private DataBase db = new DataBase();

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
			medCmb = new JComboBox<>();
			medLb.setLabelFor(medCmb);
			//medCmb.setModel(setUpComboModelMed(db.cargarMedicos()));
			medPn.add(medCmb);
		}
		{
			hInicioLb = new JLabel("Hora de inicio: ");
			medPn.add(hInicioLb);
		}
		{
			hInicioCmb = new JComboBox<>();
			hInicioLb.setLabelFor(hInicioCmb);
			hInicioCmb.setModel(setUpComboModel(generateTimeList(0, 23)));
			medPn.add(hInicioCmb);
		}
		{
			hFinalLb = new JLabel("Hora de fin:");
			medPn.add(hFinalLb);
		}
		{
			hFinCmb = new JComboBox<>();
			hFinalLb.setLabelFor(hFinCmb);
			hFinCmb.setModel(setUpComboModel(generateTimeList(0, 23)));
			medPn.add(hFinCmb);
		}
		{
			diasLb = new JLabel("Días:");
			medPn.add(diasLb);
		}
		{
			semanaPn = new JPanel();
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
				setCalendars();
				calendarI.getDayChooser().setAlwaysFireDayProperty(true);
				calendarI.getDayChooser().addPropertyChangeListener("day",
						new PropertyChangeListener() {
							public void propertyChange(
									PropertyChangeEvent evt) {
								setCalendars();
							}
						});
				diaILb.setLabelFor(calendarI);
				pnCalendarios.add(calendarI);
			}
			{
				diaFLb = new JLabel("Día final jornada:");
				diaFLb.setHorizontalAlignment(SwingConstants.CENTER);
				pnCalendarios.add(diaFLb);
			}
			{
				// calendarF = new JCalendar();
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
							if (JOptionPane.showConfirmDialog(null,
									"¿Seguro que quiere crear la jornada?") == JOptionPane.YES_OPTION) {
								guardarDatos();
								JOptionPane.showMessageDialog(null,
										"Se ha creado la jornada");
								reset();
							} else {
								JOptionPane.showMessageDialog(null,
										"La jornada no se ha creado");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Los datos seleccionados no son viables");
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
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				btnPn.add(cancelarBtn);
			}
		}
	}

	private ComboBoxModel<String> setUpComboModelMed(List<Medico> meds) {
		String[] array = new String[meds.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = meds.get(i).getId();
		}
		return new DefaultComboBoxModel<String>(array);
	}

	/**
	 * Este método genera una lista llena con Strings de horas con una
	 * separación de 1 hora entre las mismas.
	 * 
	 * Es utilizado para generar el modelo de las combos dedicadas a asignar las
	 * horas de la jornada
	 * 
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
	 * 
	 * @param l List<String> con la que se quiere generar el modelo de combo
	 * @return DefaultComboBoxModel<String> conteniendo la lista recibida.
	 */
	private DefaultComboBoxModel<String> setUpComboModel(List<String> l) {
		String[] array = l.toArray(new String[l.size()]);
		return new DefaultComboBoxModel<String>(array);
	}

	/**
	 * Método encargado de dejar todos los componentes de la ventana en su
	 * selección por defecto.
	 */
	private void reset() {
		// medCmb.setSelectedIndex(0);
		hInicioCmb.setSelectedIndex(0);
		hFinCmb.setSelectedIndex(0);
		for (Component c : semanaPn.getComponents()) {
			JToggleButton jtb = (JToggleButton) c;
			jtb.setSelected(false);
		}
		calendarI.setCalendar(Calendar.getInstance());
		calendarF.setCalendar(Calendar.getInstance());
		setCalendars();
	}

	/**
	 * Se encarga de ajustar de manera adecuada las fechas de los calendarios.
	 */
	private void setCalendars() {
		calendarF.setMinSelectableDate(calendarI.getDate());
	}

	/**
	 * Revisa que los campos están rellenados de manera adecuada.
	 * 
	 * @return
	 */
	private boolean comprobarCampos() {
		if (compruebaHoras() && compruebaDias())
			return true;
		else
			return false;
	}

	/**
	 * Comprueba que la duración de la jornada no excede de las 12 horas
	 * ininterrumpidas
	 * 
	 * @return true si no excede la condición
	 */
	private boolean compruebaHoras() {
		int hC = hInicioCmb.getSelectedIndex();
		int hF = hFinCmb.getSelectedIndex();
		return hC < hF;
	}

	/**
	 * Comprueba que el día de inicio es distinto al día de fin, y que no son
	 * nulas
	 * 
	 * @return true si se cumple esta condición
	 */
	private boolean compruebaDias() {
		return calendarI.getDate() != null && calendarF.getDate() != null
				&& calendarI.getDate().before(calendarF.getDate());
	}

	/**
	 * Envía a la clase gestorJornada los datos seleccionados en la ventana
	 */
	private void guardarDatos() {
		gestorJornada.parse(medCmb.getSelectedItem().toString(),
				hInicioCmb.getSelectedItem().toString(),
				hFinCmb.getSelectedItem().toString(), calendarI.getDate(),
				calendarF.getDate(), getDays());
		gestorJornada.guardarJornada();
	}

	/**
	 * Método que elabora un String con los días de la togglebuttos de los dias
	 * de la semana activos
	 */
	private String getDays() {
		String semana = "";
		for (Component c : semanaPn.getComponents()) {
			JToggleButton day = (JToggleButton) c;
			if (day.isSelected())
				semana += day.getText();
		}
		return semana;
	}
}
