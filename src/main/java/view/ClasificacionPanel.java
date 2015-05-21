package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Clasificacion;
import model.DataAccessor;

public class ClasificacionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tClasif;
	private DefaultTableModel tbm;
	private JScrollPane scroll;
	
	private DataAccessor da;
	
	
	public ClasificacionPanel(DataAccessor da, int anno) {
		this.da = da;
		tbm = new DefaultTableModel(new String[]
				{ "#", "Pais", "Artista" , "Cancion", "Puntos"}, 15);
		tClasif = new JTable(tbm) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scroll = new JScrollPane(tClasif);
		scroll.setViewportView(tClasif);
		this.add(scroll, BorderLayout.CENTER);
		updateData(anno);
	}

	public void updateData(Integer anyo) {
		ArrayList<Clasificacion> clasifData = da.XQuery2(anyo);
		int row = 0;
		int modelSize = tbm.getRowCount();
		int clSize = clasifData.size();
		/*
		En caso de que el resultado de la query sea de menor tamaño 
		que lo que ya hay se eliminan las filas sobrantes que no 
		corresponden a el resultado
		*/
		tbm.getDataVector().removeAllElements();
		/*if(modelSize > clSize){
			System.out.println("vacios");
			Vector<String> rowData = new Vector<String>();
			for(int j=modelSize-1; j>clSize; j--){*/
				/*
				tbm.setValueAt("", j, 0);
				tbm.setValueAt("", j, 1);
				tbm.setValueAt("", j, 2);
				tbm.setValueAt("", j, 3);
				tbm.setValueAt("", j, 4);
				*/
		/*		rowData.add("");
				rowData.add("");
				tbm.addRow(rowData);
			}
		}*/
		for(Clasificacion c : clasifData){
			Vector<String> rowData = new Vector<String>();
			rowData.add(String.valueOf( c.getPuesto() ) );
			rowData.add(c.getPais());
			rowData.add(c.getArtista());
			rowData.add(c.getCancion());
			rowData.add(String.valueOf( c.getPuntos() ) );
			
			tbm.addRow(rowData);
			/*
			tbm.setValueAt(c.getPuesto(), row, 0);
			tbm.setValueAt(c.getPais(), row, 1);
			tbm.setValueAt(c.getArtista(), row, 2);
			tbm.setValueAt(c.getCancion(), row, 3);
			tbm.setValueAt(c.getPuntos(), row, 4);
			row++;
			 */
		}
		
	}

}
