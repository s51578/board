package com.sale.korea.start;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.korea.model.BoardModel;
import com.sale.korea.service.BoardService;

@Controller
public class Start {
	
	@Autowired
    private BoardService boardService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String boardList(Model model) {
		
		List<BoardModel> BoardLists = boardService.getBoardAll();
		
		model.addAttribute("BoardLists", BoardLists);
		
		return "index";
	}
	
	@RequestMapping(value="board/detail", method=RequestMethod.GET)
	public String boardDetail(Model model,@RequestParam(name = "no") Long no) {
		
		BoardModel BoardDetail = boardService.getBoardModelByNo(no);
		
		model.addAttribute("BoardDetail", BoardDetail);
		
		return "board_detail";
	}
	@RequestMapping(value="board/create", method=RequestMethod.GET)
	public String boardCreate() {
		return "board_create";
	}
	
	@RequestMapping(value="/reg", method=RequestMethod.POST)
    public String boardReg(Model model, HttpServletRequest request,
    		@ModelAttribute("boardform") BoardModel boardModel) {
		
		BoardModel bm = boardService._reg_Board(boardModel);
		
		return "redirect:" ;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
    public String update(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		BoardModel BoardDetail = boardService.getBoardModelByNo(no);
		
		model.addAttribute("BoardDetail", BoardDetail);
		
		return "board_update";
	}
	
	@RequestMapping(value="/mod", method=RequestMethod.POST)
    public String mod(Model model, HttpServletRequest request,
    		@ModelAttribute("boardUpdateform") BoardModel boardModel
    		) {
		
		BoardModel cr = null;
		
		cr = boardService._mod_Board(boardModel);
		
		return "redirect:" + "?no=" + cr.getNumber();
		
	}
	@RequestMapping(value="/del", method=RequestMethod.GET)
    public String subdelete(Model model, HttpServletRequest request,
    		@RequestParam(name = "no") Long no) {
		
		try {
			
			boardService._del_Board(no);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return "redirect:";
	}
	
	  
}
