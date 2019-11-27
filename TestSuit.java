package framework.interfaz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import framework.report.ReportConfiguration;
import framework.report.ReportManager;
import framework.result.TestSuitResult;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description : Test Suit Super Class
 * 
 * @author jjimenez
 * @since november 27th, 2019
 */
public abstract class TestSuit extends RationalTestScript {
	
	private String name;
	private List<TestSuitResult> results;
	private Integer suitNumber;
	private List<TestCase> testCases;
	private boolean takeScreenshot = false;
	

	public void testMain(Object[] args) {
		
		testCases = getCases();
		
		if(testCases == null || testCases.isEmpty()){
			System.err.println("Cases not defined for suit "+this.getClass().getSimpleName());
		}
		
		if(getResults() == null){
			setResults(new ArrayList<TestSuitResult>());
		}
		
		TestSuitResult result = new TestSuitResult(this, suitNumber);		
		result.setIterationCount(getCurrentScriptIterationCount() + 1);
		getResults().add(result);
		
		for (TestCase testCase : testCases) {
			
			testCase.setCaseNumber(getCases().indexOf(testCase) + 1);
			testCase.setScreenshot(takeScreenshot);
			
			result.getCases().add(testCase);			
			
			if(testCase.getConfiguration() != null){
				callScript(testCase, args, testCase.getConfiguration().getIterationCount());					
			}else{				
				callScript(testCase);				
			}
		}
		
		result.setEndDate(new Date());				
		
		if(generateFrameworkReport()){
			//Generate report
		}
		
		generateCustomReport();
		
	}	
	
	protected boolean generateFrameworkReport(){
		return false;
	}
	
	/**
	 * Hook in case a custom report is required
	 * @param args
	 */
	protected void generateCustomReport(){
		
	}

	public Integer getSuitNumber() {
		return suitNumber;
	}

	public void setSuitNumber(Integer suitNumber) {
		this.suitNumber = suitNumber;
	}

	public List<TestSuitResult> getResults() {
		return results;
	}

	public void setResults(List<TestSuitResult> results) {
		this.results = results;
	}

	public abstract List<TestCase> getCases();	
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().getSimpleName().equals(obj.getClass().getSimpleName());
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
	
	public void setScreenshot(boolean set){
		this.takeScreenshot=set;
	}
	
	public boolean getSetScreenshot(){
		return this.takeScreenshot;
	}
}
