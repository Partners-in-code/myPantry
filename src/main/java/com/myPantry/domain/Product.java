package com.myPantry.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	private String name;
	private String unit;
	private String type;

	@Transient
	private MultipartFile productImage;

//	@OneToMany(mappedBy = "product")
//	@JsonIgnore
//	private List<productToPantryItem> productToPantryItemList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

//	public List<productToPantryItem> getProductToPantryItemList() {
//		return productToPantryItemList;
//	}
//
//	public void setProductToPantryItemList(List<productToPantryItem> productToPantryItemList) {
//		this.productToPantryItemList = productToPantryItemList;
//	}

}