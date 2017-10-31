$(document).ready(function () {

  fillMachine();

// $ in pennies  
  var moneyEntered = 0;

  $("#dollar-button").click(function (event) {
    moneyEntered += 100;
    console.log(moneyEntered);
    var moneyString = "$" + (moneyEntered / 100).toFixed(2);
    $("#moneyInserted").val(moneyString);
  });

  $("#quarter-button").click(function (event) {
    moneyEntered += 25;
    console.log(moneyEntered);
    var moneyString = "$" + (moneyEntered / 100).toFixed(2);
    $("#moneyInserted").val(moneyString);
  });

  $("#dime-button").click(function (event) {
    moneyEntered += 10;
    console.log(moneyEntered);
    var moneyString = "$" + (moneyEntered / 100).toFixed(2);
    $("#moneyInserted").val(moneyString);
  });

  $("#nickel-button").click(function (event) {
    moneyEntered += 5;
    console.log(moneyEntered);
    var moneyString = "$" + (moneyEntered / 100).toFixed(2);
    $("#moneyInserted").val(moneyString);
  });

  $("#make-purchase-button").click(function (event) {
    var userSelection = $("#item-selection").val();
    var urlString = "http://localhost:8080/money/" + (moneyEntered/100) + "/item/" + userSelection;
    var changeString = "";

    $.ajax({
      type: "GET",
      url: urlString,
      success: function (change) {
        console.log("call successful");

        var quarters = change.quarters;
        var dimes = change.dimes;
        var nickels = change.nickels;
        var pennies = change.pennies;

        if(quarters) {
          changeString += quarters + " quarters ";
        }
        if(dimes) {
          changeString += dimes + " dimes ";
        }
        if(nickels) {
          changeString += nickels + " nickels ";
        }
        if(pennies) {
          changeString += pennies + " pennies ";
        }
        console.log(changeString);
        $("#changeField").val(changeString);
        $("#moneyInserted").val("0.00");
        moneyEntered = 0;
// update quantity after making a purchase        
        fillMachine();
// click the actual change, and not the button        
        $("#messagesField").val("Thank you! Click on your change below");
      },
      error: function (error) {
        console.log("Error");
// this shows responseJSON        
        console.log(error);
        $("#messagesField").val(error.responseJSON.message);
      }

    });
  });

  $("#change-return-button").click(function (event) {
    var remainingPennies = moneyEntered;
    var numQ = 0;
    var numD = 0;
    var numN = 0;
    var numP = 0;
    var changeString = "";

    numQ = remainingPennies / 25;
    numQ = Math.floor(numQ);
    remainingPennies = remainingPennies % 25;
    numD = remainingPennies / 10;
    numD = Math.floor(numD);
    remainingPennies = remainingPennies % 10;
    numN = remainingPennies / 5;
    numN = Math.floor(numN);
    remainingPennies = remainingPennies % 5;
    numP = remainingPennies;
    
    if(numQ > 0) {
      changeString += numQ + " quarters ";
    }
    if(numD > 0) {
      changeString += numD + " dimes ";
    }
    if(numN > 0) {
      changeString += numN + " nickels ";
    }
    if(numP > 0) {
      changeString += numP + " pennies ";
    }

    console.log(changeString);
    $("#changeField").val(changeString);
    $("#messagesField").val("Thank you! Click on your change below");
    $("#moneyInserted").val("0.00");
    moneyEntered = 0;
    takeChangeBail();
  });

  
});

function fillMachine() {
  $.ajax({

    type: "GET",
    url: "http://localhost:8080/items",
// getting back items (the array containing the 9 things) from the call    
    success: function (items) {
      console.log("call successful");
      $.each(items, function (index, item) {
// #slot beginning pattern for div ids for slot1 to slot9 ... where info will be filled
        var string = "#slot";
// the slot is the index + 1        
        string += (index + 1);
        console.log(string);
// show 2 decimal places on price instead of just 1        
        var price = Number(item.price).toFixed(2);
// to avoid overwriting original fillMachine on load
        $(string).empty();

        $(string).append(item.name + "<br><br>" + "$" + price + "<br><br>" + "Quantity Left: " + item.quantity);
      });
    },
    error: function () {
      console.log("Error");
    }

  });
}

// click on each of the slots from HTML
function selectItem(itemNumber) {
  console.log(itemNumber);
  $("#item-selection").val(itemNumber);
// userSelection kicks off #make-purchase-button  
  userSelection = itemNumber;
}

// changeField from HTML ... not needed now?
function takeChange() {
  $("#changeField").val("none");
  $("#messagesField").val("");
  $("#moneyInserted").val("$0.00");
  $("#item-selection").val("");
}

function takeChangeBail() {
// does not clear changeField  
  $("#messagesField").val("");
  $("#moneyInserted").val("$0.00");
  $("#item-selection").val("");
}

// change
// localhost:8080/money/4.35/item/5

