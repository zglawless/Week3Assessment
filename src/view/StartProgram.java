/**
 * @author zakgl - zglawless
 * CIS175 - Fall 2021
 * Sep 15, 2022
 */
package view;

import java.util.List;
import java.util.Scanner;

import controller.DonorHelper;
import model.Donors;

/**
 * @author zakgl
 *
 */
public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static DonorHelper d = new DonorHelper();
	
	private static void addADonor() {
		System.out.print("Enter a name: ");
		String name = in.nextLine();
		System.out.print("Enter an amount: ");
		Double amount = in.nextDouble();
		Donors toAdd = new Donors(name, amount);
		d.insertAmount(toAdd);
	}
	
	private static void deleteADonor() {
		System.out.print("Enter the name to delete: ");
		String name = in.nextLine();
		System.out.print("Enter the amount to delete: ");
		Double amount = in.nextDouble();
		Donors toDelete = new Donors(name, amount);
		d.deleteDonor(toDelete);
	}
	
	private static void editADonor() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Name");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Donors> foundDonors = null;
		if (searchBy == 1) {
			System.out.print("Enter the name: ");
			String name = in.nextLine();
			foundDonors = d.searchForDonorByName(name);
			
		} 

		if (!foundDonors.isEmpty()) {
			System.out.println("Found Results.");
			for (Donors l : foundDonors) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Donors toEdit = d.searchForDonorById(idToEdit);
			System.out.println("Retrieved " + toEdit.getName() + ": " + toEdit.getAmount());
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Amount");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else if (update == 2) {
				System.out.print("New Amount: ");
				Double newAmount = in.nextDouble();
				toEdit.setAmount(newAmount);
			}

			d.updateDonor(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}
	
	private static void viewTheList() {
		List<Donors> allDonors = d.showAllDonors();
		for(Donors singleDonor : allDonors) {
			System.out.println(singleDonor.returnDonorDetails());
		}
		
	}
	public static void main(String[] args) {
		
		boolean goAgain = true;
		System.out.println("--- Welcome to the insertcoolcharity Donor List ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a Donor");
			System.out.println("*  2 -- Edit a Donor");
			System.out.println("*  3 -- Delete a Donor");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addADonor();
			} else if (selection == 2) {
				editADonor();
			} else if (selection == 3) {
				deleteADonor();
			} else if (selection == 4) {
				viewTheList();
			} else {
				d.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}
		

	}

}
