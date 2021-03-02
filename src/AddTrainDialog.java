import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class AddTrainDialog {
	private JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
	private JTextField textField = new JTextField();
	private DateTimePicker dateTimePicker = new DateTimePicker();

	JPanel p = new JPanel(new GridBagLayout());

	AddTrainDialog() {

		Border border = p.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		p.setBorder(new CompoundBorder(border, margin));

		dateTimePicker.setFormats(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM));
		dateTimePicker.setTimeFormat(DateFormat.getTimeInstance(DateFormat.MEDIUM));
		dateTimePicker.setDate(new Date());

		addComponentWithLabel("Номер:", spinner, p);
		addComponentWithLabel("Название:", textField, p);
		addComponentWithLabel("Время отправления:", dateTimePicker, p);

	}

	private void addComponentWithLabel(String labelText, Component comp, Container containingPanel) {

		JLabel label = new JLabel(labelText);
		GridBagConstraints gridBagConstraint = new GridBagConstraints();

		gridBagConstraint.fill = GridBagConstraints.BOTH;
		gridBagConstraint.insets = new Insets(0, 0, 5, 5);
		gridBagConstraint.gridx = 0;
		containingPanel.add(label, gridBagConstraint);

		gridBagConstraint.insets = new Insets(0, 5, 5, 0);
		gridBagConstraint.gridx = 1;
		containingPanel.add(comp, gridBagConstraint);
	}

	Train show() {
		int Result = JOptionPane.showConfirmDialog(Window.getWindows()[0], p, "Создание", JOptionPane.CLOSED_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (Result == JOptionPane.YES_OPTION)
			try {
				return new Train((Integer) spinner.getValue(), textField.getText(), dateTimePicker.getDate());
			} catch (Exception e) {	}

		return null;
	}
}
