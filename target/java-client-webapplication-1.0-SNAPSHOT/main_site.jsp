<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="main_site.css">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <title>Game Price Aggregator</title>
</head>
<body>

<div class = "frontpage">
    <form class = "inputs" name="search_page" method="post" action="search_results">
        <input type="text" name = "search_bar" class = "searchbar" placeholder="what are you looking for ?">
        <div class="button_s">
            <input type="submit" class="g-recaptcha" value="search">
            <script>
                function onClick(e) {
                    e.preventDefault();
                    grecaptcha.ready(function() {
                        grecaptcha.execute('6LceX9QdAAAAAPaRv60_SfIqi3oL_Tabe3FT_-dD', {action: 'submit'}).then(function(token) {
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