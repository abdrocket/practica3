package view;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import model.DataAccessor;
import model.Edicion;

public class EdicionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataAccessor da;
	
	private JTextPane tHtml;
	
	public EdicionPanel(DataAccessor da) {
		this.da = da;
		
		tHtml = new JTextPane();
		tHtml.setEditable(false);
		tHtml.setContentType("text/html");
		this.add(this.tHtml);
		
	}

	public void updateData(Integer anyo) {
		Edicion ed = da.XQuery3(anyo);
		//System.out.println(html);
		tHtml.setText(ed.toString());
	}

}
