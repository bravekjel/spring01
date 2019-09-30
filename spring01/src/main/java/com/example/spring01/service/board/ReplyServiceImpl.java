package com.example.spring01.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring01.model.board.dao.ReplyDAO;
import com.example.spring01.model.board.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	ReplyDAO replyDao;

	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session) {
		List<ReplyDTO> items = replyDao.list(bno, start, end);
		// 현재 사용자
		String userid = (String) session.getAttribute("userid");
		for (ReplyDTO dto : items) {
			if (dto.getSecret_reply().equals("y")) {
				if (userid == null) { // 비로그인 상태
					dto.setReplytext("비밀댓글입니다.");
				} else { // 로그인 상태
					// 게시물 작성자
					String writer = dto.getWriter();
					// 댓글 작성자
					String replyer = dto.getReplyer();
					if (!userid.equals(writer) && !userid.equals(replyer)) {
						dto.setReplytext("비밀댓글입니다.");
					}
				}
			}
		}
		return items;
	}

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

	@Override
	public void create(ReplyDTO dto) {
		replyDao.create(dto);

	}

	@Override
	public void update(ReplyDTO dto) {
		replyDao.update(dto);

	}

	@Override
	public void delete(Integer rno) {
		replyDao.delete(rno);

	}

	@Override
	public ReplyDTO detail(int rno) {
		return replyDao.detail(rno);
	}

}
