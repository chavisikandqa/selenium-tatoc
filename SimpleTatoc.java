package qainfotech;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class SimpleTatoc {WebDriver wd;
	public static void main(String[] args)  {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\chavisikand\\eclipse-workspace\\qainfotech\\driver\\chromedriver.exe");
		WebDriver wd = new ChromeDriver();
		wd.get("http://10.0.1.86/tatoc/basic/grid/gate");
		wd.findElement(By.cssSelector("td div.greenbox")).click();
		wd.switchTo().frame(wd.findElement(By.id("main")));
		String Box1 = wd.findElement(By.xpath("//div[text()='Box 1']")).getAttribute("class");
		System.out.println("Hii" + Box1);
		wd.switchTo().frame(wd.findElement(By.id("child")));
		String Box2 = wd.findElement(By.xpath("//div[text()='Box 2']")).getAttribute("class");
		while (!Box1.equals(Box2)) {
			wd.switchTo().defaultContent();
			wd.switchTo().frame(wd.findElement(By.id("main")));
			wd.findElement(By.xpath("//*[text()[contains(.,'Repaint Box 2')]]")).click();
			wd.switchTo().frame(wd.findElement(By.id("child")));
			Box2 = wd.findElement(By.xpath("//div[text()='Box 2']")).getAttribute("class");

		}
		wd.switchTo().defaultContent();
		wd.switchTo().frame(wd.findElement(By.id("main")));
		wd.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]")).click();

		wd.switchTo().defaultContent();
		WebElement drag = wd.findElement(By.id("dragbox"));
		WebElement drop = wd.findElement(By.id("dropbox"));
		Actions act = new Actions(wd);
		act.dragAndDrop(drag, drop).build().perform();
		wd.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]")).click();
		wd.findElement(By.xpath("//*[text()[contains(.,'Launch Popup Window')]]")).click();

		for (String handle : wd.getWindowHandles()) {

			wd.switchTo().window(handle);
		}
		wd.findElement(By.id("name")).sendKeys("Chavi");
		wd.findElement(By.id("submit")).click();
		for (String handle : wd.getWindowHandles()) {

			wd.switchTo().window(handle);
		}
		wd.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]")).click();
		wd.findElement(By.xpath("//a[contains(text(),'Generate Token')]")).click();
		String token = wd.findElement(By.xpath("//span[@id='token']")).getText();
		token = token.substring(7);
		System.out.println(token);
		Cookie name = new Cookie("Token", token);
		wd.manage().addCookie(name);
		wd.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();}
		@AfterTest
		public void closeBrowser() throws InterruptedException {
			Thread.sleep(10000);
			wd.quit();
		}
	}
	
