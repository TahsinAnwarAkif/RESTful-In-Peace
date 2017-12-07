
package com.management.hospital.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@Column(name = "is_available")
	private boolean isAvailable;
	@Column(name = "specialty", nullable = false, length = 255)
	private String specialty;
	@Column(name = "address", nullable = false, length = 255)
	private String address;
	@Column(name = "phone", unique = true, nullable = false, length = 255)
	private String phone;
	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	@Transient
	private List<Patient> patients;

	public Doctor() {

	}

	public Doctor(Doctor other) {
		this.id = other.id;
		this.name =  other.name;
		this.isAvailable =  other.isAvailable;
		this.specialty = other. specialty;
		this.address = other. address;
		this.phone =  other.phone;
		this.email = other. email;
	}

	/*
	 * @JsonManagedReference
	 * 
	 * @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch =
	 * FetchType.EAGER) private List<Patient> patients;
	 */

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

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", isAvailable=" + isAvailable + ", specialty=" + specialty
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + ", patients=" + patients + "]";
	}

}
