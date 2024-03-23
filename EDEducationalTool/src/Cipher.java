/***************************
Class: Cipher.java
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

public  class Cipher<T,D> implements Serializable{
	

	private T key;
	private char[] ciphered;
	private D algoObject;
	
	Cipher(T key, char[] ciphered,D algoObject)
	{
		this.key = key;
		this.ciphered = ciphered;
		this.algoObject = algoObject;
	}

	public D getAlgoObject() {
		return algoObject;
	}

	public T getKey() {
		return key;
	}

	public char[] getCipher() {
		return ciphered;
	}

}
