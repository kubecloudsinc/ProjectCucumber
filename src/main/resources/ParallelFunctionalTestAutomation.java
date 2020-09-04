package ic.ecx.automation.steps;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertTrue;

@CucumberOptions(features = "classpath:ic/ecx/automation/testcases",tags = {"~@ignore"})
public class ParallelFunctionalTestAutomation {
    private static final Logger logger = LoggerFactory.getLogger(ParallelFunctionalTestAutomation.class);
    public static final String KARATE_OUTPUT_PATH = "target/surefire-reports/";
    private static final String REPORT_OUTPUT_DIRECTORY = "target";
    private static final String PROJECT_NAME = "integration-tests";

    private static final int THREAD_COUNT = 1;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("environment----->>>>"+System.getenv("env"));
        //BaseTest.beforeClass();
    }

    @Test
    public void testParallel() {
        String karateOutputPath = KARATE_OUTPUT_PATH;
        KarateStats stats = CucumberRunner.parallel(getClass(), THREAD_COUNT, karateOutputPath);
        generateReport(karateOutputPath);
        assertTrue("there are scenario failures", stats.getFailCount() == 0);
    }

    private static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File(REPORT_OUTPUT_DIRECTORY), PROJECT_NAME);
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
