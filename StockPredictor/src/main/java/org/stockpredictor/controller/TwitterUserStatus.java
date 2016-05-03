package org.stockpredictor.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.jar.JarOutputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.OEmbedRequest;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.api.*;
import twitter4j.conf.*;
import twitter4j.json.DataObjectFactory;


@RestController
public class TwitterUserStatus implements Serializable{
	
	ConfigurationBuilder configurationBuilder;
	TwitterFactory twitterFactory;

	public TwitterUserStatus() {
		configurationBuilder = new ConfigurationBuilder();
		configurationBuilder
				.setDebugEnabled(true)
				.setOAuthConsumerKey("EvDxkfP3xzlZp9Ss5HoTxBFu4")
				.setOAuthConsumerSecret(
						"HoBfu1L2MLlozYhY2Gq99FtF3ZQIWyKVl2hjXEbJIUCAHL4sZ8")
				.setOAuthAccessToken(
						"702257299012849669-WTfyYVWAdmUKqRq9zPsWtS5ugMhsjbd")
				.setOAuthAccessTokenSecret(
						"5uLYJnk4pIBoDKaOVC8jrteRc6KAD20jg62Bz7x6B3IuO")
				.setJSONStoreEnabled(true);
		twitterFactory = new TwitterFactory(configurationBuilder.build());

	}
	
	
	@RequestMapping(value = "/v1/api/twittertrend", method = { RequestMethod.GET })
	public List<String> ListStatus() throws TwitterException, JSONException, JsonGenerationException, JsonMappingException, IOException 
	{
		Twitter twitter = twitterFactory.getInstance();
		String users="@Nasdaq, @google";
		String [] userList =users.split(",");
		List<String> twittesList =new ArrayList<String>();
		for (String user : userList) {
			List<Status> statusList = twitter.getUserTimeline (users, new Paging(1,1000) );
			ObjectMapper mapper = new ObjectMapper();
			int len =statusList.size();
			for (Status status : statusList) {
				twittesList.add(status.getCreatedAt()+"--->"+ status.getText());
				System.out.println("@@@@ TWITTER DATA "+status.getCreatedAt()+"--->"+ status.getText());
			}
			
		}
		System.out.println(twittesList.toString());
		return twittesList;
	}

//	public static void main(String[] args) {
//		
//	}
	
		@RequestMapping(value = "/v1/api/twittertrends", method = { RequestMethod.GET })
		public List<String> trends() throws TwitterException, JSONException, JsonGenerationException, JsonMappingException, IOException 
		{
			Twitter twitter = twitterFactory.getInstance();
			String users="@Nasdaq, @google";
			String [] userList =users.split(",");
			List<String> twittesList =new ArrayList<String>();
			for (String user : userList) {
				List<Status> statusList = twitter.getUserTimeline (users, new Paging(1,20) );
				ObjectMapper mapper = new ObjectMapper();
				int len =statusList.size();
				for (Status status : statusList) {
					twittesList.add(status.getCreatedAt()+"--->"+ status.getText());
					System.out.println("@@@@ TWITTER DATA "+status.getCreatedAt()+"--->"+ status.getText());
				}
				
			}
			System.out.println(twittesList.toString());
			return twittesList;
		}
	


}
