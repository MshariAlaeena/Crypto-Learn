/***************************
Class: Vigenere.java
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
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Vigenere implements Serializable{
	
	//the key will be numbers between 97 and 122 to avoid the problem of generating random chars , so is the plain and ciphered text 
	private int[] key;
	private int[] plain;
	private int[] cipher;
	
	Vigenere(String s)
	{
		//make string lower case and remove spaces
		String data = s.replaceAll("\\s", "").toLowerCase();
		int size = data.length();
		
		key = new int[size];
		plain = new int[size];
		cipher = new int[size];
		
		//educational purpose 
		System.out.println("A key is being generated...");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The key is randomly generated and made of letters, \n" + "Also the key is as long as the plain text." );
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Key: ");
		//generate a random key and add string to plain array as ASCII values
		for(int i = 0; i < size; i++) 
		{
			//generate random numbers between 97 and 122 to represent ASCII value for alphabetic
			key[i] = (int) Math.floor(Math.random() *(122 - 97 + 1) + 97);
			plain[i] = data.charAt(i);
			//educational purpose
			System.out.print((char)key[i]);
		}

		//educational purpose
		System.out.println("\n");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String Encrypt() 
	{
		//educational purpose
		System.out.println("Data is encrypting...\n");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("the letters have values ranging from 1 to 26 \n" + "To encrypt the text the value of the letter in the plain text is \n"
				+ "added to the value of the corresponding letter in the key producing the ciphered letter, \n" +
				"then the result is moded to 26 in case of it exceding 26. \n");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//to convert to String later
		char[] charCipher = new char[cipher.length];

		//the data will be encrypted as numbers that represent ASCII values
		int value;
		for (int i = 0; i < plain.length; i++) 
		{
			//-96 so "a" becomes 1 and "z" becomes 26 so that we make a loop using mod26
			//-97 wont work because it wont give "a" any value when adding
			value = (plain[i] - 96) + (key[i] -96);

			//exception case for number 26 because mod26 becomes 0
			if(value == 26)
				cipher[i] = value + 96;
			else
				cipher[i] = (value % 26) + 96;
				
			//to convert to String later
			charCipher[i] = (char)cipher[i];
		}
		
		//educational purpose
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new String(charCipher);
	} 
	
	public String Decrypt() 
	{
		//educational purpose
		System.out.println("Data is decrypting...\n");
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("the letters have values ranging from 1 to 26 \n \n" + "The opposite of encrypting is done, \n" + 
		"the value of the letter in the ciphered text is decreesed from the corresping letter in the key \n" + 
				"then we add 26 incase of minus value. \n");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//to convert to String later
		char[] charPlain = new char[cipher.length];

		for (int i = 0; i < plain.length; i++) 
		{
			//opposite of encryption but instead of mod 26 we will add 26 if number is negative 
			 int n = ((cipher[i] - 96) - (key[i] -96));
			 if(n < 0 )
				 n += 26;
			//to convert to string later
			 charPlain[i] = (char)(n + 96);	
		}
		return new String(charPlain);
	} 
	
	public char[] getKey() {
		char[] charKey = new char[key.length];
		for(int i = 0; i<key.length;i++)
			charKey[i] = (char)key[i];
		return charKey;
	}

	//for now
	public int[] getPlain() {
		return plain;
	}

	public char[] getCipher() {
		char[] charCipher = new char[cipher.length];
		for(int i = 0; i<cipher.length;i++)
			charCipher[i] = (char)cipher[i];
		return charCipher;
	}
}

