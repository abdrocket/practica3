package view;

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
	
	public EdicionPanel(DataAccessor da) {
		this.da = da;
		
		tHtml = new JTextPane();
		tHtml.setEditable(false);
		tHtml.setContentType("text/html");
	}

	public void updateData(Integer anyo) {
		String html = da.XQuery3(anyo);
		tHtml.setText(html);
	}

}
