package view;

//import java.awt.Dimension;
//import java.awt.FlowLayout;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import model.DataAccessor;

public class EdicionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataAccessor da;
	private JTextPane tHtml;
	
	//public EdicionPanel(DataAccessor da, Dimension d) {
	public EdicionPanel(DataAccessor da, Dimension prefSize) {
		this.da = da;
		tHtml = new JTextPane();
		tHtml.setEditable(false);
		tHtml.setContentType("text/html");
		this.setPreferredSize(prefSize);
		this.add(this.tHtml);
	}

	public void updateData(Integer anyo) {
		String ed = da.XQuery3(anyo);
		//imagenes?
		String[] aux = ed.split(System.lineSeparator());
		for(int i=0; i<aux.length; i++){
			if(aux[i].contains("img")){
				String s = aux[i];
				System.out.println("hola");
			}
		}
		ed = String.join(System.lineSeparator(), aux);
		System.out.println(ed);
		tHtml.setText(ed);
	}

}
