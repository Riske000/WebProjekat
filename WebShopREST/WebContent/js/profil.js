var app = new Vue({
	el: '#mojProfil',
	data: {
		newUser: {},
		error: '',
		istorijeTreninga: null,
		personalniTreninzi: null,
		grupniTreninzi: null
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
		}
	}
});