/***************************
Class: Keyed_Transposition.java
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

public class Keyed_Transposition implements Serializable {
	private String plainText;
	private String cipherText;
	private String key;

	public Keyed_Transposition() {
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String encrypt(String plainText) {

		String noSpacePlainText = plainText.replaceAll(" ", "");
		System.out.println("First, we will Remove all space in the text to make it easier to handle.\n");
		int textRemain = noSpacePlainText.length() % 4;

		//for better visuals
		try {
		TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
						

		if (textRemain == 1)
			noSpacePlainText += ("zzz");
		if (textRemain == 2)
			noSpacePlainText += ("zz");
		if (textRemain == 3)
			noSpacePlainText += ("z");
		System.out.println("then, if the text is larger than 4 letters we will make a blocks consist "
				+ "of 4 letters, \n so if it less or greater than 4 we will add (Z) to make it multiples of 4. \n");

		//for better visuals
		try {
		TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		char[] noSpaceCharArray = new char[noSpacePlainText.toCharArray().length];
		noSpaceCharArray = noSpacePlainText.toCharArray(); // convert a string to character array

		cipherText = transposition(noSpaceCharArray);
		return "Ciphered Text: " + cipherText + "\nKey: " + key;

	}

	public String transposition(char[] arrChar) { 
		System.out.println("after that the order of the letters will be changed for each block, \n but the order will be the same for each, then the key will be generated.");
		key = "";
		char[] temp = new char[arrChar.length];

		try {
		TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		int x = 0, x2 = 0, x3 = 0, x4 = 0;
		while (true) {
			x = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0); //
			x2 = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0); // numbers must be within 0-3 and must
			x3 = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0); // not be the same for each
			x4 = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0); //
			if (x != x2 && x != x3 && x != x4 && x2 != x3 && x2 != x4 && x3 != x4) {
				if(x == 1 && x2 == 2 && x3 == 3 && x4 == 4 )
					continue;
				else
				        break;
			}
		}

		key = (x + 1) + "" + (x2 + 1) + "" + (x3 + 1) + "" + (x4 + 1); // here we added (1) to make the rearrangement
																		// from 1 to 4
		for (int i = 0; i < arrChar.length; i = i + 4) {

			temp[i] = arrChar[x];
			temp[i + 1] = arrChar[x2];
			temp[i + 2] = arrChar[x3];
			temp[i + 3] = arrChar[x4];
			x = x + 4;
			x2 = x2 + 4;
			x3 = x3 + 4;
			x4 = x4 + 4;
		}
		String value = String.valueOf(temp);

		return value;
	}

	public String decrypt(String cipheredText, String key) {
		System.out.println("here we will do almost the same way of encryption but slightly opposite to get the original plaintext using the provided key.");

		//for better visuals
		try {
		TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		char[] temp = new char[cipheredText.length()];
		char[] arrChar = new char[cipheredText.length()];
		arrChar = cipheredText.toCharArray();

		int x, x2, x3, x4;

		x = (int) key.charAt(0) - 49; // here we will get the decimal number of the char then substract it by 49
		x2 = (int) key.charAt(1) - 49; // to get the integer value then casting char to int
		x3 = (int) key.charAt(2) - 49;
		x4 = (int) key.charAt(3) - 49;
		for (int i = 0; i < cipheredText.length(); i += 4) {
			temp[x] = arrChar[i];
			temp[x2] = arrChar[i + 1];
			temp[x3] = arrChar[i + 2];
			temp[x4] = arrChar[i + 3];
			x = x + 4;
			x2 = x2 + 4;
			x3 = x3 + 4;
			x4 = x4 + 4;
		}
		return "Plaintext: " + String.valueOf(temp);
	}
}
