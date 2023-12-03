package Interface_method;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.CreneauDao;
import DAO.MedecinDao;
import Factory_Class.CreneauFactory;
import Factory_Class.MedecinFactory;
import ItemRender.Item;
import Classes.Creneau;
import Classes.Medecin;

public class ICreneau extends JPanel {
	JPanel p1, p0;
	static JPanel p8;
	static JScrollPane p2;
	JTextField versiontf,hdebuttf,mdebuttf,hfintf,mfintf;
	static JComboBox<Item> medecincb;
	JButton btn,ret = null,remove;
	static JTable table;
	updateAction updateevent;
	removeAction removeevent;
	addAction addevent = new addAction();
	String type;
	 
	public ICreneau(String type){
		this.type=type;
		p1 = new JPanel();
		p2 = new JScrollPane();
		p0 = new JPanel();
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false;
        	}
		};
		versiontf = new JTextField();
		hdebuttf = new JTextField();
		mdebuttf = new JTextField();
		hfintf = new JTextField();
		mfintf = new JTextField();
		btn = new JButton("");
		remove = new JButton("remove");
		ret = new JButton("Go back");
		versiontf.setPreferredSize(new Dimension(170,20));
		hdebuttf.setPreferredSize(new Dimension(170,20));
		mdebuttf.setPreferredSize(new Dimension(170,20));
		hfintf.setPreferredSize(new Dimension(170,20));
		mfintf.setPreferredSize(new Dimension(170,20));
		p1.setLayout(new GridLayout(7,1));
		this.setLayout(new BorderLayout());
		this.additems();
		afficheTable(type);
		this.tableMouseClicked();
		this.addActionPerformed();
		this.retActionPerformed();
		this.add(p2);
	}
	
	public void additems() {
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		p8 = new JPanel();
		MedecinDao M = new Factory_Class.MedecinFactory().getClient(type);
    	List<Classes.Medecin> medecins = M.getAllMedecins();
    	Vector<Item> model = new Vector<Item>();
    	model.addElement(new Item(0,""));
    	for(Classes.Medecin mdc : medecins) {
    		model.addElement(new Item(mdc.getId(),mdc.getNom()+" "+mdc.getPrenom()));
    	}
    	medecincb = new JComboBox<Item>(model);
		p3.add(new JLabel("Version    : "));
		p3.add(this.versiontf);
		p4.add(new JLabel("Hdebut     : "));
		p4.add(this.hdebuttf);
		p5.add(new JLabel("Mdebut     : "));
		p5.add(this.mdebuttf);
		p6.add(new JLabel("Hfin       : "));
		p6.add(this.hfintf);
		p7.add(new JLabel("Mfin       : "));
		p7.add(this.mfintf);
		p8.add(new JLabel("Medecin    : "));
		p8.add(medecincb);
		p0.add(btn);
		p1.add(p3);
		p1.add(p4);
		p1.add(p5);
		p1.add(p6);
		p1.add(p7);
		p1.add(p8);
		p1.add(p0);
		this.add(p1, BorderLayout.WEST);
	}

	public void addActionPerformed() {
		btn.setText("add");
		btn.removeActionListener(this.updateevent);
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
				hdebuttf.setText("");
				mdebuttf.setText("");
				hfintf.setText("");
				mfintf.setText("");
				medecincb.setSelectedIndex(0);
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
		ICreneau.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				long id = Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
		        versiontf.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
		        hdebuttf.setText(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
		        mdebuttf.setText(table.getModel().getValueAt(table.getSelectedRow(), 3).toString());
		        hfintf.setText(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
		        mfintf.setText(table.getModel().getValueAt(table.getSelectedRow(), 5).toString());
		        medecincb.getModel().setSelectedItem(new Item(Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 6).toString()),table.getModel().getValueAt(table.getSelectedRow(), 7).toString()));
				btn.setText("update");
				p0.add(remove);
				p0.add(ret);	
				p0.revalidate();
				p0.repaint();
				updateActionPerformed(id);
		    }
		});
	}

	public static void afficheTable(String type) {
		try {
				new CreneauFactory();
				CreneauDao CR = CreneauFactory.getClient(type);
				List<Creneau> creneaux = CR.getAllCreneaus();
				String[] header = {"id", "version", "hdebut", "mdebut", "hfin", "mfin", "id medecin", "medecin"};
				String[] row = new String[8];
				DefaultTableModel model = new DefaultTableModel(null, header);
				
				for (Creneau cr : creneaux) {
						// Utiliser la méthode getMedecin de la classe MedecinFactory
						Medecin m = new MedecinFactory().getClient(type).getMedecin(cr.getId_medecin());

						// Vérifier si l'objet Medecin est null
						if (m != null) {
								row[0] = String.valueOf(cr.getId());
								row[1] = String.valueOf(cr.getVersion());
								row[2] = String.valueOf(cr.getHdebut());
								row[3] = String.valueOf(cr.getMdebut());
								row[4] = String.valueOf(cr.getHfin());
								row[5] = String.valueOf(cr.getMfin());
								row[6] = String.valueOf(m.getId());
								row[7] = m.getNom() + " " + m.getPrenom();
								model.addRow(row);
						}
				}

				table.setModel(model);
				p2.getViewport().add(table);
				p2.revalidate();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	public static void MedecinVaidate(String type) {
		p8.remove(medecincb);
		MedecinDao M = new MedecinFactory().getClient(type);
    	List<Medecin> medecins = M.getAllMedecins();
    	Vector<Item> model = new Vector<Item>();
    	model.addElement(new Item(0,""));
    	for(Medecin mdc : medecins) {
    		model.addElement(new Item(mdc.getId(),mdc.getNom()+" "+mdc.getPrenom()));
    	}
    	medecincb = new JComboBox<Item>(model);
    	p8.add(medecincb);
	}
	class addAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!versiontf.getText().isEmpty() && !hdebuttf.getText().isEmpty() && !mdebuttf.getText().isEmpty() && !hfintf.getText().isEmpty() && !mfintf.getText().isEmpty() && medecincb.getSelectedItem()!="") {
				Item item = (Item) medecincb.getSelectedItem();
				new CreneauFactory();
				CreneauDao CR = CreneauFactory.getClient(type);
				Creneau cr;
				try {
					int v=Integer.parseInt(versiontf.getText()),hd=Integer.parseInt(hdebuttf.getText()),md=Integer.parseInt(mdebuttf.getText()),hf=Integer.parseInt(hfintf.getText()),mf=Integer.parseInt(mfintf.getText()); 
					cr = new Creneau(0,v,hd,md,hf,mf,item.getId());
					CR.addCreneau(cr);
					versiontf.setText("");
					hdebuttf.setText("");
					mdebuttf.setText("");
					hfintf.setText("");
					mfintf.setText("");
					medecincb.setSelectedIndex(0);
					afficheTable(type);
					IRv.CreneauVaidate(type);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}	
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
			if(!versiontf.getText().isEmpty() && !hdebuttf.getText().isEmpty() && !mdebuttf.getText().isEmpty() && !hfintf.getText().isEmpty() && !mfintf.getText().isEmpty() && medecincb.getSelectedItem()!="") {
				Item item = (Item) medecincb.getSelectedItem();
				new CreneauFactory();
				CreneauDao CR = CreneauFactory.getClient(type);
				Creneau cr;
				try {
					int v=Integer.parseInt(versiontf.getText()),hd=Integer.parseInt(hdebuttf.getText()),md=Integer.parseInt(mdebuttf.getText()),hf=Integer.parseInt(hfintf.getText()),mf=Integer.parseInt(mfintf.getText()); 
					cr = new Creneau(this.id,v,hd,md,hf,mf,item.getId());
					CR.updateCreneau(cr);
					versiontf.setText("");
					hdebuttf.setText("");
					mdebuttf.setText("");
					hfintf.setText("");
					mfintf.setText("");
					medecincb.setSelectedIndex(0);
					afficheTable(type);
					IRv.CreneauVaidate(type);
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}	
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
				new CreneauFactory();
				CreneauDao C = CreneauFactory.getClient(type);
				C.removeCreneau(id);
				versiontf.setText("");
				hdebuttf.setText("");
				mdebuttf.setText("");
				hfintf.setText("");
				mfintf.setText("");
				medecincb.setSelectedIndex(0);
				afficheTable(type);
				IRv.CreneauVaidate(type);
			}
		}
	}
}
