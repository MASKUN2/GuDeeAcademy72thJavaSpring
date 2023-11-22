<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>회원탈퇴</title>
</head>
<body>
<div class="container my-5">
	<h1>회원탈퇴</h1>
	<form id="form" class="form-control form-control-lg" action="<%=request.getContextPath()%>/member/delete" method="post">
		<div class="my-3">
			<H3>아이디</H3>
			<input type="text" value="${sessionScope.member.memberId}" readonly="readonly">
		</div>
		<div class="my-3">
			<H3>현재비밀번호</H3>
			<input id="pwNow" name="pwNow" type="password" placeholder="비밀번호를 입력하세요">
		</div>
		<div class="my-3">
			<H3>비밀번호 확인</H3>
			<input id="pwNowCheck" name="pwNowCheck" type="password" placeholder="비밀번호 한번 더 입력하세요">
		</div>
		<div class="my-3">
			<button id="btn" type="button"> 회원탈퇴</button>
		</div>
	</form>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$("#btn").click(function() {
		if($("#pwNow").val() != $("#pwNowCheck").val()){
			alert("비밀번호 확인이 일치하지 않습니다.");
			return;
		}else if($("#pwNow").val().length == 0){
			alert("비밀번호를 입력하세요.");
			return;
		}else {
			$("#form").submit();			
		}
	});
</script>
</html>