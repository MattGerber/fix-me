package com.fixme.commons.database;

import java.util.ArrayList;

public class MarketResults {

    private String name;
    private ArrayList<Items> itemList = new ArrayList<>();

    public MarketResults(String marketName, String[] marketItems){
        this.name = marketName;
        for (String i : marketItems) {
            String[] s = i.split(",");
            Items item = new Items(s[0], s[1]);
            itemList.add(item);
        }
    }


    public ArrayList<Items> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return "MarketResults{" +
                "name='" + name + '\'' +
                ", itemList=" + itemList.toString() +
                '}';
    }
}
