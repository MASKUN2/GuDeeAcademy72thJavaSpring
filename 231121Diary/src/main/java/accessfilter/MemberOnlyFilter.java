package accessfilter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/member/logout", "/member/info"})
public class MemberOnlyFilter implements Filter{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request; // HttpServletRequest는 ServletRequest를 상속한다. 예전엔 http가 없었음. 세션도 나중에 생김
		String requestUrl = httpRequest.getRequestURI();
		log.info("MemberOnlyFilter   >>  url :" +  requestUrl);
		HttpSession session = httpRequest.getSession();
		Object member = session.getAttribute("member");
		String ReferUrl = httpRequest.getHeader("Referer");
		//세션 로그인 여부체크
		if(member == null) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;// 마찬가지. sendredirect가 없음
			httpResponse.sendRedirect(ReferUrl); // 이전페이지로 
			log.info("MemberOnlyFilter   >>  ACCESS DINIED AND sendRedirect :" +  ReferUrl);
			return;
		}
		
		chain.doFilter(request, response); //요청의 흐름을 이어나감
		
	}

}
