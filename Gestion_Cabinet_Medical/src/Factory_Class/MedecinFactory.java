package Factory_Class;

import DAO.MedecinDao;
import Implementation_Fichier.MedecinFichier;
import Implementation_JDBC.Medecin_Jdbc;

public class MedecinFactory {
  public MedecinDao getInstance(String str) {
		if(str.equals("Base de données"))
			return new Medecin_Jdbc();
		else if(str.equals("Fichier"))
			return new MedecinFichier();
		else
			return null;
	}
}
