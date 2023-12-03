package Interface_method;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.MedecinDao;
import Factory_Class.MedecinFactory;
import Classes.Medecin;

public class IMedecin extends JPanel {
	JPanel p1, p0;
	JScrollPane p2;
	JTextField versiontf, titretf, nomtf, prenomtf;
	JButton btn,ret = null,remove;
	JTable table;
	updateAction updateevent;
	removeAction removeevent;
	addAction addevent = new addAction();
	String type;
	 
	public IMedecin(String type){
		this.type = type;
		p1 = new JPanel();
		p2 = new JScrollPane();
		p0 = new JPanel();
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false;
        	}
		};
		versiontf = new JTextField();
		titretf = new JTextField();
		nomtf = new JTextField();
		prenomtf = new JTextField();
		btn = new JButton("");
		remove = new JButton("remove");
		ret = new JButton("Go back");
		versiontf.setPreferredSize(new Dimension(170,20));
		titretf.setPreferredSize(new Dimension(170,20));
		nomtf.setPreferredSize(new Dimension(170,20));
		prenomtf.setPreferredSize(new Dimension(170,20));
		p1.setLayout(new GridLayout(5,1));
		this.setLayout(new BorderLayout());
		this.additems();
		this.afficheTable();
		this.tableMouseClicked();
		this.addActionPerformed();
		this.retActionPerformed();
		this.add(p2);
	}
	public void addActionPerformed() {
		btn.setText("add");
		btn.removeActionListener(updateevent);
		btn.addActionListener(this.addevent);
	}
	public void updateActionPerformed(long id) {
		updateevent = new updateAction(id);
		btn.removeActionListener(this.addevent);
		btn.addActionListener(this.updateevent);
	}
	public void removeActionPerformed(long id) {
		remove.addActionListener(new removeAction(id));
	}
	public void retActionPerformed() {
		ret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				versiontf.setText("");
				titretf.setText("");
				nomtf.setText("");
				prenomtf.setText("");
				table.clearSelection();
				p0.remove(ret);
				p0.remove(remove);
				p0.revalidate();
				p0.repaint();
				addActionPerformed();
			}
		});
	}
	public void tableMouseClicked() {
		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				long id = Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
		        versiontf.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
		        titretf.setText(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
		        nomtf.setText(table.getModel().getValueAt(table.getSelectedRow(), 3).toString());
		        prenomtf.setText(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
				btn.setText("update");
				p0.add(remove);
				p0.add(ret);
				p0.revalidate();
				p0.repaint();
				updateActionPerformed(id);
				removeActionPerformed(id);
		    }
		});
	}
	public void additems() {
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		p3.add(new JLabel("Version : "));
		p3.add(versiontf);
		p4.add(new JLabel("Titre   : "));
		p4.add(titretf);
		p5.add(new JLabel("Nom     : "));
		p5.add(nomtf);
		p6.add(new JLabel("Prenom  : "));
		p6.add(prenomtf);
		p0.add(btn);
		p1.add(p3);
		p1.add(p4);
		p1.add(p5);
		p1.add(p6);
		p1.add(p0);
		this.add(p1, BorderLayout.WEST);
	}
	public void afficheTable(){
        try{
        	MedecinDao M = new MedecinFactory().getClient(type);
        	List<Medecin> medecins = M.getAllMedecins();
            String [] header = {"id","version","titre","nom","prenom"};
            String [] row = new String[5];
            DefaultTableModel model = new DefaultTableModel(null,header);
            for(Medecin mdc : medecins){
            	row[0] = String.valueOf(mdc.getId());
                row[1] = String.valueOf(mdc.getVersion());
                row[2] = mdc.getTitre();
                row[3] = mdc.getNom();
                row[4] = mdc.getPrenom();
                model.addRow(row);
            }
            table.setModel(model);
            p2.getViewport().add(table);
            p2.revalidate();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	class addAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!versiontf.getText().isEmpty() && !titretf.getText().isEmpty() && !nomtf.getText().isEmpty() && !prenomtf.getText().isEmpty()) {
				MedecinDao M = new MedecinFactory().getClient(type);
				Medecin medecin = new Medecin(0,Integer.parseInt(versiontf.getText()),titretf.getText(),nomtf.getText(),prenomtf.getText());
				M.addMedecin(medecin);
				versiontf.setText("");
				titretf.setText("");
				nomtf.setText("");
				prenomtf.setText("");
				afficheTable();
				ICreneau.MedecinVaidate(type);
				ICreneau.afficheTable(type);
			}
		}
	}
	class updateAction implements ActionListener {
		long id;
		updateAction(long i){
			this.id=i;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!versiontf.getText().isEmpty() && !titretf.getText().isEmpty() && !nomtf.getText().isEmpty() && !prenomtf.getText().isEmpty()) {
				MedecinDao M = new MedecinFactory().getClient(type);
				Medecin medecin = new Medecin(this.id,Integer.parseInt(versiontf.getText()),titretf.getText(),nomtf.getText(),prenomtf.getText());
				M.updateMedecin(medecin);
				versiontf.setText("");
				titretf.setText("");
				nomtf.setText("");
				prenomtf.setText("");
				afficheTable();
				ICreneau.MedecinVaidate(type);
				ICreneau.afficheTable(type);
			}
		}
	}
	class removeAction implements ActionListener {
		long id;
		removeAction(long i){
			this.id=i;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?","selct option",JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				MedecinDao M = new MedecinFactory().getClient(type);
				System.out.println("remove");
				M.removeMedecin(id);
				versiontf.setText("");
				titretf.setText("");
				nomtf.setText("");
				prenomtf.setText("");
				afficheTable();
				ICreneau.MedecinVaidate(type);
				ICreneau.afficheTable(type);
			}
		}
	}
}
