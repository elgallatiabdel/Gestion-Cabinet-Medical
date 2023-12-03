package Factory_Class;

import DAO.MedecinDao;
import Implementation_Fichier.MedecinFichier;
import Implementation_JDBC.Medecin_Jdbc;

public class MedecinFactory {
  public MedecinDao getClient(String type) {
		switch (type) {
			case "Base de données":
				return new Medecin_Jdbc();
			case "Fichier":
				return new MedecinFichier();
			default:
				throw new IllegalArgumentException("Implemation introuvable");
		}
	}
}
