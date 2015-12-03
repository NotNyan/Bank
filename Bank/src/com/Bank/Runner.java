package com.Bank;

import java.io.FileNotFoundException;

public class Runner {
	public static void main(String args[]) throws FileNotFoundException {
		Bank test = new Bank("Randall Thompson");
		System.out.println(test);
		test.gainAccess(5387);
		System.out.println(test);
	}
}
