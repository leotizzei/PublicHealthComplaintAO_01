
package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IDrugComplaintDt;
import publichealthcomplaint.datatypes.IDrugDataDt;
import publichealthcomplaint.datatypes.ISuspectMedicalDeviceDt;
import publichealthcomplaint.datatypes.ISuspectProductDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUtil;







public class ServletInsertDrugComplaint extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[ServletInsertDrugComplaint:doPost()]");
		PrintWriter out;
		IComplaintDt queixa;

		response.setContentType("text/html");

		out = response.getWriter();

		IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		try {
			//captcha
			HttpSession session = request.getSession();
			String captcha = (String) session.getAttribute("captcha");
			String captchaCode = (String) request.getParameter("captchacode");

			//check captcha code
			if( ! captchaCode.equals(captcha)) {
				out.println("<html><head></head><body><center><h1>Sorry, you have typed the wrong captcha code. Please try again.</h1></center></body></html>");
			}
			else{

				FacadeGeneralComplaint generalComplaintMgt = new FacadeGeneralComplaint();

				IDrugComplaintDt drugComplaint = new DrugComplaint();

				// common to all complaints
				generalComplaintMgt.readGeneralComplaintData(request, drugComplaint);

				IDrugDataDt drugData = getDrugDataValues(request);
				ISuspectProductDt suspectProduct = getSuspectProductValues(request);
				ISuspectMedicalDeviceDt suspectMedicalDeviceDt = getSuspectMedicalDeviceValues(request);

				drugComplaint.setDrugData(drugData);
				drugComplaint.setSuspectMedicalDevice(suspectMedicalDeviceDt);
				drugComplaint.setSuspectProduct(suspectProduct);
				IComplaintMgt complaint = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
				int codigo = complaint.insertComplaint(drugComplaint);

				out.println(htmlPageMgt.htmlPage("Complaint inserted", 
						"<p> <h2> Drug Complaint inserted</h2> </p>" +
								"<p> <h2> Save the complaint number: " + codigo + "</h2> </p>")); 
			}

		} catch (InvalidDateException e) {
			e.printStackTrace();
		} catch (ObjectAlreadyInsertedException e) {
			e.printStackTrace();
		} catch (ObjectNotValidException e) {
			e.printStackTrace();
		}finally {
			out.println(htmlPageMgt.close());
			out.close();
		}
	}

	private IDrugDataDt getDrugDataValues(HttpServletRequest request) throws InvalidDateException{
		//obtaining drug data
		String[] listOfProblems = request.getParameterValues("typeProblem"); 
		String[] outcomes = request.getParameterValues("outcomes");
		String dateOfEventStr = request.getParameter("dateOfEvent");
		String eventDescription = request.getParameter("eventDescription");
		String tests = request.getParameter("tests");
		String history = request.getParameter("history");
		String available = request.getParameter("available");
		String[] reported = request.getParameterValues("reported");

		//creating drug data object
		IDrugDataDt drugData = new DrugData();
		drugData.setTypeOfProblems(listOfProblems);
		drugData.setOutcomes(outcomes);
		IDateDt date = this.convertStringToDate( dateOfEventStr );
		drugData.setDateOfEvent( date );
		drugData.setEventDescription(eventDescription);
		drugData.setTests(tests);
		drugData.setHistory(history);
		drugData.setAvailable(available);
		drugData.setAlsoReported(reported);

		return drugData;
	}

	private ISuspectProductDt getSuspectProductValues(HttpServletRequest request) throws InvalidDateException {
		ISuspectProductDt suspectProduct = new SuspectProduct();
		suspectProduct.setProductName(request.getParameter("productName"));
		suspectProduct.setLabelStrength(request.getParameter("labelStrength"));
		suspectProduct.setManufacturer(request.getParameter("manufacturer"));
		suspectProduct.setDose(request.getParameter("dose"));
		suspectProduct.setFrequency(request.getParameter("frequency"));
		suspectProduct.setRoute(request.getParameter("route"));
		suspectProduct.setStartUseDate(this.convertStringToDate(request.getParameter("startDate")));
		suspectProduct.setEndUseDate(this.convertStringToDate(request.getParameter("endDate")));
		suspectProduct.setDiagnosis(request.getParameter("diagnosis"));
		suspectProduct.setEventAbated(request.getParameter("eventAbated"));
		suspectProduct.setLot(request.getParameter("lot"));
		suspectProduct.setExpirationDate(this.convertStringToDate(request.getParameter("expirationDate")));
		suspectProduct.setEventReappeared(request.getParameter("eventReappeared"));
		suspectProduct.setId(request.getParameter("uniqueID"));
		return suspectProduct;
	}

	private ISuspectMedicalDeviceDt getSuspectMedicalDeviceValues(HttpServletRequest request) throws InvalidDateException{
		ISuspectMedicalDeviceDt suspectMedDevice = new SuspectMedicalDevice();
		suspectMedDevice.setBrandName(request.getParameter("brandName"));
		suspectMedDevice.setDeviceName(request.getParameter("commonDeviceName"));
		suspectMedDevice.setManufacturer(request.getParameter("deviceManufacturer"));
		suspectMedDevice.setManufacturerCity(request.getParameter("cityManufacture"));
		suspectMedDevice.setManufacturerState(request.getParameter("stateManufacture"));
		suspectMedDevice.setModel(request.getParameter("model"));
		suspectMedDevice.setCatalog(request.getParameter("catalog"));
		suspectMedDevice.setSerial(request.getParameter("serial"));
		suspectMedDevice.setLot(request.getParameter("lotDevice"));
		suspectMedDevice.setExpirationDate(this.convertStringToDate(request.getParameter("deviceExpDate")));
		suspectMedDevice.setOtherNumber(request.getParameter("otherDeviceNumber"));
		suspectMedDevice.setDeviceOperator(request.getParameter("operatorDevice"));
		suspectMedDevice.setImplantedDate(this.convertStringToDate(request.getParameter("implantedDate")));
		suspectMedDevice.setExplantedDate(this.convertStringToDate(request.getParameter("explantedDate")));
		suspectMedDevice.setWasReused(this.convertStringToBoolean(request.getParameter("reusedDevice")));
		suspectMedDevice.setExtraInfo(request.getParameter("reusedDescription"));
		return suspectMedDevice;
	}

	private IDateDt convertStringToDate(String dateStr) throws InvalidDateException{
		if( dateStr != null ){
			if( dateStr.contains("/")){
				String[] dates = dateStr.split("/");
				IManager mgr = ComponentFactory.createInstance();
				IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");
				IDateDt date = util.createDate(0, 0, 0, Integer.parseInt( dates[0] ), Integer.parseInt( dates[1] ), Integer.parseInt( dates[2] ));
				return date;
			}
			return null;
		}
		else
			throw new InvalidDateException("Null argument");

	}

	private boolean convertStringToBoolean(String text){
		if( (text != null) && (text.equalsIgnoreCase("Yes")) )
			return true;
		else	
			return false;

	}

}