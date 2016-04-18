package com.example.phone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Manufacturer {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "manufacturer")
	private List<Phone> phones = new ArrayList<Phone>();

	private String name;
}
