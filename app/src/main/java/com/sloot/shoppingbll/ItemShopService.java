package com.sloot.shoppingbll;

import com.sloot.shoppingdal.ItemShop;
import com.sloot.shoppingdal.ItemShopRepository;

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

    public void toggleBuyItem(int index) {
        ItemShop item = objects.get(index);
        if(item.buy) item.buy = false;
        else item.buy = true;
        objects.set(index, item);
    }

    public ArrayList<ItemShop> getItems() {
        return objects;
    }
}
