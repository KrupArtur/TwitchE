package com.twitche.twitche.Model.Controller;
import javafx.scene.control.TextField;

public class SettingsControllerModel {

    private TextField BadgePer;
    private TextField BadgePricePer;
    private TextField animatedPrice;
    private TextField badgePrice;
    private TextField emorePerSpecial;
    private TextField emotePer;
    private TextField emotePrice;
    private TextField emotePricePer;
    private TextField emoteSpecialOffer;

    static SettingsControllerModel instance = new SettingsControllerModel();

    public  SettingsControllerModel(){

    }

    public static SettingsControllerModel getInstance(){
        return instance;
    }

    public TextField getBadgePer() {
        return BadgePer;
    }

    public void setBadgePer(TextField badgePer) {
        BadgePer = badgePer;
    }

    public TextField getBadgePricePer() {
        return BadgePricePer;
    }

    public void setBadgePricePer(TextField badgePricePer) {
        BadgePricePer = badgePricePer;
    }

    public TextField getAnimatedPrice() {
        return animatedPrice;
    }

    public void setAnimatedPrice(TextField animatedPrice) {
        this.animatedPrice = animatedPrice;
    }

    public TextField getBadgePrice() {
        return badgePrice;
    }

    public void setBadgePrice(TextField badgePrice) {
        this.badgePrice = badgePrice;
    }

    public TextField getEmorePerSpecial() {
        return emorePerSpecial;
    }

    public void setEmorePerSpecial(TextField emorePerSpecial) {
        this.emorePerSpecial = emorePerSpecial;
    }

    public TextField getEmotePer() {
        return emotePer;
    }

    public void setEmotePer(TextField emotePer) {
        this.emotePer = emotePer;
    }

    public TextField getEmotePrice() {
        return emotePrice;
    }

    public void setEmotePrice(TextField emotePrice) {
        this.emotePrice = emotePrice;
    }

    public TextField getEmotePricePer() {
        return emotePricePer;
    }

    public void setEmotePricePer(TextField emotePricePer) {
        this.emotePricePer = emotePricePer;
    }

    public TextField getEmoteSpecialOffer() {
        return emoteSpecialOffer;
    }

    public void setEmoteSpecialOffer(TextField emoteSpecialOffer) {
        this.emoteSpecialOffer = emoteSpecialOffer;
    }
}
