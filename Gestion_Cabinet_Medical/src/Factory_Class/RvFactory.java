package Factory_Class;

import DAO.RVDao;
import Implementation_Fichier.RVFichier;
import Implementation_JDBC.RV_Jdbc;

public class RvFactory {
  public static RVDao getClient(String type) {
		switch (type) {
			case "Base de données":
				return new RV_Jdbc();
			case "Fichier":
				return new RVFichier();
			default:
				throw new IllegalArgumentException("Implemation introuvable");
		}
	}
}
