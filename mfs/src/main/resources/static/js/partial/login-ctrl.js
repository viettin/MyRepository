var LoginCtrl = [ '$rootScope', '$scope', '$http', 'transformRequestAsFormPost', '$state', function ($rootScope, $scope, $http, transformRequestAsFormPost, $state) {
	$scope.userData = {
		email : '',
		password : ''
	}
	
	$scope.errorMessage = '';
	
	$scope.doLogin = function () {
		$scope.errorMessage = '';
		//send data cho backend va kiem tra ket qua.
		$http({
	          method  : 'POST',
	          url     : '/login',
	          data    : $scope.userData,
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
	          transformRequest: transformRequestAsFormPost
	         })
	         .then(function(response) {
	        	 if (response.data.status === 'success'){
	        		 $rootScope.userData = response.data.data;
	        		 $state.go('user');
	        	 } else {
	        		 $scope.errorMessage = 'email and password not correct';
	        	 }
	         });
	}
}]