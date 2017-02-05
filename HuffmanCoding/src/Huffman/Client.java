package Huffman;


public class Client {

	public static void main(String[] args) {
		huffmancode hcoder = new huffmancode("1111111111111122222222222222255555555555555555555555555559999999999999999999999999999999999");
		System.out.println(hcoder.enCode("951259"));
	System.out.println(hcoder.deCode("0101101111000010110111010 "));
	}

}
