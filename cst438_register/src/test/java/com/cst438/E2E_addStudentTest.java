/** End to end test for add Student
* The reason there is an addStudent button on the Semester Page as well as an addStudent button on the AddStudent Page is that I wanted to know how to do it both ways, so both ways are working. 
* In fact there was a bug on the page one that I fixed this iteration. 
 * 
 */
package com.cst438;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Arlon
 *
 */
public class E2E_addStudentTest {
	//public static final String CHROME_DRIVER_FILE_LOCATION 
    //= "C:/chromedriver_win32/chromedriver.exe";
	public static final String FIREFOX_DRIVER_FILE_LOCATION 
    = "/home/mold/Downloads/geckodriver";
	public static final String URL = "http://localhost:3000";
	public static final String ALIAS_NAME = "test";
	public static final int SLEEP_DURATION = 1000; // 1 second.
 
	@Test
	public void testAddStudentFromSemesterPage() throws Exception {
 
		// set the driver location and start driver
		//@formatter:off
		//
		// browser	property name 				Java Driver Class
		// -------  ------------------------    ----------------------
		// Edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		// Chrome   webdriver.chrome.driver     ChromeDriver
		//
		//@formatter:on
 
		//TODO update the property name for your browser 
		//System.setProperty("webdriver.chrome.driver",
        //        CHROME_DRIVER_FILE_LOCATION);
		System.setProperty("webdriver.gecko.driver",
                FIREFOX_DRIVER_FILE_LOCATION);
		//TODO update the class ChromeDriver()  for your browser
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		
		try {
			WebElement we;
			
			driver.get(URL);
			// must have a short wait to allow time for the page to download 
			Thread.sleep(SLEEP_DURATION);
			
			// find and click the add-student-button button
			we = driver.findElement(By.id("add-student-button"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			String email_to_add="fido@fidosfood"+Math.pow(10,8)*Math.random()+".com";
			driver.findElement(By.id("new_student_name_entry_field")).sendKeys("fido");
			driver.findElement(By.id("new_student_email_entry_field")).sendKeys(email_to_add);/*100M emails*/
			driver.findElement(By.id("new_student_status_entry_field")).sendKeys("hungry");
			driver.findElement(By.id("new_student_statusCode_entry_field")).sendKeys("1000");
			// find and click the submit-student-button button
			we = driver.findElement(By.id("submit-student-button"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			Thread.sleep(SLEEP_DURATION);
			
			//.Toastify__toast-body div:nth-child(2)
			System.out.println("Line 78 e2e add student looking for toast...");
			String toast_text = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)")).getText();
			System.out.println("Line 80 e2e add student possibly found toast..."+toast_text);
			
			assertEquals(toast_text,"Student successfully added");
			
			//.Toastify__toast-body div:nth-child(2)
			System.out.println("Line 78 e2e add student looking for toast...");
			toast_text = driver.findElement(By.cssSelector(".Toastify__toast:nth-child(2) .Toastify__toast-body div:nth-child(2)")).getText();
			System.out.println("Line 80 e2e add student possibly found toast..."+toast_text);
			
			assertEquals(toast_text,"DB row successfully added in student table.");
			
			//that's it I think, a round trip end-to-end add student test. Oh, wait maybe a failed email-repeats test:		
			Thread.sleep(SLEEP_DURATION);
			we = driver.findElement(By.id("add-student-button"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.id("new_student_name_entry_field")).sendKeys("fido");
			driver.findElement(By.id("new_student_email_entry_field")).sendKeys(email_to_add/*repeats above email*/);/*100M emails*/
			driver.findElement(By.id("new_student_status_entry_field")).sendKeys("hungry");
			driver.findElement(By.id("new_student_statusCode_entry_field")).sendKeys("1000");
			// find and click the submit-student-button button
			we = driver.findElement(By.id("submit-student-button"));
			we.click();
			Thread.sleep(SLEEP_DURATION);			
			Thread.sleep(SLEEP_DURATION);
			
			//.Toastify__toast-body div:nth-child(2)
			System.out.println("Line 109 e2e add student looking for toast...");
			toast_text = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)")).getText();
			System.out.println("Line 111 e2e add student possibly found toast..."+toast_text);
			
			assertEquals(toast_text,"Error when adding");
			
			//.Toastify__toast-body div:nth-child(2)
			System.out.println("Line 116 e2e add student looking for toast...");
			toast_text = driver.findElement(By.cssSelector(".Toastify__toast:nth-child(2) .Toastify__toast-body div:nth-child(2)")).getText();
			System.out.println("Line 118 e2e add student possibly found toast..."+toast_text);
			
			assertEquals(toast_text,"DB row successfully added in student table.");//this is a different bug - why does it say this?
			
			//check for invalid integer:
			Thread.sleep(SLEEP_DURATION);
			we = driver.findElement(By.id("add-student-button"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.id("new_student_name_entry_field")).sendKeys("fido");
			driver.findElement(By.id("new_student_email_entry_field")).sendKeys(email_to_add/*repeats above email*/);/*100M emails*/
			driver.findElement(By.id("new_student_status_entry_field")).sendKeys("hungry");
			driver.findElement(By.id("new_student_statusCode_entry_field")).sendKeys("1000a");
			// find and click the submit-student-button button
			we = driver.findElement(By.id("submit-student-button"));
			we.click();
			Thread.sleep(SLEEP_DURATION);			
			//.Toastify__toast-body div:nth-child(2)
			System.out.println("Line 109 e2e add student looking for toast...");
			toast_text = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)")).getText();
			System.out.println("Line 111 e2e add student possibly found toast..."+toast_text);
			
			assertEquals(toast_text,"The status code needs to be an integer");
			
			//backspace it and try again
			driver.findElement(By.id("new_student_statusCode_entry_field")).clear();
			driver.findElement(By.id("new_student_statusCode_entry_field")).sendKeys("1001");
			email_to_add="fido@fidosfood"+Math.pow(10,8)*Math.random()+".com";//don't forget to switch the email again
			driver.findElement(By.id("new_student_email_entry_field")).sendKeys(email_to_add/*repeats above email*/);/*100M emails*/

			we = driver.findElement(By.id("submit-student-button"));
			we.click();
			Thread.sleep(SLEEP_DURATION);			
			
			//.Toastify__toast-body div:nth-child(2)
			System.out.println("Line 78 e2e add student looking for toast..."); //2nd and 3rd childs because 'invalid integer' is still  out
			toast_text = driver.findElement(By.cssSelector(".Toastify__toast:nth-child(2) .Toastify__toast-body div:nth-child(2)")).getText();
			System.out.println("Line 80 e2e add student possibly found toast..."+toast_text);
			
			assertEquals(toast_text,"Student successfully added");
			
			//.Toastify__toast-body div:nth-child(2)
			System.out.println("Line 78 e2e add student looking for toast...");
			toast_text = driver.findElement(By.cssSelector(".Toastify__toast:nth-child(3) .Toastify__toast-body div:nth-child(2)")).getText();
			System.out.println("Line 80 e2e add student possibly found toast..."+toast_text);
			
			assertEquals(toast_text,"DB row successfully added in student table.");
			
			//that's three or four tests, add student, can't add student because email exists, can't because statusCode!=integer, fix=>can
			
 /* More example code from the exercise:
			// get the 2 multiply factors
			String factora = driver.findElement(By.id("factora")).getText();
			String factorb = driver.findElement(By.id("factorb")).getText();
 
			// enter the answer.  
			// find the input tag with name="attempt"
			String attempt = Integer.toString(
                          Integer.parseInt(factora) * Integer.parseInt(factorb));
			we = driver.findElement(By.name("attempt"));
			we.sendKeys(attempt);
			
			// enter an alias name
			we = driver.findElement(By.name("alias"));
			we.sendKeys(ALIAS_NAME);
			
			// find and click the submit button
			we = driver.findElement(By.id("submit"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			// verify the correct message
			we = driver.findElement(By.id("message"));
			String message = we.getText();
			assertEquals("Correct", message);
			
			// switch to the history page
			// click on the <a> link for history
			
			we = driver.findElement(By.xpath("//a[@href='/history']"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			// find all table cell entries
			// DataGrid uses a <div> with 
                      // class='MuiDataGrid-cellContent' to display cell data
			
			List<WebElement> we_list = driver.findElements(
                           By.ByClassName.className("MuiDataGrid-cellContent"));
			
			// check that first row of table contains
			//    the two factors and shows a text value of correct
			assertEquals(factora, we_list.get(0).getText());
			assertEquals(factorb, we_list.get(1).getText());
			assertEquals("true", we_list.get(4).getText());
		*/
		
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
			
		} finally {
			driver.quit();
		}
	}

}
