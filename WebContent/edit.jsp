<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

%>
    <form class="col-md-7 shadow rounded" action="new" method="post" ENCTYPE="multipart/form-data">
				<br>
				<h1>个人中心</h1>
					<label for="basic-url">选择类型</label>
					<div class="input-group mb-3 float-left">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">类型</span>
						</div>
						<input list="type" class="form-control col-md-4" name="type">
						<datalist id="type">
							<option value="food">美食</option>
							<option value="resorts">景点</option>
							<option value="culture">风俗</option>
						</datalist>

					</div>

					<br>
					<label for="basic-url">信息</label>
					<div class="input-group mb-3 float-left">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">名称</span>
						</div>
						<input type="text" name="name_input" id="name_input" class="form-control col-md-4"
							placeholder="输入名称">

					</div>

					<div class="input-group mb-3 float-left">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">介绍信息</span>
						</div>
						<!--<textarea id="info_input" name="info_input"
							class="form-control col-md-6" placeholder="输入信息"></textarea>  -->
							<input type="text" name="info_input" id="info_input" class="form-control col-md-4"
							placeholder="输入介绍">
					</div>
					
					<div class="input-group mb-3 float-left">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">图片选择</span>
						</div>
						<input class="form-control col-md-4" type="file" name="file" value="" >
					</div>
					<div class="input-group mb-3 float-left">
						<button id="btn_alter" class="btn btn-danger" type="submit" disabled>确认</button>
							<span class="text-danger font-weight-light" id="msg_alter"></span>
					
					</div>
						
			</form>
    
    
    
    
 
    
		<script type="text/javascript">

			$("#name_input").blur(function () {
				var foodname = $("#name_input").val();
				
				if (foodname == "" || foodname == null || foodname.length == 0) {
					$("#msg_alter").html("👉输入名称!");
					$("#btn_alter").prop("disabled", true);
				}
			}).focus(function () {
				$("#msg_alter").html("");
				$("#btn_alter").prop("disabled", false);
			});

		</script>