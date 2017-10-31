$(document).ready(function () {
// center all H1 and H2 elements
    $("h1").addClass("text-center");
    $("h2").addClass("text-center");
// replace the class that is on the element containing "Team Up!" with class page-header
    $(".myBannerHeading").addClass("page-header").removeClass("myBannerHeading");
// change text of "The Squad" to "Yellow Team"
    $("#yellowHeading").text("Yellow Team");
// change background color of each team list to match name of the team
    $("#orangeTeamList").css("background-color", "orange");
    $("#redTeamList").css("background-color", "red");
    $("#blueTeamList").css("background-color", "blue");
    $("#yellowTeamList").css("background-color", "yellow");
// **not working** add Joseph Banks and Simons Jones to Yellow Team list
    $("#yellowTeamList").append("<li>JosephBanks</li>").append("<li>Simon Jones</li>");
// hide element containing the text "Hide Me!!!"
    $("#oops").hide();
// remove element containing "Bogus Contact Info" from footer
    $("#footerPlaceholder").remove();
// add a p element containing your name & email to footer (Courier, 24px)
    $(".footer").append("p").text("James Lee jamesminlee@gmail.com")
    .css("font-family", "Courier").css("font-size", "24px");
    
});