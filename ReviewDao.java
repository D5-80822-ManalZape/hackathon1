package com.sunbeam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewDao extends Dao {

	public ReviewDao() throws Exception{
		// TODO Auto-generated constructor stub
	}
	
	public int createReview(Review r,Users currUser) throws Exception {
		String sql = "INSERT INTO reviews(movie_id,review,rating,user_id) VALUES(?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, r.getMovieId());
			stmt.setString(2, r.getReview());
			stmt.setInt(3, r.getRating());
			stmt.setInt(4, currUser.getId());
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}
	
	public List<Review> displayMyReviews(Users curUser) throws Exception{
		List<Review> list = new ArrayList<>();
		String sql = "select * from reviews where user_id=?";
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
	
	public Review findReviewById(int id,Users curUser) throws Exception{
		String sql = "select * from reviews where id=? and user_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.setInt(2, curUser.getId());
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					Timestamp timeStamp = rs.getTimestamp("modified_timestamp");
					Review r =  new Review(id, movieId, review, rating,curUser.getId(), timeStamp);
					return r;
				}
			} // rs.close();
		}
		return null;
	}
	
	public int updateReview(Review r,Users curUser) throws Exception{
		String sql6 = "update reviews set rating=?,review=? where id=?";
		try(PreparedStatement changeReview = con.prepareStatement(sql6)){
			changeReview.setInt(1, r.getRating());
			changeReview.setString(2, r.getReview());
			changeReview.setInt(3, r.getId());
			int count = changeReview.executeUpdate();
			return count;
		}
	}
	
	public int deleteReview(int id,Users curUser) throws Exception{
		String sql6 = "delete from reviews where id=? and user_id=?";
		try(PreparedStatement deleteReview = con.prepareStatement(sql6)){
			
			deleteReview.setInt(1, id);
			deleteReview.setInt(2, curUser.getId());
			int count = deleteReview.executeUpdate();
			return count;
		}
	}
	
	public List<Review> displayAllReviews() throws Exception{
		List<Review> list = new ArrayList<>();
		String sql = "select * from reviews";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int userId = rs.getInt("user_id");
					Timestamp timeStamp = rs.getTimestamp("modified_timestamp");
					Review r =  new Review(id, movieId, review, rating,userId, timeStamp);
					list.add(r);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}
	
	
}
