package com.myPantry.domain;

public class Pantry {
	private int id_user;
	private int id_product;
	private int quantity;

	public Pantry() {
	}

	public Pantry(int id_u, int id_p, int quant) {
		this.id_user = id_u;
		this.id_product = id_p;
		this.quantity = quant;
	}

	public Pantry(Pantry p) {
		this.id_user = p.getIdUser();
		this.id_product = p.getIdProduct();
		this.quantity = p.getQuantity();
	}

	public void setIdUser(int id_u) {
		this.id_user = id_u;
	}

	public int getIdUser() {
		return this.id_user;
	}

	public void setIdProduct(int id_p) {
		this.id_product = id_p;
	}

	public int getIdProduct() {
		return this.id_product;
	}

	public void setQuantity(int q) {
		this.quantity = q;
	}

	public int getQuantity() {
		return this.quantity;
	}
}
