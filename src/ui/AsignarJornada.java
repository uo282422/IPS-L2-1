package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;

public class AsignarJornada extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel medPn;
	private JPanel jornadaPn;
	private JLabel diaFLb;
	private JLabel diaILb;
	private JLabel medLb;
	private JLabel hInicioLb;
	private JLabel hFinalLb;
	private JLabel diasLb;

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
			JComboBox medCmb = new JComboBox();
			medLb.setLabelFor(medCmb);
			medPn.add(medCmb);
		}
		{
			hInicioLb = new JLabel("Hora de inicio: ");
			medPn.add(hInicioLb);
		}
		{
			JComboBox hInicioCmb = new JComboBox();
			hInicioLb.setLabelFor(hInicioCmb);
			medPn.add(hInicioCmb);
		}
		{
			hFinalLb = new JLabel("Hora de fin:");
			medPn.add(hFinalLb);
		}
		{
			JComboBox hFinCmb = new JComboBox();
			hFinalLb.setLabelFor(hFinCmb);
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
				confirmarBtn.setActionCommand("OK");
				btnPn.add(confirmarBtn);
				getRootPane().setDefaultButton(confirmarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.setActionCommand("Cancel");
				btnPn.add(cancelarBtn);
			}
		}
	}

}
