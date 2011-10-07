package publichealthcomplaint.supportservices.impl.rssfeedsmgr.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import publichealthcomplaint.datatypes.Constants;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

/**
 * Rss Notifier class.
 * 
 */
class RssNotifier implements Serializable {

	
	private int updateFrequency = 5;
	private String rssFormat = "rss_1.0";
	private String rssFeedTitle = "PublicHealthComplaint - Feeds";
	private String username;
	private String password;


	private Timer timer;

	public RssNotifier() {
	}

	SyndFeed createNewFeed(String format, String title, String link, String description){
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType( format );
		feed.setTitle( title );
		feed.setLink( link );
		feed.setDescription( description );
		return feed;
	}
	
	void updateFeeds(String author, String title, Date publishDate, String description) throws IOException, FeedException {
		
		SyndFeed feed = null;
		
				
		File feedSource = new File(getRssFeedFilename());
		if (feedSource.exists()) {
			
			SyndFeedInput input = new SyndFeedInput();
			feed = input.build(new XmlReader(feedSource));
		} else {
			feed = createNewFeed(getRssFormat(), getRssFeedTitle(), getServerUrl(), "Updating complaint situation");
			
		}

		List entries = feed.getEntries();
		if (entries == null) {
			entries = new ArrayList();
		}
	
		
		SyndEntry entry = createEntry(author, title, publishDate, Constants.RSSLink , description);

		
		entries.add(entry);
		feed.setEntries(entries);
	
		Writer writer = null;
		
		writer = new FileWriter( feedSource );
		
		
		SyndFeedOutput output = new SyndFeedOutput();
	
		output.output(feed, writer);
	
		writer.close();



	}

	private SyndEntry createEntry(String author, String title, Date publishDate, String link, String description){
		SyndEntry entry = new SyndEntryImpl();
		
		entry.setAuthor( author );
		
		entry.setTitle( title );
		
		//setting date
		Date date = new Date();
		entry.setPublishedDate( date );
		
		//setting link
		String dateStr = date.toString();
		dateStr = dateStr.replace(" ", "");
		dateStr = dateStr.replace(":", "");
		String newLink = new String( getServerUrl() + dateStr ); System.out.println("[RssNotifier:updateLatestBuildResults()]newlink="+newLink);
		entry.setLink( newLink );
		
		//setting description
		SyndContent syndCont = new SyndContentImpl();
		syndCont.setType("text/html");
		syndCont.setValue(description);
		entry.setDescription( syndCont );
		
		return entry;
	}
	
	
	String getRssFeedFilename() {
		return Constants.RSSRelativePath;
	}



	private int getUpdateFrequency() {
		return updateFrequency;
	}

	private void setUpdateFrequency(int updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	private String getServerUrl() {
		return Constants.RSSLink;
	}

		

	private String getRssFormat() {
		return rssFormat;
	}

	private void setRssFormat(String rssFormat) {
		this.rssFormat = rssFormat;
	}

	private String getRssFeedTitle() {
		return rssFeedTitle;
	}

	private void setRssFeedTitle(String rssFeedTitle) {
		this.rssFeedTitle = rssFeedTitle;
	}

	private String getUsername() {
		return username;
	}
	private void setUsername(String username) {
		this.username = username;
	}
	private String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	

}
