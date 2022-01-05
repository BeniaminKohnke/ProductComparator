package com.example.javaclientwebapplication;
//
// connects to database and gets all the values from it
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.sql.*;

public class DataAccess {
    public static HashMap<String, HashMap<Integer ,String>> shopProducts = new HashMap<>(); // contains name and id of products to be get by servlet

    static {
        getProducts("steam");
        shopProducts.put("steam",getProducts("steam"));
        shopProducts.put("epicGames",getProducts("epicGames"));
    }
    public static HashMap<Integer, String> getProducts(String shopName){
        HashMap<Integer, String> products = new HashMap<>();
        Connection sqlConnection = null;
        try { // connects to database
            sqlConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SCANNER;", "sa", "JAVA123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sqlConnection != null) {
            String sql = "SELECT id, name FROM [SCANNER].[dbo].[" + shopName + "];"; //command to get name and id
            try {
                Statement query = sqlConnection.createStatement();
                ResultSet response = query.executeQuery(sql); // execute statement
                while(response.next()){
                    products.put(response.getInt("id"), response.getString("name")); // it gets every product
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try { // close connection to database
                    sqlConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }

    public static Product getProductData(String shopName, int id){
        Product product = new Product(); // it will return every needed values
        Connection sqlConnection = null;
        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SCANNER;", "sa", "JAVA123"); // connect to database
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sqlConnection != null) { // create statement to get every values
            String sql = "SELECT mainPrice, discountPrice, description, languages, url, imageUrl FROM [SCANNER].[dbo].[" + shopName + "] WHERE id = " + id + ";";
            try {
                Statement query = sqlConnection.createStatement();
                ResultSet response = query.executeQuery(sql); // execute statement
                while(response.next()){ // save values in product class
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
                    sqlConnection.close(); // close connection to database
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }
}
