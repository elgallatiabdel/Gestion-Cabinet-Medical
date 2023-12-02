package Implementation_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.RV;
import Connection.Connect;
import DAO.RVDao;

public class RV_Jdbc implements RVDao{
  List<RV> RVs = new ArrayList<RV>();
	private static Connect connect;
  private static Connection con;

  static {
    connect = Connect.getCon();
    con = connect.getConnection();
  }
	private PreparedStatement stm;
	private ResultSet rs;

  @Override
	public List<RV> getAllRVs() {
		String req = "SELECT * FROM rv";
		try {
			stm = con.prepareStatement(req);
			rs = stm.executeQuery();
			while(rs.next()) {
				RV R = new RV(rs.getLong(1),String.valueOf(rs.getDate(2)), rs.getLong(3), rs.getLong(4));
				RVs.add(R);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return RVs;
	}

	@Override
	public void addRV(RV R) {
		String req = "INSERT INTO rv(jour,id_client,id_creneau) VALUES (?,?,?)";
		try {
			stm = con.prepareStatement(req);
			stm.setString(1, R.getJour());
			stm.setLong(2, R.getId_client());
			stm.setLong(3, R.getId_creneau());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateRV(RV R) {
		String req = "UPDATE rv SET jour=?,id_client=?,id_creneau=? WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setString(1, R.getJour());
			stm.setLong(2, R.getId_client());
			stm.setLong(3, R.getId_creneau());
			stm.setLong(4, R.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RV getRV(long idrv) {
		RV R=null;
		String req = "SELECT * FROM rv WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idrv);
			rs = stm.executeQuery();
			while(rs.next()) {
				R = new RV(rs.getLong(1),String.valueOf(rs.getDate(2)), rs.getLong(3), rs.getLong(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return R;
	}

	@Override
	public void removeRV(long idrv) {
		String req = "DELETE FROM rv WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idrv);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}