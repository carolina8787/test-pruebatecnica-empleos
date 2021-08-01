package com.checkingLinks.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.ResponseCodec;

public class CheckingLinksPage {

	private WebDriver driver;
	
	public CheckingLinksPage(WebDriver driver) {
		this.driver = driver; 
	}
	
	public boolean chekingPageLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		String url = "";
		List<String> brokenLinks = new ArrayList<String>(); // Aquí se guardarán los links
		List<String> okLinks = new ArrayList<String>();
		
		HttpURLConnection httpConnection = null;
		int respinseCode = 200;
		Iterator<WebElement> it = links.iterator();
		
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if(url == null || url.isEmpty()) {
				System.out.println(url + "La url está vacía");
				continue;
			}
			try {
				httpConnection = (HttpURLConnection)(new URL(url).openConnection());
				httpConnection.setRequestMethod("HEAD");
				httpConnection.connect();
				respinseCode = httpConnection.getResponseCode();
				
				if (respinseCode > 400) {
					
					System.out.println("Error de link: "+url);
					brokenLinks.add(url);
					
				} else {

					System.out.println("Link valido: "+url);
					okLinks.add(url);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Links validos:"+okLinks); //Se imprime el total de links buebos
		System.out.println("Links inválidos:"+brokenLinks.size()); //Se imprimen el total de links malos
		
		if(brokenLinks.size()>0) { //Si hay algún link malo
			System.out.println("Lista de links malos:");  
			for (int i = 0; i < brokenLinks.size(); i++) { //Se va recorriendo y mostrando los links malos
				System.out.println(brokenLinks.get(i));
			}
			return false;
			
		}else{
			 
			return true;
		}
	
    }
}

