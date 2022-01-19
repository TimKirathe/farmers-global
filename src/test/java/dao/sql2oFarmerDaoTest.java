package dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

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

    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

}