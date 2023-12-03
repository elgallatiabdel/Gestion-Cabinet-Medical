package Interface_method;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.List;

// import Classes.Client;
// import DAO.ClientDao;
// import Implementation_JDBC.Client_Jdbc;

// public class ClientJdbcGUI extends JFrame {
//     private ClientDao clientDao;

//     private JTextArea clientTextArea;
//     private JTextField idField, versionField, titreField, nomField, prenomField;

//     public ClientJdbcGUI(ClientDao clientDao) {
//         this.clientDao = clientDao;

//         setTitle("Client Management System - JDBC");
//         setSize(500, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         createUI();
//         updateClientList();
//     }

//     private void createUI() {
//         JPanel mainPanel = new JPanel(new BorderLayout());

//         // Text area to display clients
//         clientTextArea = new JTextArea();
//         JScrollPane scrollPane = new JScrollPane(clientTextArea);
//         mainPanel.add(scrollPane, BorderLayout.CENTER);

//         // Panel for client details input
//         JPanel inputPanel = new JPanel(new GridLayout(5, 2));
//         idField = new JTextField();
//         versionField = new JTextField();
//         titreField = new JTextField();
//         nomField = new JTextField();
//         prenomField = new JTextField();

//         inputPanel.add(new JLabel("ID:"));
//         inputPanel.add(idField);
//         inputPanel.add(new JLabel("Version:"));
//         inputPanel.add(versionField);
//         inputPanel.add(new JLabel("Titre:"));
//         inputPanel.add(titreField);
//         inputPanel.add(new JLabel("Nom:"));
//         inputPanel.add(nomField);
//         inputPanel.add(new JLabel("Prenom:"));
//         inputPanel.add(prenomField);

//         mainPanel.add(inputPanel, BorderLayout.SOUTH);

//         // Button panel
//         JPanel buttonPanel = new JPanel();
//         JButton addButton = new JButton("Add Client");
//         addButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 addClient();
//             }
//         });
//         JButton updateButton = new JButton("Update Client");
//         updateButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 updateClient();
//             }
//         });
//         JButton removeButton = new JButton("Remove Client");
//         removeButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 removeClient();
//             }
//         });
//         buttonPanel.add(addButton);
//         buttonPanel.add(updateButton);
//         buttonPanel.add(removeButton);

//         mainPanel.add(buttonPanel, BorderLayout.NORTH);

//         add(mainPanel);
//     }

//     private void updateClientList() {
//         List<Client> clients = clientDao.getAllClients();
//         StringBuilder clientList = new StringBuilder();
//         for (Client client : clients) {
//             clientList.append(client.getId()).append(": ").append(client.getTitre())
//                     .append(" ").append(client.getNom()).append(" ").append(client.getPrenom())
//                     .append("\n");
//         }
//         clientTextArea.setText(clientList.toString());
//     }

//     private void addClient() {
//         int version = Integer.parseInt(versionField.getText());
//         String titre = titreField.getText();
//         String nom = nomField.getText();
//         String prenom = prenomField.getText();

//         Client newClient = new Client(0, version, titre, nom, prenom);
//         clientDao.addClient(newClient);

//         updateClientList();
//         clearInputFields();
//     }

//     private void updateClient() {
//         long id = Long.parseLong(idField.getText());
//         int version = Integer.parseInt(versionField.getText());
//         String titre = titreField.getText();
//         String nom = nomField.getText();
//         String prenom = prenomField.getText();

//         Client updatedClient = new Client(id, version, titre, nom, prenom);
//         clientDao.updateClient(updatedClient);

//         updateClientList();
//         clearInputFields();
//     }

//     private void removeClient() {
//         long id = Long.parseLong(idField.getText());
//         clientDao.removeClient(id);

//         updateClientList();
//         clearInputFields();
//     }

//     private void clearInputFields() {
//         idField.setText("");
//         versionField.setText("");
//         titreField.setText("");
//         nomField.setText("");
//         prenomField.setText("");
//     }

//     public static void main(String[] args) {
//         ClientDao clientDao = new Client_Jdbc();

//         SwingUtilities.invokeLater(() -> {
//             ClientJdbcGUI clientJdbcGUI = new ClientJdbcGUI(clientDao);
//             clientJdbcGUI.setVisible(true);
//         });
//     }
// }



// ------------------------------------------------------------------------------------------------------------------------

// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.util.List;

// import Classes.Client;
// import DAO.ClientDao;

// public class ClientJdbcGUI extends JFrame {
//     private ClientDao clientDao;

//     private JTable clientTable, allClientsTable;
//     private DefaultTableModel tableModel, allClientsTableModel;

//     private JTextField idField, versionField, titreField, nomField, prenomField;

//     public ClientJdbcGUI(ClientDao clientDao) {
//         this.clientDao = clientDao;

//         setTitle("Client Management System - JDBC");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         createUI();
//         updateClientTable();
//         updateAllClientsTable();
//     }

//     private void createUI() {
//         JPanel mainPanel = new JPanel(new BorderLayout());

//         // Table to display clients
//         tableModel = new DefaultTableModel();
//         clientTable = new JTable(tableModel);
//         JScrollPane scrollPane = new JScrollPane(clientTable);
//         mainPanel.add(scrollPane, BorderLayout.CENTER);

//         // Table to display all clients
//         allClientsTableModel = new DefaultTableModel();
//         allClientsTable = new JTable(allClientsTableModel);
//         JScrollPane allClientsScrollPane = new JScrollPane(allClientsTable);
//         mainPanel.add(allClientsScrollPane, BorderLayout.EAST);

//         // Panel for client details input
//         JPanel inputPanel = new JPanel(new GridLayout(5, 2));
//         idField = new JTextField();
//         versionField = new JTextField();
//         titreField = new JTextField();
//         nomField = new JTextField();
//         prenomField = new JTextField();

//         inputPanel.add(new JLabel("ID:"));
//         inputPanel.add(idField);
//         inputPanel.add(new JLabel("Version:"));
//         inputPanel.add(versionField);
//         inputPanel.add(new JLabel("Titre:"));
//         inputPanel.add(titreField);
//         inputPanel.add(new JLabel("Nom:"));
//         inputPanel.add(nomField);
//         inputPanel.add(new JLabel("Prenom:"));
//         inputPanel.add(prenomField);

//         mainPanel.add(inputPanel, BorderLayout.SOUTH);

//         // Button panel
//         JPanel buttonPanel = new JPanel();
//         JButton addButton = new JButton("Add Client");
//         addButton.addActionListener(e -> addClient());
//         JButton updateButton = new JButton("Update Client");
//         updateButton.addActionListener(e -> updateClient());
//         JButton removeButton = new JButton("Remove Client");
//         removeButton.addActionListener(e -> removeClient());
//         JButton getClientButton = new JButton("Get Client");
//         getClientButton.addActionListener(e -> getClient());

//         buttonPanel.add(addButton);
//         buttonPanel.add(updateButton);
//         buttonPanel.add(removeButton);
//         buttonPanel.add(getClientButton);

//         mainPanel.add(buttonPanel, BorderLayout.NORTH);

//         add(mainPanel);
//     }

//     private void updateClientTable() {
//         List<Client> clients = clientDao.getAllClients();
//         updateTable(clients, tableModel);
//     }

//     public void updateAllClientsTable() {
//         List<Client> allClients = clientDao.getAllClients();
//         updateTable(allClients, allClientsTableModel);
//     }

//     private void updateTable(List<Client> clients, DefaultTableModel model) {
//         model.setRowCount(0);
//         for (Client client : clients) {
//             Object[] rowData = {client.getId(), client.getVersion(), client.getTitre(), client.getNom(), client.getPrenom()};
//             model.addRow(rowData);
//         }
//     }

//     private void addClient() {
//         int version = Integer.parseInt(versionField.getText());
//         String titre = titreField.getText();
//         String nom = nomField.getText();
//         String prenom = prenomField.getText();

//         Client newClient = new Client(0, version, titre, nom, prenom);
//         clientDao.addClient(newClient);

//         updateClientTable();
//         updateAllClientsTable();
//         clearInputFields();
//     }

//     private void updateClient() {
//         long id = Long.parseLong(idField.getText());
//         int version = Integer.parseInt(versionField.getText());
//         String titre = titreField.getText();
//         String nom = nomField.getText();
//         String prenom = prenomField.getText();

//         Client updatedClient = new Client(id, version, titre, nom, prenom);
//         clientDao.updateClient(updatedClient);

//         updateClientTable();
//         updateAllClientsTable();
//         clearInputFields();
//     }

//     private void removeClient() {
//         long id = Long.parseLong(idField.getText());
//         clientDao.removeClient(id);

//         updateClientTable();
//         updateAllClientsTable();
//         clearInputFields();
//     }

//     private void getClient() {
//         long id = Long.parseLong(idField.getText());
//         Client client = clientDao.getClient(id);

//         if (client != null) {
//             versionField.setText(String.valueOf(client.getVersion()));
//             titreField.setText(client.getTitre());
//             nomField.setText(client.getNom());
//             prenomField.setText(client.getPrenom());
//         } else {
//             JOptionPane.showMessageDialog(this, "Client not found!", "Error", JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     private void clearInputFields() {
//         idField.setText("");
//         versionField.setText("");
//         titreField.setText("");
//         nomField.setText("");
//         prenomField.setText("");
//     }

//     // public static void main(String[] args) {
//     //     ClientDao clientDao = new Client_Jdbc();

//     //     SwingUtilities.invokeLater(() -> {
//     //         ClientJdbcGUI clientJdbcGUI = new ClientJdbcGUI(clientDao);
//     //         clientJdbcGUI.setVisible(true);
//     //     });
//     // }
// }


// --------------------------------------------------------------------------------------------------------

