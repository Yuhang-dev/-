<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form class="col-md-7 shadow rounded" action="user?actionName=changeInfo" method="post">
				<br>
				<h1>个人中心</h1>

					<label for="basic-url">修改信息</label>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">昵称</span>
						</div>
						<input type="text" name="nickname_input" id="nickname_input" class="form-control col-md-4" placeholder="Username" value="${user.uNick}">

					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">密码</span>
						</div>
						<input type="password" name="password_input" id="password_input" class="form-control col-md-4" value="${user.uPassword }">

					</div>

					<label for="basic-url">地址</label>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">学校</span>
						</div>
						<input type="text" name="school_input" id="school_input" class="form-control col-md-5" id="basic-url" value="${user.uSchool}">
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">家乡</span>
						</div>
						<input type="text" name="home_input" id="home_input" class="form-control col-md-5" id="basic-url" value="${user.uHome}">
					</div>

					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">个人简介</span>
						</div>
						<textarea id="mood_input" name="mood_input" class="form-control col-md-6">${user.uMood}</textarea>
					</div>
					<br>
					
		<button id="btn_alter" class="btn btn-danger" type="submit" disabled>修改</button>
		<span class="text-danger font-weight-light" id="msg_alter"></span>
		
</form>
	<form id="form_upload" class="offset-4 col-md-7 shadow rounded float-right" action="head?actionName=uploadHead" method="post"
						ENCTYPE="multipart/form-data">

					<input type="file" name="file" form="form_upload">
					<input type="submit" value="上传头像" form="form_upload">
		</form>


		<script type="text/javascript">

			$("#nickname_input").blur(function () {
				var nickname = $("#nickname_input").val();
				
				if (nickname == "" || nickname == null || nickname.length == 0) {
					$("#msg_alter").html("👉输入用户名!");
					$("#btn_alter").prop("disabled", true);
				}
			}).focus(function () {
				$("#msg_alter").html("");
				$("#btn_alter").prop("disabled", false);
			});

			$("#password_input").blur(function () {
				var password = $("#password_input").val();
				
				if (password == "" || password == null || password.length == 0) {
					$("#msg_alter").html("👉密码不能为空!");
					$("#btn_alter").prop("disabled", true);
				}
			}).focus(function () {
				$("#msg_alter").html("");
				$("#btn_alter").prop("disabled", false);
			});
		</script>