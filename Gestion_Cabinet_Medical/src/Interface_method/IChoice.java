package Interface_method;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class IChoice extends JFrame {
	JButton sql,fichier;
	public IChoice() {
		this.setTitle("Choix d'implementation");
		this.setSize(350,100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		sql = new JButton("Base de données");
		fichier = new JButton("Fichier");
		sql.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IHome(sql.getText());
				setVisible(false);
			}
		});
		fichier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IHome(fichier.getText());
				setVisible(false);
			}
		});
		this.add(sql);
		this.add(fichier);
		this.setVisible(true);
	}
}
