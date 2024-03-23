/***************************
Class: EDEducationalTool.java
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
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class EDEducationalTool {
	public static void main(String[] args) 
	{
		FrequencyAnalysis fa = new FrequencyAnalysis();
		
		Scanner sc = new Scanner(System.in);  
		System.out.println("Welcome to the Encryption and Decryption Educational tool");
		System.out.println();
		while(true) 
		{
			try {
			System.out.print("Enter 1 for Encrypt or 2 for Decrypt: ");
			String choice = sc.nextLine();
			System.out.println();
			switch(choice)
			{
				//encryption
				case "1":
				{
						System.out.println("Please choose one of following algothirms: ");
						System.out.println("1 for monoalphabatic cipher \n" +  "2 for playfair cipher\n" + "3 for vigenere cipher \n" 
						+ "4 for Keyed Transposition cipher \n" + "5 for using 2 algothirms , Monoalphabetic cipher and Viegener cipher \n"
						+ "6 for DES cipher \n");
						System.out.print("Choice:");
						String choice2 = sc.nextLine();
						
						switch(choice2) 
						{
						//monoalphabatic cipher encryption
						case "1":
						{
							System.out.print("Please enter the plain text:");
							String plain = sc.nextLine();
							if(!fa.validInput(plain)) {
								System.out.println("The system is unable to Encrypt this text since monoAlphabatic code nature can only encrypt alphabetic letters");
								break;
							}
							System.out.print("Please enter number of times to shift: ");
							int key = sc.nextInt();

							//to remove the line from buffer
							sc.nextLine();
							
							MonoAlphabetic m = new MonoAlphabetic();
							String cipher =m.encryption(plain, key);

							//for better visuals
							Wait(3);

							System.out.println("Cipherd text: " + cipher);	
							
							//for better visuals
							Wait(2);
							
							//write the object to file
							Cipher<Integer,MonoAlphabetic> info = new Cipher<Integer,MonoAlphabetic>(key, cipher.toCharArray(), m);
							try 
							{
								File f = new File("monoCiphered.txt");
								
								//the use of a different stream class when writing is to avoid an error
								if(f.length() == 0)
								{
									ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
									writer.writeObject(info);
									writer.close();
								}
								else 
								{
									MyObjectOutputStream writer = new MyObjectOutputStream(new FileOutputStream(f,true));
									writer.writeObject(info);
									writer.close();
								}
							} 
								catch (FileNotFoundException e) 
									{
										e.printStackTrace();
									} 
									catch (IOException e) 
									{
										e.printStackTrace();
									}
					
							//encryption ended, now for another choice
							System.out.println("1 for decrypting same text \n" + "2 for Encrypting or Decrypting new text \n" 
									+ "3 to exit \n");
							System.out.print("Choice: ");
							String choice3 = sc.nextLine();
							switch(choice3) 
								{
								case "1":
									String plain2 =m.decryption(cipher, key);

									//for better visuals
									Wait(3);

									System.out.println("Ciphered text: " + plain2 );
								case "2":
									break;
								case "3":
									System.exit(0);
								}
							break;
						}
						
						//playfair cipher encryption
						case "2":
						{
							System.out.print("Please enter the plain text:");
							String plain2 = sc.nextLine();

							if(!fa.validInput(plain2)) {
								System.out.println("The system is unable to Encrypt this text since PlayFair code nature can only encrypt alphabetic letters");
								break;
							}
							//for better visuals
							System.out.println();

							PlayFair p = new PlayFair();
							String cipher = p.encPlyFr(plain2);

							//for better visuals
							Wait(3);

							//write the object to file
							Cipher<char[][],PlayFair> info = new Cipher<char[][],PlayFair>(p.getKey(), cipher.toLowerCase().toCharArray(),p);
							try 
							{
								File f = new File("playCiphered.txt");
								
								//the use of a different stream class when writing is to avoid an error
								if(f.length() == 0)
								{
									ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
									writer.writeObject(info);
									writer.close();
								}
								else 
								{
									MyObjectOutputStream writer = new MyObjectOutputStream(new FileOutputStream(f,true));
									writer.writeObject(info);
									writer.close();
								}
							} 
							catch (FileNotFoundException e) 
								{
									e.printStackTrace();
								} 
								catch (IOException e) 
								{
									e.printStackTrace();
								}
							
							//encryption ended, now for another choice
							System.out.println("1 for decrypting same text \n" + "2 for Encrypting or Decrypting new text \n" 
									+ "3 to exit \n");
							System.out.print("Choice: ");
							String choice4 = sc.nextLine();
							switch(choice4) 
								{
								case "1":
									System.out.println("Plain text:" + p.decPlyFr(cipher));
								case "2":
									break;
								case "3":
									System.exit(0);
									break;
								}
								//for better visuals
								System.out.println();
								Wait(2);
							break;
						}
						//vigenere cipher encryption
						case "3":
						{
							System.out.print("Please enter the plain text:");
							String plain3 = sc.nextLine();
							if(!fa.validInput(plain3)) {
								System.out.println("The system is unable to Encrypt this text since Vigenere code nature can only encrypt alphabetic letters");
								break;
							}
							

							Vigenere v = new Vigenere(plain3);
							System.out.println("Ciphered text: " + v.Encrypt());
							
							//for better visuals
							Wait(3);

							//write the object to file
							Cipher<char[],Vigenere> info = new Cipher<char[],Vigenere>(v.getKey(), v.getCipher(), v);
							try 
							{
								File f = new File("vigCiphered.txt");
								
								//the use of a different stream class when writing is to avoid an error
								if(f.length() == 0)
								{
									ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
									writer.writeObject(info);
									writer.close();
								}
								else 
								{
									MyObjectOutputStream writer = new MyObjectOutputStream(new FileOutputStream(f,true));
									writer.writeObject(info);
									writer.close();
								}
							} 
							catch (FileNotFoundException e) 
								{
									e.printStackTrace();
								} 
								catch (IOException e) 
								{
									e.printStackTrace();
								}
							
							//encryption ended, now for another choice
							System.out.println("1 for decrypting same text \n" + "2 for Encrypting or Decrypting new text \n" 
									+ "3 to exit \n");
							System.out.print("Choice: ");
							String choice5= sc.nextLine();
							switch(choice5) 
								{
								case "1":
								System.out.println("Decrypted text: " + v.Decrypt());
								case "2":
									break;
								case "3":
									System.exit(0);
									break;
								}

							//for better visuals
							Wait(2);

							break;
						}
						//keyed transpotion encryptin
						case "4":
						{
								System.out.print("Please enter the plain text:");
								String plain4 = sc.nextLine();

								Keyed_Transposition k = new Keyed_Transposition();
								String cipher = k.encrypt(plain4);
								System.out.println(cipher);

								//write the object to file
								Cipher<String,Keyed_Transposition> info = new Cipher<String,Keyed_Transposition>(k.getKey(), k.getCipherText().toLowerCase().toCharArray(), k);
								try 
								{
									File f = new File("keyedCiphered.txt");
									
									//the use of a different stream class when writing is to avoid an error
									if(f.length() == 0)
									{
										ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
										writer.writeObject(info);
										writer.close();
									}
									else 
									{
										MyObjectOutputStream writer = new MyObjectOutputStream(new FileOutputStream(f,true));
										writer.writeObject(info);										
										writer.close();
									}
								} 
								catch (FileNotFoundException e) 
									{
										e.printStackTrace();
									} 
									catch (IOException e) 
									{
										e.printStackTrace();
									}

								//encryption ended, now for another choice
								System.out.println("1 for decrypting same text \n" + "2 for Encrypting or Decrypting new text \n" 
										+ "3 to exit \n");
								System.out.print("Choice: ");
								String choice6 = sc.nextLine();
								switch(choice6) 
									{
									case "1":
										System.out.println(k.decrypt(k.getCipherText(),k.getKey()));
									case "2":
										break;
									case "3":
										System.exit(0);
										break;
									}	

								//for better visuals
								Wait(2);

							break;
						}
						//2 algothirms
						case "5":
						{
							//first algothirm encryption
							System.out.println("First the system will encrypt using Monoalphabtic then Viegener cipher \n");

							System.out.print("Please enter the plain text:");
							String plain = sc.nextLine();
							if(!fa.validInput(plain)) {
								System.out.println("The system is unable to Encrypt this text since Monoalphabtic and Viegener code nature can only encrypt alphabetic letters");
								break;
							}

							System.out.print("Please enter number of times to shift for MonoAlphabatic cipher: ");
							int key = sc.nextInt();

							//to remove the line from buffer
							sc.nextLine();

							MonoAlphabetic m = new MonoAlphabetic();
							String cipher =m.encryption(plain, key);

							//for better visuals
							System.out.println();
							Wait(5);

							System.out.println("Cipherd text from MonoAlphabatic cipher: " + cipher);

							//for better visuals
							Wait(5);
			
							//second algothirm encryption
							System.out.println("For the Vigenere cipher the system will use the ciphered text resulted from the MonoAlphabatic cipher");
							
										
							//for better visuals
							Wait(3);

							Vigenere v = new Vigenere(cipher);
							System.out.println("Ciphered text after both algothrims:" + v.Encrypt() );

							//for better visuals
							Wait(2);

							//encryption ended, now for another choice
							System.out.println("1 for decrypting same text \n" + "2 for Encrypting or Decrypting new text \n" 
									+ "3 to exit \n");
							System.out.print("Choice: ");
							String choice6 = sc.nextLine();
							switch(choice6) 
								{
									//decryption
									case "1":
									{
										System.out.println("Since last operation we did was encrypting with Viegener,\n "
										+"we start decrypting using Viegener ," 
										+"then decrypt using MonoAlphabatic to get the plain text.\n");

										//for better visuals
										Wait(5);

										//first algothirm decryption
										String cipher3 = v.Decrypt();
										System.out.println("Ciphered text after encrypting with Viegener cipher:"+cipher3+"\n"
										+ "which is we what we get when the system first decrypted using MonoAlphabatic cipher.");
										
										//second Algothirm decryption
										String plain2 =m.decryption(cipher, key);

										//for better visuals 
										System.out.println();

										System.out.println("plain text after decrypting using both algothrims: " + plain2);
										break;
									}
								case "2":
									break;
								case "3":
									System.exit(0);
									break;
								}	

								//for better visuals
								Wait(2);
								break;
						}

						//des
						case "6":
							{	
								System.out.print("please enter the plaintext:");
								String pt = sc.nextLine();

								DES a = new DES();

								while (pt.replaceAll("\\s", "").length() != 8) {
									System.out.println("Sorry, you should enter a number of characters that is equal to 8:");
									pt = sc.nextLine();
								}

								System.out.println();
								a.encrypt(pt.replaceAll("\\s", ""), DES.generateRandomString());
							}
						}
					break;	
				}
				

			//decryption
			case "2":
			{
					System.out.print("Please enter the cipher text: ");
					String cipher = sc.nextLine().toLowerCase().replaceAll("\\s", "");

					//for better visuals
					System.out.println();

					char[] chCopy = cipher.toCharArray();
					int found = 0;

					//monoalphabatic decryption
					File mono = new File("monoCiphered.txt");
					if(mono.exists())
					{
						try 
						{
							ObjectInputStream reader = new ObjectInputStream(new FileInputStream(mono));
							while(true)
							{
								Cipher<Integer,MonoAlphabetic> info = (Cipher<Integer,MonoAlphabetic>) reader.readObject();
								if(Arrays.equals(info.getCipher(), chCopy))
								{
									System.out.println("it was encrypted using MonoAlphabetic cipher\n");
									MonoAlphabetic m = info.getAlgoObject();
									System.out.println("Plain text:" + m.decryption(cipher, m.getKey()));
									reader.close();
									found++;
									break;
								}
							}
							
						}
							
							catch(EOFException e)
							{
							}
							
							catch (FileNotFoundException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							catch (IOException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							catch (ClassNotFoundException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					}

					//check if ciphered was found to not continue searching
					if(found == 1)
						break;

					//playfair decryption
					File play = new File("playCiphered.txt");
					if(play.exists())
					{
						try 
						{
							ObjectInputStream reader = new ObjectInputStream(new FileInputStream(play));
							while(true)
							{
								Cipher<char[][],PlayFair> info = (Cipher<char[][],PlayFair>) reader.readObject();
								if(Arrays.equals(info.getCipher(), chCopy))
								{
									System.out.println("it was encrypted using PlayFair cipher\n");
									PlayFair p = info.getAlgoObject();
									System.out.println("Plain text:" + p.decPlyFr(cipher));
									reader.close();
									found++;
									break;
								}
							}
						
						}
						
						catch(EOFException e)
						{
						}
						 
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (ClassNotFoundException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					//check if ciphered was found to not continue searching
					if(found == 1)
						break;

					//vigenere decryption
					File vig = new File("vigCiphered.txt");
					if(vig.exists())
					{
						try 
						{
							ObjectInputStream reader = new ObjectInputStream(new FileInputStream(vig));
							while(true)
							{
								Cipher<char[],Vigenere> info = (Cipher<char[],Vigenere>) reader.readObject();
								if(Arrays.equals(info.getCipher(), chCopy))
								{
									System.out.println("it was encrypted using Vigenere cipher\n");
									Vigenere v = info.getAlgoObject();
									System.out.println("Plain text:" + v.Decrypt());
									reader.close();
									found++;
									break;
								}
							}
						
						}
						
						catch(EOFException e)
						{
						}
						 
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (ClassNotFoundException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					//check if ciphered was found to not continue searching
					if(found == 1)
						break;

					//keyed transpotion decryption
					File k = new File("keyedCiphered.txt");
					if(k.exists())
					{
						try 
						{
							ObjectInputStream reader = new ObjectInputStream(new FileInputStream(k));
							while(true)
							{
								Cipher<String,Keyed_Transposition> info = (Cipher<String,Keyed_Transposition>) reader.readObject();
								if(Arrays.equals(info.getCipher(), chCopy))
								{
									System.out.println("it was encrypted using KeyedTranspotion cipher\n");
									Keyed_Transposition keyed = info.getAlgoObject();
									System.out.println(keyed.decrypt(cipher, keyed.getKey()));
									reader.close();
									found++;
									break;
								}
							}
						
						}
						
						catch(EOFException e)
						{
						}
						 
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (ClassNotFoundException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					//if it is not found the system will guess the ciphered text
					if(found == 0)
					{
						System.out.println("The ciphered text was not found in the system,\n" 
						+ "the system will try to guess the ciphered text.\n" );
						
						
						if(!fa.validInput(cipher)) {
							System.out.println("The system is unable to decrypt this text since, we don't have the key for it,"
									+ "\n and we cant try to decrypt it using frequency analysis because frequency analysis nature can only decrypt alphabetic letters");
						}
							else {
						System.out.println("Decrypted text: " + fa.decrypt(cipher));
							}
						//for visuals
						System.out.println();
					}
			}
		}
			}
			catch(Exception e) {
				System.err.println("Something went wrong!");
			}
	}	
}



	private static void Wait(int n)
	{
		//for better visuals
		try 
		{
			TimeUnit.SECONDS.sleep(n);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

