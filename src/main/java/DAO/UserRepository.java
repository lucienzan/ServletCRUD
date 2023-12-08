package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.UserModel;

public class UserRepository {
	Connection con;

	public List<UserModel> GetAll() {
		con = DBConnection.GetDBConnection();
		String query = "SELECT * FROM employee";
		List<UserModel> userList = new ArrayList<UserModel>();

		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				int id = result.getInt("id");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				int age = result.getInt("Age");
				userList.add(new UserModel(id, firstName, lastName, age));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}

	public UserModel GetUser(int id) {
		con = DBConnection.GetDBConnection();
		String query = "SELECT * FROM employee WHERE id = " + id;
		UserModel model = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				int userId = result.getInt("id");
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				int age = result.getInt("Age");
				model = new UserModel(userId, firstName, lastName, age);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	public void Create(UserModel model) {
		System.out.println(model.getFirstName());
		con = DBConnection.GetDBConnection();
		String query = "INSERT INTO employee (id,firstName,lastName,age) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, model.getId());
			pstm.setString(2, model.getFirstName());
			pstm.setString(3, model.getLastName());
			pstm.setInt(4, model.getAge());
			int success = pstm.executeUpdate();
			if (success != 0) {
				var message = String.format("User's %s is created", model.getFirstName() + " " + model.getLastName());
				System.out.println(message);
			} else {
				System.out.println("Something is wrong.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Update(UserModel model) {
		System.out.println(model.getFirstName());
		con = DBConnection.GetDBConnection();
		String query = "UPDATE Employee SET firstName = ? , lastName = ? , age = ? WHERE id = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, model.getFirstName());
			pstm.setString(2, model.getLastName());
			pstm.setInt(3, model.getAge());
			pstm.setInt(4, model.getId());
			int success = pstm.executeUpdate();
			if (success != 0) {
				var message = String.format("User's %s is updated.", model.getFirstName() + " " + model.getLastName());
				System.out.println(message);
			} else {
				System.out.println("Something is wrong.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DeleteUser(int id) {
		con = DBConnection.GetDBConnection();
		String query = "DELETE FROM employee WHERE id = " + id;
		try {
			PreparedStatement pstm = con.prepareStatement(query);
			int success = pstm.executeUpdate();
			if (success != 0) {
				System.out.println("user is deleted.");
			} else {
				System.out.println("Something is wrong.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
