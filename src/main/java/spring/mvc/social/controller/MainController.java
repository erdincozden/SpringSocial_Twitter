package spring.mvc.social.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.ehcache.CacheOperationOutcomes.SearchOutcome;
import spring.mvc.social.domain.Account;
import spring.mvc.social.domain.TweetSearchCriteria;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	public static final String LOGIN_FAILED_KEY = "label.login.failed";
	public static final String AUTHENTICATED_ACCOUNT_KEY = "authenticatedAccount";

	@Autowired
	private  Twitter twitter;
	@Autowired
	private ConnectionRepository connectionRepository;

	/*
	public MainController(Twitter twitter, ConnectionRepository connectionRepository) {
		this.twitter = twitter;
		this.connectionRepository = connectionRepository;
	}*/

	
	@ModelAttribute
	public TweetSearchCriteria criteria(){
		return new TweetSearchCriteria();
	}
	
	@RequestMapping("/")
	public String index() {

		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}
		return "main";
	}

	@RequestMapping("login")
	public String connect(HttpSession session) {
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}
		Account account = new Account();
		account.setFirstName(twitter.userOperations().getScreenName().toString());
		session.setAttribute(AUTHENTICATED_ACCOUNT_KEY, account);
		return "main";
	} 

	@RequestMapping(value="index.htm",method=RequestMethod.GET)
	public String main(Model model) {
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}
		
		SearchResults searchResults = twitter.searchOperations().search("spring.io", 10);
		List<Tweet> tweets = searchResults.getTweets();
		model.addAttribute("tweets", tweets);
		return "main";
	}
	@RequestMapping(value="index2",method={RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Tweet> listJson(@RequestBody TweetSearchCriteria criteria){
		
		if(criteria.getTitle()==null) criteria.setTitle("istanbul");
		return this.twitter.searchOperations().search(new SearchParameters(criteria.getTitle())
				.geoCode(new GeoCode(41.009047, 28.954791, 100, GeoCode.Unit.MILE))
				.resultType(SearchParameters.ResultType.RECENT)).getTweets();
	}

	@RequestMapping(value="/twitter/hashtag/{hashtagName}")
	public String hashtagDetail(Model model,@PathVariable(value = "hashtagName") String id){
		SearchResults searchResults=twitter.searchOperations().search(id);
		model.addAttribute("tweets",searchResults.getTweets());		
		return "main";
	}

}











