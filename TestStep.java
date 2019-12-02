package framework.interface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import framework.configuration.TestStepConfiguration;
import framework.result.TestStepResult;
import framework.result.TestStepType;
import library.Action;
import library.utility.Utility;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description : Test Step Super Class
 * 
 * @author jjimenez
 * @since december 12th, 2019
 */
public abstract class TestStep extends RationalTestScript {
	
	private String name;
	private Integer stepNumber;
	private TestStepConfiguration configuration;
	private List<TestStepResult> results;
	private List<String> descriptions;
	private TestStepType type;
	private Boolean succeed;
	private Boolean takescreenshot = true;
	private Date endDate;
	private Date startDate;
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public void onInitialize() {	
		if(getResults() == null){
			setResults(new ArrayList<TestStepResult>());
			startDate = new Date();
		}
	}
	
	@Override
	public void onTerminate() {
		logResult();
		setEndDate(new Date());
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().getSimpleName().equals(obj.getClass().getSimpleName());
	}	
	
	public TestStepConfiguration getConfiguration() {
		return configuration;
	}
	
	public TestStep withConfiguration(TestStepConfiguration configuration){
		this.configuration = configuration;
		return this;
	}
	
	public void addDescription(String description){
		
		System.out.println(description);
		
		if(getDescriptions() == null){
			setDescriptions(new LinkedList<String>());
		}
		
		getDescriptions().add(description);
	}

	public void addResult(TestStepResult result){
		if(getResults() == null){
			setResults(new LinkedList<TestStepResult>());
		}
		
		getResults().add(result);
	}
	
	public void logResult(){
		addResult(new TestStepResult.ResultBuilder()
		.forStep(this)
		.build());		
	}	

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public void setConfiguration(TestStepConfiguration configuration) {
		this.configuration = configuration;
	}

	public List<TestStepResult> getResults() {
		return results;
	}

	public void setResults(List<TestStepResult> results) {
		this.results = results;
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}

	public TestStepType getType() {
		return type;
	}

	public void setType(TestStepType type) {
		this.type = type;
	}

	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public String getName() {
		
		if(name == null){
			return this.getClass().getSimpleName();
		}
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(BufferedImage screenshot) {
		sleep(1);
				
		this.screenshot = takescreenshot?doScreenShot(screenshot):null;
	}
	
	private String screenshot;	
	
	public String doScreenShot(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh_mm_ss");
		String today = String.valueOf(sdf.format(new Date()));	
		String path="";
		
		try {	
			path="C:\\FUNCTIONAL-TESTER\\psc\\SC-"+today+".png";		
						
			ImageIO.write(Action.takeScreenShot(), "png", new File(path));
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
				
		return path;		
	}	
	
	public String doScreenShot(BufferedImage screenshot){

		String path="";
		
		try {	
			path="C:\\FUNCTIONAL-TESTER\\psc\\SC-"+Utility.getFormattedActualDate("hh_mm_ss")+".png";		
						
			ImageIO.write(screenshot, "png", new File(path));
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
				
		return path;		
	}
	
	public boolean 	logTestResult(String desc ,  boolean result){	
		setSucceed(result);			
		logTestResult(desc, result);			
		return result;		
	}	
	
	public boolean 	logTestResult(String desc ,  boolean result, String comment){	
		setSucceed(result);			
		logTestResult(desc, result,comment);			
		return result;		
	}	
	
	public void settakeScreenshot(boolean take){
		
		this.takescreenshot = take;
	}
}
