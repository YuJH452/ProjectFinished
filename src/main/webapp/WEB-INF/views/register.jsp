<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>





<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>
	<c:if test="${is_recovery==true }">회원 정보 수정</c:if>
	<c:if test="${is_recovery!=true }">회원가입</c:if>
</title>
<link href="assets/dist/css/styles.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css" rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
	crossorigin="anonymous"></script>

<style>

/*중복아이디체크관련*/
div#userId-container {
	position: relative;
	padding: 0px;
}

div#userId-container span.guide {
	display: none;
	font-size: 12px;
	position: absolute;
	top: 12px;
	right: 10px;
}

div#userId-container span.ok {
	color: green;
}

div#userId-container span.error, span.invalid {
	color: red;
}
</style>
</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
		<div class="container">
		<div class="row justify-content-center">
		<div class="col-lg-7">
		<div class="card shadow-lg border-0 rounded-lg mt-5">
		<div class="card-header">
		
		<h3 class="text-center font-weight-light my-4">
			<c:if test="${is_recovery==true }">회원 정보 수정</c:if>
			<c:if test="${is_recovery!=true }">회원가입</c:if>
		</h3>
		</div>
		   <div class="card-body">
		  <form id='frm' name='frm' action="${action }" method="post">
			 <div class="form-row">
				<div class="col-md-6">
					<div class="form-group">
					 <label class="small mb-1" for="firstName">First Name</label> 
					 <input class="form-control py-4" id="firstName" name="firstName"  type="text" placeholder="Enter first name" value="${member.firstName }" required/>
					</div>
					</div>
			 <div class="col-md-6">
			 <div class="form-group">
			 <label class="small mb-1" for="lastName">Last Name</label> 
			 <input class="form-control py-4" id="lastName" name="lastName" type="text" placeholder="Enter last name" value="${member.lastName }" required/>
			 </div>
			 </div>
			 <div class="col-md-6">
			  <div class="form-group" id="userId-container">
				
				<label class="small mb-1" for="nickName">Nickname</label> 
				<div class="input-group-prepend"> 
				<span class="input-group-text"> <i class="fa fa-user"></i></span> 
				<!--비번 찾기로 등러오면 닉네임 받아오게 되어있음  -->
				<input class="form-control py-4" id="nickName" name="nickName" type="text" placeholder="Enter Nick Name" value="${member.nickName }" required/> 
			
				</div>
				<div id="nick_check"></div>
				 </div>
			   </div>
			    </div>
					<!-- 이메일 -->
				<div class="form-group">
				<label class="small mb-1" for="email">Email</label>
				<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-envelope"></i>
				 </span>
				  <input id="email" name="email" class="form-control" type="email" value="${e_mail}" readonly>
				  </div>
				  
					</div>
					<!-- 패스워드 1 -->
				 <div class="form-row">
					<div class="col-md-6">
					 <div class="form-group">
					  <label class="small mb-1" for="password">Password</label>
					  <input class="form-control py-4" id="password" name="password" type="password" placeholder="Enter password" required />
						</div>
						</div>
					<div class="col-md-6">
					 <div class="form-group">
					  <label class="small mb-1" for="password4">Confirm Password</label>
					  <input class="form-control py-4" id="password2" name="c_password" type="password" placeholder="Confirm Password" required />
						</div>
						</div>  
					   <!-- 패스워드 2 -->
					<!-- <div class="col-md-6">
					<div class="form-group">
					<label class="small mb-1" for="inputConfirmPassword">Confirm Password</label>
					<input id="inputPassword2" name="Password2" class="form-control py-4" placeholder="Confirm password" type="password"
						onchange="isSame()" required>
					<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
					<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div> 
					</div>
					</div> -->
					</div>
					<div class="form-group mt-4 mb-0">
				<!-- <button class="button" name="submit" type="submit"style="vertical-align:middle">Create Account</button> -->
				
				<!-- button 태그를 쓰면 무조건 submit 되어버리더라 -->
				<input type='button' class="btn btn-primary" id="go_submit" onclick="regex()" value='${button }' style="vertical-align:middle ">
						
					</div>
						</form>
							</div>
								<div class="card-footer text-center">
									<div class="small">
										<a href="login">Have an account? Go to login</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>

		<div id="layoutAuthentication_footer">
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2020</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="assets/dist/js/scripts.js"></script>
	
	<c:if test="${is_recovery==true }"><script src="assets/dist/js/formCheck.js"></script></c:if>
	<c:if test="${is_recovery!=true }"><script src="assets/dist/js/formCheck2.js"></script></c:if>
		 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</body>
</html>
