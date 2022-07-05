var app = new Vue({
	el: '#sportskiObjekti',
	data: {
		sportObjects: null,
		searchIme: "",
		searchTip: "",
		searchLokacija: "",
		searchOcena: "",
		loggedUser: {},
		logovan: false,
		error: "",
		intId: ""
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
			this.error = " ";

			if(this.searchOcena == ""){
				this.searchOcena = "1";
			}
			if(this.searchOcena < 1 || this.searchOcena > 5){
				this.error = "Vrednost mora biti izmedju 1 i 5!";
				return;
			}
			axios.get('rest/sportskiObjekti/search', { params: { searchIme: this.searchIme, searchTip: this.searchTip, searchLokacija: this.searchLokacija, 
				searchOcena: this.searchOcena } })
				.then(response => (this.sportObjects = response.data))
		},
		
		Selected: function(sp) {
			axios.post('rest/sportskiObjekti/setSelected', { intId: sp.intId } ).then((response)=>{
				window.location.href = 'http://localhost:8080/WebShopREST/sportskiObjekat.html';
			})
			
		}


	}
});
