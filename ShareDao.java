package com.sunbeam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShareDao extends Dao{
	public ShareDao() throws Exception{
		// TODO Auto-generated constructor stub
	}
	
	
	public int shareReview(int rId,List<Integer> iArr) throws Exception{
		int count = 0;
		for(int i=0;i<iArr.size();i++) {
			String sql = "insert into shares values(?,?)";
			try(PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setInt(1, rId);
				stmt.setInt(2, iArr.get(i));
				count += stmt.executeUpdate();
			}
		}
		return count;
	}
	
	public List<Review> displayReviewsSharedWithMe(Users curUser) throws Exception{
		List<Review> list = new ArrayList<>();
		String sql = "select reviews.id,movie_id,review,rating,modified_timestamp from reviews,shares where reviews.id=shares.review_id and shares.user_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, curUser.getId());
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					Timestamp timeStamp = rs.getTimestamp("modified_timestamp");
					Review r =  new Review(id, movieId, review, rating,curUser.getId(), timeStamp);
					list.add(r);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}
}
