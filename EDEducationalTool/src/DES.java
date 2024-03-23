
/***************************
Class: DES.java
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DES {

	private int[] initialPermutation = new int[64];
	private int[] finalPermutaion = new int[64];
	private int[] Expansion = new int[48];
	int[] compressionPermutation = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20,
			13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
	private int[] numberShift = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
	private int[] permuationDbox = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27,
			3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
	private static final byte[][] S = {
			{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3,
					8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10,
					0, 6, 13 },
			{ 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11,
					5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0,
					5, 14, 9 },
			{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15,
					1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11,
					5, 2, 12 },
			{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,
					9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12,
					7, 2, 14 },
			{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8,
					6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9,
					10, 4, 5, 3 },
			{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3,
					8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6,
					0, 8, 13 },
			{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8,
					6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14,
					2, 3, 12 },
			{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9,
					2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3,
					5, 6, 11 } };

	public DES() {
		createMatrises();

	}

	public static String generateRandomString() {

		final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(8);

		for (int i = 0; i < 8; i++) {
			int randomIndex = random.nextInt(LETTERS.length());
			char randomChar = LETTERS.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}

		return stringBuilder.toString();
	}

	public static String stringToBinary(String input) {
		StringBuilder binaryStringBuilder = new StringBuilder();

		for (char c : input.toCharArray()) {
			String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
			binaryStringBuilder.append(binaryChar);
		}

		return binaryStringBuilder.toString();
	}

	public static String binaryToHexadecimal(String binaryStr) {
		// Ensure the binary string has 64 bits by padding with zeros if needed
		binaryStr = String.format("%64s", binaryStr).replace(' ', '0');

		// Initialize an empty hexadecimal string
		StringBuilder hexStr = new StringBuilder();

		// Convert the binary string to hexadecimal in 4-bit chunks
		for (int i = 0; i < 64; i += 4) {
			String chunk = binaryStr.substring(i, i + 4);
			int decimalValue = Integer.parseInt(chunk, 2);
			String hexDigit = Integer.toHexString(decimalValue);
			hexStr.append(hexDigit);
		}

		return hexStr.toString();
	}

	public static String hexTo8CharString(String hexString) {
		// Ensure the input hexadecimal string is exactly 16 characters long
		if (hexString.length() != 16) {
			throw new IllegalArgumentException("Input hexadecimal string must be 16 characters long");
		}

		// Convert the hexadecimal string to bytes
		byte[] bytes = new byte[8];
		for (int i = 0; i < 16; i += 2) {
			String hexPair = hexString.substring(i, i + 2);
			bytes[i / 2] = (byte) Integer.parseInt(hexPair, 16);
		}

		// Convert the bytes to a string
		String result = new String(bytes);

		return result;
	}

	private void createMatrises() {
		// initial permeation
		int frist_element = 58;
		int pcounter;
		for (int i = 0; i < 64; i++) {
			if (i % 8 == 0 && i != 0) {
				frist_element += 58;
			}
			if (frist_element == 66) {
				frist_element = 57;
			}

			initialPermutation[i] = frist_element;
			if (frist_element - 8 > 0) {
				frist_element -= 8;
			}
		}
		// final permeation array
		int count = 8;
		int mod = 0;
		for (int i = 1; i < 64; i += 2) {
			if ((i + mod) % 9 == 0) {

				count -= 33;
				mod++;
			}
			finalPermutaion[i] = count;
			count += 8;
		}
		count = 40;
		for (int i = 0; i < 64; i += 2) {
			if (i % 8 == 0 && i != 0) {
				count -= 33;
			}
			finalPermutaion[i] = count;
			count += 8;
		}

		// Expansion array
		int ecounter = 1;
		for (int i = 1; i < 48; i++) {
			if (i % 6 == 0) {
				ecounter -= 2;
			}

			Expansion[i] = ecounter;
			ecounter++;
		}
		Expansion[0] = 32;
		Expansion[47] = 1;
	}

	private ArrayList<String> generateKey(String key) {
		// transform key to binary
		byte[] bytes = key.getBytes();

		System.out.println("This is the 64-bit key --> " + stringToBinary(key));

		StringBuilder builder = new StringBuilder();
		// parity drop
		for (int i = 0; i < 8; i++) {
			String ch = Integer.toBinaryString(bytes[i]);
			ch = ch.substring(0, ch.length());
			builder.append(ch);
		}
		System.out.println("From that key we will generate a 56-bit key --> " + builder + "\n");

		String preLeft = builder.substring(0, builder.length() / 2);
		System.out.println("Then we will split this key into right and left halves:");
		System.out.println("The left half  --> " + preLeft);
		String preRight = builder.substring(builder.length() / 2);
		System.out.println("The right half --> " + preRight + "\n");
		System.out.println(
				"After splitting each half we will do 16 rounds of shifting each half then combining them as follows :\n");

		ArrayList<String> newKey = generateSubkeys(preLeft, preRight);

		System.out.println(
				"Then we will apply a compression permutation to compress the shifted keys from 56-bit to 48-bit keys:\n");
		int x = 1;
		for (String i : newKey) {

			System.out.println("K" + x++ + " --> " + i);
		}

		System.out.println();
		System.out.println(
				"Finally, we have finished with the first phase of DES cipher which is just for Key Generation.");
		System.out.println(
				"<- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -> ");

		return newKey;
	}

	public String encrypt(String input, String key) {

		ArrayList<String> keys = generateKey(key);

		System.out.println(
				"\nSo far we have done nothing to your plaintext, it was all about generating keys ^_^, let's use these keys to encrypt your plaintext!!");
		System.out.println("Your 64-bit plaintext \"" + input + "\" in binary will be --> " + stringToBinary(input));
		// convert every character to binary
		byte[] bytes = input.getBytes();

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			String s1 = String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0');
			builder.append(s1);
		}
		// initial permutation
		input = builder.toString();
		input = permutaion(input, initialPermutation);

		System.out.println("We will do an initial straight permutation --> " + input);

		for (int i = 0; i < 16; i++)
			input = AlgroithmFunction(input, keys.get(i), i);
		input = Swap(input);

		return permutaion(input, finalPermutaion);

	}

	private String permutaion(String input, int[] permutiaon) {

		StringBuilder builder = new StringBuilder();
		int lenght = permutiaon.length;
		for (int i = 0; i < lenght; i++) {
			// get new position from initial permutation array
			int postion = permutiaon[i];
			char ch = input.charAt(postion - 1);
			builder.append(ch + "");

		}

		if (permutiaon == finalPermutaion) {

			System.out.println(
					"Finnaly, we will do a final straight permutation and we will get --> " + builder.toString());
			System.out
					.println("The ciphered text in hexadecimal will be --> " + binaryToHexadecimal(builder.toString()));
			System.out.println();
			System.out.println("\nThat was the encryption phase ^_^.");

		}

		return builder.toString();
	}

	public ArrayList<String> generateSubkeys(String leftString, String rightString) {
		ArrayList<String> subkeys = new ArrayList<>();

		int x = 1;
		for (int round = 0; round < 16; round++) {
			// Perform alternating shifts of one and two positions to the left
			if (round % 2 == 0) {
				leftString = leftShift(leftString, numberShift[round]);
				rightString = leftShift(rightString, numberShift[round]);
			} else {
				leftString = leftShift(leftString, 2);
				rightString = leftShift(rightString, 2);
			}
			// Combine the left and right strings
			String combinedString = leftString + rightString;
			System.out.println("Round " + x++ + " --> " + combinedString);

			// Perform a permutation or transformation (e.g., PC-2) to obtain the subkey
			String subkey = performPermutationOrTransformation(combinedString);

			subkeys.add(subkey);
		}
		System.out.println();
		return subkeys;
	}

	public String performPermutationOrTransformation(String input) {

		int[] pc2 = compressionPermutation;

		char[] inputChars = input.toCharArray();
		char[] resultChars = new char[48];

		for (int i = 0; i < 48; i++) {
			int index = pc2[i] - 1; // Convert to 0-based index
			resultChars[i] = inputChars[index];
		}

		return new String(resultChars);
	}

	public static String leftShift(String input, int positions) {
		// Perform a left shift on the input string by the specified number of positions
		char[] chars = input.toCharArray();
		for (int i = 0; i < positions; i++) {
			char firstChar = chars[0];
			System.arraycopy(chars, 1, chars, 0, chars.length - 1);
			chars[chars.length - 1] = firstChar;
		}
		return new String(chars);
	}

	public static String rightShift(String input) {
		// Perform a right shift on the input string (circular shift)
		char[] chars = input.toCharArray();
		char lastChar = chars[chars.length - 1];
		System.arraycopy(chars, 0, chars, 1, chars.length - 1);
		chars[0] = lastChar;
		return new String(chars);
	}

	private String AlgroithmFunction(String input, String key, int first) {
		// divide input to left and right

		String left = input.substring(0, input.length() / 2);
		String right = input.substring(input.length() / 2);
		String output = right;

		if (first == 0) {
			System.out.println();
			System.out.println("Then we will split it into right and left halves:");
			System.out.println("The left half L0   --> " + left);
			System.out.println("The right half R0  --> " + right);
			System.out.println();
		}

		// Expansion
		right = permutaion(right, Expansion);
		if (first == 0) {
			System.out.println("Then we will put the right half and the first key on the function f(R0,K1),"
					+ " the function will do an expansion, XOR, substition, and permutation:");
			System.out.println("So we will take the right half and do an expansion permutation from 32-bit to 48-bit,"
					+ "\nwe are doing that to be able to do XOR with the first 48-bit key.");
			System.out.println("So, the right half after expansion permutation will be --> " + right);
		}

		// Xor function
		String XorString = Xor(right, key);
		if (first == 0) {
			System.out.println(
					"After doing XOR between the first key K1 and the first right half R0 the result will be --> "
							+ XorString);
			System.out.println();

		}

		// Sbox permutation
		StringBuilder Sbuilder = new StringBuilder();
		for (int i = 0; i < 48; i += 6) {
			int row = Integer.parseInt(XorString.charAt(i) + "" + XorString.charAt(i + 5), 2);
			int column = Integer.parseInt(XorString.substring(i + 1, i + 5), 2);

			int postion = row * 16 + column;
			int newchr = S[i / 6][postion];
			// to avoid under flow
			switch (Integer.toBinaryString(newchr).length()) {
			case 2:
				Sbuilder.append("00");
				break;
			case 3:
				Sbuilder.append("0");
				break;
			case 1:
				Sbuilder.append("000");
				break;
			default:
				break;
			}

			Sbuilder.append(Integer.toBinaryString(newchr));

		}

		if (first == 0) {
			System.out.println(
					"Now we have 8 blocks each block has 6 bits so the total is 48-bit\nWe need to compress it to 32-bit"
							+ ", so 8 blocks each block has 4 bits, we will do this by using s-boxes (Substitution Boxes)\nThe result after"
							+ " substitution will be --> " + Sbuilder);
			System.out.println();
		}

		right = permutaion(Sbuilder.toString(), permuationDbox);

		if (first == 0) {
			System.out
					.println("Then we will do the last phase of the function f(R0,K1) which is Straight permutaion --> "
							+ right);
			System.out.println();
		}

		right = Xor(left, right);

		if (first == 0) {
			System.out.println("Now after finishing with the function we will do an"
					+ " XOR operation between the result and the left half L0 --> " + right);
			System.out.println();
		}

		output = output + right;
		if (first == 0) {
			System.out.println(
					"We will have 64-bit that its new left half L1 = R0 and the new right half R1 = (f(R0,K1) XOR L0)\n"
							+ output);
		}
		return output;
	}

	private String Xor(String frist, String right) {
		StringBuilder Xorbuilder = new StringBuilder();
		for (int i = 0; i < frist.length(); i++) {
			int ch = Integer.parseInt(String.valueOf(frist.charAt(i)))
					^ Integer.parseInt(String.valueOf(right.charAt(i)));
			Xorbuilder.append(ch + "");
		}
		return Xorbuilder.toString();
	}

	private String Swap(String input) {
		String left = input.substring(0, input.length() / 2);
		String right = input.substring(input.length() / 2);

		System.out.println("\nWe will repeat these steps until we reach the 16th round we get :\n" + "L16 = " + left
				+ "\nR16 = " + right);
		System.out.println("L16R16 = " + left + right);
		System.out.println("Then we will swap them to get --> " + right + left);

		return right + left;
	}
}