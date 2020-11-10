<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>회원 정보 페이지</title>
<link href="assets/dist/css/styles.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css" rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
	crossorigin="anonymous"></script>
	

<style>


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
		<h3 class="text-center font-weight-light my-4">회원 정보 수정</h3>
		</div>
		   <div class="card-body">
		  <form action="updateMember.do" name='frm' method="post">
			 <div class="form-row">
				<div class="col-md-6">
					<div class="form-group">
					 <label class="small mb-1" for="firstName">First Name</label> 
					 <input class="form-control py-4" id="firstName" name="firstName"  type="text" placeholder="First Name" value="${member.firstName }" required />
					</div>
					</div>
			 <div class="col-md-6">
			 <div class="form-group">
			 <label class="small mb-1" for="lastName">Last Name</label> 
			 <input class="form-control py-4" id="lastName" name="lastName" type="text" placeholder="Last Name" value="${member.lastName }" required />
			 </div>
			 </div>
			 <div class="col-md-6">
			  <div class="form-group" id="userId-container">
			  <label class="small mb-1" for="nickName">Nickname</label>
			  
			   <div class="input-group-prepend"> 
				<span class="input-group-text"> <i class="fa fa-user"></i>
				</span> 
				<input class="form-control py-4" id="nickName" name="nickName" type="text" placeholder="New Nick Name" value="${member.nickName }" required /> 
				</div>
				<span class="guide ok">사용 가능</span> 
				<span class="guide error">사용 불가</span> <span class="guide invalid">8글자 이하</span>
				 </div>
			   </div>
			    </div>
					<!-- 이메일 -->
				<div class="form-group">
				<label class="small mb-1" for="email">Email</label>
				<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-envelope"> </i>
				 </span>
				 <input id="email" name="email" class="form-control" type="email" value="${member.email}" readonly>
				  </div>
				   
					</div>
					<!-- 패스워드 1 -->
				 <div class="form-row">
					<div class="col-md-6">
					 <div class="form-group">
					  <label class="small mb-1" for="password3">New Password</label>
					  <input class="form-control py-4" id="password" name="password" type="password" placeholder="New Password" required />
						</div>
						</div>
					<div class="col-md-6">
					 <div class="form-group">
					  <label class="small mb-1" for="password4">Confirm Password</label>
					  <input class="form-control py-4" id="password2" name="c_password" type="password" placeholder="Confirm Password" required />
						</div>
						</div>  
					</div>
					<div class="form-group mt-4 mb-0">
				<input type="button" class="btn btn-primary"  onclick="regex()" value="수정완료" style="vertical-align:middle">
					</div>
						</form>
							</div>
								<div class="card-footer text-center">
									
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
	<script src="assets/dist/js/formCheck.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</body>
</html>
