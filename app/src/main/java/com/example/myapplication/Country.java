package com.example.myapplication;

import java.io.Serializable;

public class Country implements Serializable {
    private String name;
    private String nameCapital;
    private String codeISO2;
    private String codeISONum;
    private String codeISO3;
    private String codeFIPS;
    private String telPrefix;
    private String center0;
    private String center1;
    private double geoWest;
    private double geoEast;
    private double geoNorth;
    private double geoSouth;
    private String linkban;

    public Country() {
    }

    public Country(String name, String nameCapital, String codeISO2, String codeISONum,
                   String codeISO3, String codeFIPS, String telPrefix, String center0,String center1,
                   double geoWest, double geoEast, double geoNorth, double geoSouth,String linkban) {
        this.name = name;
        this.nameCapital = nameCapital;
        this.codeISO2 = codeISO2;
        this.codeISONum = codeISONum;
        this.codeISO3 = codeISO3;
        this.codeFIPS = codeFIPS;
        this.telPrefix = telPrefix;
        this.center0 = center0;
        this.center1 = center1;
        this.geoWest = geoWest;
        this.geoEast = geoEast;
        this.geoNorth = geoNorth;
        this.geoSouth = geoSouth;
        this.linkban = linkban;
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

    public String getCenter0() {
        return center0;
    }

    public void setCenter0(String center0) {
        this.center0 = center0;
    }

    public String getCenter1() {
        return center1;
    }

    public void setCenter1(String center1) {
        this.center1 = center1;
    }

    public double getGeoWest() {
        return geoWest;
    }

    public void setGeoWest(double geoWest) {
        this.geoWest = geoWest;
    }

    public double getGeoEast() {
        return geoEast;
    }

    public void setGeoEast(double geoEast) {
        this.geoEast = geoEast;
    }

    public double getGeoNorth() {
        return geoNorth;
    }

    public void setGeoNorth(double geoNorth) {
        this.geoNorth = geoNorth;
    }

    public double getGeoSouth() {
        return geoSouth;
    }

    public void setGeoSouth(double geoSouth) {
        this.geoSouth = geoSouth;
    }

    public String getLinkban() { return linkban; }

    public void setLinkban(String linkban) { this.linkban = linkban; }
}
