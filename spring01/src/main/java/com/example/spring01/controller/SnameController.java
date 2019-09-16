package com.example.spring01.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring01.util.json.ParserUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
@RequestMapping("/snames/*")
public class SnameController {
	
	
	@RequestMapping("find.do")
	public void find( HttpServletRequest request, Model model) throws IOException {
		System.out.println("find.do");
		String snames = request.getParameter("snames");
		
		System.out.println(snames);
//		String a =" http://api.seibro.or.kr/openapi/service/StockSvc/getStkIsinByNmN1?serviceKey=qMWBfGFhp4J1REtArquWOwxObYzh%2FoesRVV7scn4Hf3Xm%2BFEMMen0BMT4MhI%2BBwsrAlz5ik%2Bb87Q0pffXDuEFw%3D%3D&secnNm="+snames+"&numOfRows=2&pageNo=1";
		String a =" http://api.seibro.or.kr/openapi/service/StockSvc/getStkIsinByNmN1?secnNm="+snames+"&numOfRows=2&pageNo=1&ServiceKey=qMWBfGFhp4J1REtArquWOwxObYzh%2FoesRVV7scn4Hf3Xm%2BFEMMen0BMT4MhI%2BBwsrAlz5ik%2Bb87Q0pffXDuEFw%3D%3D";
		
		System.out.println(a);
		String html = ParserUtil.getHtml(a);
		String xml = ParserUtil.getXML(a);
		String items = ParserUtil.getItems(a);
		String gurl = ParserUtil.geturl(a);
		System.out.println("xml==="+xml);
		System.out.println("html="+html);
		JSONObject json = XML.toJSONObject(html);
		Map<String,Object> resultMap = ParserUtil.parseJsonToMap(json.toString());
		model.addAttribute("map",resultMap);
		System.out.println("resultMap"+resultMap);
		
	}

	
}
