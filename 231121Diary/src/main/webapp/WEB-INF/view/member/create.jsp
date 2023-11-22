<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>회원가입</title>
</head>
<body>
<div class="container my-5">
	<h1>회원가입</h1>
	<form class="form-control" action="<%=request.getContextPath()%>/member/create" method="post">
		<div class="my-3">
			<input id="id" name="id" type="text" placeholder="아이디를 입력하세요">
		</div>
		<div class="my-3">
			<input id="pw" name="pw" type="password" placeholder="비밀번호를 입력하세요">
		</div>
		<div class="my-3">
			<button id="btn">회원가입</button>
		</div>
	</form>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
</script>
</html>