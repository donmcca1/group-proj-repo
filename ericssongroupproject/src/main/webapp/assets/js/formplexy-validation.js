
function checkUser(username, password) {
    $.ajax({
        url:"http://localhost:8080/ericssongroupproject/front/rest/users/" + username+"/"+ password,
        type:"GET",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            var role = data.role;
            console.log(role);
            switch(role){
                case "System Admin": window.location = "front/sa_upload.html";
                    break;
            }
        },
        error:function () {
            console.log("error");
            //return userExists;
        }
    });

}
/*function callback(data){

    data = data.role;
    return data;
    //console.log(role);
}*/

/*var role =null;
function getUser(username) {

    $.ajax({
        url:"http://localhost:8080/ericssongroupproject/front/rest/users/user/" + username,
        type:"GET",
        contentType: "application/json",
        dataType: "json",

        success:function (data) {
            var userData = JSON.stringify(data);
            var user = JSON.parse(userData);
            role = user.role;
            console.log(role);
        },
        error:function () {
            console.log("error");

        }
    });
    console.log(role);
    //return role;

}*/
jQuery(document).ready(function($){

    // Validation Login
    $("#loginButton").click(function() {
       // console.log("Here");
        var value_login = $("#login-username").val();
        var value_password = $("#login-password").val();

        // Everything is all right
        if (value_login !== "" && value_login !== null && value_password !== "" && value_password !== null) {

            checkUser(value_login, value_password);
            //console.log(checkUser(value_login, value_password));
            /*if (checkUser(value_login, value_password) !== null) {
                console.log(role);
                //get role and open pages

            } else {
                /!*!//open home page for each user type
                getUser(value_login);
                console.log(role);
                //console.log(getUser(value_login));*!/
            }*/
        }
    });
/*

        }

        // // If its not ok
        // else {
        //     // If login isn't ok
        //     if (value_login == "")
        //     {
        //         $("#status-username").removeClass("icon-check");
        //         $("#status-username").addClass("icon-close");
        //         $(".login-username").addClass("error-placeholder");
        //         $("#login-username").addClass("error-input");
        //     }
        //
        //     // If login is ok but password not
        //     else if (value_login != "")
        //     {
        //         $("#status-username").removeClass("icon-close");
        //         $("#status-username").addClass("icon-check");
        //         $(".login-username").removeClass("error-placeholder");
        //         $("#login-username").removeClass("error-input");
        //     }
        //
        //     // If password isn't ok
        //     if (value_password == "")
        //     {
        //         $("#status-password").removeClass("icon-check");
        //         $("#status-password").addClass("icon-close");
        //         $(".login-password").addClass("error-placeholder");
        //         $("#login-password").addClass("error-input");
        //     }
        //
        //     // If password is ok but login not
        //     else if (value_password != "")
        //     {
        //         $("#status-password").removeClass("icon-close");
        //         $("#status-password").addClass("icon-check");
        //         $(".login-password").removeClass("error-placeholder");
        //         $("#login-password").removeClass("error-input");
        //     }
        //
        //     return false;
        // }

    });



    // Validation Sign up
    $("#submit-button").click(function() {

        var value_name = $("#signup-name").val();
        var value_password = $("#signup-password").val();
        var value_repassword = $("#signup-repassword").val();
        var value_role = $("#signup-role").val();

		/*    // Email format validation
		 var email_values = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;

		 if (!email_values.test(value_email)) {
		 $("#status-email").removeClass("icon-check");
		 $("#status-email").addClass("icon-close");
		 $(".signup-email").addClass("error-placeholder");
		 $("#signup-email").addClass("error-input");
		 return false;
		 }*/

      /*  // Everything is all right
        if (value_name != "" && value_role != "" && value_password != "" && value_repassword == value_password)
        {
            var register = function(username, password, role) {
                this.username=username;
                this.password = password;
                this.role = role;
            }

            var user = new register($("#signup-name").val(),$("#signup-password").val(),$("#signup-role").val());
            $.ajax({
                url:"http://localhost:8080/ericssongroupproject/rest/users/register",
                type:"POST",
                contentType: "application/json",
                success:function () {
                    $(".clearfix").innerHTML("User Created");
                },
                error:function () {
                    $(".clearfix").innerHTML("User Not Created");
                },
                data:JSON.stringify(user)
            });


            //return true;
        }

        // If its not ok
        else {
            // If login isn't ok
            if (value_name == "")
            {
                $("#status-name").removeClass("icon-check");
                $("#status-name").addClass("icon-close");
                $(".signup-name").addClass("error-placeholder");
                $("#signup-name").addClass("error-input");
            }

            // If login is ok
            else if (value_name != "")
            {
                $("#status-name").addClass("icon-check");
                $("#status-name").removeClass("icon-close");
                $(".signup-name").removeClass("error-placeholder");
                $("#signup-name").removeClass("error-input");
            }

			/!*!// If email isn't ok
			 if (value_email == "")
			 {
			 $("#status-email").removeClass("icon-check");
			 $("#status-email").addClass("icon-close");
			 $(".signup-email").addClass("error-placeholder");
			 $("#signup-email").addClass("error-input");
			 }

			 // If email is ok
			 else if (value_email != "")
			 {
			 $("#status-email").addClass("icon-check");
			 $("#status-email").removeClass("icon-close");
			 $(".signup-email").removeClass("error-placeholder");
			 $("#signup-email").removeClass("error-input");
			 }*!/

            // If password isn't ok
            if (value_password == "")
            {
                $("#status-password").removeClass("icon-check");
                $("#status-password").addClass("icon-close");
                $(".signup-password").addClass("error-placeholder");
                $("#signup-password").addClass("error-input");
            }

            // If password is ok
            else if (value_password != "")
            {
                $("#status-password").addClass("icon-check");
                $("#status-password").removeClass("icon-close");
                $(".signup-password").removeClass("error-placeholder");
                $("#signup-password").removeClass("error-input");
            }

            // If repassword isn't same
            if (value_password != value_repassword || value_repassword == "")
            {
                $("#status-repassword").removeClass("icon-check");
                $("#status-repassword").addClass("icon-close");
                $(".signup-repassword").addClass("error-placeholder");
                $("#signup-repassword").addClass("error-input");
            }

            // If repassword is ok
            else if (value_password == value_repassword && value_repassword != "")
            {
                $("#status-repassword").addClass("icon-check");
                $("#status-repassword").removeClass("icon-close");
                $(".signup-repassword").removeClass("error-placeholder");
                $("#signup-repassword").removeClass("error-input");
            }
            // If role isn't ok
            if (value_role == "")
            {
                $("#status-role").removeClass("icon-check");
                $("#status-role").addClass("icon-close");
                $(".signup-role").addClass("error-placeholder");
                $("#signup-role").addClass("error-input");
            }

            // If login is ok
            else if (value_role != "")
            {
                $("#status-role").addClass("icon-check");
                $("#status-role").removeClass("icon-close");
                $(".signup-role").removeClass("error-placeholder");
                $("#signup-role").removeClass("error-input");
            }

			/!*if ($('#signup-agree:checked').val() == undefined)
			 {
			 $("#status-agree").addClass("icon-close");
			 $("#status-agree").removeClass("icon-check");
			 $('#status-agree').attr('data-original-title', 'Incorrect Email');
			 }

			 else if ($('#signup-agree:checked').val() !== undefined)
			 {
			 $("#status-agree").removeClass("icon-close");
			 $("#status-agree").addClass("icon-check");
			 $('#status-agree').prop('title', '');
			 }*!/

            return false;
        }
    });*/
});
