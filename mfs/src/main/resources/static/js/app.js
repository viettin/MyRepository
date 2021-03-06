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

var LogoutCtrl = [ '$rootScope', '$scope', '$http', 'transformRequestAsFormPost', '$state', function ($rootScope, $scope, $http, transformRequestAsFormPost, $state) {
	/*$scope.userData = {
		email : '',
		password : ''
	}*/
	
	/*$scope.errorMessage = '';*/
	
	$scope.doLogin = function () {
		$scope.errorMessage = '';
		//send data cho backend va kiem tra ket qua.
		$http({
	          method  : 'GET',
	          url     : '/logout',
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
		fullname:''
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
	$scope.userData = {
		email : ''
	}
	
	$scope.errorMessage = '';
	
	/*$scope.deleteUser = function (email) {
		alert(email);
	}*/
	
	$scope.doDelete = function (email) {
		$scope.errorMessage = '';
		//send data cho backend va kiem tra ket qua.
		//alert(email);
		$http({
	          method  : 'DELETE',
	          url     : '/delete/'+email,
	          data    : $scope.userData,
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
	          transformRequest: transformRequestAsFormPost
	         })
	         .then(function(response) {
	        	 if (response.data.status === 'success'){
	        		 $rootScope.userData = response.data.data;
	        		 $scope.errorMessage = 'good';
	        	 } else {
	        		 $scope.errorMessage = 'email not correct';
	        	 }
	         }).catch((err)=>{
	        	 console.log(err);
	         });
	}
	
	$scope.init = function () {
		$http({
	          method  : 'GET',
	          url     : '/admin'
	         })
	         .then(function(response) {
	        	 $scope.listUser = response.data;
	         });
	}
	
	$scope.init();
	
}]

var InitCtrl = [ '$rootScope', '$scope', '$http', 'transformRequestAsFormPost', '$state', function ($rootScope, $scope, $http, transformRequestAsFormPost, $state) {
	
	
	

	$scope.init1 = function () {
		$http({
	          method  : 'GET',
	          url     : '/file'
	         })
	         .then(function(response) {
	        	 $scope.listFile = response.data;
	        	 $scope.listFile = $scope.listFile.data; 
	         }).catch((err)=>{
	        	 console.log(err);
	         });
	}
	
	$scope.init1();
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
	        		 $state.go('manage-user');
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
myApp.controller('InitCtrl', InitCtrl);
myApp.controller('LogoutCtrl', LogoutCtrl);
/*myApp.controller('UploadCtrl', UploadCtrl);*/





myApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'template/home.html',
            controller: 'InitCtrl'
        })
        
        .state('login', {
        	url: '/login',
        	templateUrl: 'template/login.html',
    		controller: 'LoginCtrl'
        })
        .state('logout', {
        	url: '/logout',
        	templateUrl: 'template/home.html',
    		controller: 'LogoutCtrl'
        })
        .state('register', {
        	url: '/register',
        	templateUrl: 'template/register.html',
    		controller: 'LogupCtrl'
        })
        
        .state('manage-user', {
        	url: '/manage-user',
        	templateUrl: 'template/manage-user.html',
        	controller: 'DeleteCtrl'
        })
        .state('update', {
        	url: '/update',
        	templateUrl: 'template/update.html',
        	controller: 'UpdateCtrl'
        })
       /* .state('upload', {
        	url: '/upload',
        	templateUrl: 'template/uploadfile.html',
        	controller: 'UploadCtrl'
        })*/

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