
package com.management.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false, length=255)
    private String name;
    @Column(name="address", nullable = false, length=255)
    private String address;
    @Column(name="phone",unique = true, nullable = false, length=255)
    private String phone;
    @Column(name="email",unique = true, nullable = false, length=255)
    private String email;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    public Patient() {
    	
    }
    
    public Patient(Patient other) {
		this.id = other.id;
		this.name =  other.name;
		this.address = other. address;
		this.phone =  other.phone;
		this.email = other. email;
	}

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", doctor=" + doctor + "]";
	}
    
    
}
