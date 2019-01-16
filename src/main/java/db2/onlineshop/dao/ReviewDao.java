package db2.onlineshop.dao;

import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.compound.ReviewItem;

import java.util.List;

public interface ReviewDao {

    List<ReviewItem> getByMovie(int movieId);

    int add(int movieId, Review review);

}
