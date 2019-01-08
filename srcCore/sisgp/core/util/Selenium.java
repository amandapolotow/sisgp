package sisgp.core.util;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


public class Selenium {


	public static void main(String[] args) throws InterruptedException {


	 System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\Casa\\eclipse-workspace\\lib\\chromedriver.exe");
	  
	  //System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		
	 
	  
	// ChromeOptions options = new ChromeOptions();
	// options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type"));
	 //WebDriver driver = new ChromeDriver(options);
	 WebDriver driver = new ChromeDriver();

		driver.navigate().to("http://localhost:8080/sisgp/ProjetosLista.jsp");

		driver.manage().window().maximize();
		
		WebElement elemento;
		
		//Adicionando um projeto
		Thread.sleep(1000);
		driver.findElement(By.id("adicionar")).click();	
		Thread.sleep(1000);
		
		Select dropdownGestor = new Select(driver.findElement(By.id("id_gestor")));
		dropdownGestor.selectByVisibleText("Joana da Silva");
		//Thread.sleep(1000);
		
		Select dropdownCliente = new Select(driver.findElement(By.id("id_cliente")));
		//dropdownCliente.selectByVisibleText("Cliente 3");
		dropdownCliente.selectByValue("3");
		//Thread.sleep(1000);
		
		System.out.println();
		
		elemento = driver.findElement(By.name("codigo"));
		elemento.sendKeys("P00234");
		//Thread.sleep(1000);
		
		elemento = driver.findElement(By.name("nome"));
		elemento.sendKeys("Loja123");
		//Thread.sleep(1000);
		
		elemento = driver.findElement(By.name("descricao"));
		elemento.sendKeys("Projeto de Loja Virtual de Roupas");
		//Thread.sleep(1000);
		
		Select dropdownStatus = new Select(driver.findElement(By.id("id_status_projetos")));
		dropdownStatus.selectByVisibleText("Status 1");
		//Thread.sleep(1000);
		
		elemento = driver.findElement(By.name("dt_inicio"));
		elemento.sendKeys("11/09/2018");
		//Thread.sleep(1000);
		
		elemento = driver.findElement(By.name("dt_fim"));
		elemento.sendKeys("10/12/2018");
		//Thread.sleep(1000);
		
		elemento = driver.findElement(By.name("orcamento"));
		elemento.sendKeys("5000");
		//Thread.sleep(1000);
		
		elemento = driver.findElement(By.name("vl_final_previsto"));
		elemento.sendKeys("4000");
		//Thread.sleep(1000);
		
		driver.findElement(By.id("adicionar")).click();	
		
		Thread.sleep(2000);
		
		//Editar um projeto
		
		elemento = driver.findElement(By.name("codigo"));
		elemento.sendKeys("P00234");
		Thread.sleep(1000);
		
		driver.findElement(By.id("consultar")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("icone")).click();	
		Thread.sleep(1000);
		
		elemento = driver.findElement(By.name("nome"));
		elemento.sendKeys("Loja de Roupas");
		Thread.sleep(1000);
		
		driver.findElement(By.id("editar")).click();	
		
		//Excluir o projeto
		
		elemento = driver.findElement(By.name("codigo"));
		elemento.sendKeys("P00234");
		Thread.sleep(1000);
		
		driver.findElement(By.id("consultar")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("excluir")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("consultar")).click();
		Thread.sleep(5000);
		
		driver.quit();

	}

}
