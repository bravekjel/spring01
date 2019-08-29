package com.example.spring01.controller;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.example.spring01.model.dto.DaeCha_dataDTO;
import com.example.spring01.service.DaeCha_dataService;

@Controller
@RequestMapping("/search_stock/*")
public class SearchController {

	@Inject
	DaeCha_dataService daechaService;
	@RequestMapping("disInfo.do")
	public String disInfo(HttpServletRequest request) {
		String num = request.getParameter("num");
		request.setAttribute("key", num);
		System.out.println("num="+num);
		return "/include/search_stock/disInfo";
	}
	@RequestMapping("financialInfo.do")
	public String financialInfo(HttpServletRequest request) {
		String num = request.getParameter("num");
		request.setAttribute("key", num);
		System.out.println("num="+num);
		return "/include/search_stock/financialInfo";
	}

	@RequestMapping("stockInfo.do{num}")
	public String stockInfo(HttpServletRequest request) {
		String num = request.getParameter("num");
		request.setAttribute("key", num);
		System.out.println("number전달값="+num);
		return "/include/search_stock/stockInfo";
		
	}
	

	
	
// financialInfo 정보 가져오기 연습1	
	@RequestMapping("finan1.do")
	public String finan1(@ModelAttribute DaeCha_dataDTO dto,HttpServletRequest request) {
		System.out.println("finan1===");
		String num = request.getParameter("num");
		
		String geturl = "http://asp1.krx.co.kr/servlet/krx.asp.XMLJemu?code="+num;
		String gettime="";
		String DaeCha_ym[]= new String [6];
		String SonIk_ym[]= new String [6];
		String CashFlow_ym[]= new String [6];
		
		String DaeCha_data[][]= new String [10][10];
		String SonIk_data[][]= new String [6][10];
		String CashFlow_data[][]= new String [6][7];
		
		int daeCha_data_length = 0;
		int sonIk_data_length = 0;
		int cashFlow_data_length = 0;
		

		String line="";
		String xml = ""; 

		
		
		try{
			URL url = new URL(geturl);
			URLConnection conn = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) conn;
			InputStream is = null;
			InputStreamReader isr = null;
			
			is =  new URL(geturl).openStream();
			isr = new InputStreamReader(is, "UTF-8");
			
			
			
			BufferedReader rd = new BufferedReader(isr,400);
			
			StringBuffer strbuf = new StringBuffer();
			
			
			while ((line = rd.readLine()) != null){
				
			  	strbuf.append(line);
			}
			
			//System.out.println("재무정보");
			//System.out.println(strbuf.toString());
			
			DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
			docFact.setNamespaceAware(true);
			DocumentBuilder docBuild = docFact.newDocumentBuilder();

			Document doc = docBuild.parse(new InputSource(new StringReader(strbuf.toString())));
			doc.getDocumentElement().normalize();
				
			Element root = doc.getDocumentElement();
			
			
			/*조회시각*/
			NodeList financialTotal = doc.getElementsByTagName("financialTotal");
			
			NamedNodeMap stockinfo = financialTotal.item(0).getAttributes();
			gettime = stockinfo.getNamedItem("querytime").getNodeValue();
			
			/*대차대조표*/
			
			NodeList TBL_DaeCha_ym = doc.getElementsByTagName("TBL_DaeCha");
			NamedNodeMap daeCha_ym = TBL_DaeCha_ym.item(0).getAttributes();
			
			DaeCha_ym[0] = daeCha_ym.getNamedItem("year0").getNodeValue();
			DaeCha_ym[1] = daeCha_ym.getNamedItem("month0").getNodeValue();
			DaeCha_ym[2] = daeCha_ym.getNamedItem("year1").getNodeValue();
			DaeCha_ym[3] = daeCha_ym.getNamedItem("month1").getNodeValue();
			DaeCha_ym[4] = daeCha_ym.getNamedItem("year2").getNodeValue();
			DaeCha_ym[5] = daeCha_ym.getNamedItem("month2").getNodeValue();
			
			NodeList TBL_DaeCha_data = doc.getElementsByTagName("TBL_DaeCha_data");

			daeCha_data_length = TBL_DaeCha_data.getLength();
			
			for(int i=0;i<daeCha_data_length;i++){
				int j = 1+i;
				
				NamedNodeMap daeCha_data = TBL_DaeCha_data.item(i).getAttributes();
//--------------------------------------1------------------------------------------------	DaeCha_data			
				System.out.println("DaeCha_data=="+daeCha_data);
				System.out.println("hangMok=="+daeCha_data.getNamedItem("hangMok"+i).getNodeValue());
				System.out.println("year1Money=="+daeCha_data.getNamedItem("year1Money"+i).getNodeValue());
				System.out.println("year1GuSungRate=="+daeCha_data.getNamedItem("year1GuSungRate"+i).getNodeValue());
				System.out.println("year1JungGamRate=="+daeCha_data.getNamedItem("year1JungGamRate"+i).getNodeValue());
				System.out.println("year2Money=="+daeCha_data.getNamedItem("year2Money"+i).getNodeValue());
				System.out.println("year2GuSungRate=="+daeCha_data.getNamedItem("year2GuSungRate"+i).getNodeValue());
				System.out.println("year2JungGamRate=="+daeCha_data.getNamedItem("year2JungGamRate"+i).getNodeValue());
				System.out.println("year3Money=="+daeCha_data.getNamedItem("year3Money"+i).getNodeValue());
				System.out.println("year3GuSungRate=="+daeCha_data.getNamedItem("year3GuSungRate"+i).getNodeValue());
				System.out.println("year3JungGamRate=="+daeCha_data.getNamedItem("year3JungGamRate"+i).getNodeValue());
				
				DaeCha_data[i][0] = daeCha_data.getNamedItem("hangMok"+i).getNodeValue();
				DaeCha_data[i][1] = daeCha_data.getNamedItem("year1Money"+i).getNodeValue();
				DaeCha_data[i][2] = daeCha_data.getNamedItem("year1GuSungRate"+i).getNodeValue();
				DaeCha_data[i][3] = daeCha_data.getNamedItem("year1JungGamRate"+i).getNodeValue();
				DaeCha_data[i][4] = daeCha_data.getNamedItem("year2Money"+i).getNodeValue();
				DaeCha_data[i][5] = daeCha_data.getNamedItem("year2GuSungRate"+i).getNodeValue();
				DaeCha_data[i][6] = daeCha_data.getNamedItem("year2JungGamRate"+i).getNodeValue();
				DaeCha_data[i][7] = daeCha_data.getNamedItem("year3Money"+i).getNodeValue();
				DaeCha_data[i][8] = daeCha_data.getNamedItem("year3GuSungRate"+i).getNodeValue();
				DaeCha_data[i][9] = daeCha_data.getNamedItem("year3JungGamRate"+i).getNodeValue();
				
				
			}
			//0-9 번째 까지 
			//주식번호
			dto.setS_num(num);
			dto.setS_name("종목이름");
			
			// 0 번째  유동자산(_1)
			NamedNodeMap daeCha_data0 = TBL_DaeCha_data.item(0).getAttributes();
			dto.setHangMok_1(daeCha_data0.getNamedItem("hangMok"+0).getNodeValue());
			dto.setYear1Money_1(daeCha_data0.getNamedItem("year1Money"+0).getNodeValue());
			dto.setYear1GusungRate_1(daeCha_data0.getNamedItem("year1GuSungRate"+0).getNodeValue());
			dto.setYear1JungGamRate_1(daeCha_data0.getNamedItem("year1JungGamRate"+0).getNodeValue());
			dto.setYear2Money_1(daeCha_data0.getNamedItem("year2Money"+0).getNodeValue());
			dto.setYear2GusungRate_1(daeCha_data0.getNamedItem("year2GuSungRate"+0).getNodeValue());
			dto.setYear2JungGamRate_1(daeCha_data0.getNamedItem("year2JungGamRate"+0).getNodeValue());
			dto.setYear3Money_1(daeCha_data0.getNamedItem("year3Money"+0).getNodeValue());
			dto.setYear3GusungRate_1(daeCha_data0.getNamedItem("year3GuSungRate"+0).getNodeValue());
			dto.setYear3JungGamRate_1(daeCha_data0.getNamedItem("year3JungGamRate"+0).getNodeValue());
			
			// 1 번째  비유동자산(_2)
			NamedNodeMap daeCha_data1 = TBL_DaeCha_data.item(1).getAttributes();
			dto.setHangMok_2(daeCha_data1.getNamedItem("hangMok"+1).getNodeValue());
			dto.setYear1Money_2(daeCha_data1.getNamedItem("year1Money"+1).getNodeValue());
			dto.setYear1GusungRate_2(daeCha_data1.getNamedItem("year1GuSungRate"+1).getNodeValue());
			dto.setYear1JungGamRate_2(daeCha_data1.getNamedItem("year1JungGamRate"+1).getNodeValue());
			dto.setYear2Money_2(daeCha_data1.getNamedItem("year2Money"+1).getNodeValue());
			dto.setYear2GusungRate_2(daeCha_data1.getNamedItem("year2GuSungRate"+1).getNodeValue());
			dto.setYear2JungGamRate_2(daeCha_data1.getNamedItem("year2JungGamRate"+1).getNodeValue());
			dto.setYear3Money_2(daeCha_data1.getNamedItem("year3Money"+1).getNodeValue());
			dto.setYear3GusungRate_2(daeCha_data1.getNamedItem("year3GuSungRate"+1).getNodeValue());
			dto.setYear3JungGamRate_2(daeCha_data1.getNamedItem("year3JungGamRate"+1).getNodeValue());
			// 2 번째 자산총계(_3)
			NamedNodeMap daeCha_data2 = TBL_DaeCha_data.item(2).getAttributes();
			dto.setHangMok_3(daeCha_data2.getNamedItem("hangMok"+2).getNodeValue());
			dto.setYear1Money_3(daeCha_data2.getNamedItem("year1Money"+2).getNodeValue());
			dto.setYear1GusungRate_3(daeCha_data2.getNamedItem("year1GuSungRate"+2).getNodeValue());
			dto.setYear1JungGamRate_3(daeCha_data2.getNamedItem("year1JungGamRate"+2).getNodeValue());
			dto.setYear2Money_3(daeCha_data2.getNamedItem("year2Money"+2).getNodeValue());
			dto.setYear2GusungRate_3(daeCha_data2.getNamedItem("year2GuSungRate"+2).getNodeValue());
			dto.setYear2JungGamRate_3(daeCha_data2.getNamedItem("year2JungGamRate"+2).getNodeValue());
			dto.setYear3Money_3(daeCha_data2.getNamedItem("year3Money"+2).getNodeValue());
			dto.setYear3GusungRate_3(daeCha_data2.getNamedItem("year3GuSungRate"+2).getNodeValue());
			dto.setYear3JungGamRate_3(daeCha_data2.getNamedItem("year3JungGamRate"+2).getNodeValue());
			// 3 번째  유동부채(계) (_4)
			NamedNodeMap daeCha_data3 = TBL_DaeCha_data.item(3).getAttributes();
			dto.setHangMok_4(daeCha_data3.getNamedItem("hangMok"+3).getNodeValue());
			dto.setYear1Money_4(daeCha_data3.getNamedItem("year1Money"+3).getNodeValue());
			dto.setYear1GusungRate_4(daeCha_data3.getNamedItem("year1GuSungRate"+3).getNodeValue());
			dto.setYear1JungGamRate_4(daeCha_data3.getNamedItem("year1JungGamRate"+3).getNodeValue());
			dto.setYear2Money_4(daeCha_data3.getNamedItem("year2Money"+3).getNodeValue());
			dto.setYear2GusungRate_4(daeCha_data3.getNamedItem("year2GuSungRate"+3).getNodeValue());
			dto.setYear2JungGamRate_4(daeCha_data3.getNamedItem("year2JungGamRate"+3).getNodeValue());
			dto.setYear3Money_4(daeCha_data3.getNamedItem("year3Money"+3).getNodeValue());
			dto.setYear3GusungRate_4(daeCha_data3.getNamedItem("year3GuSungRate"+3).getNodeValue());
			dto.setYear3JungGamRate_4(daeCha_data3.getNamedItem("year3JungGamRate"+3).getNodeValue());
			// 4 번째  비유동부채(계) (_5)
			NamedNodeMap daeCha_data4 = TBL_DaeCha_data.item(4).getAttributes();
			dto.setHangMok_5(daeCha_data4.getNamedItem("hangMok"+4).getNodeValue());
			dto.setYear1Money_5(daeCha_data4.getNamedItem("year1Money"+4).getNodeValue());
			dto.setYear1GusungRate_5(daeCha_data4.getNamedItem("year1GuSungRate"+4).getNodeValue());
			dto.setYear1JungGamRate_5(daeCha_data4.getNamedItem("year1JungGamRate"+4).getNodeValue());
			dto.setYear2Money_5(daeCha_data4.getNamedItem("year2Money"+4).getNodeValue());
			dto.setYear2GusungRate_5(daeCha_data4.getNamedItem("year2GuSungRate"+4).getNodeValue());
			dto.setYear2JungGamRate_5(daeCha_data4.getNamedItem("year2JungGamRate"+4).getNodeValue());
			dto.setYear3Money_5(daeCha_data4.getNamedItem("year3Money"+4).getNodeValue());
			dto.setYear3GusungRate_5(daeCha_data4.getNamedItem("year3GuSungRate"+4).getNodeValue());
			dto.setYear3JungGamRate_5(daeCha_data4.getNamedItem("year3JungGamRate"+4).getNodeValue());
			// 5 번째 부채 총계(_6)
			NamedNodeMap daeCha_data5 = TBL_DaeCha_data.item(5).getAttributes();
			dto.setHangMok_6(daeCha_data5.getNamedItem("hangMok"+5).getNodeValue());
			dto.setYear1Money_6(daeCha_data5.getNamedItem("year1Money"+5).getNodeValue());
			dto.setYear1GusungRate_6(daeCha_data5.getNamedItem("year1GuSungRate"+5).getNodeValue());
			dto.setYear1JungGamRate_6(daeCha_data5.getNamedItem("year1JungGamRate"+5).getNodeValue());
			dto.setYear2Money_6(daeCha_data5.getNamedItem("year2Money"+5).getNodeValue());
			dto.setYear2GusungRate_6(daeCha_data5.getNamedItem("year2GuSungRate"+5).getNodeValue());
			dto.setYear2JungGamRate_6(daeCha_data5.getNamedItem("year2JungGamRate"+5).getNodeValue());
			dto.setYear3Money_6(daeCha_data5.getNamedItem("year3Money"+5).getNodeValue());
			dto.setYear3GusungRate_6(daeCha_data5.getNamedItem("year3GuSungRate"+5).getNodeValue());
			dto.setYear3JungGamRate_6(daeCha_data5.getNamedItem("year3JungGamRate"+5).getNodeValue());
			// 6 번째 자본금(_7)
			NamedNodeMap daeCha_data6 = TBL_DaeCha_data.item(6).getAttributes();
			dto.setHangMok_7(daeCha_data6.getNamedItem("hangMok"+6).getNodeValue());
			dto.setYear1Money_7(daeCha_data6.getNamedItem("year1Money"+6).getNodeValue());
			dto.setYear1GusungRate_7(daeCha_data6.getNamedItem("year1GuSungRate"+6).getNodeValue());
			dto.setYear1JungGamRate_7(daeCha_data6.getNamedItem("year1JungGamRate"+6).getNodeValue());
			dto.setYear2Money_7(daeCha_data6.getNamedItem("year2Money"+6).getNodeValue());
			dto.setYear2GusungRate_7(daeCha_data6.getNamedItem("year2GuSungRate"+6).getNodeValue());
			dto.setYear2JungGamRate_7(daeCha_data6.getNamedItem("year2JungGamRate"+6).getNodeValue());
			dto.setYear3Money_7(daeCha_data6.getNamedItem("year3Money"+6).getNodeValue());
			dto.setYear3GusungRate_7(daeCha_data6.getNamedItem("year3GuSungRate"+6).getNodeValue());
			dto.setYear3JungGamRate_7(daeCha_data6.getNamedItem("year3JungGamRate"+6).getNodeValue());
			// 7 번째 자본잉여금(_8)
			NamedNodeMap daeCha_data7 = TBL_DaeCha_data.item(7).getAttributes();
			dto.setHangMok_8(daeCha_data7.getNamedItem("hangMok"+7).getNodeValue());
			dto.setYear1Money_8(daeCha_data7.getNamedItem("year1Money"+7).getNodeValue());
			dto.setYear1GusungRate_8(daeCha_data7.getNamedItem("year1GuSungRate"+7).getNodeValue());
			dto.setYear1JungGamRate_8(daeCha_data7.getNamedItem("year1JungGamRate"+7).getNodeValue());
			dto.setYear2Money_8(daeCha_data7.getNamedItem("year2Money"+7).getNodeValue());
			dto.setYear2GusungRate_8(daeCha_data7.getNamedItem("year2GuSungRate"+7).getNodeValue());
			dto.setYear2JungGamRate_8(daeCha_data7.getNamedItem("year2JungGamRate"+7).getNodeValue());
			dto.setYear3Money_8(daeCha_data7.getNamedItem("year3Money"+7).getNodeValue());
			dto.setYear3GusungRate_8(daeCha_data7.getNamedItem("year3GuSungRate"+7).getNodeValue());
			dto.setYear3JungGamRate_8(daeCha_data7.getNamedItem("year3JungGamRate"+7).getNodeValue());
			// 8 번째 이익 잉여금 (_9)
			NamedNodeMap daeCha_data8 = TBL_DaeCha_data.item(8).getAttributes();
			dto.setHangMok_9(daeCha_data8.getNamedItem("hangMok"+8).getNodeValue());
			dto.setYear1Money_9(daeCha_data8.getNamedItem("year1Money"+8).getNodeValue());
			dto.setYear1GusungRate_9(daeCha_data8.getNamedItem("year1GuSungRate"+8).getNodeValue());
			dto.setYear1JungGamRate_9(daeCha_data8.getNamedItem("year1JungGamRate"+8).getNodeValue());
			dto.setYear2Money_9(daeCha_data8.getNamedItem("year2Money"+8).getNodeValue());
			dto.setYear2GusungRate_9(daeCha_data8.getNamedItem("year2GuSungRate"+8).getNodeValue());
			dto.setYear2JungGamRate_9(daeCha_data8.getNamedItem("year2JungGamRate"+8).getNodeValue());
			dto.setYear3Money_9(daeCha_data8.getNamedItem("year3Money"+8).getNodeValue());
			dto.setYear3GusungRate_9(daeCha_data8.getNamedItem("year3GuSungRate"+8).getNodeValue());
			dto.setYear3JungGamRate_9(daeCha_data8.getNamedItem("year3JungGamRate"+8).getNodeValue());
			// 9 번째 자본총계 (_10)
			NamedNodeMap daeCha_data9 = TBL_DaeCha_data.item(9).getAttributes();
			dto.setHangMok_10(daeCha_data9.getNamedItem("hangMok"+9).getNodeValue());
			dto.setYear1Money_10(daeCha_data9.getNamedItem("year1Money"+9).getNodeValue());
			dto.setYear1GusungRate_10(daeCha_data9.getNamedItem("year1GuSungRate"+9).getNodeValue());
			dto.setYear1JungGamRate_10(daeCha_data9.getNamedItem("year1JungGamRate"+9).getNodeValue());
			dto.setYear2Money_10(daeCha_data9.getNamedItem("year2Money"+9).getNodeValue());
			dto.setYear2GusungRate_10(daeCha_data9.getNamedItem("year2GuSungRate"+9).getNodeValue());
			dto.setYear2JungGamRate_10(daeCha_data9.getNamedItem("year2JungGamRate"+9).getNodeValue());
			dto.setYear3Money_10(daeCha_data9.getNamedItem("year3Money"+9).getNodeValue());
			dto.setYear3GusungRate_10(daeCha_data9.getNamedItem("year3GuSungRate"+9).getNodeValue());
			dto.setYear3JungGamRate_10(daeCha_data9.getNamedItem("year3JungGamRate"+9).getNodeValue());
			
			// 데이터베이스에 저장하기
			System.out.println("데이터베이스저장dto  === "+dto);
			daechaService.insert(dto);
			
			/*손익계산서*/
			
			NodeList TBL_SonIk_ym = doc.getElementsByTagName("TBL_SonIk");
			NamedNodeMap sonIk_ym = TBL_SonIk_ym.item(0).getAttributes();
			
			SonIk_ym[0] = daeCha_ym.getNamedItem("year0").getNodeValue();
			SonIk_ym[1] = daeCha_ym.getNamedItem("month0").getNodeValue();
			SonIk_ym[2] = daeCha_ym.getNamedItem("year1").getNodeValue();
			SonIk_ym[3] = daeCha_ym.getNamedItem("month1").getNodeValue();
			SonIk_ym[4] = daeCha_ym.getNamedItem("year2").getNodeValue();
			SonIk_ym[5] = daeCha_ym.getNamedItem("month2").getNodeValue();
			
			NodeList TBL_SonIk_data = doc.getElementsByTagName("TBL_SonIk_data");
			
			sonIk_data_length = TBL_SonIk_data.getLength();
			
			for(int i=0;i<sonIk_data_length;i++){
				
				NamedNodeMap sonIk_data = TBL_SonIk_data.item(i).getAttributes();
//--------------------------------------2------------------------------------------------				
				System.out.println("SonIk_data=="+sonIk_data);			
				System.out.println("hangMok=="+sonIk_data.getNamedItem("hangMok"+i).getNodeValue());
				System.out.println("year1Money=="+sonIk_data.getNamedItem("year1Money"+i).getNodeValue());
				System.out.println("year1GuSungRate=="+sonIk_data.getNamedItem("year1GuSungRate"+i).getNodeValue());
				System.out.println("year1JungGamRate=="+sonIk_data.getNamedItem("year1JungGamRate"+i).getNodeValue());
				System.out.println("year2Money=="+sonIk_data.getNamedItem("year2Money"+i).getNodeValue());
				System.out.println("year2GuSungRate=="+sonIk_data.getNamedItem("year2GuSungRate"+i).getNodeValue());
				System.out.println("year2JungGamRate=="+ sonIk_data.getNamedItem("year2JungGamRate"+i).getNodeValue());
				System.out.println("year3Money=="+sonIk_data.getNamedItem("year3Money"+i).getNodeValue());
				System.out.println("year3GuSungRate=="+sonIk_data.getNamedItem("year3GuSungRate"+i).getNodeValue());
				System.out.println("year3JungGamRate=="+sonIk_data.getNamedItem("year3JungGamRate"+i).getNodeValue());
				
				
				
				
				
				
				SonIk_data[i][0] = sonIk_data.getNamedItem("hangMok"+i).getNodeValue();
				SonIk_data[i][1] = sonIk_data.getNamedItem("year1Money"+i).getNodeValue();
				SonIk_data[i][2] = sonIk_data.getNamedItem("year1GuSungRate"+i).getNodeValue();
				SonIk_data[i][3] = sonIk_data.getNamedItem("year1JungGamRate"+i).getNodeValue();
				SonIk_data[i][4] = sonIk_data.getNamedItem("year2Money"+i).getNodeValue();
				SonIk_data[i][5] = sonIk_data.getNamedItem("year2GuSungRate"+i).getNodeValue();
				SonIk_data[i][6] = sonIk_data.getNamedItem("year2JungGamRate"+i).getNodeValue();
				SonIk_data[i][7] = sonIk_data.getNamedItem("year3Money"+i).getNodeValue();
				SonIk_data[i][8] = sonIk_data.getNamedItem("year3GuSungRate"+i).getNodeValue();
				SonIk_data[i][9] = sonIk_data.getNamedItem("year3JungGamRate"+i).getNodeValue();
				
			}
			
			/*현금흐름표*/
			
			NodeList TBL_CashFlow = doc.getElementsByTagName("TBL_CashFlow");
			NamedNodeMap cashFlow_ym = TBL_CashFlow.item(0).getAttributes();
			
			CashFlow_ym[0] = cashFlow_ym.getNamedItem("year0").getNodeValue();
			CashFlow_ym[1] = cashFlow_ym.getNamedItem("month0").getNodeValue();
			CashFlow_ym[2] = cashFlow_ym.getNamedItem("year1").getNodeValue();
			CashFlow_ym[3] = cashFlow_ym.getNamedItem("month1").getNodeValue();
			CashFlow_ym[4] = cashFlow_ym.getNamedItem("year2").getNodeValue();
			CashFlow_ym[5] = cashFlow_ym.getNamedItem("month2").getNodeValue();
			
			NodeList TBL_CashFlow_data = doc.getElementsByTagName("TBL_CashFlow_data");
			
			cashFlow_data_length = TBL_CashFlow_data.getLength();
			
			for(int i=0;i<cashFlow_data_length;i++){
				
				NamedNodeMap cashFlow_data = TBL_CashFlow_data.item(i).getAttributes();
//--------------------------------------3------------------------------------------------				
				System.out.println("CashFlow_data=="+cashFlow_data);	
				System.out.println( "hangMok=="+cashFlow_data.getNamedItem("hangMok"+i).getNodeValue());
				System.out.println("year1Money=="+cashFlow_data.getNamedItem("year1Money"+i).getNodeValue());
				System.out.println("year1JungGamRate=="+cashFlow_data.getNamedItem("year1JungGamRate"+i).getNodeValue());
				System.out.println("year2Money=="+cashFlow_data.getNamedItem("year2Money"+i).getNodeValue());
				System.out.println("year2JungGamRate=="+cashFlow_data.getNamedItem("year2JungGamRate"+i).getNodeValue());
				System.out.println("year3Money=="+cashFlow_data.getNamedItem("year3Money"+i).getNodeValue());
				System.out.println("year3JungGamRate=="+cashFlow_data.getNamedItem("year3JungGamRate"+i).getNodeValue());
				
				
				CashFlow_data[i][0] = cashFlow_data.getNamedItem("hangMok"+i).getNodeValue();
				CashFlow_data[i][1] = cashFlow_data.getNamedItem("year1Money"+i).getNodeValue();
				CashFlow_data[i][2] = cashFlow_data.getNamedItem("year1JungGamRate"+i).getNodeValue();
				CashFlow_data[i][3] = cashFlow_data.getNamedItem("year2Money"+i).getNodeValue();
				CashFlow_data[i][4] = cashFlow_data.getNamedItem("year2JungGamRate"+i).getNodeValue();
				CashFlow_data[i][5] = cashFlow_data.getNamedItem("year3Money"+i).getNodeValue();
				CashFlow_data[i][6] = cashFlow_data.getNamedItem("year3JungGamRate"+i).getNodeValue();
				
			}
		
		} catch(Exception e){
			
		}
		
		
		
//		return "redirect:/daecha/insert.do";
		return "home";
		
		
	}

}
