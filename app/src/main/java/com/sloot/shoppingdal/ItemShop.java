package com.sloot.shoppingdal;

public class ItemShop {
    public int id;
    public String name;
    public boolean buy;
    public ItemShop(int id, String name, boolean buy) {
        this.id = id;
        this.name = name;
        this.buy = buy;
    }
}