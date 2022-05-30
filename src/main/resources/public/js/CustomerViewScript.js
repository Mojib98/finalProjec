import "https://code.jquery.com/jquery-3.6.0.js";
import "https://code.jquery.com/jquery-migrate-3.4.0.js";
import "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js";
let SubServiceName;
let idSubService;
let order;
$("input[id='price']").on("change", function () {
    if ($(this).val() < 0) {
        alert("invalid price")
        $(this).val('');
    } else {

    }
});
//createOrder
$(document).ready(function () {
    $("#createOffer").click(function (event) {
        alert($("#Subservices").val());
        var formData = {
            describe: $("#describe").val(),
            localDateTime: $("#time").val(),
            subServiceId: $("#Subservices").val(),
            address: $("#address").val(),
            offerPrice: $("#price").val(),
            // subServiceId: idSubService
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/customer/createorder/",
            data: formData,
            dataType: "json",
            encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/service/allService/",
        type: "GET",
        // data: {depart:deptid},
        dataType: "json",
        success: function (response) {

            var len = response.length;

            $("#service").empty();
            for (var i = 0; i < len; i++) {
                var id = response[i]['id'];
                var name = response[i]['serviceName'];

                $("#service").append("<option value='" + id + "'>" + name + "</option>");
                // service
            }
        }
    });
});
$(document).ready(function () {
    $("#service").change(function () {
        var formData = {
            id: $(this).val(),// superheroAlias: $("#superheroAlias").val(),
        };
        $.ajax({
            url: 'http://localhost:8080/service/sub/',
            type: 'post',
            data: formData,
            dataType: 'json',
            // contentType: false,

                success: function (response) {

                    var len = response.length;
        
                    $("#Subservices").empty();
                    for (var i = 0; i < len; i++) {
                        var id = response[i]['id'];
                        var name = response[i]['subServiceName'];
        
                        $("#Subservices").append("<option value='" + id + "'>" + name + "</option>");
                        // service
                    }
            }
        });
    });

});
//____________________________---------
$("service").on("change", function () {
    SubServiceName = $("subServiceId").find(":selected").text();
    idSubService = $("input[name='subServiceId']:checked").val();

    alert(SubServiceName + " " + id + $("#time").val());
});
$(document).ready(function () {
    $("#showOrders").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/customer/myOrder/",
            type: "GET",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td id="ide">' + item.id + '</td>'
                            + '<td>' + item.describe + '</td>'
                            + '<td>' + item.offerPrice + '</td>'
                            + '<td id="email">' + item.timeForWork + '</td>'
                            + '<td id="email">' + item.workStatus + '</td>'
                            + '<td>' + "<input type='checkbox' name='myOrder' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                            + '</tr>';
                        $("#listOfOrderForChoice").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});
$(document).on('click', 'input[type="checkbox"]', function () {
    // checkbox = null;
    $('input[type="checkbox"]').not(this).prop('checked', false);
    checkbox = document.querySelector('input[type="checkbox"]');
    // alert($('input[name="subServiceId"]:checked').attr('value'))
});
//showOffer
$(document).ready(function () {
    $("#StartshowOffer").click(function (event) {
        order = $('input[name="myOrder"]:checked').attr('value');
        alert($('input[name="myOrder"]:checked').attr('value'));
        $.ajax({
            url: "http://localhost:8080/customer/myOffer/",
            type: "GET",
            data: {
                id: $('input[name="myOrder"]:checked').attr('value')
            },
            // contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    $("#OffferForOrder").empty();

                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td id="ide">' + item.id + '</td>'
                            + '<td>' + item.offerPrice + '</td>'
                            + '<td>' + item.workTime + '</td>'
                            + '<td id="email">' + item.timeForWork + '</td>'
                            + '<td id="email">' + item.expertName + '</td>'
                            // + '<td id="email">' + item.subServiceId + '</td>'
                            + '<td>' + "<input type='checkbox' name='myOffers' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                            + '</tr>';
                        $("#OffferForOrder").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});

//downWork
$(document).ready(function () {
    order = $('input[name="myOrder"]:checked').attr('value');
    $("#showDownWork").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/customer/downOrder/",
            type: "GET",
            // data: {
            //     id: $('input[name="myOrder"]:checked').attr('value')
            // },
            // contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    $("#listDownWork").empty();

                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td >' + item.id + '</td>'
                            + '<td>' + item.offerPrice + '</td>'
                            // + '<td>' + item + '</td>'
                            + '<td >' + item.describe + '</td>'
                            // + '<td >' + item + '</td>'
                            // + '<td id="email">' + item.subServiceId + '</td>'
                            + '<td>' + "<input type='checkbox' name='downOrder' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                            + '</tr>';
                        $("#listDownWork").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});
//paying
$(document).ready(function () {
    $("#paying").click(function (event) {

        alert($('input[name="downOrder"]:checked').attr('value'));
        alert($('#rating').val());
        var formData = {
            id: $('input[name="downOrder"]:checked').attr('value'),
            commentText :$("#commenrText").val(),
            rate: $('#rating').val(),
            // subServiceId: idSubService
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/customer/paying/",
            data: formData,
            dataType: "json",
            encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
//choice
$(document).ready(function () {

    $("#chioceOffer").click(function (event) {
        alert($('input[name="myOffers"]:checked').attr('value'))
        alert(order)
        var formData = {
            id: $('input[name="myOffers"]:checked').attr('value'),
            orderId:  $('input[name="myOrder"]:checked').attr('value'),
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/customer/choiceOffer/",
            data: formData,
            dataType: "json",
            encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
//-------------------------۰۰۰۰۰/
//show all Order
$(document).ready(function () {
    $("#ALlOrder").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/customer/allmyorder/",
            type: "GET",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    //itetrate thorugh each record and bind it to td
                    $("#tableOrder").empty();
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td id="ide">' + item.id + '</td>'
                            + '<td>' + item.describe + '</td>'
                            + '<td>' + item.offerPrice + '</td>'
                            + '<td id="email">' + item.timeForWork + '</td>'
                            + '<td id="email">' + item.workStatus + '</td>'
                            + '</tr>';
                        $("#tableOrder").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});
//all order
$(document).ready(function () {
    $("#").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/customer/allmyorder/",
            type: "GET",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    //itetrate thorugh each record and bind it to td
                    $("#listOfOrder").empty();
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td id="ide">' + item.id + '</td>'
                            + '<td>' + item.describe + '</td>'
                            + '<td>' + item.offerPrice + '</td>'
                            + '<td id="email">' + item.timeForWork + '</td>'
                            + '<td id="email">' + item.workStatus + '</td>'
                            + '<td>' + "<input type='checkbox' name='myOrdder' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                            + '</tr>';
                        $("#listOfOrder").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});
//info
$(document).ready(function () {
    // order=$('input[name="myOrder"]:checked').attr('value');
    $("#myInfo").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/customer/myInfo/",
            type: "GET",
            // data: {
            //     id:$('input[name="myOrder"]:checked').attr('value')
            // },
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    // $("#myInfo").empty();

                    // var id =result["rate"];
                    var name = result["budget"] ;

                    alert("my budget: "+name);

                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});

