package swingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import addRecord.AddKlienciGUI;
import dbAccess.KlienciSql;
import dbAccess.ZamowieniaSql;
import records.Klienci;
import tableModels.KlienciTableModel;

public class KlienciGUI extends JFrame{

	private List<Klienci> records;
	
	private JTable table;
	private KlienciTableModel model;
	private JScrollPane scrollPane;
	
	public KlienciGUI(JScrollPane scrollPane, JButton dodaj, JButton usun) {
		
		this.scrollPane = scrollPane;
		
		PrintAll();

		dodaj.removeActionListener(dodaj.getActionListeners()[0]);
		dodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				AddKlienciGUI addKlienciGUI = new AddKlienciGUI();
				PrintAll();
			}
		});
		usun.removeActionListener(usun.getActionListeners()[0]);
		usun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				String id = model.getValueAt(table.getSelectedRow(), 0).toString();	
				KlienciSql.delete(id);
				PrintAll();
			}
		});
	}
	
private void PrintAll() {
		
		records = KlienciSql.selectAll();
		table = new JTable();
		model = new KlienciTableModel(records);
		table.setModel(model);
		scrollPane.setViewportView(table);
		scrollPane.repaint();
		scrollPane.revalidate();
	}
}
