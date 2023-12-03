package Interface_method;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class IHome extends JFrame {
	JTabbedPane T;
	public IHome(String type) {
		T = new JTabbedPane();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(900,500);
		this.setTitle("Gestion d’un Cabinet Medicale avec "+type);
		this.setLocationRelativeTo(null);
		T.add(new IClient(type),"Clients");
		T.add(new IMedecin(type),"Medecins");
		T.add(new ICreneau(type),"Creneaux");
		T.add(new IRv(type),"Rendez-Vous");
		this.add(T);
		this.setVisible(true);
	}
}