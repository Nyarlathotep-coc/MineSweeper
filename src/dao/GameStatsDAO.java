package dao;

import entity.GameStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameStatsDAO {

	private Connection connection;

	public GameStatsDAO(Connection connection) {
		this.connection = connection;
	}

	public GameStats getGameStats(int userId) {
		String sql = "SELECT wins, losses FROM game_stats WHERE user_id = ?";
		GameStats gameStats = null;

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				gameStats = new GameStats();
				gameStats.setWins(rs.getInt("wins"));
				gameStats.setLosses(rs.getInt("losses"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gameStats;
	}

	public void updateGameStats(int userId, GameStats gameStats) {
		String sql = "UPDATE game_stats SET wins = ?, losses = ? WHERE user_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, gameStats.getWins());
			ps.setInt(2, gameStats.getLosses());
			ps.setInt(3, userId); // 使用 userId 而非 username
			int rowsUpdated = ps.executeUpdate();
			System.out.println("Rows updated: " + rowsUpdated);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}