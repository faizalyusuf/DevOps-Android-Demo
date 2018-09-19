import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {
  public class SwipeableWebDriver extends RemoteWebDriver implements
      HasTouchScreen {
    private final RemoteTouchScreen touch;

    public SwipeableWebDriver(final URL remoteAddress,
        final Capabilities desiredCapabilities) {
      super(remoteAddress, desiredCapabilities);
      touch = new RemoteTouchScreen(getExecuteMethod());
    }

    public TouchScreen getTouch() {
      return touch;
    }
  }

  private WebDriver driver;

  @Before
  public void setUp() throws Exception {
    // set up appium
    final File classpathRoot = new File(System.getProperty("user.dir"));
    //final File appDir = new File(classpathRoot,"../android_app/SimpleHelloWorld/build/outputs/apk");
    //final File app = new File(appDir, "HelloWorld-debug.apk");
    final DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("device", "Android");
    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
    capabilities.setCapability(CapabilityType.VERSION, "7.0.0");
    capabilities.setCapability(CapabilityType.PLATFORM, "OS X 10.9");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("appPackage","com.hitherejoe.animate");
    capabilities.setCapability("deviceName", "device");
    capabilities.setCapability("udid","7DDUCUT8VGVKAQIN");
    capabilities.setCapability("appActivity", "com.hitherejoe.animate.ui.activity.MainActivity");
    driver = new SwipeableWebDriver(new URL("http://localhost:4723/wd/hub"),
        capabilities);
    driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
  }

  @Test
  public void mainTest() {
    driver.findElement(By.xpath("//android.widget.TextView[@text='Object Animator']")).click();
    driver.findElement(By.id("com.hitherejoe.animate:id/fab_quiz")).click();
    driver.findElement(By.className("android.widget.ImageButton")).click();
    driver.findElement(By.xpath("//android.widget.TextView[@text='Circular Reveal']")).click();
    driver.findElement(By.id("com.hitherejoe.animate:id/fab_reveal")).click();
    driver.findElement(By.className("android.widget.ImageButton")).click();
    //driver.findElement(By.xpath("//android.widget.TextView[@text='Animated Vector Drawables']")).click();
    //WebElement title = driver.findElement(By.id("toolbar"));
    //System.out.println(title.getText());
    //assert(title.getText().equals("Kotlin Demo"));
    //final WebElement el = driver.findElement(By.id("text_view"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}