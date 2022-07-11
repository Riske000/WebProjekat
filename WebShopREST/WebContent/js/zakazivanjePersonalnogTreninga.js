var app = new Vue({
	 el: '#zakazivanjeTreninga',
	 data: {
		 trening: {},
		 error: '',
		 treneri: [],
		 objekti: [],
		 izabraniTrener: {},
		 izabraniObjekat: {},
		 datum: null,
		 vreme: null
	 },
	 mounted() {

		 axios.get('rest/korisnik1/getTreneri')
			 .then((response) => {
				 this.treneri = response.data;
				 axios.get('rest/sportskiObjekti/')
			 		.then((response) => {
				 		this.objekti = response.data;
			 		})
				 
			 })
	 },
	 methods: {
		 zakaziTrening: function(event) {
			this.trening.terminTreninga = this.datum + " " + this.vreme;
			this.trening.trenerIntId = this.izabraniTrener.intId;
			this.trening.statusTreninga = 'nije otkazan';
			this.trening.objekatIntId = this.izabraniObjekat.intId;
			axios.post('rest/zakazanTrening/', this.trening)
			 .then((response) => {
				 alert('Uspesno ste zakazali trening!')
			 })
			 event.preventDefault()
		 }
	 }
 });