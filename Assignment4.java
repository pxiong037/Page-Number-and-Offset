package Assignment4;
/**
 * Prechar Xiong
 * 10/27/19
 * ICS 462-01
 * Assignment 4
 * 
 * This program creates a method called getPageAndOffset to get the page and offset of
 * an address assuming that the system has 32-bit virtual memory address with a 4-KB
 * page size. It then prints the results to the console and to an output file.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Assignment4{
	
	/**
	 * this is the driver to run the getPageAndOffset on the numbers in the array
	 * @param args
	 */
	public static void main(String[] args){
		int[] array = {19986,347892,5978};
		for(int x : array) {
			getPageAndOffset(x);
		}
	}
	
	/**
	 * this method is passed in an address and calculate the address's page number
	 * and offset assuming the system has 32-bit virtual memory address with 
	 * a 4-KB page size.
	 * @param address to find its page number and offset
	 */
	public static void getPageAndOffset(int address){
		int pageSize = 1024*4; //1KB = 1024 so 4KB = 4096
		int power = (int) (Math.log(pageSize)/Math.log(2)); //find the log base 2 of the page size, to right shift the address this many times
		int offsetMask = pageSize-1; //4095 is the max number you can make with 12 bits, which is used to mask the first 12 bits
		int offset = address & offsetMask; //use bitwise AND with offset mask to extract the first 12 bits of the address
		int pageNumber = address>>power; //12 right shifts to get rid of the first 12 bits 
		String s = "The address " + address + " contains: \n" + 
				"     Page number = " + pageNumber + "\n" +
				"     Offset = " + offset + "\n";
		createFile();
		System.out.println(s);
		printToFile(s);
	}
	
	/**
	 * creates a file to store the output of the program
	 */
	public static void createFile() {
		try {
			FileWriter fileWriter;
			File file = new File("HW4output.txt");
			PrintWriter printWriter;
			if(file.exists()) {
				fileWriter = new FileWriter(file.getAbsoluteFile(), true);
				printWriter = new PrintWriter(fileWriter);
			} else {
				fileWriter = new FileWriter(file);
				printWriter = new PrintWriter(fileWriter);
				printWriter.println("Prechar Xiong \nICS 462 Assignment #4 \n");
			}
			printWriter.close();
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * prints to the file the string passed in
	 * @param msg to be printed to the file
	 */
	public static void printToFile(String msg) {
		try {
			File file = new File("HW4output.txt");
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(msg);
			printWriter.close();
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
}