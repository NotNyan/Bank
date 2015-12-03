package com.Bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

public class Bank {
	final String name;
	private int Pin;
	private double USD;
	private boolean Unlocked = false;
	private File file = new File("/users/hank/test.txt");
	private Deposit deposit;

	public Bank(String name) {
		this.name = name;
		if(hasPin()) {
			System.out.println(name+" has a pin already");
			setPin(getPin());}
		else{
			System.out.println("No pin found... Creating one for you.");
			setPin(getRandomNumberInRange(1000, 9999));
			writePin();
		}
	}
	private boolean hasPin() {
		String line;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				line = reader.readLine();
				System.out.println(line);
				reader.close();
				if(line == null){return false;}
				if(line.length() < 4){return false;}
				if(Pattern.matches("[a-zA-Z.?]+", line) == true){return false;}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
	}
	public String getName() {
		return name;
	}
	private int getPin() {
		String line = null;
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				line = reader.readLine();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Integer.parseInt(line);

	}
	
	private void writePin() {
		String line = Integer.toString(Pin);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(line);
			writer.close();
			System.err.println("Written Pin down for you in: "+file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setPin(int pin) {
		Pin = pin;
	}
	
	public void gainAccess(int PinNum) {
		if (PinNum == Pin)
			Unlocked = true;
		else 
			System.out.println("Access Denied.");
	}
	
	private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public String toString() {
		if(Unlocked){return name + " | Money: "+USD;}
		else {return name + " #LOCKED#";}
		
	}
}
