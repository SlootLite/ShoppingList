package com.sloot.shoppingdal;


public class ItemShop {
    public long id;
    public String name;
    public boolean buy;

    public ItemShop(long id, String name, boolean buy) {
        this.id = id;
        this.name = name;
        this.buy = buy;
    }
}