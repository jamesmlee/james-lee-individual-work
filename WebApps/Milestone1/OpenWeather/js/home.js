$(document).ready(function () {

    $("#forecastWeather").hide();
    $("#currentWeather").hide();

    $("#get-weather-button").click(function(event) {

      $('#currentWeather').show();

      var url = 'http://api.openweathermap.org/data/2.5/weather?zip=' + $('#enter-zip').val() + ',us&appid=cffcd5d8665223e49fe5b67229f3075c';
      console.log(url);

      $.ajax({
       type: 'GET',
       url: url,
       success: function(data, status) {
         var iconUrl = 'http://openweathermap.org/img/w/' + data.weather[0].icon + '.png';
// adds city name to header for current weather
         $('#currentHeader').append(data.name);
         $('#iconAndDescription').append('<img src="' + iconUrl + '">' + data.weather[0].description);
       },
       error: function() {
         console.log("Error");
       }
       });

     });

   });

   function getTodaysWeather() {
// can put first ajax call here       
   }

   // mine: cffcd5d8665223e49fe5b67229f3075c
   // 4fd6546e03854def178a9111c12ff40e