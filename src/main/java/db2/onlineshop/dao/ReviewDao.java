package db2.onlineshop.dao;

import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.model.MovieReview;

public interface ReviewDao extends Persistent<MovieReview>, MovieChild<Review> {

}
