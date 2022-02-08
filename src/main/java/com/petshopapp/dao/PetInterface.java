package com.petshopapp.dao;

import java.util.List;

import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

public interface PetInterface {

	public void commit();
	
	public void insertPetDetails(PetDetails pet);
	
	public void updatePetDetails(PetDetails pet);
	
	public void updatePetStatus(int petId, String status, int adminId);
	
	public List<PetDetails> showAllpetsDetails(Customers customer);
	
	public PetDetails showCurrentPet(int petId);
	
	public List<PetDetails> showNotApprovedPetList();
	
	public List<PetDetails> showMypetdetails(int cusId);
	
	public List<PetDetails> searchPetDetails(String search, int customerId);
	
	public void delete(PetDetails pet);
	
	public void updatePetAvailableQuantity(PetDetails pet);
	
	public String getPetStatus(PetDetails pet);
	
	
}
