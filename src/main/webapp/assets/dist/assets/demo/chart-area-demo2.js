// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Area Chart Example

let incDec;
let dayColumn;
let incDecList = [];

let dayList = [];
let comparator = null;
let xmlData;
let myLineChart;
let myBarChart;
let sidoList = [];
let cntList = [];

$('.info1').css('display', 'none');
$('.info2').css('display', 'none');
$('.info3').css('display', 'none');
$('.info4').css('display', 'none');




$.ajax({
	url: "areaChart.do",
	method: "GET",
	dataType: "xml",
	success: function(data) {

		xmlData = data;
		//alert(data);

		console.log(data);

		var items = $(data).find('item');
		$(items).each(function() {

			//gubunEn에 '합계'라고 되어있는 item'값만 뽑기
			if ($(this).find('gubunEn').text() == "Total") {

				//동일날짜 데이터가 두번 이상 찍혀있으면 거름
				if ($(this).find('stdDay').text() == comparator) {
					console.log(comparator + ' 의 중복은 너굴맨이 처리했으니 안심하라구')
				} else {
					//stdDay 받아와서 dayList배열에 추가 -> 각 일자가 중복 없이 배열에 들어와야함
					dayColumn = $(this).find('stdDay').text();
					//xml의 stdDay값에 쓸데없이 00시가 무조건 달려있으므로  substr로 짤라준다
					dayList.unshift(dayColumn.substr(0, 13));	//xml이 최근 날짜 -> 예전날짜 순으로 나오므로 배열 맨 앞에다가 하나씩 추가한다
					//console.log(dayColumn);

					//incDec(추가 확진자 수) 받아와서  incDecList 배열에 추가 -> 각 일자마다 일일 추가 확진자 수가 배열에 들어옴
					incDec = $(this).find('incDec').text();
					incDecList.push(incDec); //오늘꺼 뽑기 쉽게 하기 위해 일단 push
					//console.log(incDec);
					// 다 등록했으면 중복일자 방지를 위해 comparator에 담음
					comparator = dayColumn;
				}
			}
		}
		);


		/*
				console.log(dayList);
				console.log(incDecList);
				console.log("------------------------------------");
				console.log(incDec);*/

		/*오늘 확진자수를 뽑아 그것을 기준으로 index페이지 표시*/
		var t_Num = incDecList[0];
	if (t_Num == 0) {
			$('.info1').css('display', 'inline-block');
		} else if (t_Num > 0 && t_Num < 50) {
			$('.info2').css('display', 'inline-block');
		} else if (t_Num >= 50 && t_Num < 100) {
			$('.info3').css('display', 'inline-block');
		} else if (t_Num >= 100) {
			$('.info4').css('display', 'inline-block');
		}

		//dayList와 일치하도록 배열 뒤집기
		incDecList.reverse();


		//chart.js


		var ctx = document.getElementById("myAreaChart");
		myLineChart = new Chart(ctx, {
			type: 'line',
			data: {
				//레이블을 ajax로 받아온 날짜 리스트로
				labels: dayList,
				datasets: [{
					label: "추가 확진자 수",
					lineTension: 0.3,
					backgroundColor: "rgba(2,117,216,0.2)",
					borderColor: "rgba(2,117,216,1)",
					pointRadius: 5,
					pointBackgroundColor: "rgba(2,117,216,1)",
					pointBorderColor: "rgba(255,255,255,0.8)",
					pointHoverRadius: 5,
					pointHoverBackgroundColor: "rgba(2,117,216,1)",
					pointHitRadius: 50,
					pointBorderWidth: 2,
					//데이터 값은 ajax로 뽑아온 incDecList로
					data: incDecList,
				}],
			},
			options: {
				scales: {
					xAxes: [{
						time: {
							unit: 'date'
						},
						gridLines: {
							display: false
						},
						ticks: {
							maxTicksLimit: 7
						}
					}],
					yAxes: [{
						ticks: {
							min: 0,
							//최근 6일 중 가장 높았던 날 숫자로
							max: Math.max.apply(null, incDecList),
							maxTicksLimit: 6
						},
						gridLines: {
							color: "rgba(0, 0, 0, .125)",
						}
					}],
				},
				legend: {
					display: false
				}
			}
		});

		//초기 바 차트 생성용 임시 변수
		var sidoList2 = [];
		var cntList2 = [];
		var today = new Date();
		
		var yesterday = new Date(today - 1000 * 60 * 60 *24);	//오늘 데이터가 아직 없을떄 사용할 어제 날짜  ->  오늘밀리세컨드 - (1000밀리세컨드*60초*60분*24시간)
		//console.log(yesterday);

		//moment라이브러리 이용하여 오늘 날짜를 api 에서 제공된 형식처럼 포맷팅 (ex) 2020년 10월 26일 00시
		var f_Today = moment(today).format('YYYY년 MM월 DD일 00시');
		var f_Yest = moment(yesterday).format('YYYY년 MM월 DD일 00시')
		//console.log(f_Today);
		//console.log(f_Yest);

		//stdDay가 포맷팅된 오늘날짜와 같은 item에 한하여 지역명과 누적확진자수 값을 가져온다. 가져온 값은 하나씩 push해서 배열에 넣는다.
		$(items).each(function() {
			if ($(this).find('stdDay').text() == f_Today) {
				var sido = $(this).find('gubun').text();
				sidoList.push(sido);
				var cnt = $(this).find('defCnt').text();
				cntList.push(cnt);
			}
		});
		
		//아직 오늘 데이터가 안들어온 경우 어제꺼라도 보여주기
		if (sidoList[0]==null) {
			sidoList=[];
			cntList=[];
			$(items).each(function() {
				if ($(this).find('stdDay').text() == f_Yest) {
					var sido = $(this).find('gubun').text();
					sidoList.push(sido);
					var cnt = $(this).find('defCnt').text();
					cntList.push(cnt);
				}
			})
		}
		
		//console.log(sidoList);
		//console.log(cntList);

		for (i = 1; i <= 9; i++) {
			sidoList2.push(sidoList[i]);
			cntList2.push(cntList[i]);
		}
		sidoList2.push(sidoList[18]);
		cntList2.push(cntList[18]);

	

		var ctx2 = document.getElementById("myBarChart");
		myBarChart = new Chart(ctx2, {
			type: 'bar',
			data: {
				labels: sidoList2,
				datasets: [{
					label: "누적 확진자 수",
					backgroundColor: "rgba(2,117,216,1)",
					borderColor: "rgba(2,117,216,1)",
					data: cntList2
				}],
			},
			options: {
				scales: {
					xAxes: [{
						time: {
							unit: '지역명'
						},
						gridLines: {
							display: false
						},
						//가로축 최대 갯수. 이 숫자를 넘어간 크기의 배열을 넣으면 에러나는거 같다.
						ticks: {
							maxTicksLimit: 10
						}
					}],
					yAxes: [{
						ticks: {
							min: 0,
							max: Math.max.apply(null, cntList),
							maxTicksLimit: 5
						},
						gridLines: {
							display: true
						}
					}],
				},
				legend: {
					display: false
				}
			}
		});
	},
	//xml자체가 안들어온 경우 - 우리 서버가 죽었거나 api 서버에 접근이 안됨(정부서버가 죽었거나 내 네트워크가 안되거나)
	error: function(data) {
		alert("네트워크 상황이 좋지 않거나 서버에서 응답하지 않습니다. 잠시 후 다시 시도해주세요.");
		console.log(data);
	}

});

//지역을 선택하면 각 지역별 확진수대로 그래프 update해주는 함수
//chart.js의 update(); 메소드로  처리

function graphChanger(areaName) {
	var items = $(xmlData).find('item');
	var dayList2 = [];
	var incDecList2 = [];
	var comparator2 = '';

	document.getElementById('area').innerHTML = areaName;

	$(items).each(function() {



		//gubunEn에 '합계'라고 되어있는 item'값만 뽑기
		if ($(this).find('gubunEn').text() == areaName) {

			//동일날짜 데이터가 두번 이상 찍혀있으면 거름
			if ($(this).find('stdDay').text() == comparator2) {
				console.log(comparator + ' 의 중복은 너굴맨이 처리했으니 안심하라구')
			} else {
				//stdDay 받아와서 dayList배열에 추가 -> 각 일자가 중복 없이 배열에 들어와야함
				var dayColumn2 = $(this).find('stdDay').text();
				//xml의 stdDay값에 쓸데없이 00시가 무조건 달려있으므로  substr로 짤라준다
				dayList2.unshift(dayColumn2.substr(0, 13));	//xml이 최근 날짜 -> 예전날짜 순으로 나오므로 배열 맨 앞에다가 하나씩 추가한다
				console.log(dayColumn2);

				//incDec(추가 확진자 수) 받아와서  incDecList 배열에 추가 -> 각 일자마다 일일 추가 확진자 수가 배열에 들어옴
				var incDec2 = $(this).find('incDec').text();
				incDecList2.unshift(incDec2);
				console.log(incDec2);
				// 다 등록했으면 중복일자 방지를 위해 comparator에 담음
				comparator2 = dayColumn2;
			}
		}
	})
	myLineChart.data.labels = dayList2;
	myLineChart.data.datasets[0].data = incDecList2;
	myLineChart.update();

};

function barChanger(gubun) {

	var sdl = [];
	var cntl = [];

	//만들어진 각 배열에서 index 1부터 9번까지는 광역시/특별시 이므로 여기까지만 뽑아서 새로운 배열을 만든다.
	if (gubun == 0) {
		for (var i = 1; i <= 9; i++) {
			sdl.push(sidoList[i]);
			cntl.push(cntList[i]);
		}
		sdl.push(sidoList[18]);
		cntl.push(cntList[18]);
		//만들어진 각 배열에서 index 10번부터 17번까지는 도 단위 데이터가 나오므로 해당부분만 뽑아서 새로운 배열을 만든다.
	} else if (gubun == 1) {
		for (var i = 10; i <= 17; i++) {
			sdl.push(sidoList[i]);
			cntl.push(cntList[i]);
		}
		sdl.push(sidoList[18]);
		cntl.push(cntList[18]);
		//혹시라도 다른 요청이 들어오면 컷
	} else {
		return alert('잘못된 접근입니다.')
	}

	//console.log(sidoList3);

	myBarChart.data.labels = sdl;
	myBarChart.data.datasets[0].data = cntl;
	myBarChart.update();

}

