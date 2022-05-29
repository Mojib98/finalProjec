import "https://code.jquery.com/jquery-3.6.0.js";
import "https://code.jquery.com/jquery-migrate-3.4.0.js";
import "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js";
$("input[type='file']").on("change", function () {
    if (this.files[0].size > 300000) {
        alert("Please upload file less than 2MB. Thanks!!");
        $(this).val('');
    }
    // var file = this.files[0];
    // var fileType = file["type"];
    // var validImageTypes = ["image/jpg"];
    // if ($.inArray(fileType, validImageTypes) < 0) {
    //   alert("insert current format");
    //   $(this).val('');
    //   // invalid file type code goes here.
    // }

});
/*$("input[name='email']").on("change", function () {
  var EmailRegex = /^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$/;
  // alert($(this).val());
  if (EmailRegex.test($(this).val())) {

  } else {
    alert("invalid email")
    $(this).val('');
  }
});*/
// $("input[name='password']").on("change", function () {
//   var EmailRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
//   // alert($(this).val());
//   if (EmailRegex.test($(this).val())) {
//   } else {
//     alert("invalid password")
//     $(this).val('');
//   }
// });
let urlsend;
$(document).ready(function () {
    $("#button").click(function (event) {
        // alert($('input:radio[name=flexRadioDefault]:checked').attr('id'));
        var checker = $('input:radio[name=flexRadioDefault]:checked').attr('id');
        if (checker == 'expert') {
            // alert("it's checked");
            urlsend = "http://localhost:8080/singup/expert/";
            // const fi = document.getElementById('image').val();
            // alert(fi)
            // if(fi== ''){
            //   alert("dsfd")
            // }
        }
        else {
            urlsend = "http://localhost:8080/singup/customer/";
        }

        alert(urlsend)
        event.preventDefault();

        // Get form
        var form = $('#myform')[0];

        // Create an FormData object
        var data = new FormData(form);


        $.ajax({
            // enctype: 'multipart/form-data',
            type: "POST",
            method: "POST",
            // processData: false,
            url: urlsend,
            data: data,
            dataType: "json",
            encode: true,
            processData: false,
            contentType: false,
            success: function (data) {

                $("#result").text(data);
                console.log("SUCCESS : ", data);
                $("#btnSubmit").prop("disabled", false);

            },
            error: function (e) {

                $("#result").text(e.responseText);
                console.log("ERROR : ", e);
                $("#btnSubmit").prop("disabled", false);

            }
        })

        event.preventDefault();
    });
});
