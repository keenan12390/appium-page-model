import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.JsonReader;

import static utils.JsonReader.getTestData;


public class samsungS20Test {
    AppiumDriver<MobileElement> driver;
    public static String url;
    public static String username;
    public static String password;

    static {
        try {
            url = getTestData("url");
            username = getTestData("username");
            password = getTestData("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeTest
    public void setup() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            String platform = "android";

            if (platform.equalsIgnoreCase("android")) {
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 6");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(MobileCapabilityType.APP, "/Users/KeenanMeyer/workspace/appium/practice/proverbial_android.apk");
            }
            else if(platform.equalsIgnoreCase("ios")) {

                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.4");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");

            }


            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            driver = new AppiumDriver<MobileElement>(url, caps);

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        } catch (Exception exp) {
            System.out.println("Cause is : " + exp.getCause());
            System.out.println("Message is : " + exp.getMessage());
            exp.printStackTrace();

        }

    }

    @Test
    public void sampleTest() throws InterruptedException, IOException, ParseException {
        JSONArray jsonArray = JsonReader.getJsonArray("languages");
        Iterator<String> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
            System.out.println("I am inside sample Test");

            //click on geolocation
            MobileElement geoLocationElement = driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
            geoLocationElement.click();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            MobileElement findButton = driver.findElementById("com.lambdatest.proverbial:id/find");
            Assert.assertEquals(findButton.isDisplayed(), true, "Find button is not displayed");
            Thread.sleep(300);

            //enter url
            MobileElement urlTextInput = driver.findElementById("com.lambdatest.proverbial:id/url");
            urlTextInput.sendKeys(url);
            System.out.println("Sent Keys");
            Thread.sleep(300);

            //click find to search
            findButton.click();
            System.out.println("find button clicked");
            Thread.sleep(350);


            //checking if  logo displayed
            MobileElement loginButton = driver.findElementByXPath("//android.widget.Button[@resource-id=\"login-button\"]\n");
            Assert.assertEquals(loginButton.isDisplayed(), true, "Swag labs Login is not displayed");
            System.out.println("logon done");
            Thread.sleep(200);

            //lOGIN
            MobileElement urlUserNameTextInput = driver.findElementByXPath("//android.widget.EditText[@resource-id=\"user-name\"]");
            String usernameTxt =  String.valueOf(driver.findElementByXPath("//android.view.View[@text=\"standard_user\"]").getText());
            urlUserNameTextInput.sendKeys(usernameTxt);
            System.out.println("USERNAME" + usernameTxt);
            Thread.sleep(300);

            MobileElement urlPasswordTextInput = driver.findElementByXPath("//android.widget.EditText[@resource-id=\"password\"]");
            String passwordTxt =  String.valueOf(driver.findElementByXPath("//android.view.View[@text=\"secret_sauce\"]").getText());
            urlPasswordTextInput.sendKeys(passwordTxt);
            System.out.println("PASSWORD" + passwordTxt);
            Thread.sleep(300);

            loginButton.click();
            System.out.println("Login clicked");
            Thread.sleep(500);

//            MobileElement productLbl = driver.findElementById("//android.widget.EditText[@resource-id=\"Products\"]");
//            Assert.assertEquals(productLbl.isDisplayed(), true, "Not successfully logged in");

    }


        @AfterSuite
        public void tear_down () {
            driver.quit();
        }

}