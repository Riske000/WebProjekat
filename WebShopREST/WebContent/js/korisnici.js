var app = new Vue({
	el: '#korisnici',
	data: {
		korisnici: null,
		searchIme: "",
		searchPrezime: "",
		searchKorisnickoIme: "",
		loggedUser: {}
	},
	mounted() {
		axios.get('rest/korisnik1')
			.then(response => (this.korisnici = response.data))
		axios.get('rest/korisnik1/currentUser').then((response) => {
			this.loggedUser = response.data
			})
	},
	methods: {
		pretraziKorisnike: function() {
			axios.get('rest/korisnik1/search', { params: { searchIme: this.searchIme, searchPrezime: this.searchPrezime, searchKorisnickoIme: this.searchKorisnickoIme } })
				.then(response => (this.korisnici = response.data))
		}


	}
});