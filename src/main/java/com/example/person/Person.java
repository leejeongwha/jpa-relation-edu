package com.example.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Job job;

	private String name;
}
