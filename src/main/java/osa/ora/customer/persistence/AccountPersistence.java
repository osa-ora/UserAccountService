package osa.ora.customer.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import osa.ora.DBConnection;
import osa.ora.customer.exception.JsonMessage;
import osa.ora.beans.User;

public class AccountPersistence {

	private Connection conn = null;

	public AccountPersistence() {
		

	}
	private Connection getConnection(){
		conn = DBConnection.getInstance().getConnection();
		return conn;
	}
	public JsonMessage save(User account) {
		String insertTableSQL = "INSERT INTO account "
				+ "(login, password, email) "
				+ "VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(insertTableSQL)) {

			preparedStatement.setString(1, account.getLogin());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			return new JsonMessage("Error", "Account add failed: "
					+ e.getMessage());
		} catch (Exception e) {
			return new JsonMessage("Error", "Account add failed: "
					+ e.getMessage());
		}
		return new JsonMessage("Success", "Account add succeeded...");
	}

	public JsonMessage update(User account) {
		String updateTableSQL = "UPDATE acount SET login= ?, password= ?,   email=?  WHERE id=?";
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(updateTableSQL);) {
			preparedStatement.setString(1, account.getLogin());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getEmail());
			preparedStatement.setLong(4, account.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
                    e.printStackTrace();
			return new JsonMessage("Error", "Account update failed: "
					+ e.getMessage());
		} catch (Exception e) {
                    e.printStackTrace();
			return new JsonMessage("Error", "Account update failed: "
					+ e.getMessage());
		}
		return new JsonMessage("Success", "Account add succeeded...");
	}

	public JsonMessage delete(long id) {
		String deleteRowSQL = "DELETE FROM account WHERE id=?";
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(deleteRowSQL)) {
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			return new JsonMessage("Error", "Account delete failed: "
					+ e.getMessage());
		} catch (Exception e) {
			return new JsonMessage("Error", "Account delete failed: "
					+ e.getMessage());
		}
		return new JsonMessage("Success", "Account delete succeeded...");
	}

	public User[] findAll() {
		String queryStr = "SELECT * FROM account";
		return this.query(queryStr);
	}

	public User findbyId(long id) {
		String queryStr = "SELECT * FROM account WHERE id=" + id;
		User account = null;
		User accounts[] = this.query(queryStr);
		if (accounts != null && accounts.length > 0) {
			account = accounts[0];
		}
		return account;
	}
	public User loginUser(String login,String password) {
		String queryStr = "SELECT * FROM account WHERE login='" + login+"' and password='"+password+"'";
		User account = null;
		User accounts[] = this.query(queryStr);
		if (accounts != null && accounts.length > 0) {
			account = accounts[0];
		}
		return account;
	}

	public User[] query(String sqlQueryStr) {
		ArrayList<User> cList = new ArrayList<>();
		try (PreparedStatement stmt = getConnection().prepareStatement(sqlQueryStr)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cList.add(new User(rs.getLong("ID"), rs
						.getString("LOGIN"), rs.getString("PASSWORD"), rs
						.getString("EMAIL")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cList.toArray(new User[0]);
	}
}
