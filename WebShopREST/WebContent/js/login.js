var app = new Vue({
	el: '#loginForm',
	data: {
		userToLogin: {},
		error: ''
	},
	mounted() {
		this.userToLogin = {
			intId: '', korisnickoIme: null, sifra: null
		}
	},
	methods: {
		loginUser : function(event) {
			this.error = ""
			if (!this.userToLogin.korisnickoIme || !this.userToLogin.sifra) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			axios.post('rest/korisnik1/login', this.userToLogin)
				.then((response) => {
					alert('Uspesno ste se ulogovali')
					window.location.href = 'http://localhost:8080/WebShopREST/sportObjects.html';
				}).catch(() =>{
					alert('Pogresno korisnicko ime ili sifra!')
					event.preventDefault();
					return;
				})
			event.preventDefault();
			return;
		}
	}
});
