package com.example.fauziw97.taxapp.Model;

/**
 * Created by Fauziw97 on 9/15/17.
 */

public class SpeciesModel {

    private String speciesImage;
    private String speciesName;
    private String speciesStatus;

    public SpeciesModel(String speciesName, String speciesStatus) {
        this.speciesName = speciesName;
        this.speciesStatus = speciesStatus;
    }

    public String getSpeciesImage() {
        return speciesImage;
    }

    public void setSpeciesImage(String speciesImage) {
        this.speciesImage = speciesImage;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getSpeciesStatus() {
        return speciesStatus;
    }

    public void setSpeciesStatus(String speciesStatus) {
        this.speciesStatus = speciesStatus;
    }
}
