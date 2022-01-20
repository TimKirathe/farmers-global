package models;

import java.util.Objects;

public class Review {
    private String content;
    private String writtenBy;
    private int id;
    private int rating;
    private String farmername;
    private long createdat;

    public Review(String writtenBy, int rating, String content, String farmername) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.content = content;
        this.farmername = farmername;
        this.createdat = System.currentTimeMillis();

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFarmername() {
        return farmername;
    }

    public void setFarmername(String farmername) {
        this.farmername = farmername;
    }

    public long getCreatedat() {
        return createdat;
    }

    public void setCreatedat(long createdat) {
        this.createdat = createdat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && rating == review.rating && createdat == review.createdat && content.equals(review.content) && writtenBy.equals(review.writtenBy) && farmername.equals(review.farmername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, writtenBy, id, rating, farmername, createdat);
    }

}
