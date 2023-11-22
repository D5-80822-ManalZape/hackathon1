package com.sunbeam;

import java.sql.Timestamp;
import java.util.Objects;

public class Review {
    private int id;
    private int movieId;
    private String review;
    private int rating;
    private int userId;
    private Timestamp modifiedTimestamp;

    // Constructors

    public Review() {
    }

    public Review(int id, int movieId, String review, int rating, int userId, Timestamp modifiedTimestamp) {
        this.id = id;
        this.movieId = movieId;
        this.review = review;
        this.rating = rating;
        this.userId = userId;
        this.modifiedTimestamp = modifiedTimestamp;
    }
    
    public Review(int id, int movieId, String review, int rating, int userId) {
        this.id = id;
        this.movieId = movieId;
        this.review = review;
        this.rating = rating;
        this.userId = userId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // toString() method for easy debugging

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", userId=" + userId +
                ", modifiedTimestamp=" + modifiedTimestamp.toString() +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return id == other.id;
	}
    
    
    
    
}
