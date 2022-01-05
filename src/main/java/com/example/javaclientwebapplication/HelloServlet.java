//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
// this creates our results site
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

@WebServlet( // create name and value of this servlet to be able connect it with starting page
        name = "search_results",
        value = {"/search_results"}
)
public class HelloServlet extends HttpServlet {
    public String is_searching;


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // everything happens in doPost method
        PrintWriter out = response.getWriter(); // this one will write our html site
        this.is_searching = request.getParameter("search_bar"); // here is what client was looking for


        ArrayList<Product> EpicProducts = new ArrayList<>(); // arraylist for contains results from epic games
        ArrayList<Product> SteamProducts = new ArrayList<>(); // arraylist for contains results from steam

        HashMap<Integer, String> steam = DataAccess.shopProducts.get("steam"); // we get hasmap of product's id and name that are on discount on steam from database
        HashMap<Integer, String> epicGames = DataAccess.shopProducts.get("epicGames"); // we get hasmap of product's id and name that are on discount on epic from database

        // searching for steam results
        for(Map.Entry<Integer, String> pair : steam.entrySet()){ // this method is compares string (what client is looking for ) with what is in  database
            if(pair.getValue().toUpperCase().contains(this.is_searching.toUpperCase())){ // its upper case our string so that it was written the same
                Product p = DataAccess.getProductData("steam",pair.getKey());
                p.id= pair.getKey();
                p.name = pair.getValue();
                SteamProducts.add(p); // add steam product to arraylist
            }
        }

        // searching for epic games results
        for(Map.Entry<Integer, String> pair : epicGames.entrySet()){ // this method is compares string (what client is looking for ) with what is in  database
            if(pair.getValue().toUpperCase().contains(this.is_searching.toUpperCase())){ // its upper case our string so that it was written the same
                Product p = DataAccess.getProductData("epicGames",pair.getKey()); // gets all value from database
                p.id= pair.getKey();
                p.name = pair.getValue();
                EpicProducts.add(p); // add epic product to arraylist
            }
        }

        //
        // this part create our result site
        //
        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\" />");

        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"search_results_style.css\">"); // connecting to all fonts,sheets etc.
        out.println("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
        out.println("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
        out.println("<link href=\"https://fonts.googleapis.com/css2?family=Quicksand:wght@300&display=swap\" rel=\"stylesheet\">");
        out.println("<title>Game Price Aggregator</title>");
        out.println("</head>");

        out.println("<body>"); // its creates search bar and submit button
        out.println("<div class=\"main_contener_search_bar\">");
        out.println("<form class=\"inputs\" name=\"search_page\" method=\"post\" action=\"search_results\">"); // we  connected it with the same servlet to relate to itself
        out.println("<input type=\"text\" name=\"search_bar\" class=\"searchbar\" placeholder=\"what are you looking for ?\">");
        out.println("<div class=\"button_s\">");
        out.println("<input type=\"submit\" class=\"search_button\" value=\"search\">");
        out.println("</div>");
        out.println("</form>");


        out.println("<div class=\"main_contener\">"); // its our main container


            out.println("<div class=\"epic_site\">"); // epic games container starts here

        out.println("<div class=\"site_image\">");
        out.println("<image src=\"https://i.postimg.cc/52c2yGHj/epic-game-logo.png\" width=\"26%\" height=\"17%\">"); // loads epic games store logo
        out.println("</image>");
        out.println("</div>");

        for(Product ep : EpicProducts) { // loop to write every added product in epic games array list
            out.println("<div class=\"product\">");

            out.println("<div class=\"title\">");
            out.println("<a class=\"product_title_link\" href=\""+ ep.url+"\">"+ep.name+"</a>"); // product title with url to store page
            out.println("</div>");

            out.println("<div class=\"image_and_price\">");

            out.println("<div class=\"product_image\">");
            out.println("<a href=\""+ep.url+"\">"); // url to products's store page in epic games
            out.println("<image src=\""+ep.imageUrl+"\"></image>"); // image of product
            out.println("</a>");
            out.println("</div>");

            out.println("<div class=\"prices\">");
            out.println("<div class=\"normal_price\">");
            out.println(ep.mainPrice); // normal price
            out.println("</div>");

            out.println("<div class=\"discount\">");
            out.println("<span class=\"discount_epic\">"+ep.discountPrice+"</span>"); // discount price
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"description\">");
            out.println(ep.description); // description of product
            out.println("</div>");

            out.println("<div class=\"languages\">");
            out.println(ep.languages); // available languages in epic games

            out.println("</div>");

            out.println("</div>");
        }
        out.println("</div>"); // epic games store container ends here


        out.println("<div class=\"steam_site\">"); // steam container starts here

        out.println("<div class=\"site_image\">");
        out.println("<image src=\"https://i.postimg.cc/CLPXq7j7/steam-logo.png\" width=\"30.5%\" height=\"15%\"></image>"); // loads steam store logo
        out.println("</div>");

        for(Product sp : SteamProducts) { // loop to write every product in steam array list
            out.println("<div class=\"product\">");

            out.println("<div class=\"title\">");
            out.println("<a class=\"product_title_link\" href=\""+sp.url+"\">"+sp.name+"</a>"); // product title and url to its product steam store page
            out.println("</div>");

            out.println("<div class=\"image_and_price\">");
            out.println("<div class=\"prices\">");
            out.println("<div class=\"normal_price\">");
            out.println(sp.mainPrice); // normal price
            out.println("</div>");

            out.println("<div class=\"discount\">");
            out.println("<span class=\"discount_steam\">"+sp.discountPrice+"</span>"); // discount price
            out.println("</div>");
            out.println("</div>");

            out.println("<div class=\"product_image\">");
            out.println("<a href=\""+sp.url+"\">"); // url to product steam store page
            out.println("<image src=\""+sp.imageUrl+"\" ></image>"); // image of product
            out.println("</a>");
            out.println("</div>");


            out.println("</div>");
            out.println("<div class=\"description_steam\">");
            out.println(sp.description); // description of product in steam
            out.println("</div>");

            out.println("<div class=\"languages_steam\">");
            out.println(sp.languages); // available languges on steam
            out.println("</div>");

            out.println("</div>");
        }//
            out.println("</div>");

        out.println("</div>");
        out.println("</div>");
        out.println("</body>");

        out.println("</html>");




}}
