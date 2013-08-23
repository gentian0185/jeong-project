package hs.bbs.comment;

import hs.bbs.dao.BBSDaoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CommentImpl {
	@Autowired
	BBSDaoService bbsdaoService;
	
	@RequestMapping("commentRead")
	@ResponseBody
	public List<CommentVO> commRead(@RequestParam int article_num){
		return bbsdaoService.getAllComment(article_num);
	}
	
	// /comment/commentWrite
	@RequestMapping("commentWrite")
	public @ResponseBody List<CommentVO> commWrite(@RequestParam("article_num") int article_num,
			@RequestParam String comment_content){		
		bbsdaoService.writeComment(article_num,comment_content);
		return commRead(article_num); 
		
	}
}


