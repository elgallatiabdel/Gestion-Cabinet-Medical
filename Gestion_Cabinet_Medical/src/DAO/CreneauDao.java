package DAO;

import java.util.List;
import Classes.Creneau;

public interface CreneauDao {
  List<Creneau> getAllCreneaus();
	void addCreneau(Creneau C);
	void updateCreneau(Creneau C);
	void removeCreneau(long idc);
	Creneau getCreneau(long idc);
}
