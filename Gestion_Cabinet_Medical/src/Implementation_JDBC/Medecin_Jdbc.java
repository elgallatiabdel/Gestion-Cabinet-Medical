package Implementation_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.Medecin;
import Connection.Connect;
import DAO.MedecinDao;

public class Medecin_Jdbc implements MedecinDao {
  List<Medecin> medecins = new ArrayList<Medecin>();
	private static Connect connect;
  private static Connection con;

  static {
    connect = Connect.getCon();
    con = connect.getConnection();
  }
	private PreparedStatement stm;
	private ResultSet rs;

  @Override
  public List<Medecin> getAllMedecins() {
		String req = "SELECT * FROM medecins";
		try {
			stm = con.prepareStatement(req);
			rs = stm.executeQuery();
			while(rs.next()) {
				Medecin c = new Medecin(rs.getLong(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
				medecins.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medecins;
  }

  @Override
	public void addMedecin(Medecin M) {
		String req = "INSERT INTO medecins(version,titre,nom,prenom) VALUES (?,?,?,?)";
		try {
			stm = con.prepareStatement(req);
			stm.setInt(1, M.getVersion());
			stm.setString(2, M.getTitre());
			stm.setString(3, M.getNom());
			stm.setString(4, M.getPrenom());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }

  @Override
	public void updateMedecin(Medecin M) {
		String req = "UPDATE medecins SET version=?,titre=?,nom=?,prenom=? WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setInt(1, M.getVersion());
			stm.setString(2, M.getTitre());
			stm.setString(3, M.getNom());
			stm.setString(4, M.getPrenom());
			stm.setLong(5, M.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }

  @Override
  public Medecin getMedecin(long idm) {
    Medecin medecin = null;
		// con = Connect.getCon();
		String req = "SELECT * FROM medecins WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idm);
			rs = stm.executeQuery();
			while(rs.next()) {
				medecin = new Medecin(rs.getLong(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medecin;
  }

  @Override
	public void removeMedecin(long idm) {
		String req = "DELETE FROM medecins WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idm);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
}