//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.javaclientwebapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "search_results",
        value = {"/search_results"}
)
public class HelloServlet extends HttpServlet {
    public String is_searching;

    public HelloServlet() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        this.is_searching = request.getParameter("search_bar");


        ArrayList<Product> EpicProducts = new ArrayList<>();
        ArrayList<Product> SteamProducts = new ArrayList<>();

        HashMap<Integer, String> steam = DataAccess.shopProducts.get("steam");
        HashMap<Integer, String> epicGames = DataAccess.shopProducts.get("epicGames");

        for(Map.Entry<Integer, String> pair : steam.entrySet()){
            if(pair.getValue().toUpperCase().contains(this.is_searching.toUpperCase())){
                Product p = DataAccess.getProductData("steam",pair.getKey());
                p.id= pair.getKey();
                p.name = pair.getValue();
                SteamProducts.add(p);
            }
        }

        for(Map.Entry<Integer, String> pair : epicGames.entrySet()){
            if(pair.getValue().toUpperCase().contains(this.is_searching.toUpperCase())){
                Product p = DataAccess.getProductData("epicGames",pair.getKey());
                p.id= pair.getKey();
                p.name = pair.getValue();
                EpicProducts.add(p);
            }
        }
        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"search_results_style.css\">");
        out.println("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
        out.println("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
        out.println("<link href=\"https://fonts.googleapis.com/css2?family=Quicksand:wght@300&display=swap\" rel=\"stylesheet\">");
        out.println("<title>Game Price Aggregator</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<div class=\"main_contener_search_bar\">");
        out.println("<form class=\"inputs\" name=\"search_page\" method=\"post\" action=\"search_results\">");
        out.println("<input type=\"text\" name=\"search_bar\" class=\"searchbar\" placeholder=\"what are you looking for ?\">");
        out.println("<div class=\"button_s\">");
        out.println("<input type=\"submit\" class=\"search_button\" value=\"search\">");
        out.println("</div>");
        out.println("</form>");


        out.println("<div class=\"main_contener\">");


            out.println("<div class=\"epic_site\">");

        out.println("<div class=\"site_image\">");
        out.println("<image src=\"https://i.postimg.cc/52c2yGHj/epic-game-logo.png\" width=\"26%\" height=\"17%\">");
        out.println("</image>");
        out.println("</div>");

        for(Product ep : EpicProducts) {
            out.println("<div class=\"product\">");

            out.println("<div class=\"title\">");
            out.println("<a class=\"product_title_link\" href=\""+ ep.url+"\">"+ep.name+"</a>");
            out.println("</div>");

            out.println("<div class=\"image_and_price\">");

            out.println("<div class=\"product_image\">");
            out.println("<a href=\""+ep.url+"\">");
            out.println("<image src=\""+ep.imageUrl+"\"></image>");
            out.println("</a>");
            out.println("</div>");

            out.println("<div class=\"prices\">");
            out.println("<div class=\"normal_price\">");
            out.println(ep.mainPrice);
            out.println("</div>");

            out.println("<div class=\"discount\">");
            out.println("<span class=\"discount_epic\">"+ep.discountPrice+"</span>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"description\">");
            out.println(ep.description);
            out.println("</div>");

            out.println("<div class=\"languages\">");
            out.println(ep.languages);

            out.println("</div>");

            out.println("</div>");
        }
        out.println("</div>");


        out.println("<div class=\"steam_site\">");

        out.println("<div class=\"site_image\">");
        out.println("<image src=\"https://i.postimg.cc/CLPXq7j7/steam-logo.png\" width=\"30.5%\" height=\"15%\"></image>");
        out.println("</div>");

        for(Product sp : SteamProducts) {
            out.println("<div class=\"product\">");

            out.println("<div class=\"title\">");
            out.println("<a class=\"product_title_link\" href=\""+sp.url+"\">"+sp.name+"</a>");
            out.println("</div>");

            out.println("<div class=\"image_and_price\">");
            out.println("<div class=\"prices\">");
            out.println("<div class=\"normal_price\">");
            out.println(sp.mainPrice);
            out.println("</div>");

            out.println("<div class=\"discount\">");
            out.println("<span class=\"discount_steam\">"+sp.discountPrice+"</span>");
            out.println("</div>");
            out.println("</div>");

            out.println("<div class=\"product_image\">");
            out.println("<a href=\""+sp.url+"\">");
            out.println("<image src=\""+sp.imageUrl+"\" ></image>");
            out.println("</a>");
            out.println("</div>");


            out.println("</div>");
            out.println("<div class=\"description_steam\">");
            out.println(sp.description);
            out.println("</div>");

            out.println("<div class=\"languages_steam\">");
            out.println(sp.languages);
            out.println("</div>");

            out.println("</div>");
        }//
            out.println("</div>");

        out.println("</div>");
        out.println("</div>");
        out.println("</body>");

        out.println("</html>");




}}
