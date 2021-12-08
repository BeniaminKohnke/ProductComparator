package com.example.javaclientwebapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.sql.*;

public class DataAccess {
    public static HashMap<String, HashMap<Integer ,String>> shopProducts = new HashMap<>();

    static {
        getProducts("steam");
        shopProducts.put("steam",getProducts("steam"));
        shopProducts.put("epicGames",getProducts("epicGames"));
    }
    public static HashMap<Integer, String> getProducts(String shopName){
        HashMap<Integer, String> products = new HashMap<>();
        Connection sqlConnection = null;
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SCANNER;", "sa", "JAVA123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sqlConnection != null) {
            String sql = "SELECT id, name FROM [SCANNER].[dbo].[" + shopName + "];";
            try {
                Statement query = sqlConnection.createStatement();
                ResultSet response = query.executeQuery(sql);
                while(response.next()){
                    products.put(response.getInt("id"), response.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    sqlConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }

    public static Product getProductData(String shopName, int id){
        Product product = new Product();
        Connection sqlConnection = null;
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SCANNER;", "sa", "JAVA123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sqlConnection != null) {
            String sql = "SELECT mainPrice, discountPrice, description, languages, url, imageUrl FROM [SCANNER].[dbo].[" + shopName + "] WHERE id = " + id + ";";
            try {
                Statement query = sqlConnection.createStatement();
                ResultSet response = query.executeQuery(sql);
                while(response.next()){
                    product.mainPrice = response.getString("mainPrice");
                    product.discountPrice = response.getString("discountPrice");
                    product.description = response.getString("description");
                    product.url = response.getString("url");
                    product.imageUrl = response.getString("imageUrl");
                    product.languages = response.getString("languages");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    sqlConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }
}
