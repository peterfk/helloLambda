/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hellolambda;

import java.util.List;

/**
 *
 * @author peterkipping
 */
public class Employee {

	private String name;
	private List<String> phoneNumbers;

	public Employee(String name, List<String> phoneNumbers) {
		this.name = name;
		this.phoneNumbers = phoneNumbers;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

}
