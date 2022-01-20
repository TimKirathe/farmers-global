package dao;

import models.Review;

import java.util.List;

public interface reviewDao {

    //CREATE
    void save(Review review);

    //READ
    Review findById(int id);
    List<Review> getAll();

    //DELETE
    void deleteAll();
}
