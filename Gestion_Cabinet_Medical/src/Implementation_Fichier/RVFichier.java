package Implementation_Fichier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Classes.RV;
import DAO.RVDao;

public class RVFichier implements RVDao, Serializable {
  private static final long serialVersionUID = 4L;
  private static long lastRVId = 0;
  private Set<Long> usedIds = new HashSet<>();

  @Override
  public List<RV> getAllRVs() {
    List<RV> rvs = new ArrayList<>();

    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("rvs.ser"))) {
      rvs = (List<RV>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    updateUsedIdsRv(rvs);
    return rvs;
  }

  @Override
  public void addRV(RV rv) {
    List<RV> rvs = getAllRVs();
    long newId = generateUniqueIdRv();
    rv.setId(newId);
    rvs.add(rv);
    usedIds.add(newId);
    saveRvs(rvs);
  }

  private synchronized long generateUniqueIdRv() {
        lastRVId++;
        while (usedIds.contains(lastRVId)) {
            lastRVId++;
        }
        return lastRVId;
    }

    private void updateUsedIdsRv(List<RV> rvs) {
        for (RV r : rvs) {
            usedIds.add(r.getId());
        }
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