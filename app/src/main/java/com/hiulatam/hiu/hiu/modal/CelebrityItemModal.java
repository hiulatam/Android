package com.hiulatam.hiu.hiu.modal;

import java.io.Serializable;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/8/17.
 */

public class CelebrityItemModal implements Serializable {

    private String image;
    private String name;
    private String article;
    private String percentage;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
