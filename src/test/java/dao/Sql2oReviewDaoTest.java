package dao;

import models.Review;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oReviewDaoTest {

    private static Sql2oReviewDao sql2oReviewDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/farmer_global_test";
        Sql2o sql2o = new Sql2o(connectionString, "adamu", "Adamu");
        sql2oReviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void afterEach() {
        sql2oReviewDao.deleteAll();
    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

    @Test
    void reviewAddedSuccessfullyToDb() {
        Review testReview = setupReview();
        sql2oReviewDao.save(testReview);
        Review savedReview = sql2oReviewDao.findById(testReview.getId());
        assertTrue(testReview.equals(savedReview));
    }

    @Test
    void allReviewsReturnedByDb() {
        List<Review> reviews = new ArrayList<>();
        Review testReview = setupReview();
        Review testReview2 = new Review("tIM", 3, "Could have been better", "Kamu");
        Review testReview3 = new Review("BAB", 5, "Excellent taste of the produce", "Richard");
        sql2oReviewDao.save(testReview);
        sql2oReviewDao.save(testReview2);
        sql2oReviewDao.save(testReview3);
        List<Review> savedReviews = sql2oReviewDao.getAll();
        reviews.add(testReview);
        reviews.add(testReview2);
        reviews.add(testReview3);
        assertEquals(savedReviews, reviews);
    }

    public Review setupReview () {
        Review testReview = new Review("Kim", 4, "Great service", "Adamu");
        return testReview;
    }

}