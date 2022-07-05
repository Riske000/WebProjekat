var app = new Vue({
	el: '#mojProfil',
	data: {
		newUser: {},
		error: '',
		istorijeTreninga: null,
		id: ''
	},
	mounted() {
		axios.get('rest/korisnik1/currentUser')
			.then((response) => {
				this.newUser = response.data;
				this.id = this.newUser.intId;
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

		getTreninzi: function() {
			axios.get('rest/istorijaTreninga/getITforUser', { params: { idKorisnika: this.id } }).
				then((response) => {
					this.istorijeTreninga = response.data;
				})
		},
	}
});