package com.example.myapplication;

public class Country {
    private String name;
    private String nameCapital;
    private String codeISO2;
    private String codeISONum;
    private String codeISO3;
    private String codeFIPS;
    private String telPrefix;
    private String center;
    private String geoWest;
    private String geoEast;
    private String geoNorth;
    private String geoSouth;

    public Country() {
    }

    public Country(String name, String nameCapital, String codeISO2, String codeISONum,
                   String codeISO3, String codeFIPS, String telPrefix, String center,
                   String geoWest, String geoEast, String geoNorth, String geoSouth) {
        this.name = name;
        this.nameCapital = nameCapital;
        this.codeISO2 = codeISO2;
        this.codeISONum = codeISONum;
        this.codeISO3 = codeISO3;
        this.codeFIPS = codeFIPS;
        this.telPrefix = telPrefix;
        this.center = center;
        this.geoWest = geoWest;
        this.geoEast = geoEast;
        this.geoNorth = geoNorth;
        this.geoSouth = geoSouth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCapital() {
        return nameCapital;
    }

    public void setNameCapital(String nameCapital) {
        this.nameCapital = nameCapital;
    }

    public String getCodeISO2() {
        return codeISO2;
    }

    public void setCodeISO2(String codeISO2) {
        this.codeISO2 = codeISO2;
    }

    public String getCodeISONum() {
        return codeISONum;
    }

    public void setCodeISONum(String codeISONum) {
        this.codeISONum = codeISONum;
    }

    public String getCodeISO3() {
        return codeISO3;
    }

    public void setCodeISO3(String codeISO3) {
        this.codeISO3 = codeISO3;
    }

    public String getCodeFIPS() {
        return codeFIPS;
    }

    public void setCodeFIPS(String codeFIPS) {
        this.codeFIPS = codeFIPS;
    }

    public String getTelPrefix() {
        return telPrefix;
    }

    public void setTelPrefix(String telPrefix) {
        this.telPrefix = telPrefix;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getGeoWest() {
        return geoWest;
    }

    public void setGeoWest(String geoWest) {
        this.geoWest = geoWest;
    }

    public String getGeoEast() {
        return geoEast;
    }

    public void setGeoEast(String geoEast) {
        this.geoEast = geoEast;
    }

    public String getGeoNorth() {
        return geoNorth;
    }

    public void setGeoNorth(String geoNorth) {
        this.geoNorth = geoNorth;
    }

    public String getGeoSouth() {
        return geoSouth;
    }

    public void setGeoSouth(String geoSouth) {
        this.geoSouth = geoSouth;
    }
}
