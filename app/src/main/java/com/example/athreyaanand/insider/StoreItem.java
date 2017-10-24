package com.example.athreyaanand.insider;

/**
 * Created by athreyaanand on 10/23/17.
 */

public class StoreItem {
    int itemImage;
    String itemName, itemDesc;

    public StoreItem(int itemImage, String itemName, String itemDesc){
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemDesc = itemDesc;

    }

    public int getItemImage() {
        return itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }
}
