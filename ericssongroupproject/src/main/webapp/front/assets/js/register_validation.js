/**
 * Created by d16125338 on 24/04/2017.
 */
//check username
function checkUsername(username) {
    $.ajax({
        type:"GET",
        url:"/front/rest/users/"+ username,
        dataType:"json",
        success: function(data) {
            if(data == true){
                $("#username").text("Username exists.");
                $("#status-name").removeClass("icon-check");
                $("#status-name").addClass("icon-close");
                $(".signup-name").addClass("error-placeholder");
                $("#signup-name").addClass("error-input");

            }  if(data == false){
                $("#status-name").addClass("icon-check");
                $("#status-name").removeClass("icon-close");
                $(".signup-name").removeClass("error-placeholder");
                $("#signup-name").removeClass("error-input");

            }
        }

    });

}
//check password strength
function checkStrength(password){
    //initial strength
    var strength = 0;
    //if the password length is less than 6, return message
    if(password.length < 6){
        $("#result").removeClass();
        $("#result").addClass("short");
        return "Too short"
    }
    //if length is 8 characters or more, increase strength value
    if(password.length > 7) strength += 1;
    //if password contains both lower and uppercase characters, increase strength value
    if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1;
    //if it has numbers and characters, increase strength value
    if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1 ;
    //if it has one special character, increase strength value#
    if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1;

    //if value is less than 2
    if (strength < 2 ) {
        $('#result').removeClass();
        $('#result').addClass('weak');
        return 'Weak' }
    else if (strength == 2 ) {
        $('#result').removeClass();
        $('#result').addClass('good') ;
        return 'Good' }
    else {
        $('#result').removeClass() ;
        $('#result').addClass('strong') ;
        return 'Strong' }
}
$(document).ready(function(){
    $("#login-form").submit(function(event){
        event.preventDefault();
    });
    //when user enters username check for availability
    //registering user
    $("#signup-name").on("keyup", function () {
        var input = $(this);
        var is_uname = input.val();
        checkUsername(is_uname);
    });
    //check password strength
    $("#signup-password").on("keyup", function () {
        $("#result").html(checkStrength($("#signup-password").val()));

    });

    //check if both passwords match
    $("#signup-repassword").on("input", function () {
        var pwd = $("#signup-password").val();
        var rpwd = $("#signup-repassword").val();
        if (pwd !== rpwd || rpwd === "")
        {
            $("#status-repassword").removeClass("icon-check");
            $("#status-repassword").addClass("icon-close");
            $(".signup-repassword").addClass("error-placeholder");
            $("#signup-repassword").addClass("error-input");
        }

        // If repassword is ok
        else if (pwd === rpwd && rpwd !== "")
        {
            $("#status-repassword").addClass("icon-check");
            $("#status-repassword").removeClass("icon-close");
            $(".signup-repassword").removeClass("error-placeholder");
            $("#signup-repassword").removeClass("error-input");
        }

    });
    //when user enters role
    $("#signup-role").on("input", function () {
        var input = $(this);
        var is_role = input.val();
        if(is_role){
            $("#status-role").addClass("icon-check");
            $("#status-role").removeClass("icon-close");
            $(".signup-role").removeClass("error-placeholder");
            $("#signup-role").removeClass("error-input");
        }else{
            $("#status-role").removeClass("icon-check");
            $("#status-role").addClass("icon-close");
            $(".signup-role").addClass("error-placeholder");
            $("#signup-role").addClass("error-input");
        }

    });
    //now sign up user
    var register = function(username, password, role) {
        this.username=username;
        this.password = password;
        this.role = role;
    }
    $("#submit-button").click(function () {
        var username = $("#status-name").val();
        var password = $("#status-password").val();
        var rpassword = $("#status-repassword").val();
        var role = $("#status-role").val();
        if(username != "" && password != "" && rpassword != "" && role != ""){
            var user = new register(username,password,role);
            $.ajax({
                url:"http://localhost:8080/ericssongroupproject/rest/users/register",
                type:"POST",
                contentType: "application/json",
                success:function () {
                    alert("user created");
                },
                error:function () {
                    alert("error")
                },
                data:JSON.stringify(user)
            });
        }else {
            // If login isn't ok
            if (username === "")
            {
                $("#status-name").removeClass("icon-check");
                $("#status-name").addClass("icon-close");
                $(".signup-name").addClass("error-placeholder");
                $("#signup-name").addClass("error-input");
            }

            // If login is ok
            else if (username !== "")
            {
                $("#status-name").addClass("icon-check");
                $("#status-name").removeClass("icon-close");
                $(".signup-name").removeClass("error-placeholder");
                $("#signup-name").removeClass("error-input");
            }

            // If password isn't ok
            if (password === "")
            {
                $("#status-password").removeClass("icon-check");
                $("#status-password").addClass("icon-close");
                $(".signup-password").addClass("error-placeholder");
                $("#signup-password").addClass("error-input");
            }

            // If password is ok
            else if (password !== "")
            {
                $("#status-password").addClass("icon-check");
                $("#status-password").removeClass("icon-close");
                $(".signup-password").removeClass("error-placeholder");
                $("#signup-password").removeClass("error-input");
            }

            // If repassword isn't same
            if (password !== rpassword || rpassword === "")
            {
                $("#status-repassword").removeClass("icon-check");
                $("#status-repassword").addClass("icon-close");
                $(".signup-repassword").addClass("error-placeholder");
                $("#signup-repassword").addClass("error-input");
            }

            // If repassword is ok
            else if (password === rpassword && rpassword != "")
            {
                $("#status-repassword").addClass("icon-check");
                $("#status-repassword").removeClass("icon-close");
                $(".signup-repassword").removeClass("error-placeholder");
                $("#signup-repassword").removeClass("error-input");
            }
            // If role isn't ok
            if (role === "")
            {
                $("#status-role").removeClass("icon-check");
                $("#status-role").addClass("icon-close");
                $(".signup-role").addClass("error-placeholder");
                $("#signup-role").addClass("error-input");
            }

            // If login is ok
            else if (role !== "")
            {
                $("#status-role").addClass("icon-check");
                $("#status-role").removeClass("icon-close");
                $(".signup-role").removeClass("error-placeholder");
                $("#signup-role").removeClass("error-input");
            }

            return false;
        }


    });



});