package publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.sun.syndication.io.FeedException;

import publichealthcomplaint.datatypes.IComplaintDt;

public interface IRSSFeedsMgt {
	
	public void updateFeeds(IComplaintDt complaint) throws IOException, FeedException;
	
	public File getRSSFile() throws FileNotFoundException;

}
