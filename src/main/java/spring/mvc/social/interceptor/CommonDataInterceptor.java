package spring.mvc.social.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonDataInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Twitter twitter;
	@Autowired
	private ConnectionRepository connectionRepository;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (connectionRepository.findPrimaryConnection(Twitter.class) != null) {
			Trends tt = twitter.searchOperations().getLocalTrends(Long.valueOf(23424969));
			// tt.getTrends().get(0).getName()
			request.setAttribute("randomTweets", tt.getTrends());

		}
	}


}
