var app = new Vue({
	el: '#mojProfil',
	data: {
		newUser: {},
		error: '',
		istorijeTreninga: null,
	},
	mounted() {
		axios.get('rest/korisnik1/currentUser')
			.then((response) => {
				this.newUser = response.data;
				axios.get('rest/istorijaTreninga/getITforUser', { params: { idKorisnika: this.newUser.intId } }).
					then((response) => {
						this.istorijeTreninga = response.data;
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