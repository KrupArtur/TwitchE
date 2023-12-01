package com.twitche.twitche.Model;

public class SettingsDefaultOptions {
    static int costEmotes = 80;
    static int costEmotesProm = 220;
    static int emotesPer = 3;
    static int costAnimated = 120;
    static int costBadge = 20;
    static int costBadgeProm = 100;
    static int badgesPer = 3;
    static int howMuchToPromotion = 5;
    static int promo = 15;
    static boolean context = false;

    static String path = "";

    public static int getCostEmotes() {
        return costEmotes;
    }

    public static  int getCostEmotesProm() {
        return costEmotesProm;
    }

    public static int getEmotesPer() {
        return emotesPer;
    }

    public static int getCostAnimated() {
        return costAnimated;
    }

    public static int getCostBadge() {
        return costBadge;
    }

    public static int getCostBadgeProm() {
        return costBadgeProm;
    }

    public static int getBadgesPer() {
        return badgesPer;
    }

    public static int getHowMuchToPromotion() {
        return howMuchToPromotion;
    }

    public static int getPromo() {
        return promo;
    }

    public static void setCostEmotes(int costEmotes) {
        SettingsDefaultOptions.costEmotes = costEmotes;
    }

    public static void setCostEmotesProm(int costEmotesProm) {
        SettingsDefaultOptions.costEmotesProm = costEmotesProm;
    }

    public static void setEmotesPer(int emotesPer) {
        SettingsDefaultOptions.emotesPer = emotesPer;
    }

    public static void setCostAnimated(int costAnimated) {
        SettingsDefaultOptions.costAnimated = costAnimated;
    }

    public static void setCostBadge(int costBadge) {
        SettingsDefaultOptions.costBadge = costBadge;
    }

    public static void setCostBadgeProm(int costBadgeProm) {
        SettingsDefaultOptions.costBadgeProm = costBadgeProm;
    }

    public static void setBadgesPer(int badgesPer) {
        SettingsDefaultOptions.badgesPer = badgesPer;
    }

    public static void setHowMuchToPromotion(int howMuchToPromotion) {
        SettingsDefaultOptions.howMuchToPromotion = howMuchToPromotion;
    }

    public static void setPromo(int promo) {
        SettingsDefaultOptions.promo = promo;
    }

    public static boolean isContext() {
        return context;
    }

    public static void setContext(boolean context) {
        SettingsDefaultOptions.context = context;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String pathnew) {
        if(pathnew != null)
            path = pathnew;
    }
}
