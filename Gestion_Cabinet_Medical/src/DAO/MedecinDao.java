package DAO;

import java.util.List;
import Classes.Medecin;

public interface MedecinDao {
  List<Medecin> getAllMedecins();
	void addMedecin(Medecin M);
	void updateMedecin(Medecin M);
	void removeMedecin(long idm);
	Medecin getMedecin(long idm);
}
