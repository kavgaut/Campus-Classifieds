package com.my.friends;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/** To generate a unique number that is stored in a file which is created dynamically if it does not exist.
 * if the file exists then the value is read from the file.
 * @author Ramya
 *
 */
public class PostIdGenerator{
	int counter = 0;
	/** To read the number that is stored in a file which is
	 * provided as the input
	 * @param inputFile
	 * @return
	 */
	public int getRentalId(File inputFile){
		try{
			if(!inputFile.exists()){
				return 1;
			}
			else {
				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				String s = br.readLine();
				try{
					counter = Integer.parseInt(s);
				}
				catch(NumberFormatException e){
					counter = 0;
				}
				
				br.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return counter;
	}
	
	/** To write the generated unique number into the existing file
	 * so that it can be accessed next time the get methos is called
	 * @param count
	 * @param inputFile
	 */
	public void setRentalId(int count, File inputFile){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile));
			bw.write(Integer.toString(count));
			bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** reads the unique id from the file, stores it in count to return to the called method
	 * then increments the id and then writes it into the file
	 * @param inputFile
	 * @return
	 */
	public int writeRentalId(File inputFile) {
		int count;
		int rId = getRentalId(inputFile);
		count = rId;
		rId++;
		setRentalId(rId, inputFile);
		return count;
	}
}