package Implementation_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.Creneau;
import Connection.Connect;
import DAO.CreneauDao;

public class Creneau_Jdbc implements CreneauDao {
  List<Creneau> creneaux = new ArrayList<Creneau>();
  private static Connection con;
	private PreparedStatement stm;
	private ResultSet rs;

  static {
    con = Connect.getCon().getConnection();
  }

  @Override
  public List<Creneau> getAllCreneaus() {
		String req = "SELECT * FROM creneaux";
		try {
			stm = con.prepareStatement(req);
			rs = stm.executeQuery();
			while(rs.next()) {
				Creneau c = new Creneau(rs.getLong(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getInt(6),rs.getLong(7));
				creneaux.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creneaux;
  }

  @Override
	public void addCreneau(Creneau C) {
		String req = "INSERT INTO creneaux(version,hdebut,mdebut,hfin,mfin,id_medecin) VALUES (?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(req);
			stm.setInt(1, C.getVersion());
			stm.setInt(2, C.getHdebut());
			stm.setInt(3, C.getMdebut());
			stm.setInt(4, C.getHfin());
			stm.setInt(5, C.getMfin());
			stm.setLong(6, C.getId_medecin());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }

  @Override
	public void updateCreneau(Creneau C) {
		String req = "UPDATE creneaux SET version=?,hdebut=?,mdebut=?,hfin=?,mfin=?,id_medecin=? WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setInt(1, C.getVersion());
			stm.setInt(2, C.getHdebut());
			stm.setInt(3, C.getMdebut());
			stm.setInt(4, C.getHfin());
			stm.setInt(5, C.getMfin());
			stm.setLong(6, C.getId_medecin());
			stm.setLong(7, C.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  };

  @Override
	public Creneau getCreneau(long id) {
    Creneau clt=null;
		String req = "SELECT * FROM creneaux WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, id);
			rs = stm.executeQuery();
			while(rs.next()) {
				clt = new Creneau(rs.getLong(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getInt(6),rs.getLong(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clt;
  }

  @Override
	public void removeCreneau(long id) {
		String req = "DELETE FROM creneaux WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
}