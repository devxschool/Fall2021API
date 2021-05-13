##Fall2021 Food Delivery API test Automation Framework

This framework includes API tests for food cache endpoints and user registration endpoints


####Prerequisites
You must have java and maven installed on your machine

####How to run the tests
In order to run regression test you need to run the following maven command

 ```shell script
mvn clean install -Dserver=qa1 -Ddb.password=GiveDBPassword
```

####Breakdown of the framework mechanism

When mvn clean install is run maven triggers maven surefire plugin below

 ```xml
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.21.0</version>
                <configuration>
                    <includes>
                        <include>**/RegressionRunner.java</include>
                    </includes>
                    <testFailureIgnore>true</testFailureIgnore>
                </configuration>
            </plugin>
```

The surefire plugin triggers the Cucumber runner class below

 ```java
@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"classpath:features"},
        glue = {"steps"},

        tags = {"@regression"}, 

        plugin = {"json:target/cucumber.json"},
        format = {"pretty", "html:target/reports"}
)
public class RegressionRunner {


    @AfterClass
    public static void afterRegression(){
        DBUtils.close();
    }
}
```

The Regression runner class scans all feature files under features folder and executes all scenarios with @regression tag
on

Note:
There are before hooks to make sure the food delivery app is up and running before executing the regression, which 
help us avoid manual intervention 

```java
  @Before(order = 0)
    public void startUpTheApp() {
        Thread thread = new Thread(){
            public void run(){
                AppSession appSession = new LocalSession();
                String result = appSession.sendCommand("java -jar -Dspring.datasource.url=jdbc:mysql://3.131.35.165:3306/food_delivery_askar /Users/askarmusakunov/Desktop/FoodDelivery/food_delivery-3.2.1.jar &");
                System.out.println(result);
            }
        };
        thread.start();
    }
```


Also, we there is a before hook to make sure that app under test is in a clean state where all cache and db are 
cleaned and prepped for the next test

```java
    @Before(order = 1)
    public void setup() throws InterruptedException {
        Thread.sleep(60000);
        RestHttpRequest.addHeaders();
        Response response = RestHttpRequest.requestSpecification
                .when()
                .request("POST", CLEAR_CACHE.getEndpoint());

        Assert.assertEquals(200, response.getStatusCode());
    }
```

And lastly once the tests are done executing the framework generates cucumber reports thanks to the cucumber reports plugin

 ```xml
               <plugin>
                            <!-- pie chart nice cucumber report -->
                            <groupId>net.masterthought</groupId>
                            <artifactId>maven-cucumber-reporting</artifactId>
                            <version>2.8.0</version>
                            <!-- how this plugin should be executed -->
                            <executions>
                                <execution>
                                    <id>AnyID</id>
                                    <!-- which maven phase this plugin should be executed -->
                                    <phase>verify</phase>
                                    <!-- this plugin specific goals -->
                                    <goals>
                                        <goal>generate</goal>
                                    </goals>
                                    <!-- here give configuration for the report like name, where
                                          the plugin should find cucumber.json and where the final nice report
                                          should be created(generated)? -->
                                    <configuration>
                                        <!-- what ever we put here will appear in the report title -->
                                        <projectName>Amazon Alexa ${project.version}</projectName>
                                        <!-- where the nice report should be generated in? -->
                                        <!-- from pom file project build directory is target folder -->
                                        <outputDirectory>${project.build.directory}/fall2020CucumberReport</outputDirectory>
                                        <!-- which cucumber junit statistics from runner class should I use? -->
                                        <cucumberOutput>${project.build.directory}/cucumber.json</cucumberOutput>
                                    </configuration>
                                </execution>
                            </executions>
                        </plugin>
```
The cucumber report is generated in the target folder of the maven project

![Alt text](src/main/resources/images/CucumberReports.png?raw=true "Optional Title")