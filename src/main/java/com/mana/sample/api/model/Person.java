package com.mana.sample.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.mana.sample.api.model.view.View;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

	@NotBlank
    @Size(min = 0, max = 50)
	private String firstName;
	
	@NotBlank
    @Size(min = 0, max = 150)
	private String lastName;

	@Nullable
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses;

}
