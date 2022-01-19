package dao;

import models.Farmer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class sql2oFarmerDao implements farmerDao {

    private final Sql2o sql2o;

    public sql2oFarmerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Farmer farmer) {
        try(Connection con)
    }
}
