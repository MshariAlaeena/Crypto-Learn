/***************************
Class: MonoAlphabetic.java
SWE314 Project phase 1
Fall 2023
Team:
Faisal Alwahhabi 443102495
Mishary Alaeena 443101459
Mishary Aldawood 443102219
Mishary Almuammmar 443101420
Talal Alrafee 443100850
Turki Alsugair 443101786




**************************/
import java.io.*;
import java.util.concurrent.TimeUnit;

public class MonoAlphabetic implements Serializable {

	private String cipher;
	private int key;
	
	char Alpha[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z' };

	public String encryption(String sentence, int numbr) {
		this.key = numbr;
		sentence = sentence.toLowerCase().replaceAll("\\s", "");;

		System.out.println("Data is encrypting...\n");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("every letter is shifted to the right by the number of times provided, \n" 
		+ "A loop is made if we reach the end the of the alphabit to A.");
		
		cipher = "";
		for (int i = 0; i < sentence.length(); i++) {
			for (int j = 0; j < Alpha.length; j++) {

				if (sentence.charAt(i) == Alpha[j]) {
					cipher += Alpha[(j + numbr) % 26];
				}
			}

		}
		return cipher;
	}

	public String decryption(String sentence, int numbr) {
		System.out.println("Data is decrypting...\n");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("every letter is shifted to the left by the number of times previously provided, \n" 
		+ "A loop is made if we reach the begining of the alphabit to Z. ");
		
		sentence = sentence.toLowerCase().replaceAll("\\s", "");;
		String plain = "";
		for (int i = 0; i < sentence.length(); i++) {
			for (int j = 0; j < Alpha.length; j++) {
				if (sentence.charAt(i) == Alpha[j]) {
					if (j - numbr >= 0) {
						plain += Alpha[j - numbr];
					} else {
						plain += Alpha[j - numbr + 26];
					}
				}
			}
		}
		return plain;
	}
	
	public String getCipher() {
		return cipher;
	}

	public int getKey() {
		return key;
	}
}