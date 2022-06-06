import "https://code.jquery.com/jquery-3.6.0.js";
import "https://code.jquery.com/jquery-migrate-3.4.0.js";
// import "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js";
import "./Redirect.js";
import "./jquery-captcha.js";
var OrderId;
var CustomerId;
var budget;
var rate;


$(document).ready(function () {
    var captcha;
    var captcha = new Captcha($('#canvas'));
    //  alert(captcha)
    $('#payingbut').on('click', function () {
        const ans = captcha.valid($('input[name="code"]').val());
        if(ans){
                paying();
        }else{
            captcha = new Captcha($('#canvas'));
        }
        console.log(ans);

    });
});
$(document).ready(function () {
    setTimeout(function () {
        alert("This is the alert message for timer");
        $.redirect('/CustomerView.html',null,'get');  

    }, 50000);
    var s=$.redirect.getForm.val
 


});


var queryString = new Array();
window.onload = function () {
if (queryString.length == 0) {
    if (window.location.search.split('?').length > 1) {
        var params = window.location.search.split('?')[1].split('&');
        for (var i = 0; i < params.length; i++) {
            var key = params[i].split('=')[0];
            var value = decodeURIComponent(params[i].split('=')[1]);
            queryString[key] = value;
        }
    }
}
if (queryString["orderId"] != null && queryString["rate"] != null) {
    var data = "<u>Values from QueryString</u><br /><br />";
    data += "<b>Name:</b> " + queryString["orderId"] + " <b>Technology:</b> " + queryString["customeId"];
    document.getElementById("lblData").innerHTML = data;
    OrderId=queryString["orderId"];
    // CustomerId=queryString["customerId"];
    rate=queryString["rate"];
}
alert("id order"+OrderId);
$.ajax({
    url: "http://localhost:8080/customer/howMuch/",
    type: "GET",
    data: {
        id:OrderId
    },
    // contentType: "application/json;charset=utf-8",
    dataType: "json",
    success: function (result) {
        debugger;
        if (result) {
            // $("#myInfo").empty();
            // var id =result["rate"];
            
            // alert(result.val)
            budget = result["offerPrice"] ;
            // alert("my budget: "+budget);
            document.getElementById("paying").innerHTML = budget;
        }
    },
    error: function (errormessage) {
        alert(errormessage.responseText);
    }
});

};
// $(document).ready(function () {
// $("#paying").click(function (event) {
function paying(){

var formData = {
    id:OrderId,
    amount:budget,
    rate:rate
};
$.ajax({
    type: "POST",
    method: "POST",
    url: "http://localhost:8080/customer/onlinePaying/",
    data: formData,
    dataType: "json",
    // encode: true,
    success: function (response) {
        if (response) {
            $.redirect('/CustomerView.html',null,'get');         
        }
    },error: function (errormessage) {
        alert(errormessage.responseText);
    }
})

// event.preventDefault();
}