package login;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class UserDao {
	public static Connection getConnection() {
		java.sql.Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:Mysql://localhost:3306/Repository_name","username","Password");
			System.out.println("Connection Created Successfully....");
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}

	public static int save(User u) {
		int status = 0;
		Connection con=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			
//			TO CHECK USERNAME ALREADY EXIST OR NOT
			String check="SELECT username FROM credintial WHERE username=?";
			PreparedStatement ps = con.prepareStatement(check);
			ps.setString(1, u.getUsername());
			rs=ps.executeQuery();
			
			if(rs.next()) {
				status=-1;  //USERNAME ALREADY EXISTS
			}else {
				String insert="INSERT INTO credintial(username,password) VALUES(?,?)";
				ps=con.prepareStatement(insert);
				ps.setString(1, u.getUsername());
				ps.setString(2, u.getPassword());
				status=ps.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean validate(String username,String password) {
		boolean status = false;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String query = "SELECT * FROM credintial WHERE username=? AND password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}


