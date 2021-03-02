import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Trains extends JFrame {

	TrainTable table = new TrainTable();
	AddTrainDialog dialog = new AddTrainDialog();
	SearchBox search = new SearchBox(table);

	public Trains() {
		super("Расписание поездов");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		setJMenuBar(menuBar);

		getContentPane().add(new JScrollPane(table));

		setSize(600, 480);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JMenu createFileMenu() {
		JMenu file = new JMenu("Меню");
		JMenuItem create = new JMenuItem(new CreateAction());
		JMenuItem sort = new JMenuItem(new SortAction());
		JMenuItem show = new JMenuItem(new ShowListAction());
		JMenuItem search = new JMenuItem(new SearchAction());
		JMenuItem exit = new JMenuItem(new ExitAction());

		create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		sort.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		show.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		search.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		
		file.add(create);
		file.add(sort);
		file.add(show);
		file.add(search);
		file.addSeparator();
		file.add(exit);

		return file;
	}

	class CreateAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		CreateAction() {
			putValue(NAME, "Создать");
		}

		public void actionPerformed(ActionEvent e) {
			Train train = dialog.show();
			if (train != null)
				table.add(train);
		}
	}

	class SortAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		SortAction() {
			putValue(NAME, "Сортировать");
		}

		public void actionPerformed(ActionEvent e) {

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(sorter);

			List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
			sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
			sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
			sorter.setSortKeys(sortKeys);
		}
	}

	class ShowListAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		ShowListAction() {
			putValue(NAME, "Показать список");
		}

		public void actionPerformed(ActionEvent e) {
			table.setVisible(!table.isVisible());
		}
	}

	class SearchAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		SearchAction() {
			putValue(NAME, "Поиск");
		}

		public void actionPerformed(ActionEvent e) {
			search.show();
		}
	}

	class ExitAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		ExitAction() {
			putValue(NAME, "Выход");
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}


	public static void main(String[] args) {
		new Trains();
	}
}