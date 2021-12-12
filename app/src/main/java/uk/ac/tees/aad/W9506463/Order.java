package uk.ac.tees.aad.W9506463;

public class Order {
    String vechileNumber;
    String date;
    String service;

    @Override
    public String toString() {
        return "Order{" +
                "vechileNumber='" + vechileNumber + '\'' +
                ", date='" + date + '\'' +
                ", service='" + service + '\'' +
                '}';
    }

    public Order(String vechileNumber, String date, String service) {
        this.vechileNumber = vechileNumber;
        this.date = date;
        this.service = service;
    }

    public Order() {
    }

    public String getVechileNumber() {
        return vechileNumber;
    }

    public void setVechileNumber(String vechileNumber) {
        this.vechileNumber = vechileNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
