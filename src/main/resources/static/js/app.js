var app = angular.module('RESTINPEACE', ['ngResource']);

app.controller('DoctorPatientController', ['$scope', '$resource',function($scope,$resource) {
    
    function fetchAllDoctors(){													//GET
        $scope.doctors = $resource('http://localhost:8080/doctors'
        ).query(function(data){return data;});
    };
 
    function fetchAllPatients(){												//GET
        $scope.patients = $resource('http://localhost:8080/patients'
        ).query(function(data){return data;});
    };
    
    fetchAllDoctors();
    
    fetchAllPatients();
    
    $scope.refresh = function(){
    	fetchAllDoctors();
    	fetchAllPatients();
    	fetchDoctorsPatients();
    };
    
    $scope.addDoctor = function(){											   //CREATE
    	Doctors = $resource(
    		    "http://localhost:8080/doctors",
    		    {},
    		    {save: {method:'POST',isArray:false}}
    	);
    	
    	var newDoctor = {};
		
		newDoctor.id		    = $scope.personForm.id;
		newDoctor.name 	     = $scope.personForm.name;
		newDoctor.isAvailable= $scope.personForm.isAvailable;
		newDoctor.specialty	 = $scope.personForm.specialty;
		newDoctor.address	 = $scope.personForm.address;
		newDoctor.phone		 = $scope.personForm.phone;
		newDoctor.email		 = $scope.personForm.email;
		
		Doctors.save(newDoctor);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.isAvailable="";
	    $scope.personForm.specialty ="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.addPatient = function(){												//CREATE
    	Patients = $resource(
    		    "http://localhost:8080/patients",
    		    {},
    		    {save: {method:'POST',isArray:false}}
    	);
    	
    	var newPatient     = {};
    	var assignedDoctor = {};
		
		newPatient.id 	    = $scope.personForm.id;
		newPatient.name	    = $scope.personForm.name;
		newPatient.address	= $scope.personForm.address;
		newPatient.phone	= $scope.personForm.phone;
		newPatient.email	= $scope.personForm.email;
		
		
		assignedDoctor.id         = $scope.personForm.AssignedId;
		assignedDoctor.isAvailable= "";
		assignedDoctor.specialty  = "";
		assignedDoctor.address    = "";
		assignedDoctor.phone	  = "";
		assignedDoctor.email	  = "";
		
		
		newPatient.doctor = assignedDoctor; 
		Patients.save(newPatient);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
		$scope.personForm.AssignedId =""; 
    };
    
    $scope.updateDoctor = function(){									//UPDATE
		
    	Doctors = $resource(
    		    "http://localhost:8080/doctors/:id",
    		    {},
    		    {save: {method:'PUT', params: {id: '@id'}}}
    	);
    	
		var updatingDoctor = {};
		
		updatingDoctor.id 			= $scope.personForm.id;
		updatingDoctor.name 		= $scope.personForm.name;
		updatingDoctor.isAvailable	= $scope.personForm.isAvailable;
		updatingDoctor.specialty	= $scope.personForm.specialty;
		updatingDoctor.address		= $scope.personForm.address;
		updatingDoctor.phone		= $scope.personForm.phone;
		updatingDoctor.email		= $scope.personForm.email;
		
		Doctors.save({id: $scope.personForm.id}, updatingDoctor);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.isAvailable="";
	    $scope.personForm.specialty ="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
 $scope.updatePatient = function(){									//UPDATE
		
    	Patients = $resource(
    		    "http://localhost:8080/patients/:id",
    		    {},
    		    {save: {method:'PUT', params: {id: '@id'}}}
    	);
    	
		var updatingPatient = {};
		var assignedDoctor	= {};
		
		updatingPatient.id 			= $scope.personForm.id;
		updatingPatient.name 		= $scope.personForm.name;
		updatingPatient.address		= $scope.personForm.address;
		updatingPatient.phone		= $scope.personForm.phone;
		updatingPatient.email		= $scope.personForm.email;

		assignedDoctor.id         = $scope.personForm.AssignedId;
		assignedDoctor.isAvailable= "";
		assignedDoctor.specialty  = "";
		assignedDoctor.address    = "";
		assignedDoctor.phone	  = "";
		assignedDoctor.email	  = "";
		
		updatingPatient.doctor = assignedDoctor;
		Patients.save({id: $scope.personForm.id}, updatingPatient);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
		$scope.personForm.AssignedId =""; 
    };
    
    $scope.deleteDoctor = function(){								//DELETE
    	Doctors = $resource(
    		    "http://localhost:8080/doctors/:id",
    		    {},
    		    {save: {method:'DELETE', params: {id: '@id'}}}
    	);
    	
			
		Doctors.delete({id: $scope.personForm.id}).then(function successCallback(response) {
			$scope.Message = response;
		}, function errorCallback(response) {
		    
		});
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.isAvailable="";
	    $scope.personForm.specialty ="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.deletePatient = function(){						//DELETE
    	Patients = $resource(
    		    "http://localhost:8080/patients/:id",
    		    {},
    		    {save: {method:'DELETE', params: {id: '@id'}}}
    	);
    	
			
		Patients.delete({id: $scope.personForm.id}).then(function successCallback(response) {
			$scope.Message = response;
		}, function errorCallback(response) {
		    
		});
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.fetchDoctorsPatients = function()					//GET
    {
    	
    	Patients = $resource("http://localhost:8080/doctors/:id/patients");

    	$scope.patientsOfDoctor = Patients.query({id: $scope.personForm.doctorId},function(data){return data;});
    	
    };
    
    $scope.deleteDoctorsPatient = function(){					//DELETE
    	
    	Patients = $resource(
    		    "http://localhost:8080/doctors/:doctorId/patients/:patientId",
    		    {},
    		    {save: {method:'DELETE', params: {doctorId:'@id', patientId: '@id'}}}
    	);
		Patients.delete({ patientId: $scope.personForm.patientId }).then(function successCallback(response) {
			$scope.Message = response;
		}, function errorCallback(response) {
		    
		});
		
                $scope.personForm.doctorId = "";		
		$scope.personForm.patientId = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";

    };
    
    $scope.addPatientOfADoctor = function(){												//CREATE
    	Patients = $resource(
    		    "http://localhost:8080/doctors/:id/patients",
    		    {},
    		    {save: {method:'POST',isArray:false}}
    	);
    	
    	var newPatient = {};
		
		newPatient.id 	    = $scope.personForm.id;
		newPatient.name	    = $scope.personForm.name;
		newPatient.address	= $scope.personForm.address;
		newPatient.phone	= $scope.personForm.phone;
		newPatient.email	= $scope.personForm.email;
		
		Patients.save({id: $scope.personForm.doctorId}, newPatient);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.updateDoctorsPatient = function(){							//UPDATE
    	
    	Patients = $resource(
    		    "http://localhost:8080/doctors/:doctorId/patients/:patientId",
    		    {},
    		    {save: {method:'PUT', params: {patientId: '@id',doctorId: '@id'}}}
    	);
    	
		var updatingPatient = {};
		
		updatingPatient.id 	   	    = $scope.personForm.patientId;
		updatingPatient.name 		= $scope.personForm.name;
		updatingPatient.address		= $scope.personForm.address;
		updatingPatient.phone		= $scope.personForm.phone;
		updatingPatient.email		= $scope.personForm.email;
		updatingPatient.doctorId    = $scope.personForm.doctorId;
		
		Patients.save({doctorId: $scope.personForm.doctorId}, updatingPatient);
				
		$scope.personForm.patientId = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
		$scope.personForm.doctorId= "";
    };
}]);