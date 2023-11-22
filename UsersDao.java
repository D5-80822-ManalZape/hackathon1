package com.sunbeam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDao extends Dao{
	public UsersDao() throws Exception {
	}
	
	public int save(Users u) throws Exception {
		String sql = "INSERT INTO users VALUES(default, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getMobile());
			stmt.setDate(6, DateUtil.utilToSqlDate(u.getBirth()));
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}
	
	public Users findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					Date uDate = DateUtil.sqlToUtilDate(rs.getDate("birth"));
					return new Users(id, fname, lname, email, password, mobile ,uDate);
				}
			} // rs.close();
		} // stmt.close();
		return null;
	}
	
	public int changeUser(Users u) throws Exception {
//		String sql3 = "select * from users where email=?";
//		PreparedStatement deleteUserById1 = con.prepareStatement(sql3);
//		deleteUserById1.setString(1, u.getEmail());
//		try(ResultSet rs = deleteUserById1.executeQuery()){
//			if (rs.next()) {
		
					String sql6 = "update users set first_name=?,last_name=?,email=?,password=?,mobile=?,birth=? where id=?";
					try(PreparedStatement changeVoter = con.prepareStatement(sql6)){
						changeVoter.setString(1, u.getFirstName());
						changeVoter.setString(2, u.getLastName());
						changeVoter.setString(3, u.getEmail());
						changeVoter.setString(4, u.getPassword());
						changeVoter.setString(5, u.getMobile());
						changeVoter.setDate(6, DateUtil.utilToSqlDate(u.getBirth()));
						changeVoter.setInt(7, u.getId());
						int count = changeVoter.executeUpdate();
						return count;
					}	
					
//			} else {
//				System.out.println("User not found");
//				return 0;
//			}
//		}	
	}
	
	public int changePass(Users u) throws Exception{
		String sql6 = "update users set password=? where id=?";
		try(PreparedStatement changePass = con.prepareStatement(sql6)){
			changePass.setString(1, u.getPassword());
			changePass.setInt(2, u.getId());
			int count = changePass.executeUpdate();
			return count;
		}
	}
	
	public List<Users> findAll() throws Exception {
		List<Users> list = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					Date uDate = DateUtil.sqlToUtilDate(rs.getDate("birth"));
					Users nUser =  new Users(id, fname, lname, email, password, mobile ,uDate);
					list.add(nUser);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}
}
