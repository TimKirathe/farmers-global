import dao.Sql2oReviewDao;
import dao.sql2oFarmerDao;
import models.Farmer;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        String connectionString = "jdbc:postgresql://localhost:5432/farmer_global";
        Sql2o sql2o = new Sql2o(connectionString, null, null);

        Sql2oReviewDao sql2oReviewDao = new Sql2oReviewDao(sql2o);
        sql2oFarmerDao sql2oFarmerDao = new sql2oFarmerDao(sql2o);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/farmers/new", (req, res) -> {
            String name = req.queryParams("name");
            String location = req.queryParams("location");
            String number = req.queryParams("number");
            String produce = req.queryParams("produce");
            int amountOfProduce = Integer.parseInt(req.queryParams("amountOfProduce"));
            int price = Integer.parseInt(req.queryParams("price"));
            String email = req.queryParams("email");
            String photoLink = req.queryParams("photoLink");
            Farmer newFarmer = new Farmer(name, location, number, produce, amountOfProduce, email, price, photoLink);
            sql2oFarmerDao.save(newFarmer);
            res.redirect("/farmers");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/farmers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "farmer-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/reviews/new", (req, res) -> {
            String name = req.queryParams("name");
            String content = req.queryParams("content");
            int rating = Integer.parseInt(req.queryParams("rating"));
            String writtenBy = req.queryParams("writtenBy");
            Review newReview = new Review(writtenBy, rating, content, name);
            sql2oReviewDao.save(newReview);
            res.redirect("/reviews");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/reviews/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "review-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/farmers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Farmer> farmers = sql2oFarmerDao.getAll();
            model.put("farmers", farmers);
            return new ModelAndView(model, "farmers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/reviews", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Review> reviews = sql2oReviewDao.getAll();
            model.put("reviews", reviews);
            return new ModelAndView(model, "reviews.hbs");
        }, new HandlebarsTemplateEngine());

        get("/farmer/:id/new-order", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params("id"));
            Farmer farmer = sql2oFarmerDao.findById(id);
            model.put("farmer", farmer);
            return new ModelAndView(model, "order-page.hbs");
        }, new HandlebarsTemplateEngine());

        post("/farmer/new-order", (req, res) -> {
            res.redirect("/success");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/success", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}