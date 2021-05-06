package runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import db_utils.DBUtils;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//Cucumber Options Annotations is used to setup runner class configs like which feature files should be scanner
//where to look for steps defs etc.
@CucumberOptions(
        //which feature files to scan
        //classpath - is root path of the project compiled code.
        features = {"classpath:features"},
        //where to look for step defs
        glue = {"steps"},
        //tags are same thing as groups in TestNG(test filters)
        //tags applied on a feature file level are applied to all scenarios in that feature file
        //tilde - ~ means do not include this tag
        tags = {"@regression"}, // "@tag", "@tag" -> two seprate double quotes, mean &&
        //"@tag, @tag2" -> a single double mean ||

        //is responsible for generating junit reports in json format. -> json file
        //json -> java script object notation.
        //junit JSON reports are the first level of reports
        //we will need it for generating cucumber reports -> which will genarate the test repot
        //based on this .json report.
        plugin = {"json:target/cucumber.json"},
        //generating cucumber html reports from cucumber.json file
        //html -is a face of the webpage. it's used for creating webpages. static webpage.
        format = {"pretty", "html:target/reports"}
//        dryRun = false
)
public class RegressionRunner {


    @AfterClass
    public static void afterRegression(){
        DBUtils.close();
    }
}
