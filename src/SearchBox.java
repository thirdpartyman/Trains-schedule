import java.awt.GridBagConstraints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class SearchBox extends JDialog {

	JComboBox comboBox = new JComboBox();
	JTable refTable;

	SearchBox(JTable table) {
		refTable = table;
		comboBox.setEditable(true);

		GridBagConstraints gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(comboBox);

		Window parent = Window.getWindows()[0];
		setAlwaysOnTop(true);
		setModalityType(ModalityType.MODELESS);
		setSize(300, 60);
		setLocationRelativeTo(parent);

		comboBox.addActionListener((event) -> {
			JComboBox comboBox = (JComboBox) event.getSource();
			Object obj = comboBox.getSelectedItem();
			setFilter(obj);
		});
		
		installEscapeCloseOperation(this);
		resetFilterOnClose();
	}

	private void setFilter(Object number) {
		TableRowSorter<TrainTable.TrainTableModel> sorter = (TableRowSorter<TrainTable.TrainTableModel>) refTable
				.getRowSorter();
		if (sorter == null)
			sorter = new TableRowSorter<TrainTable.TrainTableModel>((TrainTable.TrainTableModel) refTable.getModel());

		sorter.setRowFilter(RowFilter.regexFilter(number.toString(), 0));
		refTable.setRowSorter(sorter);
	}
	
	void resetFilterOnClose()
	{
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	((TableRowSorter<TrainTable.TrainTableModel>) refTable.getRowSorter()).setRowFilter(null);
            }
        });
	}

	public void show() {
		HashSet items = new HashSet();
		int column = 0;
		for (int row = 0; row < refTable.getModel().getRowCount(); row++)
			items.add(refTable.getModel().getValueAt(row, column));

		comboBox.setModel(new DefaultComboBoxModel(items.toArray()));

		super.show();
	}


	private static final KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
	static final String dispatchWindowClosingActionMapKey = "com.spodding.tackline.dispatch:WINDOW_CLOSING";

	static void installEscapeCloseOperation(final JDialog dialog) {
		Action dispatchClosing = new AbstractAction() {
			public void actionPerformed(ActionEvent event) {
				dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
			}
		};
		JRootPane root = dialog.getRootPane();
		root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeStroke, dispatchWindowClosingActionMapKey);
		root.getActionMap().put(dispatchWindowClosingActionMapKey, dispatchClosing);
	}
}
