<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html> <!-- This file generate our starting page -->
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="main_site.css">
    <script src="https://www.google.com/recaptcha/api.js?render=6LceX9QdAAAAAPaRv60_SfIqi3oL_Tabe3FT_-dD"></script> <!-- its load reCaptcha script-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"> <!-- load new font from google fonts -->
    <title>Game Price Aggregator</title>
</head>
<body>

<div class = "frontpage">
    <form class = "inputs" name="search_page" method="post" action="search_results"> <!-- This connects starting page with results page and send what did we write -->
        <input type="text" name = "search_bar" class = "searchbar" placeholder="what are you looking for ?"> <!-- its create search bar to write in -->
        <div class="button_s">
            <input type="submit" class="g-recaptcha" value="search"> <!-- creates button to submit to change page (class has to be g-recaptcha to reCaptcha works properly)-->
            <script> // here we add reCaptcha script to our button
                function onClick(e) {
                    e.preventDefault();
                    grecaptcha.ready(function() {
                        grecaptcha.execute('6LceX9QdAAAAAPaRv60_SfIqi3oL_Tabe3FT_-dD', {action: 'submit'}).then(function(token) { // inside execute has to be site key
                            // Add your logic to submit to your backend server here.
                        });
                    });
                }
            </script>
        </div>
    </form>
</div>

</body>
</html>