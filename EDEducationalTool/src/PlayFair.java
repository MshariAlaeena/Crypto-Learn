/***************************
Class: PlayFair.java
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

public class PlayFair implements Serializable {
	
	
	private StringBuilder con = new StringBuilder();
	
	private StringBuilder ciphTxt = new StringBuilder();
	
	private char [][] key= {
		    {'L', 'G', 'D', 'B', 'A'},
		    {'Q', 'M', 'H', 'E', 'C'},
		    {'U', 'R', 'N', 'I', 'F'},
		    {'X', 'V', 'S', 'O', 'K'},
		    {'Z', 'Y', 'W', 'T', 'P'}
		};
	
	String checkRepeated(String a) {
		
		System.out.println("First we're going to check if there are repeated letters,\n"
				+ " if there then we'll add 'X' between them.");
		
				//for better visuals
				try {
					TimeUnit.SECONDS.sleep(4);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		// con = new StringBuilder();
		
		    a=a.replaceAll(" " , "");
		    
			for (int i=0; i < a.length()-1; i++) {
			
				if( i + 1 <a.length() && a.charAt(i) == a.charAt(i+1)) {
						con.append(a.charAt(i));
						con.append('X');
					
				}
				else
					con.append(a.charAt(i));
			}
			
			con.append(a.charAt(a.length()-1));
			System.out.println("The message after this step is: " + con.toString() + "\n"); 
			
			return con.toString();
			}
	

	String nbLetters (String a) {
		
		String p = checkRepeated(a);
		
		System.out.println("Now let's check if the number of letters is odd or even,"
				+ " if it's odd then we'll add 'Z' to make it even,\n"
				+ " unless the last letter is 'Z' then we'll add 'X' instead.\n"
				+ " if it's even nothing will change.");

				//for better visuals
					try {
						TimeUnit.SECONDS.sleep(4);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		if (p.length() %2 != 0) {
			if (p.toUpperCase().charAt(p.length()-1) == 'Z')
				p = p.concat("X");
			else
				p = p.concat("Z");
		}
		
		System.out.println("The message after this step is: " + p + "\n");
		
		return p;
	}
	
	String encPlyFr (String plainTxt) {
		
		//for better visuals
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		plainTxt = nbLetters(plainTxt);
		
		System.out.println("Final step is to take every two letters and cipher it using the key.\n"
				+ "If the letters are in the same row in the matrix, we replace them with the letters to their right.\n"
				+ "If the letters are in the same column, we replace them with the letters below them.\n"
				+ "If the letters neither share a row nor a column, we form a rectangle with them and replace them with the other two corner letters.\n");
		
		for (int i =0; i<plainTxt.length(); i+=2) 
			ciphTxt.append(encUsingKey(plainTxt.charAt(i), plainTxt.charAt(i + 1)));			
		
		System.out.println("Finally the cipherd message is " + ciphTxt.toString() + " now you can use it safely:)\n");
		return ciphTxt.toString();
	}

	
	String encUsingKey (char x, char y) {
		
		    int[] posX = findPos(Character.toUpperCase(x)); 
		    int[] posY = findPos(Character.toUpperCase(y)); 
		    
		    int rowX = posX[0];
		    int colX = posX[1];
		    int rowY = posY[0];
		    int colY = posY[1];

		    if (rowX == rowY) {
		        if (colX == 4)
		            return String.valueOf(Character.toUpperCase(key[rowX][0]) + String.valueOf(Character.toUpperCase(key[rowY][colY + 1]))); 
		        else if (colY == 4)
		            return String.valueOf(Character.toUpperCase(key[rowX][colX + 1]) + String.valueOf(Character.toUpperCase(key[rowY][0]))); 
		        return String.valueOf(Character.toUpperCase(key[rowX][colX + 1]) + String.valueOf(Character.toUpperCase(key[rowY][colY + 1]))); 
		    } else if (colX == colY) {
		        if (rowX == 4)
		            return String.valueOf(Character.toUpperCase(key[0][colX]) + String.valueOf(Character.toUpperCase(key[rowY + 1][colY]))); 
		        else if (rowY == 4)
		            return String.valueOf(Character.toUpperCase(key[rowX + 1][colX]) + String.valueOf(Character.toUpperCase(key[0][colY]))); 
		        return String.valueOf(Character.toUpperCase(key[rowX + 1][colX]) + String.valueOf(Character.toUpperCase(key[rowY + 1][colY]))); 
		    } else
		        return String.valueOf(Character.toUpperCase(key[rowX][colY]) + String.valueOf(Character.toUpperCase(key[rowY][colX]))); 
		}
	
		int[] findPos(char x){
			
			int [] pos = new int [2];
			
			for (int i=0; i<5; i++) {
				for(int j =0; j<5; j++)
					if (key[i][j] == x ) {
						pos[0] = i;
						pos[1] = j;
					}
			}	
			return pos;
		}
		
		/* end of encryption.
		 ------------------------------------------------
		 beginnig of decryption.
		 */
		
		String decPlyFr (String a) {
			
			
			//for better visuals
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			con = new StringBuilder();
			System.out.println("We'll take every two letters and decrypt it using the key.\n"
					+ "If the letters are in the same row, we replace them with the letters to their left.\n"
					+ "If the letters are in the same column, we replace them with the letters above them.\n"
					+ "If the letters neither share a row nor a column, we form a rectangle with them and replace them with the other two corner letters.\n");
			for (int i=0; i<a.length(); i+=2) 
				con.append(decUsingKey(a.charAt(i), a.charAt(i+1)));
			
			return con.toString();
		}
		
		
		String decUsingKey(char a, char b) {
			
			int[] posX = findPos(Character.toUpperCase(a)); 
		    int[] posY = findPos(Character.toUpperCase(b)); 
		    
		    
		    
		    int rowX = posX[0];
		    int colX = posX[1];
		    int rowY = posY[0];
		    int colY = posY[1];
		    
		    if (rowX == rowY) {
		        if (colX == 0)
		            return String.valueOf(Character.toUpperCase(key[rowX][4]) + String.valueOf(Character.toUpperCase(key[rowY][colY - 1]))); 
		        else if (colY == 0)
		            return String.valueOf(Character.toUpperCase(key[rowX][colX - 1]) + String.valueOf(Character.toUpperCase(key[rowY][4]))); 
		        return String.valueOf(Character.toUpperCase(key[rowX][colX - 1]) + String.valueOf(Character.toUpperCase(key[rowY][colY - 1])));
		    } else if (colX == colY) {
		        if (rowX == 0)
		            return String.valueOf(Character.toUpperCase(key[4][colX]) + String.valueOf(Character.toUpperCase(key[rowY - 1][colY]))); 
		        else if (rowY == 0)
		            return String.valueOf(Character.toUpperCase(key[rowX - 1][colX]) + String.valueOf(Character.toUpperCase(key[4][colY]))); 
		        return String.valueOf(Character.toUpperCase(key[rowX - 1][colX]) + String.valueOf(Character.toUpperCase(key[rowY - 1][colY]))); 
		    } else
		        return String.valueOf(Character.toUpperCase(key[rowX][colY]) + String.valueOf(Character.toUpperCase(key[rowY][colX])));	
		}
		
		String getCiphTxt() {
			return ciphTxt.toString();
		}


		public char[][] getKey() {
			return key;
		}
}

