package DAO;

import java.util.List;
import Classes.Client;

public interface ClientDao {
  List<Client> getAllClients();
	void addClient(Client C);
	void updateClient(Client C);
	void removeClient(long idc);
	Client getClient(long idc);
}
