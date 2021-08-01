package com.PruebaTecnica.Empleos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.checkingLinks.test.CheckingLinksPage;

public class EmpleosTest {

	private WebDriver driver;
	CheckingLinksPage page;
	
	@Before
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://www.choucairtesting.com/");
		page = new CheckingLinksPage(driver);
		
	}
	
	@Test
	public void ValidateFilter() throws InterruptedException //Test para validar el filtro de b�squeda
	{
		//Arrange		
		//Act		
		driver.findElement(By.id("menu-item-550")).click(); //Dar clic en bot�n "Empleos" del men�
		Thread.sleep(1500);
		
		//Dar clic en el �cono de la lupa
		driver.findElement(By.cssSelector(".search-icon")).click();
		Thread.sleep(1500);
		
		WebElement searchbox = driver.findElement(By.id("is-search-input-0")); //Se busca donde se escribir� la palabra
		searchbox.clear(); //Se limpia
		searchbox.sendKeys("calidad"); //Se busca la palabra calidad
		searchbox.submit(); //Se env�a la busqueda
		Thread.sleep(1500);
		
		String resultText = "Resultados de b�squeda para �calidad� � Choucair Testing";

		//Assert
		assertEquals(resultText, driver.getTitle());
		
	}
	
	@Test
	public void ValidatePortalButton() throws InterruptedException { //Test para validar el bot�n de Ir al portal de empleos y aceptar
		
		//Arrange
		JavascriptExecutor js = (JavascriptExecutor) driver; // Se instancia una librer�a de Javascript para Scroll
		
		//Act
		driver.get("https://www.choucairtesting.com/empleos-testing/");
		Thread.sleep(1500);
		js.executeScript("window.scrollBy(0,500)"); //Se hace scroll a la mitad de la p�gina
		Thread.sleep(1500);
		driver.findElement(By.partialLinkText("Ir al portal de empleos")).click(); // Se da clic en el bot�n Ir al portal de empleos y abrir ventana emergente
		Thread.sleep(1500);
			
		driver.findElement(By.partialLinkText("CONTINUAR")).click(); // Se da clic en el bot�n Continuar de la ventana emergente y se va al portal de empleos
		Thread.sleep(1500);
		
		String ResultText = "Choucair-Testing"; 

		//Assert
		assertEquals(ResultText, driver.getTitle());
		
	}
	
	@Test
	public void ValidateLinks() throws InterruptedException { //M�todo encargado de Validar todos los links del m�dulo empleos
		//Arrange
		//Act
		driver.get("https://www.choucairtesting.com/empleos-testing/");
		
		//Assert
		assertTrue("Links errados", page.chekingPageLinks());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}	
}