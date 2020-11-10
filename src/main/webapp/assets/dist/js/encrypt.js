	var dice_origin = document.getElementById('dice').value; //인증번호 원본값
	var passphrase = "1234";	// 암호화에 사용할 암호해제키
    var encrypt = CryptoJS.AES.encrypt(dice_origin, passphrase); //AES 암호화된 인증번호
	var decrypted = CryptoJS.AES.decrypt(encrypt, passphrase); //복호화된 인증번호

//메일로 전송한 인증번호(dice 값) 암호화 해서 소스보기해도 알 수 없게 암호화
$(function(){
	//input hidden으로 들어간 dice 값을 암호화된 값으로 바꿔치기
	document.getElementById('dice').value = encrypt;
	console.log(encrypt);
	console.log(decrypted.toString(CryptoJS.enc.Utf8));
})

	//제출 버튼을 누르면 제출되기 직전에 복호화된 인증번호로 바꿔친 다음에 form이 제출 된다.
function form2_submit(){
	document.getElementById('dice').value= decrypted.toString(CryptoJS.enc.Utf8);
	document.frm2.submit();
	
}