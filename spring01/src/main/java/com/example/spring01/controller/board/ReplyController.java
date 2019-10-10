package com.example.spring01.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.board.dto.ReplyDTO;
import com.example.spring01.service.board.Pager;
import com.example.spring01.service.board.ReplyService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	@Inject
	ReplyService replyService;

	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyDTO dto, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		dto.setReplyer(userid);
		replyService.create(dto);
	}

	@RequestMapping(value = "/delete/{rno}")
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) {
		ResponseEntity<String> entity = null;
		try {
			replyService.delete(rno);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ResponseEntity : 데이터+http status code
	// @ResponseBody : 객체를 json으로
	// @RequestBody : json을 객체로
	@RequestMapping(value = "insert_rest.do", method = RequestMethod.POST)
	public ResponseEntity<String> insert_rest(@RequestBody ReplyDTO dto, HttpSession session) {
		ResponseEntity<String> entity = null;
		try {
			String userid = (String) session.getAttribute("userid");
			// String userid="kim";
			dto.setReplyer(userid);
			replyService.create(dto);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// /reply/list/1 => 1번 게시물의 댓글 목록 리턴
	// /reply/list/2 => 2번 게시물의 댓글 목록 리턴
	// @PathVariable : url에 입력될 변수값 지정
	// http://localhost:9000/spring01/reply/list/1/1
	// 1번 게시물의 1페이지
	// http://localhost:9000/spring01/reply/list/1/2
	// 1번 게시물의 2페이지
	@RequestMapping(value = "/detail/{rno}", method = RequestMethod.GET)
	public ModelAndView reply_detail(@PathVariable("rno") int rno, ModelAndView mav) {
		ReplyDTO dto = replyService.detail(rno);
		mav.setViewName("board/reply_detail");
		mav.addObject("dto", dto);
		return mav;
	}

	@RequestMapping(value = "/list.do/{bno}/{curPage}", method = RequestMethod.GET)
	public ModelAndView reply_list(@PathVariable("bno") int bno, @PathVariable int curPage, ModelAndView mav,
			HttpSession session) {
		int count = replyService.count(bno); // 댓글 갯수
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<ReplyDTO> list = replyService.list(bno, start, end, session);
		// 뷰의 이름 지정
		// WEB-INF/views/board/reply_list.jsp
		mav.setViewName("board/reply_list");
		// 뷰에 전달할 데이터 저장
		mav.addObject("list", list);
		mav.addObject("pager", pager);
		// reply_list.jsp로 포워딩
		return mav;
	}

	// list.do?bno=100
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno, @RequestParam(defaultValue = "1") int curPage, ModelAndView mav,
			HttpSession session) {
		int count = replyService.count(bno); // 댓글 갯수
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<ReplyDTO> list = replyService.list(bno, start, end, session);
		// 뷰의 이름 지정
		// WEB-INF/views/board/reply_list.jsp
		mav.setViewName("board/reply_list");
		// 뷰에 전달할 데이터 저장
		mav.addObject("list", list);
		mav.addObject("pager", pager);
		// reply_list.jsp로 포워딩
		return mav;
	}

	@RequestMapping("update/{rno}")
	@ResponseBody
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @ModelAttribute ReplyDTO dto) {
		System.out.println("update/"+rno);
		
		ResponseEntity<String> entity = null;
		try {
			dto.setRno(rno);
			// 서비스 호출
			replyService.update(dto);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
//	@RequestMapping(value = "update/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
//	@ResponseBody
//	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyDTO dto) {
//		ResponseEntity<String> entity = null;
//		try {
//			dto.setRno(rno);
//			// 서비스 호출
//			replyService.update(dto);
//			entity = new ResponseEntity<String>("success", HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//		return entity;
//	}

	// @Controller : return => view(화면)
	// @RestController : return => 데이터
	// @ResponseBody : 리턴 데이터를 json으로 변환(생략가능)
	@RequestMapping("list_json.do")
	public @ResponseBody List<ReplyDTO> list_json(@RequestParam(defaultValue = "1") int curPage, @RequestParam int bno,
			HttpSession session) {
		int count = 10; // 댓글 갯수
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<ReplyDTO> list = replyService.list(bno, start, end, session);
		return list;
	}
}