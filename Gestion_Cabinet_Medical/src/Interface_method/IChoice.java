package Interface_method;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class IChoice extends JFrame {
	JButton data_base,fichier;
	public IChoice() {
		this.setTitle("Choix d'implementation");
		this.setSize(350,100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		data_base = new JButton("Base de données");
		fichier = new JButton("Fichier");
		data_base.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IHome(data_base.getText());
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
		this.add(data_base);
		this.add(fichier);
		this.setVisible(true);
	}
}
