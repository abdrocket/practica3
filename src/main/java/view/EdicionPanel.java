package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

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
	
	public EdicionPanel(DataAccessor da, Dimension d) {
		this.da = da;
		tHtml = new JTextPane();
		tHtml.setEditable(false);
		tHtml.setContentType("text/html");
		this.tHtml.setPreferredSize(d);
		this.add(this.tHtml);
	}

	public void updateData(Integer anyo) {
		String ed = da.XQuery3(anyo);
		tHtml.setText(ed);
	}

}
