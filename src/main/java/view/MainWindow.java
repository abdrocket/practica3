package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;

import model.DataAccessor;

public class MainWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataAccessor da;
	private ClasificacionPanel clPanel;
	private EdicionPanel edPanel;
	
	private JTabbedPane tabPanel;
	
	private JComboBox<Integer> anyoCombo;
	
	public MainWindow(DataAccessor da){
		this.da = da;
		this.setLayout(new BorderLayout());
		this.setBounds(300, 300, 600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tabPanel = new JTabbedPane();
		
		edPanel = new EdicionPanel(da);
		
		
		
		tabPanel.add("Informacion", edPanel);
		tabPanel.add("Clasificacion", clPanel);
		
		ArrayList<Integer> lAnyos = this.da.XQuery1();
		Integer[] anyos = new Integer[lAnyos.size()];
		
		int i = 0;
		for(Integer a : lAnyos){
			anyos[i] = a;
			i++;
		}
		
		anyoCombo = new JComboBox<Integer>(anyos);
		anyoCombo.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent arg0) {
				Integer anyo = (Integer) anyoCombo.getSelectedItem();
				if(anyo != null){
					edPanel.updateData(anyo);
					clPanel.updateData(anyo);
				}
			}
		});
		
		edPanel.updateData((Integer) anyoCombo.getSelectedItem());
		
		clPanel = new ClasificacionPanel(da, (Integer) anyoCombo.getSelectedItem());
		clPanel.updateData((Integer) anyoCombo.getSelectedItem());
		
		
		clPanel.setPreferredSize(new Dimension(300,550));
		
		this.add(tabPanel,BorderLayout.CENTER);
		this.add(anyoCombo,BorderLayout.NORTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
