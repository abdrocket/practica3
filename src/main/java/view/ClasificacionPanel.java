package view;

import java.util.ArrayList;

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
		if(modelSize > clSize){
			for(int j=modelSize-1; j>clSize; j--){
				tbm.setValueAt("", j, 0);
				tbm.setValueAt("", j, 1);
				tbm.setValueAt("", j, 2);
				tbm.setValueAt("", j, 3);
				tbm.setValueAt("", j, 4);
			}
		}
		for(Clasificacion c : clasifData){
			tbm.setValueAt(c.getPuesto(), row, 0);
			tbm.setValueAt(c.getPais(), row, 1);
			tbm.setValueAt(c.getArtista(), row, 2);
			tbm.setValueAt(c.getCancion(), row, 3);
			tbm.setValueAt(c.getPuntos(), row, 4);
	
			row++;
		}
		
	}

}
