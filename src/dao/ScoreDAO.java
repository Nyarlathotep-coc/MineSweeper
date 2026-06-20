package dao;

import entity.Score;
import util.DBUtil;

import java.sql.*;

public class ScoreDAO {
    private Connection connection;

    public ScoreDAO(Connection connection) {
        this.connection = connection;
    }

    public void updateScore(Score score) {
        String sql = "UPDATE scores SET score = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, score.getScore());
            ps.setInt(2, score.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getScore(int userId) {
        String sql = "SELECT score FROM scores WHERE user_id = ?";
        int score = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                score = rs.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }
}
