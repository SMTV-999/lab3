package ru.simatov.labs.lab3.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ru.simatov.labs.lab3.models.User;

@Service
public class UserBase implements UserRepository {

	private final static String URL = "jdbc:mysql://127.0.0.1:3306/lab3?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";  
	private final static String USERNAME = "root";
	private final static String PASSWORD = "rootroot";
	
	public UserBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver driver not found.");
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<User> doList() {
		
			ArrayList<User> blogers = null;
			String sqlQuerry = "select * from lab3.user order by countposts desc;";
			try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					Statement st = connection.createStatement()) {
				st.execute(sqlQuerry);
				
				try (ResultSet rs = st.getResultSet()) {
					blogers = new ArrayList<>();
					while (rs.next()) {
						User bloger = new User(rs.getLong("id"), rs.getString("login"), rs.getString("full_name"), rs.getInt("countposts"));
						blogers.add(bloger);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return blogers;
		
	}

	@Override
	public void save(User user) {
		String sqlQuerry = "insert into lab3.user (login, full_name, countposts) values ('" + user.getLogin() + "', '" + user.getFullName() + "',  '" + user.getCountposts() + "');";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			
			st.execute(sqlQuerry);
		
		} catch (SQLException e) {
			System.out.println("Error in adding bloger");
			e.printStackTrace();
		}
	}

	@Override
	public void change() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		String sqlQuerry = "delete from lab3.user where id = '" + id + "';";
		String sqlQuerry2 ="delete from lab3.post where author_id = '" + id +  "';";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			
			st.execute(sqlQuerry);
			st.execute(sqlQuerry2);
		
		} catch (SQLException e) {
			System.out.println("Error in deleting bloger");
			e.printStackTrace();
		}
		
	}

	@Override
	public User findById(Long id) {
		User user = null;
		String sqlQuerry = "select * from lab3.user where id like '" + id + "';";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			st.execute(sqlQuerry);
			try (ResultSet rs = st.getResultSet()) {
				while (rs.next()) {
					user = new User(rs.getLong("id"), rs.getString("login"), rs.getString("full_name"), rs.getInt("countposts"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	

}
