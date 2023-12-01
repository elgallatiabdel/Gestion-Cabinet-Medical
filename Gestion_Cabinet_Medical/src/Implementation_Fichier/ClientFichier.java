package Implementation_Fichier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Classes.Client;
import DAO.ClientDao;

public class ClientFichier implements ClientDao, Serializable {
  private static final long serialVersionUID = 1L;
  private static long lastClientId = 0;

  @Override
  public List<Client> getAllClients() {
    List<Client> clients = new ArrayList<>();
    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("clients.ser"))) {
      clients = (List<Client>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return clients;
  }

  @Override
  public void addClient(Client client) {
    List<Client> clients = getAllClients();
    lastClientId++;
    client.setId(lastClientId);
    clients.add(client);
    saveClients(clients);
  }

  @Override
  public void updateClient(Client updatedClient) {
    List<Client> clients = getAllClients();
    for (int i = 0; i < clients.size(); i++) {
      if (clients.get(i).getId() == updatedClient.getId()) {
        clients.set(i, updatedClient);
        break;
      }
    }
    saveClients(clients);
  }

  @Override
  public void removeClient(long clientId) {
    List<Client> clients = getAllClients();
    clients.removeIf(client -> client.getId() == clientId);
    saveClients(clients);
  }

  @Override
  public Client getClient(long clientId) {
    List<Client> clients = getAllClients();
    for (Client client : clients) {
      if (client.getId() == clientId) {
        return client;
      }
    }
    return null;
  }

  private static void saveClients(List<Client> clients) {
    try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("clients.ser"))) {
      objectOut.writeObject(clients);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

