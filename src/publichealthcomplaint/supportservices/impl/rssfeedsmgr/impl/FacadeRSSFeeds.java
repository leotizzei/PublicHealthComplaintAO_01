package publichealthcomplaint.supportservices.impl.rssfeedsmgr.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.sun.syndication.io.FeedException;

import publichealthcomplaint.datatypes.IAnimalComplaintDt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IDrugComplaintDt;
import publichealthcomplaint.datatypes.IFoodComplaintDt;
import publichealthcomplaint.datatypes.IScheduleDt;
import publichealthcomplaint.datatypes.ISpecialComplaintDt;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov.IRSSFeedsMgt;

class FacadeRSSFeeds implements IRSSFeedsMgt {


	public File getRSSFile() throws FileNotFoundException {
		RSSPublisher pub = new RSSPublisher();
		return pub.getRSSFile();
	}


	public void updateFeeds(IComplaintDt complaint) throws IOException, FeedException {
		RssNotifier notifier = new RssNotifier();
		String author = complaint.getAtendente().getName();
		String title = getTypeOfComplaint(complaint) + " complaint update";
		String description = "[Complaint: "+complaint.getDescricao()+"] [Solution: "+complaint.getObservacao()+"]";
		IDateDt datedt = complaint.getDataParecer();
		Date publishDate = convertIDateDtToDate(datedt);
		
		notifier.updateFeeds(author, title, publishDate, description);

	}

	private Date convertIDateDtToDate(IDateDt datedt){
		Date date = new Date();
		if( datedt != null ){
			date.setMonth(datedt.getMes());
			date.setYear(datedt.getAno());
			date.setDate(datedt.getDia());

			IScheduleDt schedule = datedt.getHorario();
			if( schedule != null ){
				date.setHours(schedule.getHora());
				date.setMinutes(schedule.getMinuto());
			}
		}
		return date;
	}

	private String getTypeOfComplaint(IComplaintDt complaintDt){

		if( complaintDt instanceof IAnimalComplaintDt)
			return "Animal";
		if( complaintDt instanceof IDrugComplaintDt)
			return "Drug";
		if( complaintDt instanceof IFoodComplaintDt)
			return "Food";
		if( complaintDt instanceof ISpecialComplaintDt)
			return "Special";
		else
			return "Non-identified type of";


	}

}
