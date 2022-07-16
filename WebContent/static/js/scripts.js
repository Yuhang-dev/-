function checkingLogin() {

    var userName = $("#uName").val();
    var userPassword = $("#uPassword").val();
	
	
    if (userName == null || userName.trim() == "") {
        document.getElementById("msg").innerHTML = "用户名为空";
        return;
    }
    else if (userPassword == null || userPassword.trim() == "") {
        document.getElementById("msg").innerHTML = "密码为空";
        return;
    }
     document.getElementById("loginForm").submit();
};

function checkingRegister() {

    var userName = $("#uName").val();
    var userPassword = $("#uPassword").val();


    if (userName == null || userName.trim() == "") {
        document.getElementById("msg").innerHTML = "用户名为空";
        return;
    }
    else if (userPassword == null || userPassword.trim() == "") {
        document.getElementById("msg").innerHTML = "密码为空";
        return;
    }

    document.getElementById("registerform").submit();
};