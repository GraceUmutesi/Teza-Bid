package com.example.teza_bid;

public class categories {
    private String ArtsAndMotorycles;
    private String AutomobilesAndMotorycles;
    private String Electronics;
    private String Entertainment;
    private String Furniture;
    private String OfficeAndSchoolSupplies;
    private String OtherAccessories;
    private String mName;
    private String mPrice;
    private String mImageUrl;
    public categories() {
    }

    public categories(String artsAndMotorycles, String automobilesAndMotorycles, String electronics,
                      String entertainment, String furniture,
                      String officeAndSchoolSupplies, String otherAccessories ,String name, String price, String imageUrl) {
        ArtsAndMotorycles = artsAndMotorycles;
        AutomobilesAndMotorycles = automobilesAndMotorycles;
        Electronics = electronics;
        Entertainment = entertainment;
        Furniture = furniture;
        OfficeAndSchoolSupplies = officeAndSchoolSupplies;
        OtherAccessories = otherAccessories;
        mName=name;
        mPrice=price;
        mImageUrl=imageUrl;
    }



    public String getArtsAndMotorycles() {
        return ArtsAndMotorycles;
    }

    public void setArtsAndMotorycles(String artsAndMotorycles) {
        ArtsAndMotorycles = artsAndMotorycles;
    }

    public String getAutomobilesAndMotorycles() {
        return AutomobilesAndMotorycles;
    }

    public void setAutomobilesAndMotorycles(String automobilesAndMotorycles) {
        AutomobilesAndMotorycles = automobilesAndMotorycles;
    }

    public String getElectronics() {
        return Electronics;
    }

    public void setElectronics(String electronics) {
        Electronics = electronics;
    }

    public String getEntertainment() {
        return Entertainment;
    }

    public void setEntertainment(String entertainment) {
        Entertainment = entertainment;
    }

    public String getFurniture() {
        return Furniture;
    }

    public void setFurniture(String furniture) {
        Furniture = furniture;
    }

    public String getOfficeAndSchoolSupplies() {
        return OfficeAndSchoolSupplies;
    }

    public void setOfficeAndSchoolSupplies(String officeAndSchoolSupplies) {
        OfficeAndSchoolSupplies = officeAndSchoolSupplies;
    }

    public String getOtherAccessories() {
        return OtherAccessories;
    }

    public void setOtherAccessories(String otherAccessories) {
        OtherAccessories = otherAccessories;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
