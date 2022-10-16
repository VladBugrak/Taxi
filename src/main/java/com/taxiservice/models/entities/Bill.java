package com.taxiservice.models.entities;

import java.util.Objects;

public class Bill {

    private long id;
    private long total;
    private boolean isPaid ;
    private User user;
    private Order order;

    public Bill() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return total == bill.total &&
                isPaid == bill.isPaid &&
                Objects.equals(order, bill.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, isPaid, order);
    }


}
