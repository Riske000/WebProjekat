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
		createUser: function (event) {
			
		}
	}
});