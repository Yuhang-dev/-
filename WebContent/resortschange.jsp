<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form class="col-md-7 shadow rounded" action="data?actionName=change" method="post">
				<br>
				<h1>修改</h1>
				<input type="hidden" name="type" value="resorts">
					<label for="basic-url">“景点”</label>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">名称</span>
						</div>
						<input type="text" name="resortsname_input" id="resortsname_input" class="form-control col-md-4" placeholder="Resortsname" value="${name}" readonly="readonly">

					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">介绍</span>
						</div>
						<textarea id="resortsinfo_input" name="resortsinfo_input" class="form-control col-md-6" placeholder="Resortsinfo"></textarea>

					</div>

					<br>
					
		<button id="btn_alter" class="btn btn-danger" type="submit" disabled>修改</button>
		<span class="text-danger font-weight-light" id="msg_alter"></span>
		
</form>
	<form id="form_upload" class="offset-4 col-md-7 shadow-sm rounded float-right" action="head?actionName=uploadResortsPic&rName=${name}" method="post"
						ENCTYPE="multipart/form-data">

					<input type="file" name="file" form="form_upload">
					<input type="submit" value="上传图片" form="form_upload">
		</form>


		<script type="text/javascript">

			$("#resortsname_input").blur(function () {
				var foodname = $("#resortsname_input").val();
				
				if (foodname == "" || foodname == null || foodname.length == 0) {
					$("#msg_alter").html("👉输入景点名称!");
					$("#btn_alter").prop("disabled", true);
				}
			}).focus(function () {
				$("#msg_alter").html("");
				$("#btn_alter").prop("disabled", false);
			});

		</script>