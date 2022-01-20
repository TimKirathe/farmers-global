package dao;

import models.Farmer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class sql2oFarmerDaoTest {
    private static sql2oFarmerDao sql2oFarmerDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/farmer_global_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oFarmerDao = new sql2oFarmerDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void afterEach() {
        sql2oFarmerDao.deleteAll();
    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

    @Test
    void farmerGetsSavedToDb() {
        Farmer farmer = setUpFarmer();
        sql2oFarmerDao.save(farmer);
        Farmer savedFarmer = sql2oFarmerDao.findById(farmer.getId());
        assertTrue(farmer.equals(savedFarmer));
    }

    @Test
    void ableToGetAllSavedFarmersBackFromDb() {
        List<Farmer> createdFarmers = new ArrayList<>();
        Farmer farmer = setUpFarmer();
        Farmer farmer2 = new Farmer("James", "Nairobi", "223415267", "Fruits", 25, 100);
        Farmer farmer3 = new Farmer("James", "Kisumu", "0765432165", "Fish", 5, 700);
        sql2oFarmerDao.save(farmer);
        sql2oFarmerDao.save(farmer2);
        sql2oFarmerDao.save(farmer3);
        List<Farmer> savedFarmers = sql2oFarmerDao.getAll();
        createdFarmers.add(farmer);
        createdFarmers.add(farmer2);
        createdFarmers.add(farmer3);
        assertEquals(createdFarmers, savedFarmers);
    }

    @Test
    void farmersGetDeletedFromDb() {
        Farmer farmer = setUpFarmer();
        Farmer farmer2 = new Farmer("James", "Nairobi", "223415267", "Fruits", 25, 100);
        Farmer farmer3 = new Farmer("Moab", "Kisumu", "0765432165", "Fish", 5, 700);
        sql2oFarmerDao.save(farmer);
        sql2oFarmerDao.save(farmer2);
        sql2oFarmerDao.save(farmer3);
        sql2oFarmerDao.deleteAll();
        List<Farmer> savedFarmers= sql2oFarmerDao.getAll();
        assertEquals(0, savedFarmers.size());
    }

    public Farmer setUpFarmer() {
        Farmer testFarmer = new Farmer("Timothy", "Nairobi", "0764321244", "Beans", 10, 300);
        return testFarmer;
    }

}