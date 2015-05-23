package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
	
	public ClasificacionPanel(DataAccessor da, int anno, Dimension dimension) {
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
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tClasif.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		tClasif.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		tClasif.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		tClasif.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		tClasif.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		
		this.setSize(dimension);
		this.add(scroll);
	}

	public void updateData(Integer anyo) {
		ArrayList<Clasificacion> clasifData = da.XQuery2(anyo);
		tbm.getDataVector().removeAllElements();
		for(Clasificacion c : clasifData){
			Vector<String> rowData = new Vector<String>();
			rowData.add(String.valueOf( c.getPuesto() ) );
			rowData.add(c.getPais());
			rowData.add(c.getArtista());
			rowData.add(c.getCancion());
			rowData.add(String.valueOf( c.getPuntos() ) );
			tbm.addRow(rowData);
		}
	}

}
