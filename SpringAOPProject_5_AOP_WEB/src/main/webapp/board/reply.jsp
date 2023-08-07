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
  <h2> eoekq</h2>
  </div>
  <form method="post" action="../board/reply_ok.do">
	<table class="table">
		<tr>
			<th width="15%" class="text-right success">이름</th>
			<td width="85%"><input type="text" name="name" size="15" class="input-sm">
			<input type="hidden" name="pno" value="${pno }">
			</td>
			
			</tr>
			<tr>
			<th width="15%" class="text-right success">제목</th>
			<td width="85%"><input type="text" name="subject" size="50" class="input-sm"></td>
			</tr>
			<tr>
			<th width="15%" class="text-right success">내용</th>
			<td width="85%"><textarea rows="10" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<th width="15%" class="text-right success">첨부파일</th>
				<td width="85%">
					<table class="table">
						<tr>
							<td class="text-right">
								<input type="button" value="추가"
									class="btn btn-xs btn-info" id="addBtn"
								>
								<input type="button" value="취소"
									class="btn btn-xs btn-warning" id="removeBtn"
								>
							</td>
						</tr>
					</table>
					<table class="table" id="user-table">
						<tbody>
							
						</tbody>
					</table>
				</td>
			
			</tr>
			<tr>
			
			<th width="15%" class="text-right success">비밀번호</th>
			<td width="85%"><input type="password" name="pwd" size="10" class="input-sm"></td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<button class="btn btn-sm btn-primary">ekqqus</button>
				<input type="button" value="취소" class="btn btn-sm btn-primary"
				 	onclick="javascript:history.back()">
			</td>
		</tr>
	</table>
	</form>
  </main>
</div>
</body>
</html>