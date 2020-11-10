var idJ = /^[a-zA-Zㄱ-ㅎ가-힣0-9!@#$%^&*]*$/;
       
       $("#nickName").blur(function() {
         // id = "id_reg" / name = "userId"
         var nickName = $('#nickName').val();
         $.ajax({
            url : 'nickcheck?nickName='+ nickName,
            type : 'get',
            success : function(data) {
               console.log("1 = 중복o / 0 = 중복x : "+ data);                     
               
               if (data == 1) {
                     // 1 : 아이디가 중복되는 문구
                     $("#nick_check").text("사용중인 닉네임입니다 :p");
                     $("#nick_check").css("color", "red");
                     $("#go_submit").attr("disabled", true);
                  } else {
                     
                     if(idJ.test(nickName)){
                        // 0 : 아이디 길이 / 문자열 검사
                        $("#nick_check").text("");
                        $("#go_submit").attr("disabled", false);
               
                     } else if(nickName == ""){
                        
                        $('#nick_check').text('닉네임을 입력해주세요 :)');
                        $('#nick_check').css('color', 'red');
                        $("#go_submit").attr("disabled", true);            
                        
                     } else {
                        
                        $('#nick_check').text("한글 ,소문자 ,숫자 4~12자리 특수기호 불가 ");
                        $('#nick_check').css('color', 'red');
                        $("#go_submit").attr("disabled", true);
                     }
                     
                  }
               }, error : function() {
                     console.log("실패");
               }
            });
         });



function regex(){
								var firstName = document.getElementById('firstName').value;
								var lastName = document.getElementById('lastName').value;
								var nickName= document.getElementById('nickName').value;
								var pw = document.getElementById('password').value;
								var cpw = document.getElementById('password2').value;
								var is_confirmed = (pw==cpw);
								
								
								var nameTester =  /^[a-zA-Zㄱ-ㅎ가-힣]*$/;			//한글, 영문만
								var nickTester = /^[a-zA-Zㄱ-ㅎ가-힣0-9!@#$%^&*]*$/; //한글, 영문, 숫자, 기호 일부
								var msg = '';
								
							
								
								
								if(nameTester.test(firstName)){
									console.log('first OK')
									$('#firstName').css('background-color','white');
								}else{
									$('#firstName').css('background-color','#ffd592');
									console.log('first failed')									
								}								
								if(nameTester.test(lastName)){
									console.log('last OK')
									$('#lastName').css('background-color','white');
								}else{
									$('#lastName').css('background-color','#ffd592');
									console.log('last failed')
									
								}
								
								if(!(nameTester.test(firstName)&&nameTester.test(lastName))){
									msg = '● 이름 란에 숫자와기호는 사용할 수 없습니다.'
								}
								
								if(nickTester.test(nickName)){
									console.log('nick OK')
									$('#nickName').css('background-color','white');
								}else{
									$('#nickName').css('background-color','#ffd592');
									console.log('nick failed')
									if(msg==''){
										msg='● 닉네임에 사용할 수 없는 기호가 포함되어 있습니다.'
									}else{
									msg = msg + '<br>' + '● 닉네임에 사용할 수 없는 기호가 포함되어 있습니다.'
									}
								}
								
								if(is_confirmed){
									if(pw!=null && pw!=''){
										console.log('비번 체크 OK')
										$('#password2').css('background-color','white');
										$('#password').css('background-color','white');
									}else{
										console.log('비번란이 공백')
										$('#password').css('background-color', '#ffd592')
											if(msg==''){
												msg='● 비밀번호를 입력하세요.'
											}else{
											msg = msg + '<br>' + '● 비밀번호를 입력하세요.'
											}
									}
								}else{
									$('#password2').css('background-color','#ffd592');
									console.log('비번체크 틀림')
									if(msg==''){
										msg='● 비밀번호가 일치하지 않습니다.'
									}else{
									msg = msg + '<br>' + '● 비밀번호가 일치하지 않습니다.'
									}
								}
								
								if(nameTester.test(firstName)&&nameTester.test(lastName)&&nickTester.test(nickName)&&is_confirmed&&(pw!=null&&pw!='')){
									jechul();
									
								}else{
									
									toastr.error(msg, '입력에 오류가 있습니다.');
									return;
								}
								
								
								
							}	
							
							function jechul(){
								document.frm.submit();
							}
							
							
							