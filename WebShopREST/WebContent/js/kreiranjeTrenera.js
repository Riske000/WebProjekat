var app = new Vue({
	el: '#kreiranjeTrenera',
	data: {
		userToRegister: {},
		error: ''
	},
	mounted() {
		this.userToRegister = {
			intId: '', korisnickoIme: null, sifra: null, ime: null, prezime: null, pol: null, datumRodjenja: null, uloga: 'trener'
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
					if(response.data === ""){
						alert('Korisnicko ime vec postoji!')
					}else{
						alert('Uspesno ste kreirali trenera!')
					}
				})
			event.preventDefault();
			return;
		}
	}
});