package ru.simatov.labs.lab3.repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import ru.simatov.labs.lab3.models.Post;

@Service
public class PostBase implements PostRepository {

	private final static String URL = "jdbc:mysql://127.0.0.1:3306/lab3?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";  
	private final static String USERNAME = "root";
	private final static String PASSWORD = "rootroot";
	
	public PostBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver driver not found.");
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Post> getList() {
		ArrayList<Post> posts = null;
		String sqlQuerry = "select * from lab3.post;";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			st.execute(sqlQuerry);
			try (ResultSet rs = st.getResultSet()) {
				posts = new ArrayList<>();
				while (rs.next()) {
					Post post = new Post(rs.getLong("id"), rs.getString("text"), rs.getString("heading"), rs.getString("author"), rs.getLong("author_id"), rs.getDate("date"));
					posts.add(post);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public void addPost(Post post) {
		String sqlQuerry = "insert into lab3.post (heading, text, author, author_id, date) values ('" + post.getHeading() + "', '" + post.getText() + "',  '" + post.getAuthor() + "', '" + post.getAuthor_id() + "', now());";
		String sqlQuerry2 = "update lab3.user set countposts = countposts + 1 where id = '" + post.getAuthor_id() + "';";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			
			st.execute(sqlQuerry);
			st.execute(sqlQuerry2);
		
		} catch (SQLException e) {
			System.out.println("Error in adding post");
			e.printStackTrace();
		}
		
	}

	@Override
	public void changeText(Long id, String text) {
		String sqlQuerry = "update lab3.post set text = '" + text + "' where id = '" + id + "';";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			
			st.execute(sqlQuerry);
		
		} catch (SQLException e) {
			System.out.println("Error in changing text");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deletePost(Long id) {
		
		String sqlQuerry0 = "select * from lab3.post where id = " + id +";";
		String sqlQuerry = "delete from lab3.post where id = '" + id + "';";
		String sqlQuerry2 = "";
		
		
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			
			st.execute(sqlQuerry0);
			try (ResultSet rs = st.getResultSet()) {
				while (rs.next()) {
					Post post = new Post(rs.getLong("id"), rs.getString("text"), rs.getString("heading"), rs.getString("author"), rs.getLong("author_id"), rs.getDate("date"));
					Long authorId = post.getAuthor_id();
					sqlQuerry2 = "update lab3.user set countposts = countposts - 1 where id = '" + authorId + "';";
				}
			}
			st.execute(sqlQuerry);
			st.execute(sqlQuerry2);
		
		} catch (SQLException e) {
			System.out.println("Error in deleting post");
			e.printStackTrace();
		}
		
		
	}

	@Override
	public ArrayList<Post> getPostsToDate(Date date) {
		ArrayList<Post> posts = null;
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		String sqlQuerry = "select * from lab3.post where date ='" + format.format(date) +"';";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			st.execute(sqlQuerry);
			try (ResultSet rs = st.getResultSet()) {
				posts = new ArrayList<>();
				while (rs.next()) {
					Post post = new Post(rs.getLong("id"), rs.getString("text"), rs.getString("heading"), rs.getString("author"), rs.getLong("author_id"), rs.getDate("date"));
					posts.add(post);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public ArrayList<Post> getUserPosts(Long userId) {
		ArrayList<Post> posts = null;
		String sqlQuerry = "select * from lab3.post where author_id like '" + userId + "';";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			st.execute(sqlQuerry);
			try (ResultSet rs = st.getResultSet()) {
				posts = new ArrayList<>();
				while (rs.next()) {
					Post post = new Post(rs.getLong("id"), rs.getString("text"), rs.getString("heading"), rs.getString("author"), rs.getLong("author_id"), rs.getDate("date"));
					posts.add(post);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	public static void main(String[] args) {
		PostBase pb = new PostBase();
		ArrayList<Post> posts = pb.getList();
		System.out.println(posts.get(0));
	}

	@Override
	public Post findById(Long id) {
		Post post = null;
		String sqlQuerry = "select * from lab3.post where id like '" + id + "';";
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = connection.createStatement()) {
			st.execute(sqlQuerry);
			try (ResultSet rs = st.getResultSet()) {
				while (rs.next()) {
					post = new Post(rs.getLong("id"), rs.getString("text"), rs.getString("heading"), rs.getString("author"), rs.getLong("author_id"), rs.getDate("date"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return post;
	}

}
