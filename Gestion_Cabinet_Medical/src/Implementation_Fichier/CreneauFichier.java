package Implementation_Fichier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Classes.Creneau;
import DAO.CreneauDao;

public class CreneauFichier implements CreneauDao, Serializable {
  private static final long serialVersionUID = 2L;
  private static long lastCreneauId = 0;

  @Override
  public List<Creneau> getAllCreneaus() {
    List<Creneau> creneau = new ArrayList<>();
    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("creneaus.ser"))) {
      creneau = (List<Creneau>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return creneau;
  }

  @Override
  public void addCreneau(Creneau C) {
    List<Creneau> creneaux = getAllCreneaus();
    lastCreneauId++;
    C.setId(lastCreneauId);
    creneaux.add(C);
    savecreneau(creneaux);
  }

  @Override
  public void updateCreneau(Creneau C) {
    List<Creneau> creneau = getAllCreneaus();
    for (int i = 0; i < creneau.size(); i++) {
      if (creneau.get(i).getId() == C.getId()) {
        creneau.set(i, C);
        break;
      }
    }
    savecreneau(creneau);
  }

  @Override
  public void removeCreneau(long idc) {
    List<Creneau> creneau = getAllCreneaus();
    creneau.removeIf(Creneau -> Creneau.getId() == idc);
    savecreneau(creneau);
  }

  @Override
  public Creneau getCreneau(long idc) {
    List<Creneau> creneau = getAllCreneaus();
    for (Creneau Creneau : creneau) {
      if (Creneau.getId() == idc) {
        return Creneau;
      }
    }
    return null;
  }


  private static void savecreneau(List<Creneau> creneau) {
    try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("creneaus.ser"))) {
      objectOut.writeObject(creneau);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
