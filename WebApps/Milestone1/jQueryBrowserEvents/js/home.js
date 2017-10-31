$(document).ready(function () {
// only the content in the Main section should display when the page is loaded
    $("#mainInfoDiv").siblings().hide();
// when Akron button is clicked, only content in Akron section should display.
// weather info for Akron should be hidden initially
    $("#akronButton").on("click", function() {
        $("#akronInfoDiv").toggle("slow");
        $("#akronWeather").hide();
    });
// Minneapolis
    $("#minneapolisButton").on("click", function() {
        $("#minneapolisInfoDiv").toggle("slow");
        $("#minneapolisWeather").hide();
    });
// Louisville
    $("#louisvilleButton").on("click", function() {
        $("#louisvilleInfoDiv").toggle("slow");
        $("#louisvilleWeather").hide();
    });
// when Show/Hide Weather button is clicked, display if hidden or hide if showing. hide = default
    $("#akronWeatherButton").on("click", function() {
        $("#akronWeather").toggle("slow");
    });
// Minneapolis
    $("#minneapolisWeatherButton").on("click", function() {
        $("#minneapolisWeather").toggle("slow");
    });
// Louisville
    $("#louisvilleWeatherButton").on("click", function() {
        $("#louisvilleWeather").toggle("slow");
    });
// change background color of any table row to “WhiteSmoke” when hovering over.
// background color of the row should return to white when the mouse pointer is no longer hovering over 
// $("tr").hover(
    $("tr:not(:first-child)").hover(
        function() {
// Gray because WhiteSmoke too hard to see            
            $(this).css("background-color", "Gray");
        },

        function() {
            $(this).css("background-color", "");
        }
    );

});