package Interface_method;

// public class IRv {
  
// }

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

import DAO.ClientDao;
import DAO.CreneauDao;
import DAO.RVDao;
import Factory_Class.ClientFactory;
import Factory_Class.CreneauFactory;
import Factory_Class.RvFactory;
import ItemRender.Item;
import Classes.Client;
import Classes.Creneau;
import Classes.RV;

public class IRv extends JPanel {
	JPanel p1, p0;
	static JPanel p4;
	static JScrollPane p2;
	JTextField jourtf;
	static JComboBox<Item> clientcb;
	static JComboBox<String> creneaucb;
	JButton btn,ret = null,remove;
	static JTable table;
	updateAction updateevent;
	removeAction removeevent;
	addAction addevent = new addAction();
	String type;
	
	public IRv(String type){
		this.type = type;
		p1 = new JPanel();
		p2 = new JScrollPane();
		p0 = new JPanel();
		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false;
        	}
		};
		jourtf = new JTextField();
		jourtf.setText("yyyy-mm-dd");
		creneaucb = new JComboBox<String>();
		btn = new JButton("");
		remove = new JButton("remove");
		ret = new JButton("Go back");
		jourtf.setPreferredSize(new Dimension(170,20));
		p1.setLayout(new GridLayout(5,1));
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
		p4 = new JPanel();
		JPanel p5 = new JPanel();
		ClientDao C = new ClientFactory().getClient(type);
    	List<Client> clients = C.getAllClients();
    	Vector<Item> model = new Vector<Item>();
    	model.addElement(new Item(0,""));
    	for(Client clt : clients) {
    		model.addElement(new Item(clt.getId(),clt.getNom()+" "+clt.getPrenom()));
    	}
    	clientcb = new JComboBox<Item>(model);
    	new CreneauFactory();
      CreneauDao CR = CreneauFactory.getClient(type);
    	List<Creneau> creneaux = CR.getAllCreneaus();
    	creneaucb.addItem("");
    	for(Creneau cr : creneaux) {
    		creneaucb.addItem(String.valueOf(cr.getId()));
    	}
		p3.add(new JLabel("Jour       : "));
		p3.add(jourtf);
		p4.add(new JLabel("Client     : "));
		p4.add(clientcb);
		p5.add(new JLabel("Creneau    : "));
		p5.add(creneaucb);
		p0.add(btn);
		p1.add(p3);
		p1.add(p4);
		p1.add(p5);
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
				jourtf.setText("yyyy-mm-dd");
				clientcb.setSelectedIndex(0);
				creneaucb.setSelectedIndex(0);
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
		IRv.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				long id = Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
		        jourtf.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
		        clientcb.getModel().setSelectedItem(new Item(Long.parseLong(table.getModel().getValueAt(table.getSelectedRow(), 2).toString()),table.getModel().getValueAt(table.getSelectedRow(), 3).toString()));
		        creneaucb.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(), 4));
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
	public static void afficheTable(String type){
        try{
        	new RvFactory();
          RVDao RV = RvFactory.getClient(type);
        	List<RV> rvs = RV.getAllRVs();
            String [] header = {"id","jour","id client","client","creneau"};
            String [] row = new String[5];
            DefaultTableModel model = new DefaultTableModel(null,header);
            for(RV rv : rvs){
            	Client c = new ClientFactory().getClient(type).getClient(rv.getId_client());
            	row[0] = String.valueOf(rv.getId());
                row[1] = String.valueOf(rv.getJour());
                row[2]= String.valueOf(rv.getId_client());
                row[3] = new Item(c.getId(),c.getNom()+" "+c.getPrenom()).toString();
                row[4] = String.valueOf(rv.getId_creneau());
                model.addRow(row);
            }
            table.setModel(model);
            p2.getViewport().add(table);
            p2.revalidate();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	public static void ClientVaidate(String type) {
		p4.remove(clientcb);
		ClientDao C = new ClientFactory().getClient(type);
    	List<Client> clients = C.getAllClients();
    	Vector<Item> model = new Vector<Item>();
    	model.addElement(new Item(0,""));
    	for(Client clt : clients) {
    		model.addElement(new Item(clt.getId(),clt.getNom()+" "+clt.getPrenom()));
    	}
    	clientcb = new JComboBox<Item>(model);
    	System.out.println(clientcb.getItemAt(1));
		p4.add(clientcb);
	}
	public static void CreneauVaidate(String type) {
		new CreneauFactory();
    CreneauDao CR = CreneauFactory.getClient(type);
    	List<Creneau> creneaux = CR.getAllCreneaus();
    	creneaucb.removeAllItems();
    	creneaucb.addItem("");
    	for(Creneau cr : creneaux) {
    		creneaucb.addItem(String.valueOf(cr.getId()));
    	}
	}
	class addAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!jourtf.getText().isEmpty() && clientcb.getSelectedItem()!="" && creneaucb.getSelectedItem()!="") {
				Item item = (Item) clientcb.getSelectedItem();
				String cr = (String) creneaucb.getSelectedItem(), date= jourtf.getText();
				new RvFactory();
        RVDao R = RvFactory.getClient(type);
				RV rv;
				try {
					rv = new RV(0,date,item.getId(),Long.parseLong(cr));
					R.addRV(rv);
					jourtf.setText("yyyy-mm-dd");
					clientcb.setSelectedIndex(0);
					creneaucb.setSelectedIndex(0);
					afficheTable(type);
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
			if(!jourtf.getText().isEmpty() && clientcb.getSelectedItem()!="" && creneaucb.getSelectedItem()!="") {
				Item item = (Item) clientcb.getSelectedItem();
				String cr = (String) creneaucb.getSelectedItem(), date= jourtf.getText();
				new RvFactory();
        RVDao R = RvFactory.getClient(type);
				RV rv;
				try {
					rv = new RV(this.id,date,item.getId(),Long.parseLong(cr));
					R.updateRV(rv);
					jourtf.setText("yyyy-mm-dd");
					clientcb.setSelectedIndex(0);
					creneaucb.setSelectedIndex(0);
					addActionPerformed();
					afficheTable(type);
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
				new RvFactory();
        RVDao RV = RvFactory.getClient(type);
				RV.removeRV(id);
				jourtf.setText("yyyy-mm-dd");
				clientcb.setSelectedIndex(0);
				creneaucb.setSelectedIndex(0);
				afficheTable(type);
			}
		}
	}
}

