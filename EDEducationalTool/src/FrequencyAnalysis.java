/***************************
Class: FrequencyAnalysis.java
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
public class FrequencyAnalysis {
	private int freqTableSize1 = 25;
	private int freqTableSize2 = 17;
	private int freqTableSize3 = 19;
	private int freqCountSize = 0;
	private Block[] freqCount;
	private char[] alphabet = new char[26];
	private Block[] freqTable1 = { new Block("Z", 0.074), new Block("Q", 0.095), new Block("X", 0.15),
			new Block("K", 0.77), new Block("V", 1), new Block("B", 1.5), new Block("J", 0.153), new Block("P", 1.9),
			new Block("Y", 2), new Block("G", 2.1), new Block("F", 2.2), new Block("W", 2.3), new Block("M", 2.4),
			new Block("U", 2.75), new Block("C", 2.8), new Block("L", 4), new Block("D", 4.3), new Block("R", 6),
			new Block("H", 6.1), new Block("S", 6.3), new Block("N", 6.7), new Block("I", 7), new Block("O", 7.5),
			new Block("A", 8.2), new Block("T", 9.1), new Block("E", 12.7) };
	private Block[] freqTable2 = { new Block("ES", 1.092301), new Block("HI", 1.092302), new Block("IS", 1.109),
			new Block("IT", 1.134), new Block("NG", 1.05), new Block("OR", 1.151), new Block("HA", 1.274),
			new Block("OU", 1.28), new Block("AT", 1.33), new Block("EN", 1.38),

			new Block("ON", 1.4), new Block("ND", 1.57), new Block("RE", 1.7), new Block("AN", 2.14),
			new Block("ER", 2.17), new Block("IN", 2.2), new Block("HE", 3.6), new Block("TH", 3.8)

	};

	private Block[] freqTable3 = { new Block("TIO", 0.378), new Block("THI", 0.394), new Block("WIT", 0.397),
			new Block("ALL", 0.422), new Block("VER", 0.43), new Block("ITH", 0.4312), new Block("YOU", 0.437),
			new Block("WAS", 0.46), new Block("TER", 0.461), new Block("ION", 0.5), new Block("ENT", 0.53),
			new Block("FOR", 0.555), new Block("ERE", 0.56), new Block("THA", 0.593), new Block("HIS", 0.596),
			new Block("HAT", 0.65), new Block("HER", 0.82), new Block("ING", 1.14), new Block("AND", 1.59),
			new Block("THE", 3.5) };

	public String decrypt(String msg) {
		String demsg = "";
		msg = msg.toUpperCase().replaceAll("\\s+", "");

		fillAlphabet(msg);
		printAlphabet();

		for (int i = 0; i < msg.length(); i++) {
			if (alphabet[msg.charAt(i) - 65] != 0)
				demsg += alphabet[msg.charAt(i) - 65];
			else
				demsg += msg.charAt(i);
		}

		return demsg;
	}

	public void fillAlphabet(String msg) {

		freqCount(msg);
		System.out.println("First, we will count the frequency of each block of letters of the message,\n"
				+ " each block will consist of one, two, or three letters\r\n"
				+ "and for this message it is from lowest to highest frequency is:");
		SortFreqCount();
		printFreqCount();
		System.out.println("Then we will use linguistic information \n"
				+ "such as that the letter E is the most frequent Letter in English \n"
				+ "or that the most frequent word that consists of three letters is \"The\".");
		Boolean plainBlockNotFindAMatch = true;
		int tempOrderChange = 0;
		int freqtableBlockSize = 0;
		while (true) {
			changeOdrerOfFreqTable(freqtableBlockSize, tempOrderChange, plainBlockNotFindAMatch);
			tempOrderChange = freqtableBlockSize = 0;
			plainBlockNotFindAMatch = true;

			if (!(notFull(msg) && enoughData())) {
				if (freqCountSize <= 0)
					return;
				else
					freqCountSize--;
			}
			String cipherBlock = getMaxFreqCount().block;
			while (notFull(msg) && enoughData()) {

				String plainBlock = getMaxFreqTable(cipherBlock.length()).block;

				if (validBlock(cipherBlock, plainBlock)) {
					fillBlockToAlphabet(cipherBlock, plainBlock);
					freqCountSize--;
					deleteFreqTableBlock(cipherBlock.length());
					plainBlockNotFindAMatch = false;
					break;

				} else {

					if (validPlainBlockToDelete(cipherBlock, plainBlock))
						deleteFreqTableBlock(cipherBlock.length());

					if (validFreqCountBlockToDelete(cipherBlock, plainBlock)) {
						freqCountSize--;

						break;
					}
					if (!((validFreqCountBlockToDelete(cipherBlock, plainBlock))
							|| validPlainBlockToDelete(cipherBlock, plainBlock))) {
						deleteFreqTableBlock(cipherBlock.length());
						tempOrderChange++;
						freqtableBlockSize = cipherBlock.length();
					}
				}
			}

		}
	}

	public void freqCount(String msg) {
		createFreqCount(msg);
		findBlocks(msg);
		freqCountSize--;
		for (int i = 0; i <= freqCountSize; i++)
			for (int j = 0; j + freqCount[i].block.length() <= msg.length(); j++)
				if (freqCount[i].block.equals(msg.substring(j, j + freqCount[i].block.length())))
					freqCount[i].freq++;

	}

	public void createFreqCount(String msg) {
		int size;
		switch (msg.length()) {
		case 1:
			size = 1;
			break;
		case 2:
			size = 3;
			break;
		case 3:
			size = 6;
			break;
		default:
			size = msg.length() * 3 - 2;

		}

		freqCount = new Block[size];
		for (int i = 0; i < freqCount.length; i++) {
			freqCount[i] = new Block("", 0);
		}
	}

	public void findBlocks(String msg) {
		int blockSize = 1;

		boolean notUsed = true;
		while (blockSize < 4 && blockSize <= msg.length()) {
			for (int i = 0; msg.length() >= i + blockSize; i++) {
				for (int j = 0; j < freqCountSize; j++) {
					if (freqCount[j].block.equals(msg.substring(i, i + blockSize)))
						notUsed = false;
				}
				if (notUsed) {
					freqCount[freqCountSize].block = msg.substring(i, i + blockSize);
					freqCountSize++;
				}
				notUsed = true;
			}
			blockSize++;
		}

	}

	public void printFreqCount() {
		for (int i = 0; i <= freqCountSize; i++) {
			System.out.println("The Block: " + freqCount[i].block + " its Frequency: " + freqCount[i].freq);
		}
	}

	public void printAlphabet() {
		System.out.println("From that information,\n" + "we will decide the following \n"
				+ "and exchange each letter of the message with the following. ");
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] != 0)
				System.out.println("The Cipherletter: " + (char) (i + 65) + " its Plain Letter: " + alphabet[i]);
		}
	}

	public Block getMaxFreqCount() {

		return freqCount[freqCountSize];
	}

	public Block getMaxFreqTable(int blockSize) {
		switch (blockSize) {
		case 1:
			return freqTable1[freqTableSize1];

		case 2:
			return freqTable2[freqTableSize2];
		case 3:
			return freqTable3[freqTableSize3];

		}
		return null;
	}

	public Boolean enoughData() {
		if (freqCountSize >= 0) {

			switch (getMaxFreqCount().block.length()) {
			case 1:
				return freqTableSize1 >= 0;

			case 2:
				return freqTableSize2 >= 0;
			case 3:
				return freqTableSize3 >= 0;

			}
		}
		return false;

	}

	public boolean notFull(String msg) {
		for (int i = 0; i < msg.length(); i++)
			if (alphabet[msg.charAt(i) - 65] == 0)
				return true;
		return false;
	}

	public void fillBlockToAlphabet(String cipher, String plain) {
		for (int i = 0; i < cipher.length(); i++) {

			alphabet[cipher.charAt(i) - 65] = plain.charAt(i);
		}
	}

	public boolean validLetter(char cipher, char plain) {
		for (int i = 0; i < alphabet.length; i++)
			if (alphabet[i] == plain && i != cipher - 65)
				return false;
		if (alphabet[cipher - 65] == 0 || alphabet[cipher - 65] == plain)
			return true;
		return false;
	}

	public boolean validBlock(String cipher, String plain) {
		for (int i = 0; i < cipher.length(); i++)
			if (!validLetter(cipher.charAt(i), plain.charAt(i)))
				return false;
		return true;
	}

	public void SortFreqCount() {

		for (int i = 0; i < freqCountSize; i++) {
			for (int j = 0; j < freqCountSize; j++) {
				if (freqCount[j].freq > freqCount[j + 1].freq || (freqCount[i].freq == freqCount[i + 1].freq
						&& freqCount[i].block.length() > freqCount[i + 1].block.length())) {
					Block tmp = freqCount[j];
					freqCount[j] = freqCount[j + 1];
					freqCount[j + 1] = tmp;
				}
			}
		}
	}

	public boolean validFreqCountLetterToDelete(char cipher, char plain) {
		if (alphabet[cipher - 65] != 0)
			return true;
		return false;

	}

	public boolean validFreqCountBlockToDelete(String cipher, String plain) {
		for (int i = 0; i < cipher.length(); i++)
			if (!validFreqCountLetterToDelete(cipher.charAt(i), plain.charAt(i)))
				return false;
		return true;
	}

	public boolean validPlainLetterToDelete(char cipher, char plain) {
		for (int i = 0; i < alphabet.length; i++)
			if (alphabet[i] == plain)
				return true;
		return false;

	}

	public boolean validPlainBlockToDelete(String cipher, String plain) {
		for (int i = 0; i < cipher.length(); i++)
			if (!validPlainLetterToDelete(cipher.charAt(i), plain.charAt(i)))
				return false;
		return true;
	}

	public void deleteFreqTableBlock(int BlockSize) {
		switch (BlockSize) {
		case 1:
			freqTableSize1--;
			break;
		case 2:
			freqTableSize2--;
			break;
		case 3:
			freqTableSize3--;
			break;
		}

	}

	public void changeOdrerOfFreqTable(int freqTableBlockSize, int tempOrderChange, boolean plainBlockNotFindAMatch) {
		if (plainBlockNotFindAMatch) {
			switch (freqTableBlockSize) {
			case 1:
				freqTableSize1 += tempOrderChange;
				freqCountSize--;
				return;
			case 2:

				freqTableSize3 += tempOrderChange;
				freqCountSize--;
				return;
			case 3:

				freqTableSize3 += tempOrderChange;
				freqCountSize--;
				return;

			}
		}
		Block[] freqTable = null;
		int freqTableSize = 0;
		switch (freqTableBlockSize) {
		case 1:
			freqTable = freqTable1;
			freqTableSize = freqTableSize1;
			freqTableSize1 += tempOrderChange - 1;
			break;
		case 2:
			freqTable = freqTable2;
			freqTableSize = freqTableSize2;
			freqTableSize2 += tempOrderChange - 1;
			break;
		case 3:
			freqTable = freqTable3;
			freqTableSize = freqTableSize3;
			freqTableSize3 += tempOrderChange - 1;
			break;
		default:
			return;
		}
		for (int i = freqTableSize + 1; i < freqTableSize + tempOrderChange; i++) {
			Block tmp = freqTable[i];
			freqTable[i] = freqTable[i + 1];
			freqTable[i + 1] = tmp;
		}

	}

	public boolean validInput(String input) {
		input = input.replaceAll("\\s+", "").toUpperCase();
		for (int i = 0; i < input.length(); i++)
			if (input.charAt(i) < 65 || input.charAt(i) > 90)
				return false;
		return true;

	}

}