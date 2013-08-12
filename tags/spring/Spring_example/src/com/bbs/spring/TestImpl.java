package com.bbs.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestImpl {
	@RequestMapping("/test.hs")
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("add","목이 아파요");
		System.out.println(mav.getViewName());

	    //bbsServlet-servlet.xml의 viewResolver bean의
		//prefix, suffix에 의해 /"인자값".jsp 가 이름값으로 지정된다.
		mav.setViewName("test");

	     //반환해주고 나면 view-name 에 해당하는 파일이 실행된다.
		return mav;
	}
}

/* 주석 부분에 대해 이해하기.
@Controller
public class TestImpl {
	@RequestMapping("/vv.hs")
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("add","목이 아파요");
		System.out.println(mav.getViewName());

		//mav.setViewName("test");
		return mav;
	}
}
*/