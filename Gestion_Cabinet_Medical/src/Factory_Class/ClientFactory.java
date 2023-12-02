package Factory_Class;


import DAO.ClientDao;
import Implementation_Fichier.ClientFichier;
import Implementation_JDBC.Client_Jdbc;

public class ClientFactory {
  public static ClientDao getClient(String type) {
		switch (type) {
			case "Base de données":
				return new Client_Jdbc();
			case "Fichier":
				return new ClientFichier();
			default:
				throw new IllegalArgumentException("Implemation introuvable");
		}
	}
}