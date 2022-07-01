var app = new Vue({
	el: '#mojProfil',
	data: {
		newUser: {},
		error: ''
	},
	mounted() {
		axios.get('rest/korisnik1/currentUser')
		.then((response) => {this.newUser = response.data})
		},
	methods: {
		updateUser: function (event) {
			axios.put('rest/korisnik1/', this.newUser)
					.then((response) => {
						alert('Podaci su uspesno promenjeni ')
					})
			event.preventDefault();
		}
	}
});