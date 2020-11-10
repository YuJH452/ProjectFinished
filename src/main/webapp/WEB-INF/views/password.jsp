<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Page Title - SB Admin</title>
        <link href="assets/dist/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/sha256.js"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">${cardHeader }</h3></div>
                                    <div class="card-body">
                                        <div class="small mb-3 text-muted">메일 주소를 입력하면 해당 메일로 인증키를 보내드립니다.</div>
                                        
                                        <!-- 1단계 폼 /email /password  -->
                                         <c:if test='${sub1=="Email"}'>
                                        <form action='${action }' method='post'>
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">${sub1 }</label>
                                                <input class="form-control py-4" id="inputEmailAddress" type="email" name="e_mail" aria-describedby="emailHelp" placeholder="${placeholder }" required />
                                            </div>
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="login">Return to login</a>
                                                <button type='submit' class="btn btn-primary" >${btn }</button>
                                           </div>
                                        </form>
                                        </c:if>
                                        
                                        <!-- 2단계 폼 /auth.do /reset -->
                                         <c:if test='${sub1=="인증번호"}'>
                                         <form action='${action2 }' name='frm2' method='post'>
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">${sub1 }</label>
                                                <input class="form-control py-4" id="injeungBno" name="email_injeung" aria-describedby="emailHelp" placeholder="${placeholder }" />
                                            	<!--dice 값은 암호화 되어 브라우저에서는 알 수 없다  -->
                                            	<input id='dice' name="dice" class="form-control" type="hidden" value= "${dice}" >
                                            	<input name="e_mail" class="form-control" type="hidden" value="${e_mail }" >
                                            		
                                            </div>
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="login">Return to login</a>
                                                <input type='button' class="btn btn-primary" onclick="form2_submit()" value="${btn }">
                                           </div>
                                        </form>
                                        </c:if>
                                        
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small"><a href="email">Need an account? Sign up!</a></div>
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
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2020</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="assets/dist/js/scripts.js"></script>
        <script src="assets/dist/js/encrypt.js"></script>
    </body>
</html>
