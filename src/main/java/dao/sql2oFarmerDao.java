package dao;

import models.Farmer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class sql2oFarmerDao implements farmerDao {

    private final Sql2o sql2o;

    public sql2oFarmerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Farmer farmer) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO farmers (name, location, number, produce, amountofproduceinkg, email, priceof1kgofproduce, wallet) VALUES (:name, :location, :number, :produce, :amountofproduceinkg, :email, :priceof1kgofproduce, :wallet)";
            int id = (int) con.createQuery(sql).bind(farmer).executeUpdate().getKey();
            farmer.setId(id);
        } catch(Sql2oException e) {
            System.out.println(e);
        }
    }

    @Override
    public Farmer findById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM farmers WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Farmer.class);
        }
    }

    @Override
    public List<Farmer> getAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM farmers";
            return con.createQuery(sql).executeAndFetch(Farmer.class);
        }
    }

    @Override
    public void deleteAll() {
        String sql1 = "TRUNCATE TABLE farmers";
        try(Connection con = sql2o.open()) {
            String sql2 = "ALTER SEQUENCE farmers_id_seq RESTART";
            con.createQuery(sql1).executeUpdate();
            con.createQuery(sql2).executeUpdate();
        } catch(Sql2oException e) {
            System.out.println(e);
        }
    }
}
