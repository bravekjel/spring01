package com.example.spring01.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring01.service.board.BoardService;
import com.example.spring01.util.MediaUtils;
import com.example.spring01.util.UploadFileUtils;

@Controller
public class AjaxUploadController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	@Inject
	BoardService boardService;
	// servlet-context.xml에 선언된 값
	@Resource(name = "uploadPath")
	String uploadPath;

	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
		// views/upload/uploadAjax.jsp로 이동
	}

	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		// 파일 정보를 로그에 출력
		logger.info("originalName:" + file.getOriginalFilename());
		logger.info("size:" + file.getSize());
		logger.info("contentType:" + file.getContentType());
		// new ResponseEntity(데이터, 상태코드)
		// new ResponseEntity(업로드된 파일이름, 상태코드)
		return new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping("/upload/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null; // 스트림
		ResponseEntity<byte[]> entity = null;
		try {

			// 파일의 확장자 검사
			// test.jpg a.b.c.d.jpg
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			// 헤더를 생성
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + fileName);
			if (mType != null) { // 이미지 파일인 경우
				headers.setContentType(mType);
			} else { // 이미지 파일이 아닌 경우
				fileName = fileName.substring(fileName.indexOf("_") + 1); // uuid를 제외한 파일 이름
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 컨텐트 타입
				// 파일이름에 한글이 들어간 경우
				// 스트링.getBytes("언어셋") 스트링을 바이트배열로 변환
				// new String( 바이트배열, "언어셋") 문자열의 인코딩변경
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");// 첨부파일정보
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if (in != null)
				in.close();
		}
		return entity;
	}

	@ResponseBody
	@RequestMapping(value = "/upload/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		System.out.println("deleteFile1="+fileName);
		// fileName에는 이미지 파일의 경우 썸네일 파일 이름이 넘어옴
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);// 확장자 검사
		MediaType mType = MediaUtils.getMediaType(formatName);
		if (mType != null) { // 이미지 파일의 경우 원본 파일 삭제
			// \2017\07\19\s_d451d6e9-5049-4495-8caa-38a4ab386825_winter3.png
			// \2017\07\19\d451d6e9-5049-4495-8caa-38a4ab386825_winter3.png
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();// 썸네일삭제
		}
		// 이미지 파일의 경우 썸네일이 삭제됨
		System.out.println("deleteFile2="+fileName);
		boardService.deleteFile(fileName);
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();// 파일삭제
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
