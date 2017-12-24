package com.management.hospital.controller;

public class Mappings {

	//DOCTOR CONTROLLER URLs
	public static final String SHOW_DOCTORS = "/showDoctors";
	public static final String SHOW_A_DOCTOR = "/showDoctors/{id}";
	public static final String ADD_A_DOCTOR = "/addDoctor";
	public static final String UPDATE_A_DOCTOR = "/updateDoctor/{id}";
	public static final String DELETE_A_DOCTOR = "/deleteDoctor/{id}";
	//END OF DOCTOR CONTROLLER URLs
	
	//PATIENT CONTROLLER URLs
	public static final String SHOW_PATIENTS = "/showPatients";
	public static final String SHOW_A_PATIENT = "/showPatients/{id}";
	public static final String ADD_A_PATIENT = "/addPatient";
	public static final String UPDATE_A_PATIENT = "/updatePatient/{id}";
	public static final String DELETE_A_PATIENT = "/deletePatient/{id}";
	public static final String GET_ADD_PATIENT_FORM = "/showAddPatientForm";
	public static final String GET_UPDATE_PATIENT_FORM = "/showUpdatePatientForm/{id}";
	//END OF PATIENT CONTROLLER URLs
	
	//USER CONTROLLER URLs
	public static final String SHOW_USERS 	 = "/showUsers";
	public static final String SHOW_A_USER 	 = "/showUsers/{id}";
	public static final String ADD_A_USER 	 = "/addUser";
	public static final String UPDATE_A_USER = "/updateUser/{id}";
	public static final String DELETE_A_USER = "/deleteUser/{id}";
	//END OF USER CONTROLLER URLs

	//public static final String AFTERWARDS = "/**";
}
