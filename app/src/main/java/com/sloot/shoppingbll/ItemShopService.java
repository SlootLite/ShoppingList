package com.sloot.shoppingbll;

import com.sloot.shoppingdal.ItemShop;

import java.util.ArrayList;

/**
 * Управление списком покупок
 */
public class ItemShopService {
    private ArrayList<ItemShop> objects = new ArrayList<>();

    public void addItem(ItemShop item) {
        objects.add(item);
    }

    public void removeItem(int index) {
        objects.remove(index);
    }
}
