package org.covid19.ourapp.ajax;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.covid19.ourapp.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//RestController라고 어노테이션을 붙이면 이 클래스 내 메소드들은 모두 자동으로 @ResponseBody한것과 같은 결과를 반환한다
@RestController
public class AjaxController {
	
	@Autowired
	MemberService memberService;
	
	//한글 꺠짐을 막기 위해 produces 값에 charset을 지정한다
	@RequestMapping(value = "/areaChart.do", produces = "application/xml; charset=utf8")
	
	public String AreaChart() throws Exception {

		final Logger logger = LoggerFactory.getLogger(AjaxController.class);
		
		//api의 날짜 변수 포맷에 맞게 바꿔줄 날짜 포맷터
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		Date baseDate = new Date();
		
		//오늘날짜에서 5일 빼기
		cal.setTime(baseDate);
		cal.add(Calendar.DATE, -5);
		Date minus5  = cal.getTime();
		
		//오늘날짜
		String today = sdf.format(baseDate);
		//5일전 날짜
		String five_days_ago = sdf.format(minus5);
		
			
		/* params - 공식 가이드에 UTF-8로 인코딩하여 보내라고 하였으므로 인코딩함*/
		StringBuilder sb = new StringBuilder(
				"http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson");

		String serviceKey = "jDwaWRfkGxuVMn4uEmJqV2BJlKUfV%2B9HHcMyLV2Qfj0JWR2ZWcO3mZ1cI4pKtanQ7BCwRt9GHfyg8nLvkyQxSw%3D%3D";

		//서비스키는 이미 UTF-8로 인코딩 된 것이 제공되고있으므로 디코딩 후 재인코딩
		String serviceKeyDecoded = URLDecoder.decode(serviceKey, "UTF-8");
		String startCreate_dt = five_days_ago;
		String endCreateDt = today;

		sb.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKeyDecoded, "UTF-8"));
		sb.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		sb.append("&" + URLEncoder.encode("startCreateDt", "UTF-8") + "="
				+ URLEncoder.encode(startCreate_dt, "UTF-8")); /* 검색할 생성일 범위의 시작 */
		sb.append("&" + URLEncoder.encode("endCreateDt", "UTF-8") + "="
				+ URLEncoder.encode(endCreateDt, "UTF-8")); /* 검색할 생성일 범위의 종료 */
		logger.info(sb.toString());
		URL url = new URL(sb.toString());
		//HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		//서버의 xml을 읽어오기 위해 bufferedReader객체 생성(InputStreamReader를 통해 utf-8형식으로 url에서 읽어온다.)
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		
		//읽어온 내용을 한줄씩 임시로 받아둘 변수
		String inputLine;
		//최종적으로 읽어진 xml문서 전체가 담길 변수
		String XmlDoc = "";

		//readLine으로 한줄씩 읽어와서 inputLine에 담기면 그 내용을 XmlDoc에 저장
		while ((inputLine = br.readLine()) != null) {
			XmlDoc += inputLine.trim();
		}
		
		//다 읽었으면 리더객체 닫기
		br.close();

		//test
		//System.out.println("XML 내용: "+ XmlDoc);

		return XmlDoc;

	}

	@RequestMapping(value= "/nickcheck" ,method = RequestMethod.GET)
	 
	   public int idCheck(@RequestParam("nickName") String nickName) {

	      return memberService.nickNameCheck(nickName);
	   
	   }
	
	
}



