package com.example.spring01.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring01.model.board.dao.BoardDAO;
import com.example.spring01.model.board.dto.BoardDTO;
@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAO boardDao;

	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName);

	}

	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}

	@Override
	public void create(BoardDTO dto) throws Exception {
		String title = dto.getTitle();
		String writer = dto.getWriter();
		String content=dto.getContent();
		// replace(A,B) A를 B로 변경
		// 태그 문자 처리( < => &lt; > => &gt; )
		title = title.replace("<","&lt;");
		title = title.replace(">","&gt;");
		writer = writer.replace("<","&lt;");
		writer = writer.replace(">","&gt;");
		content = content.replace("<","&lt;");
		content = content.replace(">","&gt;");
		// 공백 문자 처리( 공백 => &nbsp; )
		title = title.replace(" ","&nbsp;&nbsp;");
		writer = writer.replace(" ","&nbsp;&nbsp;");
		// 줄바꿈 문자 처리 ( \n => <br> )
		content = content.replace("\n","<br>");
		dto.setContent(content);
		dto.setTitle(title);
		dto.setWriter(writer);
		//게시물 등록
		boardDao.create(dto);
		//첨부파일 정보 등록
		String[] files=dto.getFiles(); //첨부파일 배열
		//첨부파일이 없으면 종료
		if(files==null) return;
		//첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){
		boardDao.addAttach(name);
		}
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}
	
	//트랜잭션
	@Transactional
	@Override
	public void update(BoardDTO dto) throws Exception {
		boardDao.update(dto);
		//첨부파일 정보 등록
		String[] files=dto.getFiles(); //첨부파일 배열
		//첨부파일이 없으면 종료
		if(files==null) return;
		//첨부파일들의 정보를 tbl_attach 테이블에 insert
		for(String name : files){
		boardDao.updateAttach(name, dto.getBno());
		}
	}

	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);

	}

	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		return boardDao.listAll(start, end, search_option,keyword);
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time=0;
		//세션에 저장된 조회시간 검색
		if(session.getAttribute("update_time_"+bno)
		!= null){
		update_time=(long)session.getAttribute(
		"update_time_"+bno);
		}
		//시스템의 현재 시간
		long current_time=System.currentTimeMillis();
		//일정 시간이 경과 후 조회수 증가 처리
		if(current_time - update_time > 5*1000){
		boardDao.increaseViewcnt(bno);
		session.setAttribute(
		"update_time_"+bno, current_time);
		}
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		return boardDao.countArticle(search_option, keyword);
	}

}
