package com.sloot.shoppingdal;

import android.content.Context;

import java.util.ArrayList;

public class ItemShopRepository implements IItemShopRepository {
    private DatabaseAdapter adapter;
    public ItemShopRepository(Context context) {
        adapter = new DatabaseAdapter(context);
    }

    @Override
    public ArrayList<ItemShop> getItems() {
        adapter.open();
        ArrayList<ItemShop> items = adapter.getItems();
        adapter.close();
        return items;
    }

    @Override
    public ItemShop getItem(int index) {
        adapter.open();
        ItemShop item = adapter.getItem(index);
        adapter.close();
        return item;
    }

    @Override
    public long addItem(ItemShop item) {
        adapter.open();
        long id = adapter.insert(item);
        adapter.close();
        return id;
    }

    @Override
    public void editItem(ItemShop item) {
        adapter.open();
        adapter.update(item);
        adapter.close();
    }

    @Override
    public void removeItem(ItemShop item) {
        adapter.open();
        adapter.delete(item.getId());
        adapter.close();
    }
}
