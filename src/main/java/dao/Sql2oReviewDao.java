package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oReviewDao implements reviewDao {

    private final Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Review review) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO reviews (farmername, writtenby, content, rating) VALUES (:farmername, :writtenby, :content, :rating)";
            int id = (int) con.createQuery(sql).bind(review)
                    .addParameter("farmername", review.getFarmername())
                    .addParameter("writtenby", review.getWrittenBy())
                    .addParameter("content", review.getContent())
                    .addParameter("rating", review.getRating())
                    .executeUpdate().getKey();
            review.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }

    @Override
    public Review findById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM reviews WHERE id = :id";
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Review.class);
        }
    }

    @Override
    public List<Review> getAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM reviews";
            return con.createQuery(sql).executeAndFetch(Review.class);
        }
    }

    @Override
    public void deleteAll() {
        String sql1 = "TRUNCATE TABLE reviews";
        String sql2 = "ALTER SEQUENCE reviews_id_seq RESTART";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql1).executeUpdate();
            con.createQuery(sql2).executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
}
