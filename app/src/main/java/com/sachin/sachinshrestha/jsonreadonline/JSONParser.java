package com.sachin.sachinshrestha.jsonreadonline;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SachinShrestha on 6/15/2016.
 */
public class JSONParser {
    public static List<FoodItems> parseFeed(String content) {
        try {
            JSONArray ar = new JSONArray(content);
            List<FoodItems> foodItems = new ArrayList<>();

            for(int i=0;i<ar.length();i++){
                JSONObject obj = ar.getJSONObject(i);
                FoodItems FoodItems=new FoodItems();

                FoodItems.setProductId(obj.getInt("productId"));
                FoodItems.setName(obj.getString("name"));
                FoodItems.setCategory(obj.getString("category"));
                FoodItems.setPhoto(obj.getString("photo"));
                FoodItems.setPrice(obj.getDouble("price"));

                foodItems.add(FoodItems);
            }
            return foodItems;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
