$(document).ready(function() {

	$('#register-form').submit(registracija());
	$('#login-form').submit(logovanje());

});

function registracija() {
	return function(event) {
		event.preventDefault();

		let username = $('input[name="username-reg"]').val();
		let password = $('input[name="password-reg"]').val();
		let ime = $('input[name="name"]').val();
		let prezime = $('input[name="surname"]').val();
		let pol = $('input[name="gender"]').val();
		let datumRodjenja = $('input[name="dateOfBirth"]').val();

		$.ajax({
			type : 'POST',
			url : 'rest/user/register',
			data : JSON.stringify({
				username : username,
				password : password,
				firstName : ime,
				lastName : prezime,
				gender : pol,
				dateOfBirth : datumRodjenja
				
			}),
			contentType : 'application/json',
			success : function() {
				alert('Registracija je uspešna!');
			},
			error : function(message) {
				alert(message.responseText);
			}
		});
	}
}

function logovanje() {
	return function(event) {
		event.preventDefault();
		let username = $('input[name="username-log"]').val();
		let password = $('input[name="password-log"]').val();
		$.ajax({
			type : 'POST',
			url : 'rest/user/login',
			data : JSON.stringify({
				username : username,
				password : password
			}),
			contentType : 'application/json',
			success : function() {
				window.location.href = "http://localhost:8081/DMProject/homepage.html";
				alert('Logovanje je uspešno!');
			},
			error : function(message) {
				alert(message.responseText);
			}
		});
	}
}