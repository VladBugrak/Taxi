package com.taxiservice.models.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Order {

    private long id;
    private Type type;
    private int weightGr;
    private LocalDate arrivalDate;
    private AvailableOption availableOption;
    private Bill bill;
    private User user;

    public enum Type {
        MAIL("order.type.mail"),
        PARCEL("order.type.parcel"),
        PACKAGE("order.type.package"),
        CARGO("order.type.cargo");

        private String bundle;

        public String getBundle() {
            return bundle;
        }

        Type(String bundle) {
            this.bundle = bundle;
        }
    }

    public Order() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public int getWeightGr() {
        return weightGr;
    }
    public void setWeightGr(int weightGr) {
        this.weightGr = weightGr;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public AvailableOption getAvailableOption() {
        return availableOption;
    }
    public void setAvailableOption(AvailableOption availableOption) {
        this.availableOption = availableOption;
    }

    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return weightGr == order.weightGr &&
                type == order.type &&
                Objects.equals(arrivalDate, order.arrivalDate) &&
                Objects.equals(availableOption, order.availableOption) &&
                Objects.equals(bill, order.bill) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weightGr, arrivalDate, availableOption, bill, user);
    }


}
