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
	        		 $state.go('home');
	        	 } else {
	        		 $scope.errorMessage = 'email and password not correct';
	        	 }
	         });
	}
}]

var LogupCtrl = [ '$rootScope', '$scope', '$http', 'transformRequestAsFormPost', '$state', function ($rootScope, $scope, $http, transformRequestAsFormPost, $state) {
	$scope.userData = {
		email : '',
		password : '',
		username:''
	}
	
	$scope.errorMessage = '';
	
	$scope.doLogup = function () {
		$scope.errorMessage = '';
		//send data cho backend va kiem tra ket qua.
		$http({
	          method  : 'POST',
	          url     : '/adduser',
	          data    : $scope.userData,
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
	          transformRequest: transformRequestAsFormPost
	         })
	         .then(function(response) {
	        	 if (response.data.status === 'success'){
	        		 $rootScope.userData = response.data.data;
	        		 $state.go('home');
	        	 } else {
	        		 $scope.errorMessage = 'email and password not correct';
	        	 }
	         });
	}
}]

var DeleteCtrl = [ '$rootScope', '$scope', '$http', 'transformRequestAsFormPost', '$state', function ($rootScope, $scope, $http, transformRequestAsFormPost, $state) {
	$scope.userData1 = {
		email : ''
	}
	
	$scope.errorMessage = '';
	
	$scope.doDelete = function () {
		$scope.errorMessage = '';
		//send data cho backend va kiem tra ket qua.
		$http({
	          method  : 'DELETE',
	          url     : '/delete/'+$scope.userData1.email,
	          data    : $scope.userData,
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
	          transformRequest: transformRequestAsFormPost
	         })
	         .then(function(response) {
	        	 if (response.data.status === 'success'){
	        		 $rootScope.userData = response.data.data;
	        		 $scope.errorMessage = 'good';
	        	 } else {
	        		 $scope.errorMessage = 'email and password not correct';
	        	 }
	         });
	}
}]
var UpdateCtrl = [ '$rootScope', '$scope', '$http', 'transformRequestAsFormPost', '$state', function ($rootScope, $scope, $http, transformRequestAsFormPost, $state) {
	$scope.userData1 = {
		username:'',
		email : '',
		password:''
	}
	
	$scope.errorMessage = '';
	
	$scope.doUpdate = function () {
		$scope.errorMessage = '';
		//send data cho backend va kiem tra ket qua.
		$http({
	          method  : 'PATCH',
	          url     : '/update/'+$scope.userData1.email+'?fullname='+$scope.userData1.username+'&password='+$scope.userData1.password,
	          data    : $scope.userData1.data,
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
	          transformRequest: transformRequestAsFormPost
	         })
	         .then(function(response) {
	        	 if (response.data.status === 'success'){
	        		 $rootScope.userData = response.data.data;
	        		 $scope.errorMessage = 'good';
	        	 } else {
	        		 $scope.errorMessage = 'email and password not correct';
	        	 }
	         });
	}
}]

var myApp = angular.module('myApp', ['ui.router']);

myApp.controller('LoginCtrl', LoginCtrl);
myApp.controller('LogupCtrl', LogupCtrl);
myApp.controller('DeleteCtrl', DeleteCtrl);
myApp.controller('UpdateCtrl', UpdateCtrl);



myApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'template/home.html'
        })
        
        /*.state('login', {
        	url: '/login',
        	templateUrl: 'template/login.html',
    		controller: 'LoginCtrl'
        })*/
        .state('login2', {
        	url: '/login2',
        	templateUrl: 'template/login2.html',
    		controller: 'LoginCtrl'
        })
        .state('register', {
        	url: '/register',
        	templateUrl: 'template/register.html',
        	controller: 'LogupCtrl'
        })
        .state('delete', {
        	url: '/delete',
        	templateUrl: 'template/delete.html',
        	controller: 'DeleteCtrl'
        })
        .state('update', {
        	url: '/update',
        	templateUrl: 'template/update.html',
        	controller: 'UpdateCtrl'
        })

});



myApp.factory(
        "transformRequestAsFormPost",
        function() {
            // I prepare the request data for the form post.
            function transformRequest( data, getHeaders ) {
                var headers = getHeaders();
                headers[ "Content-type" ] = "application/x-www-form-urlencoded; charset=utf-8";
                return( serializeData( data ) );
            }
            // Return the factory value.
            return( transformRequest );
            // ---
            // PRVIATE METHODS.
            // ---
            // I serialize the given Object into a key-value pair string. This
            // method expects an object and will default to the toString() method.
            // --
            // NOTE: This is an atered version of the jQuery.param() method which
            // will serialize a data collection for Form posting.
            // --
            // https://github.com/jquery/jquery/blob/master/src/serialize.js#L45
            function serializeData( data ) {
                // If this is not an object, defer to native stringification.
                if ( ! angular.isObject( data ) ) {
                    return( ( data == null ) ? "" : data.toString() );
                }
                var buffer = [];
                // Serialize each key in the object.
                for ( var name in data ) {
                    if ( ! data.hasOwnProperty( name ) ) {
                        continue;
                    }
                    var value = data[ name ];
                    buffer.push(
                        encodeURIComponent( name ) +
                        "=" +
                        encodeURIComponent( ( value == null ) ? "" : value )
                    );
                }
                // Serialize the buffer and clean it up for transportation.
                var source = buffer
                    .join( "&" )
                    .replace( /%20/g, "+" )
                ;
                return( source );
            }
        }
    );