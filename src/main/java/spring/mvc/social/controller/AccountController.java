package spring.mvc.social.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.mvc.social.domain.Account;
import spring.mvc.social.domain.TweetSearchCriteria;

@Controller
public class AccountController {

	public static final String AUTHENTICATED_ACCOUNT_KEY = "authenticatedAccount";
	
	@Autowired
	private  Twitter twitter;
	@Autowired
	private ConnectionRepository connectionRepository;
	/*
	public AccountController(Twitter twitter, ConnectionRepository connectionRepository) {
		this.twitter = twitter;
		this.connectionRepository = connectionRepository;
	}*/
	
	@ModelAttribute
	public TweetSearchCriteria criteria(){
		return new TweetSearchCriteria();
	}
	
	@RequestMapping("myprofile")
	public String myProfile(Model model){
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}
		TwitterProfile profile=twitter.userOperations().getUserProfile();
		List<Tweet> tweets = twitter.timelineOperations().getUserTimeline();
		model.addAttribute("userprofile",profile);
		model.addAttribute("tweets",tweets);
		return "profile/myprofile";
	}
	
	@RequestMapping("friendlist")
	public String friendList(Model model){
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}
		CursoredList<TwitterProfile> friendList=twitter.friendOperations().getFriends();
		model.addAttribute("friendLists",friendList);
		return "profile/friendList";
	}
	
	
	@RequestMapping("loginSucess")
	public String loginSuccess(HttpSession session,Model model) {
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}
		Account account = new Account();
		account.setFirstName(twitter.userOperations().getScreenName().toString());
		session.setAttribute(AUTHENTICATED_ACCOUNT_KEY, account);
		
		
		SearchResults searchResults = twitter.searchOperations().search("spring.io", 10);
		List<Tweet> tweets = searchResults.getTweets();
		model.addAttribute("tweets", tweets);
		return "main";

	}
	@RequestMapping("sendTweet")
	public String sendTweet(Model model,@RequestParam(value = "content") String content){
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}
		twitter.timelineOperations().updateStatus(content);
		TwitterProfile profile=twitter.userOperations().getUserProfile();
		List<Tweet> tweets = twitter.timelineOperations().getUserTimeline();
		model.addAttribute("userprofile",profile);
		model.addAttribute("tweets",tweets);
		return "profile/myprofile";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
