var app = new Vue({
	el: '#sportskiObjekti',
	data: {
		sportObjects: null,
		searchValue: "",
		criterium: "",
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
			axios.get('rest/sportskiObjekti/search', { params: { searchValue: this.searchValue, criterion: this.criterium } })
				.then(response => (this.sportObjects = response.data))
		}


	}
});
