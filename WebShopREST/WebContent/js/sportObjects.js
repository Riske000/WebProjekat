var app = new Vue({
	el: '#sportskiObjekti',
	data: {
		sportObjects: null,
		searchIme: "",
		searchTip: "",
		searchLokacija: "",
		searchOcena: "1",
		loggedUser: {},
		logovan: false
	},
	mounted() {
		axios.get('rest/sportskiObjekti')
			.then(response => (this.sportObjects = response.data))
		axios.get('rest/korisnik1/currentUser').then((response) => {
			this.loggedUser = response.data
			this.logovan = true
			})
	},
	methods: {
		pretraziOjekte: function() {
			axios.get('rest/sportskiObjekti/search', { params: { searchIme: this.searchIme, searchTip: this.searchTip, searchLokacija: this.searchLokacija, 
				searchOcena: this.searchOcena } })
				.then(response => (this.sportObjects = response.data))
		}


	}
});
