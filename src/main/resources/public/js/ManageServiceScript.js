import "https://code.jquery.com/jquery-3.6.0.js";
import "https://code.jquery.com/jquery-migrate-3.4.0.js";
import "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js";
$("input[name='price']").on("change", function () {
    if ($(this).val() >= 0) {
    } else {
        alert("negative mony")
        $(this).val('');
    }
});
$(document).ready(function () {
    alert($("#newServiceName").val())
    $("#butservice").click(function (event) {
        var formData = {
            serviceName: $("#newServiceName").val()

            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/service/addservice",
            data: formData,
            dataType: "json",
            // encode: true,
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});
$(document).ready(function () {
    $("#butsub").click(function (event) {
        alert($("#services").val())
        var formData = {
            serviceName: $("#services").val(),
            subServiceName: $("#subService").val(),
            basePrice: $("#price").val(),
            describe: $("#describe").val()

            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/service/addsub",
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
    $("#show").click(function (event) {
        BindpatientData();
    })
});
function BindpatientData() {
    $.ajax({
        url: "http://localhost:8080/service/sub",
        type: "GET",
        method : "GET",
        // contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            debugger;
            if (result) {
                $("#tblbody").empty();

                //itetrate thorugh each record and bind it to td
                var html = '';
                $.each(result, function (key, item) {
                    var tablerow = '<tr>'
                        + '<td>' + item.id + '</td>'
                        + '<td>' + item.subServiceName + '</td>'
                        + '<td>' + item.basePrice + '</td>'
                        + '</tr>';
                    $("#tblbody").append(tablerow);
                });
            }
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
};
    $(document).ready(function () {
        $("#addSubServicePanel").click(function (event) {
            $.ajax({
                url: "http://localhost:8080/service/allService",
                type: "GET",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function (result) {
                    $("#services").empty();
                    $.each(result,function(key, value)
                    {
                        $("#services").append('<option value="' + value.serviceName +'">' + value.serviceName + '</option>');
                    });
                },
                error: function (errormessage) {
                    alert(errormessage.responseText);
                }
        })
    });
});