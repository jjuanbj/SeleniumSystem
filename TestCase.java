package framework.interface;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import framework.configuration.TestCaseConfiguration;
import framework.result.TestCaseResult;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description : Test Case Super Class
 * 
 * @author jjimenez
 * @since december 03th, 2019
 */
public abstract class TestCase extends RationalTestScript {

	private String name;
	private TestCaseConfiguration configuration;
	private List<TestCaseResult> results;
	private Integer caseNumber;
	private List<TestStep>steps = new LinkedList<>();
	private boolean takeScreenshot=false;

	private Object[] args;

	public void testMain(Object[] args) {

		this.args = args;
		steps = getSteps();
		
		if (steps == null || steps.isEmpty()) {
			System.err.println("Steps not defined for case "
					+ this.getClass().getSimpleName());
			return;
		}

		if (getResults() == null) {
			setResults(new ArrayList<TestCaseResult>());
		}
		int currentDatapoolRow = 0;
		
		dpReset();

		while (!dpDone()) {
			
			TestCaseResult result = new TestCaseResult(this);
			result.setIterationCount(getCurrentScriptIterationCount() + 1);
			getResults().add(result);

			for (TestStep testStep : steps) {

				result.getSteps().add(testStep);

				try {
					testStep.setStepNumber(steps.indexOf(testStep) + 1);

					if (testStep.getConfiguration() != null) {
						testStep.settakeScreenshot(takeScreenshot);
						callScript(testStep, args, testStep.getConfiguration()
								.getIterationCount());
					} else {
						callScript(testStep);
					}
				} catch (Exception e) {
					e.printStackTrace();
					testStep.addDescription("An unexpected error occurred in the step: "
							+ testStep.getName());
					testStep.setSucceed(false);

					if (getExceptionSteps() != null
							&& !getExceptionSteps().isEmpty()) {
						for (TestStep exceptionStep : getExceptionSteps()) {

							result.getSteps().add(exceptionStep);

							try {
								callScript(exceptionStep);
							} catch (Exception ex) {
								ex.printStackTrace();
								exceptionStep
										.addDescription("An unexpected error occurred in the exception step: "
												+ exceptionStep.getName());
								exceptionStep.setSucceed(false);
							}
						}
					}
					break;
				}
			}
			
			result.setEndDate(new Date());
			
			currentDatapoolRow++;
			
			if(configuration != null){
				if(currentDatapoolRow == configuration.getIterationCount()){
					break;
				}
			}			
			dpNext();
		}

		if (generateFrameworkReport()) {
			// Generate report
		}

		generateCustomReport();
	}
	
	public  Object[] getArgs(){		
		return this.args;
	}

	public abstract List<TestStep> getSteps();

	@Override
	public boolean equals(Object obj) {
		return this.getClass().getSimpleName()
				.equals(obj.getClass().getSimpleName());
	}

	@Override
	public int hashCode() {
		return this.getClass().getSimpleName().hashCode();
	}

	public TestCase withConfiguration(TestCaseConfiguration configuration) {
		this.configuration = configuration;
		return this;
	}

	public TestCaseConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(TestCaseConfiguration configuration) {
		this.configuration = configuration;
	}

	public List<TestCaseResult> getResults() {
		return results;
	}

	public void setResults(List<TestCaseResult> results) {
		this.results = results;
	}

	public Integer getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(Integer caseNumber) {
		this.caseNumber = caseNumber;
	}

	protected boolean generateFrameworkReport() {
		return false;
	}

	/**
	 * Hook in case a custom report is required
	 * 
	 * @param args
	 */
	protected void generateCustomReport() {

	}

	public List<TestStep> getExceptionSteps() {
		return null;
	}

	public String getName() {

		if (name == null) {
			return this.getClass().getSimpleName();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println(name);
	}
	
	public void setScreenshot(boolean set){
		this.takeScreenshot=set;
	}
	
	public boolean getSetScreenshot(){
		return this.takeScreenshot;
	}
}
