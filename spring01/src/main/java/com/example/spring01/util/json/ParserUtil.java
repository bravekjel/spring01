package com.example.spring01.util.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParserUtil {
	public static Map<String, Object> parseJsonToMap(String json) {
		System.out.println("json="+json);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}
	
	public static String geturl(String url) {
		String line= "";
		try {
			URL geturl = new URL(url);
			URLConnection conn = geturl.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) conn;
			InputStream is = null;
			InputStreamReader isr = null;
			
			is = new URL(url).openStream();
			isr = new InputStreamReader(is,"UTF-8");
			
			BufferedReader rd = new BufferedReader(isr,400);
			
			StringBuffer strbuf = new StringBuffer();
			
			while((line = rd.readLine()) != null) {
				strbuf.append(line);
			}
			System.out.println("geturl의 line="+strbuf);
			DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
			docFact.setNamespaceAware(true);
			DocumentBuilder docBuild = docFact.newDocumentBuilder();

			Document doc = docBuild.parse(new InputSource(new StringReader(strbuf.toString())));
			doc.getDocumentElement().normalize();
			
			
			
			Element root = doc.getDocumentElement();
		} catch (Exception e) {
			System.out.println("에러 e="+e);
		}
		return url;
		
	}

	public static String getHtml(String url) {
		System.out.println("url="+url);
		
		try {
			URL targetUrl = new URL(url);
//			System.out.println("targetUrl=="+targetUrl);
//			System.out.println("new  BufferedReader===="+new BufferedReader(new InputStreamReader(targetUrl.openStream())));
			BufferedReader reader = new BufferedReader(new InputStreamReader(targetUrl.openStream()));
			StringBuilder html = new StringBuilder();
			String current = "";
			while ((current = reader.readLine()) != null) {
				System.out.println("currenr==="+current);
				html.append(current);
			}
			reader.close();
			return html.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getItems(String url) {
		//1.페이지에 접근해줄 Document객체 생성
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			try {
				Document xml = documentBuilder.parse(url);
				xml.getDocumentElement().normalize();
				System.out.println("Root element:"+xml.getDocumentElement().getNodeName());
		//2.파싱에 정보가 있는 tag에 접근
				NodeList nList = xml.getElementsByTagName("items");
				System.out.println("파싱할 리스트 수:"+nList.getLength());
		//3. list에 담긴 데이터 출력하기
				for(int temp = 0; temp<nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						System.out.println("#############");
						System.out.println("eElement값:"+eElement);
						System.out.println("item값:"+getTagValue("item",eElement));
						System.out.println("주식이름:"+getTagValue("korSecnNm",eElement));
						System.out.println("주식번호:"+getTagValue("shotnlsin",eElement));
						
						
					}//end if
				} // end for
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch (ParserConfigurationException e) {
			
		}
		return url;
	}
	
	public static String getXML(String url) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document xml = documentBuilder.parse(url);
			xml.getDocumentElement().normalize();
			System.out.println("Root element :"+ xml.getDocumentElement().getNodeName());
			Element root = xml.getDocumentElement();
			System.out.println("root---"+root);
			NodeList nodeList = root.getElementsByTagName("items");
			NodeList nList = xml.getElementsByTagName("items");
			System.out.println("nList======"+nList+"   갯수=="+nList.getLength());
			
			System.out.println("nodeList=="+nodeList);
			
			if(nList.getLength()==0) return url;
			for(int i=0; i<nList.getLength(); i++) {
				Node nItem = nList.item(i);
				System.out.println("nodeList.item(i)==="+nItem);
				
				try {
					String id = getTagValue("id",(Element)nItem);
					System.out.println("id="+id);
				} catch (Exception e) {
				}
			}
			if(nodeList.getLength()==0) return url;
			for(int i=0; i<nodeList.getLength(); i++) {
				Node nodeItem = nodeList.item(i);
				System.out.println("nodeList.item(i)==="+nodeItem);
				
				try {
					String id = getTagValue("id",(Element)nodeItem);
					System.out.println("id="+id);
				} catch (Exception e) {
				}
			}
		} catch (ParserConfigurationException e) {
		}catch(SAXException e) {
		}catch(IOException e) {
		}catch(Exception e) {
		}
		return url;
	}
	private static String getTagValue(String sTag, Element element) {
		try {
			String result = element.getElementsByTagName(sTag).item(0).getTextContent();
			return result;
		} catch (NullPointerException e) {
			return "";
		}catch(Exception e) {
			return "";
		}
	}
}
