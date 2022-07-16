<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="static/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
    <div class="container-fluid">
        <div class="d-flex flex-wrap align-items-stretch">
            <div class="col-lg-8 col-12 order-lg-2 order-1 min-vh-100 background-walk-x position-relative overlay-gradient-bottom"
                data-background="static/img/bg.png" style="background-image: url(static/img/bg.png)">

            </div>
            <div class="col-lg-4 col-md-6 col-12 order-lg-1 min-vh-100 order-2 bg-white" style="margin-top: 30px">
                <h1 class="brand">注 册</h1>
                <form action="register" method="post" id="registerform">
                    <div class="form-group">

                        <label for="inputUserName">
                            用户名
                        </label>
                        <input id="uName" name="uName" type="text" class="form-control" placeholder="用户名">
                    </div>
                    <div class=" form-group">

                        <label for="inputUserPassword">
                            密&nbsp;&nbsp;&nbsp;&nbsp;码
                        </label>
                        <input id="uPassword" name="uPassword" type="password" class="form-control" placeholder="密码">
                    </div>


                    <br>
                    <span id="msg" class="text-danger">${message }</span>
                    <br>
                    <button type="button" class="btn btn-success offset-10" onclick="checkingRegister()">
                        注册
                    </button>
                </form>
            </div>
        </div>
    </div>


    <script src="static/js/jquery.min.js" type="text/javascript"></script>
    <script src="static/js/popper.min.js" type="text/javascript"></script>
    <script src="static/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="static/js/scripts.js" type="text/javascript"></script>
</body>

</html>