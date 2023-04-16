package com.mana.sample.api.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.mana.sample.api.model.view.View;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;


@Data
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = { "id" }, alphabetic = true)
@ToString 
public abstract class BasicEntity implements Cloneable, Serializable {
    
	private static final long serialVersionUID = 4540504797372682270L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonView({ View.Id.class } ) 
	protected Long id;
	
    
}