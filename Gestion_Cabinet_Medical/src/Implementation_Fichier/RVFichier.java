package Implementation_Fichier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Classes.RV;
import DAO.RVDao;

public class RVFichier implements RVDao, Serializable {
  private static final long serialVersionUID = 4L;

  @Override
  public List<RV> getAllRVs() {
    List<RV> rvs = new ArrayList<>();

    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("rvs.ser"))) {
      rvs = (List<RV>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return rvs;
  }

  @Override
  public void addRV(RV rv) {
    List<RV> rvs = getAllRVs();
    rvs.add(rv);
    saveRvs(rvs);
  }

  @Override
  public void updateRV(RV rv) {
    List<RV> rvs = getAllRVs();
    for (int i = 0; i < rvs.size(); i++) {
      if (rvs.get(i).getId() == rv.getId()) {
        rvs.set(i, rv);
        break;
      }
    }
    saveRvs(rvs);
  }

  @Override
  public void removeRV(long idrv) {
    List<RV> rvs = getAllRVs();
    rvs.removeIf(rv -> rv.getId() == idrv);
    saveRvs(rvs);
  }

  @Override
  public RV getRV(long idrv) {
    List<RV> rvs = getAllRVs();
    for (RV rv : rvs) {
      if (rv.getId() == idrv) {
        return rv;
      }
    }
    return null;
  }


  private static void saveRvs(List<RV> rvs) {
    try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("rvs.ser"))) {
      objectOut.writeObject(rvs);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
