package Factory_Class;

import DAO.RVDao;
import Implementation_Fichier.RVFichier;
import Implementation_JDBC.RV_Jdbc;

public class RvFactory {
  public RVDao getInstance(String str) {
		if(str.equals("Base de données"))
			return new RV_Jdbc();
		else if(str.equals("Fichier"))
			return new RVFichier();
		else
			return null;
	}
}
