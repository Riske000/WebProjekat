var app = new Vue({
	el: '#registrationForm',
	data: {
		userToRegister: {},
		error: ''
	},
	mounted() {
		this.userToRegister = {
			intId: '', korisnickoIme: null, sifra: null, ime: null, prezime: null, pol: null, datumRodjenja: null, uloga: 'kupac'
		}
	},
	methods: {
		createUser: function(event) {
			this.error = ""
			if (!this.userToRegister.korisnickoIme || !this.userToRegister.sifra || !this.userToRegister.ime || !this.userToRegister.prezime
			 			|| !this.userToRegister.pol || !this.userToRegister.datumRodjenja) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			axios.post('rest/korisnik1', this.userToRegister)
				.then((response) => {
					alert('Uspesno ste se registrovali!')
				})
			event.preventDefault();
		}
	}
});
