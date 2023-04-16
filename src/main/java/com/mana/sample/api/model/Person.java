package com.mana.sample.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.mana.sample.api.model.view.View;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonView(View.PersonDetails.class)
@Builder
public class Person extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Address> addresses;

}
