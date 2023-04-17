package com.mana.sample.api.model.view;

public interface View {
	
    public interface Id {};
    
    public interface Base extends Id {};
	
	public interface PersonDetails extends Base {};
	public interface AddressDetails extends Base {};
	
}
