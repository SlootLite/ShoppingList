package com.sloot.shoppingbll;

import android.content.Context;

import com.sloot.shoppingdal.ItemShop;
import com.sloot.shoppingdal.ItemShopRepository;

import java.util.ArrayList;

/**
 * Управление списком покупок
 */
public class ItemShopService {
    private ArrayList<ItemShop> itemShops;
    private ItemShopRepository itemShopRepository;

    public ItemShopService(Context context) {
        itemShopRepository = new ItemShopRepository(context);
        itemShops = itemShopRepository.getItems();
    }

    public void addItem(ItemShop item) {
        item.id = itemShopRepository.addItem(item);
        itemShops.add(item);
    }

    public void removeItem(ItemShop item) {
        itemShops.remove(item);
        itemShopRepository.removeItem(item);
    }

    public void toggleBuyItem(int index) {
        ItemShop item = itemShops.get(index);
        if(item.buy)
            item.buy = false;
        else
            item.buy = true;
        itemShops.set(index, item);
        itemShopRepository.editItem(item);
    }

    public ItemShop getItem(int index) {
        return itemShops.get(index);
    }

    public ArrayList<ItemShop> getItems() {
        return itemShops;
    }
}
