package com.connect.brick.controller.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.Question;
import com.connect.brick.model.QuestionReply;
import com.connect.brick.service.QuestionService;

//@RequestMapping(value="/master/qna")
@Controller
public class Master_QuestionController {

	@Autowired
	QuestionService questionService;
	
	@RequestMapping(value =RequestMappingConstants._MASTER_QNA_MAIN, method = RequestMethod.GET)
	public String main(Model model, HttpServletRequest request) {

		List<Question> questions = questionService.getQuestionAll();

		model.addAttribute("questions", questions);

		return "views/master/qna/qna_main";
	}

	@RequestMapping(value = RequestMappingConstants._MASTER_QNA_FORM, method = RequestMethod.GET)
	public String form(Model model, HttpServletRequest request) {

		return "views/master/qna/qna_created";
	}

	@RequestMapping(value =RequestMappingConstants._MASTER_QNA_REG, method = RequestMethod.POST)
	public String reg(Model model, HttpServletRequest request,

			@ModelAttribute("qnaform") Question qna) {

		Question result = questionService.regQuestion(qna);

		return "redirect:"+RequestMappingConstants._MASTER_QNA_DETAIL+"?no=" + result.getNo();
	}

	@RequestMapping(value = RequestMappingConstants._MASTER_QNA_DETAIL, method = RequestMethod.GET)
	public String detail(Model model, HttpServletRequest request,

			@RequestParam(name = "no") Long no) {

		Question result = questionService.getQuestion(no);

		model.addAttribute("result", result);

		return "redirect:"+RequestMappingConstants._MASTER_QNA_DETAIL;
	}

	// 문의글 삭제
	@RequestMapping(value =RequestMappingConstants._MASTER_QNA_DEL)
	public String deleteQuestion(@RequestParam(name="no") Long no) {
		
		questionService.deleteQuestion(no);
		
		return "redirect:"+RequestMappingConstants._MASTER_QNA_MAIN;
	}
	
	// 답변작성 폼
	@RequestMapping(value =RequestMappingConstants._MASTER_QNA_REPLY_FORM, method = RequestMethod.GET)
	public String answerCreatedForm(Model model, HttpServletRequest request, @RequestParam(name = "no") Long no) {
		
		Question question = questionService.getQuestion(no);
		
		if(question.getQuestionReply() != null) {
			model.addAttribute("msg", "이미 등록된 답변입니다.");
			return "redirect:"+ RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		}
		
		
		model.addAttribute("question", question);
		
		return "views/master/qna/answer_created";
	}
	
	// 답변작성
	@RequestMapping(value = RequestMappingConstants._MASTER_QNA_REPLY_REG, method = RequestMethod.POST)
	public String answerCreatedReg(Model model, HttpServletRequest request, 
			@ModelAttribute("answerForm") QuestionReply reply, @RequestParam(name="questionNo") Long no) {
		
		Question question = questionService.getQuestion(no);
		
		if(question.getQuestionReply() != null) {
			model.addAttribute("msg", "이미 등록된 답변입니다.");
			return "redirect:"+ RequestMappingConstants._ERROR_LOAD + HttpStatus.BAD_REQUEST.value();
		}
		
		reply.setQuestion(question);
		
		QuestionReply answer = questionService.regAnswer(reply);
		
		return "redirect:"+RequestMappingConstants._MASTER_QNA_DETAIL+"?no=" + question.getNo();
	}
	
	// 답변수정 폼
	@RequestMapping(value = RequestMappingConstants._MASTER_QNA_REPLY_UPDATE, method = RequestMethod.GET)
	public String answerUpdateForm(Model model, HttpServletRequest request, @RequestParam(name="no") Long no) {
		
		Question question = questionService.getQuestion(no);
		
		model.addAttribute("question", question);
		
		return "views/master/qna/answer_update";
	}

	// 답변 수정
	@RequestMapping(value = RequestMappingConstants._MASTER_QNA_REPLY_MOD, method = RequestMethod.POST)
	public String answerUpdateReg(Model model, HttpServletRequest request, @ModelAttribute("answerUpdateForm") QuestionReply reply, @RequestParam(name="questionNo") Long no) {

		Question question = questionService.getQuestion(no); 
		
		reply.setQuestion(question);
		reply.setReplyDate(question.getQuestionReply().getReplyDate());
		
		QuestionReply updateReply = questionService.modReply(reply);
		
		return "redirect:"+RequestMappingConstants._MASTER_QNA_DETAIL+"?no=" + question.getNo();
	}
	
}