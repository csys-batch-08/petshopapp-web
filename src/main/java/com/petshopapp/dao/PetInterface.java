package com.petshopapp.dao;

import java.util.List;

import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;

public interface PetInterface {

	public void insert(PetDetails pet);
	
	public void update(PetDetails pet);
	
	public void updateStatus(int petId,String status,int adminId);
	
	public List<PetDetails> showAllpets(Customers customer);
	
	public PetDetails showPet(int petId);
	
	public List<PetDetails> showNotApprovedPetList();
	
	public List<PetDetails> showMypetdetails(int cusId);
	
	public List<PetDetails> searchPetDetails(String search,int customerId);
	
	public void delete(PetDetails pet) ;
	
	public void updatePetAviQty(PetDetails pet);
	
	public String getPetStatus(PetDetails pet);
	
}
