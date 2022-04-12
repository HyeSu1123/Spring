<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 중복체크</title>
</head>
<body bgcolor="#FFFFCC">
	<div align="center">
		<br>${email }<br> <!-- 모델객체로 받아온(Model model선언 -->
		${msg } <br><br>
		<a href="#" onclick="self.close()">닫기</a>
	</div>
</body>
</html>