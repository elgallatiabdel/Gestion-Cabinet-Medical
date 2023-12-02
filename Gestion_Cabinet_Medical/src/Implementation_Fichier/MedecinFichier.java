package Implementation_Fichier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Classes.Medecin;
import DAO.MedecinDao;

public class MedecinFichier implements MedecinDao, Serializable {
  private static final long serialVersionUID = 3L;
  private static long lastMedecinId = 0;

  @Override
  public List<Medecin> getAllMedecins() {
    List<Medecin> medecins = new ArrayList<>();

    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("medecins.ser"))) {
      medecins = (List<Medecin>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return medecins;
  }

  @Override
  public void addMedecin(Medecin M) {
    List<Medecin> medecins = getAllMedecins();
    lastMedecinId++;
    M.setId(lastMedecinId);
    medecins.add(M);
    savemedecin(medecins);
  }

  @Override
  public void updateMedecin(Medecin M) {
    List<Medecin> medecins = getAllMedecins();
    for (int i = 0; i < medecins.size(); i++) {
      if (medecins.get(i).getId() == M.getId()) {
        medecins.set(i, M);
        break;
      }
    }
    savemedecin(medecins);
  }

  @Override
  public void removeMedecin(long idm) {
    List<Medecin> medecins = getAllMedecins();
    medecins.removeIf(medecin -> medecin.getId() == idm);
    savemedecin(medecins);
  }

  @Override
  public Medecin getMedecin(long idm) {
    List<Medecin> medecins = getAllMedecins();
    for (Medecin m : medecins) {
      if (m.getId() == idm) {
        return m;
      }
    }
    return null;
  }


  private static void savemedecin(List<Medecin> medecin) {
    try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("medecins.ser"))) {
      objectOut.writeObject(medecin);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}