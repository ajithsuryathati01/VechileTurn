package uk.ac.tees.aad.W9506463;

public class Car {

    String carNumber;

    String mobile;

    String carBrand;

    String model;

    public Car(String carNumber, String mobile, String carBrand, String model) {
        this.carNumber = carNumber;
        this.mobile = mobile;
        this.carBrand = carBrand;
        this.model = model;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
