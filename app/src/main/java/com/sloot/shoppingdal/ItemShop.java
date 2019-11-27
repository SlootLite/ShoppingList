package com.sloot.shoppingdal;


public class ItemShop {
    private long id;
    private String name;
    private boolean buy;

    public ItemShop(long id, String name, boolean buy) {
        this.id = id;
        this.name = name;
        this.buy = buy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }
}