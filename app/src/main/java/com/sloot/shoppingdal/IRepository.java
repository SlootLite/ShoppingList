package com.sloot.shoppingdal;

import java.util.Collection;

interface IItemShopRepository {
    Collection<ItemShop> getItems();
    ItemShop getItem(int index);
    long addItem(ItemShop item);
    void editItem(ItemShop item);
    void removeItem(ItemShop item);
}
