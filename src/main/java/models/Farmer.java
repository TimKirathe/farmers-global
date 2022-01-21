package models;

import java.util.Objects;

public class Farmer {
    private String name;
    private String location;
    private String number;
    private String produce;
    private int amountOfProduceInKg;
    private String email;
    private int priceOf1kgOfProduce;
    private int id;
    private String photoLink;



    public Farmer(String name, String location, String number, String produce, int amountOfProduceInKg, int priceOf1kgOfProduce, String photoLink) {
        this.name = name;
        this.location = location;
        this.number = number;
        this.produce = produce;
        this.amountOfProduceInKg = amountOfProduceInKg;
        this.email = "No email available";
        this.priceOf1kgOfProduce = priceOf1kgOfProduce;
        this.photoLink = photoLink;
    }

    public Farmer(String name, String location, String number, String produce, int amountOfProduceInKg, String email, int priceOfProduce, String photoLink) {
        this.name = name;
        this.location = location;
        this.number = number;
        this.produce = produce;
        this.amountOfProduceInKg = amountOfProduceInKg;
        this.email = email;
        this.priceOf1kgOfProduce = priceOfProduce;
        this.photoLink = photoLink;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public int getPriceOf1kgOfProduce() {
        return priceOf1kgOfProduce;
    }

    public String getNumber() {
        return number;
    }

    public String getProduce() {
        return produce;
    }

    public int getAmountOfProduceInKg() {
        return amountOfProduceInKg;
    }


}
