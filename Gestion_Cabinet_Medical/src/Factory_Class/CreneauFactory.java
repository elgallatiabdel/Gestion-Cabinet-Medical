package Factory_Class;

import DAO.CreneauDao;
import Implementation_Fichier.CreneauFichier;
import Implementation_JDBC.Creneau_Jdbc;

public class CreneauFactory {
  public static CreneauDao getClient(String type) {
		switch (type) {
			case "Base de données":
				return new Creneau_Jdbc();
			case "Fichier":
				return new CreneauFichier();
			default:
				throw new IllegalArgumentException("Implemation introuvable");
		}
	}
}