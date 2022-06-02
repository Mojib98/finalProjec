import "https://code.jquery.com/jquery-3.6.0.js";
import "https://code.jquery.com/jquery-migrate-3.4.0.js";
import "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js";
let orid;
var checkbox;
$(document).ready(function () {
    $("#button").click(function (event) {
        alert($('input[name="check"]:checked').attr('value'));
        var formData = {
            describe: $("#describe").val(),
            localDateTime: $("#time").val(),
            address: $("#address").val(),
            offerPrice: $("#price").val(),
            orderId: $('input[name="check"]:checked').attr('value')
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/exp/writeOffer",
            data: formData,
            dataType: "json",
            encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
//serOrderforOffer
$(document).ready(function () {
    $("#createOffer").click(function (event) {
    $.ajax({
        url: "http://localhost:8080/exp/seeorder/",
        type: "GET",
        // data: {depart:deptid},
        dataType: "json",
        success: function (result) {
            debugger;
            if (result) {
                //itetrate thorugh each record and bind it to td
                var html = '';
                $.each(result, function (key, item) {
                    var tablerow = '<tr>'
                        + '<td id="ide">' + item.id + '</td>'
                        + '<td>' + item.subServiceName+'</td>'
                        + '<td >' + item.describe + '</td>'
                        + '<td >' + item.timeForWork + '</td>'
                        + '<td >' + item.orderPrice + '</td>'
                        + '<td >' + item.address + '</td>'
                        + '<td>' + "<input type='checkbox' name='check' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                        + '</tr>';
                    $("#tblbody").append(tablerow);
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
    checkbox = null;
    $('input[type="checkbox"]').not(this).prop('checked', false);
    checkbox = document.querySelector('input[type="checkbox"]');
});
/*    $(document).ready(function(){
        $('input[name="check1"]').on('change', function() {
            $('input[name="check1"]').not(this).prop('checked', false);
            alert("The best cricketer is: " + $('input[name="check1"]:checked').attr("id"));
        });
    });*/
//serviceList
    $(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/exp/serviceList/",
        type: "GET",
        // data: {depart:deptid},
        dataType: "json",
        success: function (response) {

            var len = response.length;

            $("#listof").empty();
            for (var i = 0; i < len; i++) {
                var id = response[i]['id'];
                var name = response[i]['serviceName'];

                // $("#service").append("<option value='"+id+"'>"+name+"</option>");
                $("#listof").append("<input type='checkbox' name='check' class='radio' value='" + name + "' id='" + id + "' />" + name + "</label>");

            }
        }
    });
    $("#request").click(function (event) {
        var formData = {
            serviceName: $('input[name="check"]:checked').attr('value')
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/exp/request",
            data: formData,
            dataType: "json",
            encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
//orderForStart
$(document).ready(function () {
    // order=$('input[name="myOrder"]:checked').attr('value');
    $("#showOrderForStart").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/exp/startOrder/",
            type: "GET",
            // data: {
            //     id:$('input[name="myOrder"]:checked').attr('value')
            // },
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    $("#listOrderForStart").empty();

                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td id="ide">' + item.id + '</td>'
                            + '<td>' + item.describe + '</td>'
                            + '<td>' + item.orderPrice + '</td>'
                            + '<td id="email">' + item.address + '</td>'
                            + '<td>' + "<input type='checkbox' name='forStart' class='forStart' value='" + item.id + "' id='" + item.id + "' />" + "<lable></label>" + '</td>'
                            + '</tr>';
                        $("#listOrderForStart").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
    // });
    // $(document).ready(function () {

    $("#forStartWork").click(function (event) {
        alert($('input[name="forStart"]:checked').attr('id'))
        var formData = {
            id: $('input[name="forStart"]:checked').attr('id'),
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/exp/startwork/",
            data: formData,
            dataType: "json",
            encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
//DownWork
$(document).ready(function () {
    // order=$('input[name="myOrder"]:checked').attr('value');
    $("#showDownWork").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/exp/donelist/",
            type: "GET",
            // data: {
            //     id:$('input[name="myOrder"]:checked').attr('value')
            // },
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    $("#listOrderForDown").empty();
                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td id="ide">' + item.id + '</td>'
                            + '<td>' + item.describe + '</td>'
                            + '<td>' + item.offerPrice + '</td>'
                            + '<td id="email">' + item.address + '</td>'
                            + '<td>' + "<input type='checkbox' name='finishing' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                            + '</tr>';
                        $("#listOrderForDown").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});
//finishWork
$(document).ready(function () {
    $("#finish").click(function (event) {
        alert($('input[name="finishing"]:checked').attr('value'))
        var formData = {
            id: $('input[name="finishing"]:checked').attr('value'),
            // superheroAlias: $("#superheroAlias").val(),
        };
        alert(formData)

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/exp/finish/",
            data: formData,
            dataType: "json",
            encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
//order
$(document).ready(function () {

    $("#showAllOrderBut").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/exp/seeAllorder/",
            type: "GET",
            // data: {depart:deptid},
            dataType: "json",
            success: function (result) {
                debugger;
                if (result) {
                    //itetrate thorugh each record and bind it to td
                    $("#AllOrders").empty();
                    var html = '';
                    $.each(result, function (key, item) {
                        var tablerow = '<tr>'
                            + '<td id="ide">' + item.id + '</td>'
                            + '<td>' + item.subServiceName+'</td>'
                            + '<td >' + item.describe + '</td>'
                            + '<td >' + item.timeForWork + '</td>'
                            + '<td >' + item.orderPrice + '</td>'
                            + '<td >' + item.address + '</td>'
                            + '</tr>';
                        $("#AllOrders").append(tablerow);
                    });
                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});
//show info
$(document).ready(function () {
    // order=$('input[name="myOrder"]:checked').attr('value');
    $("#info").click(function (event) {
        $.ajax({
            url: "http://localhost:8080/exp/showMyInfo/",
            type: "GET",
            // data: {
            //     id:$('input[name="myOrder"]:checked').attr('value')
            // },
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                if (result) {
                    // $("#myInfo").empty();

                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    var id =result["rate"];
                    var name = result["wallet"] ;

                    alert("my rate:"+id+"  "+"my budget: "+name)
                    $("#myInfo").append("<p>"+id+" "+name+"</p>");

                }
            },
            error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });
});
