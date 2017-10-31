$(document).ready(function(){

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/contacts',
        success: function(contactArray) {
// get a reference to the 'allContacts' div
            var contactsDiv = $('#allContacts');

// go through each of the returned contacts and append the info to the contactsDiv.
// for each thing in contactArray, run the function
            $.each(contactArray, function(index, contact) {
                var contactInfo = '<p>';
                contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
                contactInfo += 'Company: ' + contact.company + '<br>';
                contactInfo += 'Email: ' + contact.email + '<br>';
                contactInfo += 'Phone: ' + contact.phone + '<br>';
                contactInfo += '<hr>';

                contactsDiv.append(contactInfo);
            })
        },
        error: function() {
            alert('FAILURE!');
        }
    });

// this code only runs if user clicks the button:
// make an ajax call to the endpoint that allows us to create a contact ... go to the url.
// in request body, it's also requesting a JSON string with the contact info we want to create.
// create a JSON string and grab that info from the form. 
// specify http headers to help give guidance to web service about what's being sent. 
// tell the web service what we expect back (JSON).
// register SUCCESS or FAILURE

    $('#add-button').on('click', function() {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/contact',
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                company: $('#add-company').val(),
                phone: $('#add-phone').val(),
                email: $('#add-email').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function(contact) {
                // get a reference to the 'newContact' div
                var newContactDiv = $('#newContact');

                // append contact info to the newContact div
                var contactInfo = '<p>';
                contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
                contactInfo += 'Company: ' + contact.company + '<br>';
                contactInfo += 'Email: ' + contact.email + '<br>';
                contactInfo += 'Phone: ' + contact.phone + '<br>';
                contactInfo += '<hr>';

                newContactDiv.append(contactInfo);

            },
            error: function() {
                alert('FAILURE');
            }
        });

    });

})
