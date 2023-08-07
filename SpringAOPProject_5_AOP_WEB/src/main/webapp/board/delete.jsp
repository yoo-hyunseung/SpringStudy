<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
  margin: 0px auto;
  width:800px;
}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
  <div class="row">
  <h2> tkrwpgkrl </h2>
  <form method="post" action="../board/delete_ok.do">
		<table class="table">
			<tr>
				<td class="text-center inline">
					비밀번호:<input type="password" name="pwd" size="20">
					<input type="hidden" name="no" value="${no }">					
				</td>
			</tr>
			<tr>
				<td class="text-center inline">
					
					<input type="button" value="취소" class="btn btn-sm btn-primary"
				 	onclick="javascript:history.back()">
				</td>
			</tr>
		</table>
		</form>
  </div>
  </main>
</div>

</body>
</html>