import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

public class TrainTable extends JTable {
	TrainTableModel model = new TrainTableModel();

	TrainTable() {
		setModel(model);
		setAutoCreateRowSorter(true);
		
		getColumnModel().getColumn(0).setMaxWidth(100);
		getColumnModel().getColumn(0).setPreferredWidth(100);
		getColumnModel().getColumn(2).setMaxWidth(150);
		getColumnModel().getColumn(2).setPreferredWidth(150);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
	}

	void add(Train train) {
		model.trains.add(train);
		updateUI();
		int index = model.trains.size() - 1;
		getRowSorter().rowsInserted(index, index);
	}
	
	
	List<Train> getTrains()
	{
		return model.trains;
	}
	
	
	class TrainTableModel extends AbstractTableModel {
		public List<Train> trains = new ArrayList<>();
		private String[] columns = new String[] { "Номер поезда", "Пункт назначения", "Время отправления" };

		public int getColumnCount() {
			return columns.length;
		}

		public int getRowsCount() {
			return trains.size();
		}

		public Object getValueAt(int row, int col) {
			Train club = trains.get(row);
			switch (col) {
			case 0:
				return club.getNumber();
			case 1:
				return club.getName();
			case 2:
				return new SimpleDateFormat("dd MMM yyyy, HH:mm ").format(club.getDepartureTime());
			default:
				return null;
			}
		}

		public String getColumnName(int col) {
			return columns[col];
		}

		@Override
		public int getRowCount() {
			return trains.size();
		}
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
		    if (trains.isEmpty()) {
		        return Object.class;
		    }
		    return getValueAt(0, columnIndex).getClass();
		}
	}

}