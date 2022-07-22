import "https://code.jquery.com/jquery-3.6.0.js";
import "https://code.jquery.com/jquery-migrate-3.4.0.js";
import "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js";
$(document).ready(function () {
    $("#show").click(function (event) {
        BindpatientData();
    })
});

function BindpatientData() {
    $.ajax({
        url: "http://localhost:8080/user/listExpert/",
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
                        + '<td>' + item.firstName + " " + item.lastName + '</td>'
                        + '<td id="email">' + item.email + '</td>'
                        + '<td>' + item.status + '</td>'
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
}
$(document).ready(function () {
    $("#speccialtyExpert").click(function (event) {
        specialtyList();
    })
});

function specialtyList() {
    $.ajax({
        url: "http://localhost:8080/user/specialty/",
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
                        + '<td>' + item.expertFirstName + " " + item.expertLastName + '</td>'
                        + '<td id="email">' + item.serviceName + '</td>'
                        + '<td>' + "<input type='checkbox' name='checkspecialty' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                        + '</tr>';
                    $("#tblbodyspecialty").append(tablerow);
                });
            }
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
}

// var userList = $('input[type=checkbox]:checked').serializeArray();
// alert(userList)
$(document).ready(function () {
    $("#butdet").click(function (event) {
        //     var checkboxValues = [];
        //     $('input.removeLater:checked').map(function () {
        //         checkboxValues.push($(this).val());
        //     });
        //     console.log(checkboxValues);
        // }
        var selectedExpert = new Array();
        var s = {};
        var categoryVals = [];
        // categoryVals.push('');
        $('input[name="check"]:checked').map(function () {
            var se = "'id':'" + $(this).val() + "'";
            categoryVals.push($(this).val());
            // alert(categoryVals)
            selectedExpert.push(this.value);
        });
        var formData = {
            ids: categoryVals
            // superheroAlias: $("#superheroAlias").val(),
        };
        // var formData = {
        //     ids: selectedExpert
        //
        //     // superheroAlias: $("#superheroAlias").val(),
        // };
        // var postData = {"arr": selectedExpert };
        // JSON.stringify(adresses.toArray())
        // var myJsonString = JSON.parse(selectedExpert);
        // alert(myJsonString)
        // alert(selectedExpert)

        // alert("Number of selected Languages: " + selectedExpert.length + "\n" + "And, they are: " + selectedExpert.values);
        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/user/expertdetermine/",
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
    $("#butdetspecialty").click(function (event) {
        //     var checkboxValues = [];
        //     $('input.removeLater:checked').map(function () {
        //         checkboxValues.push($(this).val());
        //     });
        //     console.log(checkboxValues);
        // }
        var selectedExpert = new Array();
        var s = {};
        var categoryVals = [];
        // categoryVals.push('');
        $('input[name="checkspecialty"]:checked').map(function () {
            var se = "'id':'" + $(this).val() + "'";
            categoryVals.push($(this).val());
            // alert(categoryVals)
            selectedExpert.push(this.value);
        });
        var formData = {
            ids: categoryVals
            // superheroAlias: $("#superheroAlias").val(),
        };
        // var formData = {
        //     ids: selectedExpert
        //
        //     // superheroAlias: $("#superheroAlias").val(),
        // };
        // var postData = {"arr": selectedExpert };
        // JSON.stringify(adresses.toArray())
        // var myJsonString = JSON.parse(selectedExpert);
        // alert(myJsonString)
        // alert(selectedExpert)

        // alert("Number of selected Languages: " + selectedExpert.length + "\n" + "And, they are: " + selectedExpert.values);
        $.ajax({
            type: "POST",
            method: "POST",
            url: "http://localhost:8080/user/specialtydetermine/",
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
    $("#search").click( function (event) {
        var form = $('#myform')[0];
        // Create an FormData object
        var data = new FormData(form);
        var formData = {
            // serviceName: $("#service").val(),
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            url: 'http://localhost:8080/user/searchexpert',
            type: 'POST',
            // method: 'POST',
            data: formData,
            dataType: 'json',

            success: function (response) {
                if (response) {
                    $("#expertResult").empty();
                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(response, function (key, item) {
                        var tablerow = '<tr>'

                            + '<td>' + item.id + '</td>'
                            + '<td>' + item.firstName + '</td>'
                            + '<td>' + item.lastName + '</td>'
                            + '<td>' + "<input type='checkbox' name='subServiceId' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                            + '</tr>';
                        $("#expertResult").append(tablerow);
                    });
                }
            },error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });

});
//_____________________________________
$(document).ready(function () {
    $("#searchcustomer").click( function (event) {
        var form = $('#myform')[0];
        // Create an FormData object
        var data = new FormData(form);
        var formData = {
            // serviceName: $("#service").val(),
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            url: 'http://localhost:8080/user/searchcustomer/',
            type: 'POST',
            // method: 'POST',
            data: formData,
            dataType: 'json',

            success: function (response) {
                if (response) {
                    $("#CustomerResult").empty();
                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(response, function (key, item) {
                        var tablerow = '<tr>'

                            + '<td>' + item.id + '</td>'
                            + '<td>' + item.firstName + '</td>'
                            + '<td>' + item.lastName + '</td>'
                            + '<td>' + "<input type='checkbox' name='subServiceId' class='radio' value='" + item.id + "' id='" + item.id + "' />" + "</label>" + '</td>'
                            + '</tr>';
                        $("#CustomerResult").append(tablerow);
                    });
                }
            },error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });

});
//   ___________________________________


// $(document).ready(function () {
//             $("#search").click(function (event) {
//                 // var formData = {
//                 //     serviceName: $("#service").val(),

//                 //     // superheroAlias: $("#superheroAlias").val(),
//                 // };
//                 var form = $('#myform')[0];
// //         // Create an FormData object
//         var data = new FormData(form);


//                 $.ajax({
//                     type: "POST",
//                     method: "POST",
//                     url: "http://localhost:8080/user/searchexpert/",
//                     data: this.data,
//                     dataType: "json",
//                     encode: true,
//                 }).done(function (data) {
//                     console.log(data);
//                 });

//                 event.preventDefault();
//             });
//         });
//______________________________
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
                $("#listof").append("<input type='checkbox' name='check' class='radio' value='"+id+"' id='"+id+"' />" + name + "</label>");

            }
        }
    });
});
//________________________________________
$("#request").click(function (event) {
    var formData = {
        serviceId: $('input[name="check"]:checked').attr('value'),
        expertId:$('input[#insertspecialty ]').attr('value')
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
//-------------------------------------history of customer---------------
$(document).ready(function () {
    $("#historyOfCustomerbut").click(function (event) {
        userHistory("http://localhost:8080/user/customerHistory",$("#inputemailcustomer").val());
    })
});

function userHistory(targetUrl,email) {
    $.ajax({
        url: targetUrl,
        type: "GET",
        data: {
            email:email,
        },
        // contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            debugger;
            if (result) {
                //itetrate thorugh each record and bind it to td
                var html = '';
                $("#tblbodyhistoryOffer").empty();
                $.each(result, function (key, item) {
                    var tablerow = '<tr>'
                        + '<td id="ide">' + item.id + '</td>'
                        + '<td>' + item.expertName  + '</td>'
                        + '<td>' + item.offerPrice + '</td>' //price
                        + '<td>' + item.workTime + '</td>' //worktime
                        + '<td>' + item.subServiceName + '</td>' // serviceName
                        + '<td>' + item.workStatus + '</td>' //status
                        + '<td>' + item.orderId + '</td>' //orderId
                        + '</tr>';
                    $("#tblbodyhistoryOffer").append(tablerow);
                });
            }
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
}
$(document).ready(function () {
    $("#historyOfExpertbut").click(function (event) {
        userHistory("http://localhost:8080/user/expertHistory",$("#inputemailexpert").val());
    })
});

function expertHistory(expertHistory) {
    $.ajax({
        url: expertHistory,
        type: "GET",
        data: {
            email:$("#inputemailexpert").val(),
        },
        // contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            debugger;
            if (result) {
                //itetrate thorugh each record and bind it to td
                var html = '';
                $("#tblbodyhistoryOffer").empty();
                $.each(result, function (key, item) {
                    var tablerow = '<tr>'
                        + '<td id="ide">' + item.id + '</td>'
                        + '<td>' + item.expertName  + '</td>'
                        + '<td>' + item.offerPrice + '</td>' //price
                        + '<td>' + item.workTime + '</td>' //worktime
                        + '<td>' + item.subServiceName + '</td>' // serviceName
                        + '<td>' + item.workStatus + '</td>' //status
                        + '<td>' + item.orderId + '</td>' //orderId
                        + '</tr>';
                    $("#tblbodyhistoryOffer").append(tablerow);
                });
            }
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
}
//______________________orderSearch___________________
$(document).ready(function () {
    $("#doOrderSearch").click( function (event) {
        var form = $('#searchForm')[0];
        // Create an FormData object
        var data = new FormData(form);
        var formData = {
            serviceName: $("#Service").val(),
            subServiceName: $("#subService").val(),
            workStatus: $("#workStatus").val(),
            startDate: $("#startDate").val(),
            finishDate: $("#finishDate").val()

            // superheroAlias: $("#superheroAlias").val(),
        };

        $.ajax({
            url: 'http://localhost:8080/user/searchorder',
            type: 'POST',
            method: 'POST',
            data: formData,
            dataType: 'json',

            success: function (response) {
                if (response) {
                    $("#tblbodyresultSearch").empty();
                    //itetrate thorugh each record and bind it to td
                    var html = '';
                    $.each(response, function (key, item) {
                        var tablerow = '<tr>'

                            + '<td>' + item.id + '</td>'
                            + '<td>' + item.email + '</td>'
                            + '<td>' + item.orderPrice + '</td>'
                            + '<td>' + item.timeForWork + '</td>'
                            + '<td>' + item.subServiceName + '</td>'
                            + '<td>' + item.workStatus + '</td>'
                            + '<td>' + item.offerId + '</td>'
                            // + '<td>' + item + '</td>'
                            + '</tr>';
                        $("#tblbodyresultSearch").append(tablerow);
                    });
                }
            },error: function (errormessage) {
                alert(errormessage.responseText);
            }
        });
    });

});
//__________________searchByDate_______________-
$(document).ready(function () {
    $("#doUserSearch").click(function (event) {
        searchByDate();
    })
});

function searchByDate() {
    $.ajax({
        url: "http://localhost:8080/user/finbuserbydate",
        type: "GET",
        data: {
            date:$("#singupdate").val(),
        },
        // contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            debugger;
            if (result) {
                //itetrate thorugh each record and bind it to td
                var html = '';
                $("#userSearchResult").empty();
                $.each(result, function (key, item) {
                    var role;
                    if(item.rate==null){
                        item.rate='customer';
                        role='customer';
                    }else{
                        role='expert';
                    }
                    var tablerow = '<tr>'
                        + '<td id="ide">' + item.id + '</td>'
                        + '<td>' + item.firstName+" " +item.lastName + '</td>'
                        + '<td>' + item.email + '</td>' 
                        + '<td>' + item.status + '</td>' 
                        + '<td>' + item.wallet + '</td>' 
                        + '<td>' + item.rate + '</td>' 
                        + '<td>' + role + '</td>' 
                        + '</tr>';
                    $("#userSearchResult").append(tablerow);
                });
            }
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
}
$(document).ready(function () {
    $("#doCustomerSearchByNumber").click(function (event) {
        searchCustomerByOrderNumber();
    })
});

function searchCustomerByOrderNumber() {
    $.ajax({
        url: "http://localhost:8080/user/findcustomerordernum",
        type: "GET",
        data: {
            number:$("#numberOrder").val(),
        },
        // contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            debugger;
            if (result) {
                //itetrate thorugh each record and bind it to td
                var html = '';
                $("#userSearchResultByNumber").empty();
                $.each(result, function (key, item) {
                    var role;
                    if(item.rate==null){
                        item.rate='customer';
                        role='customer';
                    }else{
                        role='expert';
                    }
                    var tablerow = '<tr>'
                        + '<td id="ide">' + item.id + '</td>'
                        + '<td>' + item.firstName+" " +item.lastName + '</td>'
                        + '<td>' + item.email + '</td>' 
                        + '<td>' + item.status + '</td>' 
                        + '<td>' + item.wallet + '</td>' 
                        + '<td>' + item.rate + '</td>' 
                        + '<td>' + role + '</td>' 
                        + '</tr>';
                    $("#userSearchResultByNumber").append(tablerow);
                });
            }
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
}
$(document).ready(function () {
    $("#doExpertSearchByNumber").click(function (event) {
        searchExpertByOrderNumber();
    })
});

function searchExpertByOrderNumber() {
    $.ajax({
        url: "http://localhost:8080/user/findexpertordernum",
        type: "GET",
        data: {
            number:$("#numberExpertOrder").val(),
        },
        // contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            debugger;
            if (result) {
                //itetrate thorugh each record and bind it to td
                var html = '';
                $("#userSearchResultByNumber").empty();
                $.each(result, function (key, item) {
                   
                    var tablerow = '<tr>'
                        + '<td id="ide">' + item.id + '</td>'
                        + '<td>' + item.firstName+" " +item.lastName + '</td>'
                        + '<td>' + item.email + '</td>' 
                        + '<td>' + item.status + '</td>' 
                        + '<td>' + item.wallet + '</td>' 
                        + '<td>' + item.rate + '</td>' 
                        + '<td>' + 'expert' + '</td>' 
                        + '</tr>';
                    $("#userSearchResultByNumber").append(tablerow);
                });
            }
        },
        error: function (errormessage) {
            alert(errormessage.responseText);
        }
    });
}
$(document).ready(function () {
    $("#showMangeService").click(function (event) {
        window.location.replace('./ManageService.html');

    })
});
