// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

let ServiceKey = "ZpOe8SCcPJ6ksjW7xzMmIHV7OicS0CxYyQaCVARUFNf%2B9tOEZzC0wQaOYWpqn4mu7jZ5%2B3wDGqW0aKzECFYZbA%3D%3D";
let pageNo = 1;
let numOfRows = 500;
let CreateDt = new Date();

function dateToYear(date) {
    var year = date.getFullYear();

    var month = date.getMonth() + 1;
    if (month < 10)  {
        month = '0' + month;
    }

    var date = date.getDate();
    if (date < 10) {
        date = '0' + date;
    }
    
    return year + '' + month + '' + date;
}



let endCreateDt = startCreateDt;

console.log(startCreateDt);

//차트용 ajax
/*   $(document).ready(function(){
        $.ajax({
            url:"data.xml",  //전송페이지, 서버주소
            type:"get",  //get, post 방식
            data:"전송할_데이터",
            dataType:"xml",  //요청한 데이터 형식(html,xml,json,text,jsonp)
            success:function(data){  //data:서버쪽에서 날라오는 데이터
                //전송에 성공하면 실행될 코드;
            },
            error:function () {
                //전송에 실패하면 실행될 코드;
            }
        });
    });
*/


// Area Chart Example
var ctx = document.getElementById("myAreaChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: ["Mar 1", "Mar 2", "Mar 3", "Mar 4", "Mar 5", "Mar 6", "Mar 7", "Mar 8", "Mar 9", "Mar 10", "Mar 11", "Mar 12", "Mar 13"],
    datasets: [{
      label: "Sessions",
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
      data: [10000, 30162, 26263, 18394, 18287, 28682, 31274, 33259, 25849, 24159, 32651, 31984, 38451],
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
          max: 40000,
          maxTicksLimit: 5
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
