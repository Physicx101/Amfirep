package com.example.fauziw97.taxapp.Model;

/**
 * Created by Fauziw97 on 15/12/17.
 */

public class TeamModel {
    private String teamImage;
    private String teamName;
    private String teamMail;
    private String teamFb;

    public TeamModel (String teamImage, String teamName, String teamMail, String teamFb) {
        this.teamImage = teamImage;
        this.teamName = teamName;
        this.teamMail = teamMail;
        this.teamFb = teamFb;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String teamImage) {
        this.teamImage = teamImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamMail() {
        return teamMail;
    }

    public void setTeamMail(String teamMail) {
        this.teamMail = teamMail;
    }

    public String getTeamFb() {
        return teamFb;
    }

    public void setTeamFb(String teamFb) {
        this.teamFb = teamFb;
    }
}
