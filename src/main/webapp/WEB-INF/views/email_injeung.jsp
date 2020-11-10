<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>인증 받은 번호 입력 화면</title>
    
  
</head>

<body>

<div id="card" class="card bg-light">

    <article id="od" class="card-body mx-auto" >


        <p class="divider-text">
            <span id="font" class="bg-light" >COVID</span>
        </p>
        <form action="injeung.do" method="post"  > <!-- 받아온 인증코드를 컨트롤러로 넘겨서 일치하는지 확인 -->
            
            
            
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                </div>
                <input name="email_injeung" class="form-control" placeholder="인증 번호 입력" type="text" required>
                <input name="dice" class="form-control" type="hidden" value= "${dice}" >
                <input name="e_mail" class="form-control" type="hidden" value="${e_mail }" >
                
            </div> <!-- form-group// -->

            <!-- 버튼 -->
            <div class="form-group">
           		 <button class="button" name="submit" type="submit"style="vertical-align:middle"><span>send</span></button>
                
            </div> <!-- form-group// -->


            <p class="text-center">로그인 바로가기/ <a href="${pageContext.request.contextPath}/index">로그인</a></p>
        </form>
    </article>
</div> <!-- card.// -->

</body>
</html>