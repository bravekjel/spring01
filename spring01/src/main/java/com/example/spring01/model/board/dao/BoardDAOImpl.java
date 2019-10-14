package com.example.spring01.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.board.dto.BoardDTO;
@Repository // 현재 클래스를 dao bean으로 등록
public class BoardDAOImpl implements BoardDAO {
	@Inject
	SqlSession sqlSession;
	@Override
	public void deleteFile(String fullName) {
		sqlSession.delete("board.deleteAttach",fullName);

	}
	@Override
	public void updateAttach(String fullName, int bno) {
		Map<String,Object> map=new HashMap<>();
		map.put("fullName", fullName);
		map.put("bno", bno);
		sqlSession.insert("board.updateAttach",map);

	}

	@Override
	public void create(BoardDTO dto) throws Exception {
		sqlSession.insert("board.insert", dto);

	}
	
	@Override
	public void addAttach(BoardDTO dto) {
		sqlSession.insert("board.addAttach", dto);

	}
	@Override
	public List<String> getAttach(int bno) {
		return sqlSession.selectList("board.getAttach",bno);
	}
	@Override
	public BoardDTO read(int bno) throws Exception {
		return sqlSession.selectOne("board.view",bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		sqlSession.update("board.updateArticle",dto);

	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.deleteArticle", bno);

	}

	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		Map<String,Object> map=
		new HashMap<String,Object>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		//between #{start} and #{end}에 입력될 값
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("board.listAll",map);
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("board.increaseViewcnt",bno);

	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		Map<String,String> map=
		new HashMap<String,String>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		return sqlSession.selectOne( "board.countArticle", map);
	}

}
