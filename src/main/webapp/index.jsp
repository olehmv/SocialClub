<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Registration Form</title>
<style>
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.username.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="SocialClub" class="ng-cloak">
	<div class="generic-container"
		ng-controller="RegistrationController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Profile Registration Form </span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="text" ng-model="ctrl.profile.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="name">Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.profile.username" id="name"
									class="username form-control input-sm"
									placeholder="Enter your name" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.name.$error.required">This is a
										required field</span> <span ng-show="myForm.name.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.name.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>



					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="email">Email</label>
							<div class="col-md-7">
								<input type="email" ng-model="ctrl.profile.email" id="email"
									class="email form-control input-sm"
									placeholder="Enter your Email" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.email.$error.required">This is a
										required field</span> <span ng-show="myForm.email.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="password">Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.profile.password" id="password"
									class="username form-control input-sm"
									placeholder="Enter your password" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.password.$error.required">This is a
										required field</span> <span ng-show="myForm.password.$error.minlength">Minimum
										length required is 3</span> <span ng-show="myForm.password.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.profile.id ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Users </span>
		</div>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID.</th>
						<th>Name</th>
						<th>Email</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="p in ctrl.profiles">
						<td><span ng-bind="p.id"></span></td>
						<td><span ng-bind="p.username"></span></td>
						<td><span ng-bind="p.email"></span></td>
						<td>
							<button type="button" ng-click="ctrl.edit(p.id)"
								class="btn btn-success custom-width">Edit</button>
							<button type="button" ng-click="ctrl.remove(p.id)"
								class="btn btn-danger custom-width">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
	<script src="<c:url value='/static/js/module/social_club.js' />"></script>
	<script src="<c:url value='/static/js/service/profile_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/registration_controller.js' />"></script>
</body>
</html>