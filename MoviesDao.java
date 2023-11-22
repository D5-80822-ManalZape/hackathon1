package com.sunbeam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoviesDao extends Dao{
	public MoviesDao() throws Exception{
		
	}
	
	public List<Movie> findAll() throws Exception {
		List<Movie> list = new ArrayList<>();
		String sql = "SELECT * FROM movies";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					Date release = DateUtil.sqlToUtilDate(rs.getDate("release"));
					Movie m = new Movie(id,title,release);
					list.add(m);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}
}
