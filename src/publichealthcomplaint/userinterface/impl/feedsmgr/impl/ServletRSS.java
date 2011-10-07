package publichealthcomplaint.userinterface.impl.feedsmgr.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.userinterface.impl.feedsmgr.spec.req.IRSSFeedsMgt;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

public class ServletRSS extends HttpServlet {





	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();

	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		IRSSFeedsMgt rssFeeds = null;
		File rssFile = rssFeeds.getRSSFile();

		if( rssFile == null ){
			System.out.println("[HomeServlet:doGet()] rssFile is null");
			throw new FileNotFoundException();
		}
		else{
			if( !rssFile.exists())
				System.out.println("[HomeServlet:doGet()] rssFile does not exist");
			else{

				XmlReader xmlReader = new XmlReader(rssFile); 
				SyndFeedInput syndFeedInput = new SyndFeedInput(); 
				SyndFeed feed;
				Writer writer = null;
				try {
					feed = syndFeedInput.build(xmlReader);
					writer = resp.getWriter();
					resp.setContentType("application/xml; charset=UTF-8");
					SyndFeedOutput output = new SyndFeedOutput();
					output.output(feed, writer);
				} catch (IllegalArgumentException e1) {

					e1.printStackTrace();
				} catch (FeedException e1) {

					e1.printStackTrace();
				}

				writer.flush();
				writer.close();
			}
		}
	}

}
