package jsp;

import java.sql.*;

public class CustomerDAOImpl implements CustomerDAO {

	static Connection con;
	static PreparedStatement ps;
	
	@Override
	public int insertCustomer(Customer c) {

		int status = 0;
		
		try {
			
			con = MySQL.getCon();
			ps = con.prepareStatement("INSERT INTO CUSTOMERSS(USENAME,PASSWORD,FULLNAME) VALUES(?,?,?)");
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getName());
			status = ps.executeUpdate();
			con.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public Customer getCustomer(String userid, String pass) {
		
		Customer c = new Customer();
		
		try {
			
			con = MySQL.getCon();
			ps=con.prepareStatement("select * from customerss where usename=? and password=?");
			ps.setString(1, userid);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				c.setUsername(rs.getString(2));
				c.setPassword(rs.getString(3));
				c.setName(rs.getString(4));
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return c;
	}

}
