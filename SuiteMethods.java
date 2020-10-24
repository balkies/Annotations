package annotations;


import java.beans.PropertyVetoException;
import java.io.IOException;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlSuite;

import io.appium.java_client.remote.MobileCapabilityType;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.CaratFunctions;
import wrapper.Dashboard;
import wrapper.PreRequirement;


public class SuiteMethods extends CaratFunctions {


	protected String excelName="";
	protected String dataSheetName="";
	protected static String testCaseName;
	protected static String testDescription;
	protected static String deviceVersion;

	PreRequirement pre=new PreRequirement();
	@BeforeSuite
	public void beforeSuite(ITestContext ct) throws IOException, InterruptedException, PropertyVetoException{
		//deviceName=configProp("DeviceName");
		
		readDashboard(ct);
//		readDevice();
	//	startAppium();
		Reporter.startResult();
		//	pre.startAppiumServer();
	}

	@BeforeTest
	public void beforeTest(){
	}

	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(){
		Reporter.startTestCase(testCaseName,testDescription);
		if(OS().equals("WINDOWS")){
			pre.startAdbDevices();
		}
		//	wait(5);
		//pre.startAppiumServer();
		launchApp();
		deviceVersion=driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION).toString();
		//	closePermissionPopup(); "Added By Rajaveni: Permission feature has been removed as per Google Policy hence commented this line of code"

		//	wait(10);

	}


	@AfterSuite
	public void afterSuite(){

	}

	@AfterTest
	public void afterTest(){

	}

	@AfterClass
	public void afterClass(){
		//quitBrowser();
	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod(){

		//new PreRequirement().killAppiumServer();
		
		Reporter.endResult();
		closeApp();
	}

	@DataProvider(name="fetchData")
	public Object[][] getData(){
		return DataInputProvider.getSheet(excelName,dataSheetName) ;		
	}
}
