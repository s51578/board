package com.sale.korea.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale.korea.model.BoardModel;
import com.sale.korea.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired(required=true)
    private BoardRepository boardRepository;
	
	@Transactional
	public List<BoardModel> getBoardAll() {
		
		return boardRepository.findAllByOrderByDateDesc();
	}
	
	@Transactional
	public BoardModel getBoardModelByNo(Long no) {
		return boardRepository.findBoardModelBynumber(no);
	}
	
	@Transactional
	public BoardModel _reg_Board(BoardModel boardModel) {
		
		LocalDateTime now = LocalDateTime.now();
		
		boardModel.setDate(now);
		
		return boardRepository.save(boardModel);
	}
	@Transactional
	public BoardModel _mod_Board(BoardModel boardModel) {
		LocalDateTime now = LocalDateTime.now();
		
		boardModel.setDate(now);
		return boardRepository.save(boardModel);
	}
	
	@Transactional
	public void _del_Board(Long no) {
		boardRepository.deleteById(no);
	}

}
