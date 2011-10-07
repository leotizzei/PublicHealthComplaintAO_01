package publichealthcomplaint.supportservices.impl.rssfeedsmgr.impl;

import java.io.File;
import java.io.FileNotFoundException;



class RSSPublisher {
	
	public File getRSSFile() throws FileNotFoundException{
		
		RssNotifier rssNotifier = new RssNotifier();
		String rssPath = rssNotifier.getRssFeedFilename();
		File rssFile = new File( rssPath );
		
		if( rssFile == null ){
			System.out.println("[RSSPublisher:getRSSFile()] file is null");
			throw new FileNotFoundException();
		}
		else{
			if( !rssFile.exists() ){
				System.out.println("[RSSPublisher:getRSSFile()] file "+rssFile.getAbsolutePath()+" does not exist");
				return null;
			}
			else{
				return rssFile;
			}
		}
	
	}

}
