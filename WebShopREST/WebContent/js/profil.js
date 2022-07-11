var app = new Vue({
	el: '#mojProfil',
	data: {
		newUser: {},
		error: '',
		istorijeTreninga: null,
		personalniTreninzi: null,
		grupniTreninzi: null,
		searchObjekat: "",
		pocetniDatum: "",
		pocetnoVreme: "",
		krajnjiDatum: "",
		krajnjeVreme: "",
		pocetno: "",
		krajnje: ""
	},
	mounted() {
		axios.get('rest/korisnik1/currentUser')
			.then((response) => {
				this.newUser = response.data;
				axios.get('rest/istorijaTreninga/getITforUser', { params: { idKorisnika: this.newUser.intId } }).
					then((response) => {
						this.istorijeTreninga = response.data;
					})

				axios.get('rest/trening/getPersonalTrainings', { params: { idKorisnika: this.newUser.intId } }).
				then((response) => {
					this.personalniTreninzi = response.data;
				})

				axios.get('rest/trening/getGroupTrainings', { params: { idKorisnika: this.newUser.intId } }).
				then((response) => {
					this.grupniTreninzi = response.data;
				})
			})
	},
	methods: {
		updateUser: function(event) {
			axios.put('rest/korisnik1/', this.newUser)
				.then((response) => {
					alert('Podaci su uspesno promenjeni ')
				})
			event.preventDefault();
		},
		pretraziTreninge: function(event){
			this.pocetno = this.pocetniDatum + " " + this.pocetnoVreme;
			this.krajnje = this.krajnjiDatum + " " + this.krajnjeVreme;
			axios.get('rest/istorijaTreninga/search', { params: { searchObjekat: this.searchObjekat, pocetno: this.pocetno, krajnje: this.krajnje} })
				.then(response => (this.istorijeTreninga = response.data))
		}
	}
});