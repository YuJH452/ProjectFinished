
function search() {
	var param = document.getElementById('param').value;
	var Squery = 'https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=' + param;
	window.location.href=Squery;
}
