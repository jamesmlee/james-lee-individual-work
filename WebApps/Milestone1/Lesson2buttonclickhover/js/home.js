$(document).ready(function() {
// click: be able to click Toggle Numbers! and have it hide/reveal #1s
    $("#toggleNumbers").on("click", function() {
        $("h2").toggle("slow");
    });
// click: center headings (h1, h2) and buttons
    $("#centerUp").on("click", function() {
        $("h1").addClass("text-center");
        $("h2").addClass("text-center");
        $("#buttonDiv").addClass("text-center");
    });
// click: make headings blue    
    $("#headingsBlue").on("click", function() {
        $("h1").css("color", "blue");
    });

// HOVER: change background color of div when mouse hovers over it
    $("div").hover(
// in function ... runs when we first hover over        
        function() {
            $(this).css("background-color", "CornflowerBlue");
        },
// out function ... runs when our mouse leaves
        function() {
// empty string sets to default            
            $(this).css("background-color", "");
        }
    );

// HOVER: change background color of h2 which is inside a div
// both will occur at the same time    
    $("h2").hover(
        function() {
// #s 1-4 will change to dark orange within the blue background div
            $(this).css("color", "DarkOrange");
        },
        function() {
            $(this).css("color", "");
        }
    );

// HOVER: change title to red when hovering in, and pink when leaving
    $("#mainHeading").hover(
        function() {
            $(this).css("color", "red");
        },
        function() {
            $(this).css("color", "pink");
        }
    );
});


