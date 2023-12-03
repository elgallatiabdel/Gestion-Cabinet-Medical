package DAO;

import java.util.List;
import Classes.RV;

public interface RVDao {
	List<RV> getAllRVs();
	void addRV(RV rv);
	void updateRV(RV rv);
	void removeRV(long idrv);
	RV getRV(long idrv);
}