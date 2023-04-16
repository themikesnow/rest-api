package com.mana.sample.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mana.sample.api.model.view.View;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BasicEntity {
	
	private static final long serialVersionUID = 1L;
	private String line1;
	private String line2;
	private String city;
	private String state;
	
	@ManyToOne
	@JsonView(View.AddressDetails.class)
	@JsonIgnore
	private Person person;
	
}
