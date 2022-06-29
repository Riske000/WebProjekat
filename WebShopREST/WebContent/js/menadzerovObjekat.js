var app = new Vue({
	el: '#sportskiObjekat',
	data: {
		loggedUser: {},
		sportskiObjekat: {},
		treneri: null,
		kupci: null
	},
	mounted() {
		axios.get('rest/korisnik1/currentUser').then((response) => {
			this.loggedUser = response.data
			})
		axios.get('rest/sportskiObjekti/getSportsObject', { params: { id: this.sportskiObjekat.intId } }).
			then((response) => {
			this.sportskiObjekat = response.data
			})
		axios.get('rest/sportskiObjekti/getSportsObject', { params: { id: this.sportskiObjekat.intId } }).
			then((response) => {
			this.sportskiObjekat = response.data
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