package dao;

import models.Farmer;

import java.util.List;

public interface farmerDao {

    //CREATE
    void save(Farmer farmer);


    //READ
    Farmer findById(int id);
    List<Farmer> getAll();


    //DELETE
    void deleteAll();
}
