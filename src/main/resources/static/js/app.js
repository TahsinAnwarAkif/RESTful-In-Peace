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
    	User = $resource(
    		    "http://localhost:8080/doctors",
    		    {},
    		    {save: {method:'POST',isArray:false}}
    	);
    	
    	var user = {};
		
		user.id		    = $scope.personForm.id;
		user.name 	    = $scope.personForm.name;
		user.isAvailable= $scope.personForm.isAvailable;
		user.specialty	= $scope.personForm.specialty;
		user.address	= $scope.personForm.address;
		user.phone		= $scope.personForm.phone;
		user.email		= $scope.personForm.email;
		
		User.save(user);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.isAvailable="";
	    $scope.personForm.specialty ="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.addPatient = function(){												//CREATE
    	User = $resource(
    		    "http://localhost:8080/patients",
    		    {},
    		    {save: {method:'POST',isArray:false}}
    	);
    	
    	var user = {};
		
		user.id 	    = $scope.personForm.id;
		user.name	    = $scope.personForm.name;
		user.address	= $scope.personForm.address;
		user.phone		= $scope.personForm.phone;
		user.email		= $scope.personForm.email;
		
		User.save(user);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.updateDoctor = function(){									//UPDATE
		
    	User = $resource(
    		    "http://localhost:8080/doctors/:id",
    		    {},
    		    {save: {method:'PUT', params: {id: '@id'}}}
    	);
    	
		var user = {};
		
		user.id = $scope.personForm.id;
		user.name = $scope.personForm.name;
		user.isAvailable= $scope.personForm.isAvailable;
		user.specialty	= $scope.personForm.specialty;
		user.address	= $scope.personForm.address;
		user.phone		= $scope.personForm.phone;
		user.email		= $scope.personForm.email;
		
		User.save({id: $scope.personForm.id}, user);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.isAvailable="";
	    $scope.personForm.specialty ="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
 $scope.updatePatient = function(){									//UPDATE
		
    	User = $resource(
    		    "http://localhost:8080/patients/:id",
    		    {},
    		    {save: {method:'PUT', params: {id: '@id'}}}
    	);
    	
		var user = {};
		
		user.id = $scope.personForm.id;
		user.name = $scope.personForm.name;
		user.address	= $scope.personForm.address;
		user.phone		= $scope.personForm.phone;
		user.email		= $scope.personForm.email;
		
		User.save({id: $scope.personForm.id}, user);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.deleteDoctor = function(){								//DELETE
    	User = $resource(
    		    "http://localhost:8080/doctors/:id",
    		    {},
    		    {save: {method:'DELETE', params: {id: '@id'}}}
    	);
    	
			
		User.delete({id: $scope.personForm.id}).then(function successCallback(response) {
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
    	User = $resource(
    		    "http://localhost:8080/patients/:id",
    		    {},
    		    {save: {method:'DELETE', params: {id: '@id'}}}
    	);
    	
			
		User.delete({id: $scope.personForm.id}).then(function successCallback(response) {
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
    	
    	User = $resource("http://localhost:8080/doctors/:id/patients");

    	$scope.patientsOfDoctor = User.query({id: $scope.personForm.doctorId},function(data){return data;});
    	
    };
    
    $scope.deleteDoctorsPatient = function(){					//DELETE
    	
    	User = $resource(
    		    "http://localhost:8080/patients/:id",
    		    {},
    		    {save: {method:'DELETE', params: {id: '@id'}}}
    	);
    	
			
		User.delete({id: $scope.personForm.patientId}).then(function successCallback(response) {
			$scope.Message = response;
		}, function errorCallback(response) {
		    
		});
				
		$scope.personForm.patientId = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";

    };
    
    $scope.addPatientOfADoctor = function(){												//CREATE
    	User = $resource(
    		    "http://localhost:8080/doctors/:id/patients",
    		    {},
    		    {save: {method:'POST',isArray:false}}
    	);
    	
    	var user = {};
		
		user.id 	    = $scope.personForm.id;
		user.name	    = $scope.personForm.name;
		user.address	= $scope.personForm.address;
		user.phone		= $scope.personForm.phone;
		user.email		= $scope.personForm.email;
		
		User.save({id: $scope.personForm.doctorId}, user);
				
		$scope.personForm.id = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    };
    
    $scope.updateDoctorsPatient = function(){							//UPDATE
    	
    	User = $resource(
    		    "http://localhost:8080/doctors/:doctorId/patients/:patientId",
    		    {},
    		    {save: {method:'PUT', params: {patientId: '@id',doctorId: '@id'}}}
    	);
    	
		var user = {};
		
		user.id 	    = $scope.personForm.patientId;
		user.name 		= $scope.personForm.name;
		user.address	= $scope.personForm.address;
		user.phone		= $scope.personForm.phone;
		user.email		= $scope.personForm.email;
		
		User.save({doctorId: $scope.personForm.doctorId}, user);
				
		$scope.personForm.patientId = "";
		$scope.personForm.name="";
		$scope.personForm.address="";
		$scope.personForm.phone="";
		$scope.personForm.email="";
    	
    };
    
    $scope.fetchPatientsDoctor = function(){
    	
    	User = $resource("http://localhost:8080/patients/:id/doctor");

    	$scope.doctorOfPatient = User.query({id: $scope.personForm.patientId2},function(data){return data;});
    	
    }
    
}]);