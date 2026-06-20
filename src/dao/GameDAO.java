package dao;

import java.sql.*;

import entity.Game;
import util.DBUtil;

public class GameDAO {

	public void saveGame(Game game) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	        conn = DBUtil.getConnection();
	        String sql = "INSERT INTO games (user_id, game_time, status) VALUES (?, ?, ?)";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, game.getUserId());
	        stmt.setInt(2, 0); 
	        stmt.setString(3, game.getStatus());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.closeConnection(conn);
	    }
	}
}
