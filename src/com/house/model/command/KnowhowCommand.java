package com.house.model.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.house.common.Paging;
import com.house.model.dao.KnowhowDAO;
import com.house.model.vo.KnowhowVO;

public class KnowhowCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		request.setAttribute("category", category);
		
		List<String> menu = Arrays.asList("시공정보","수납","꾸미기팁","청소","DIY&리폼","이거어때","생활정보","건축&주택","상업공간","지식백과");
		request.setAttribute("menu", menu);

		// 페이징 처리
		Paging p = new Paging();
		
		List<KnowhowVO> list = null;
		if(category == null) {
			list = KnowhowDAO.showKnowhow();
		} else {
			list = KnowhowDAO.showKnowhow(category);
		}
		request.setAttribute("list", list);
		
		return "/WEB-INF/community/knowhow/knowhow.jsp";
	}

}
