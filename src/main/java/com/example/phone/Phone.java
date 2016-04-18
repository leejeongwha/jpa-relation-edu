package com.example.phone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Phone {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Manufacturer manufacturer;

	private String name;

	/**
	 * 방어코드(양쪽 방향에 모두 값을 입력)
	 * 
	 * @param manufacturer
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		if (this.manufacturer != null) {
			this.manufacturer.getPhones().remove(this);
		}
		this.manufacturer = manufacturer;
		manufacturer.getPhones().add(this);
	}
}
