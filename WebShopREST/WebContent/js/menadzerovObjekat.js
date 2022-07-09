var app = new Vue({
	el: '#sportskiObjekat',
	data: {
		loggedUser: {intId: '', korisnickoIme: null, sifra: null, ime: null, prezime: null, pol: null, datumRodjenja: null, uloga: null, istorijaTreninga: null, clanarina: null, poseceniObjekti: null, brojSakupljenihPoena:'', tipKupca: null, sportskiObjekat: {intId: '', ime: null, tipObjekta: null, status: null, lokacija: {geografskaSirina: '', geografskaDuzina: '', ulica: null, broj: '', mesto: null, postanskiBroj: null},
			logoObjekta:" ", pocetakRadnogVremena: null, krajRadnogVremena: null}},
		treneri: null,
		kupci: null,
		id:'',
		treninzi: null
	},
	mounted() {
		axios.get('rest/korisnik1/currentUser').then((response) => {
			this.loggedUser = response.data;
			this.id = this.loggedUser.sportskiObjekat.intId;

			axios.get('rest/trening/getTreneri', { params: { idSportskogObjekta: this.id} }).
			then((response) => {
			this.treneri = response.data;
			})
			axios.get('rest/korisnik1/getKupci', { params: { idSportskogObjekta: this.id} }).
			then((response) => {
			this.kupci = response.data;
			})
			
			axios.get('rest/trening/getTreninzi', { params: { idSportskogObjekta: this.id} }).
			then((response) => {
			this.treninzi = response.data;
			})
			
		})
			
	},
	methods: {
		izmeniTrening: function(tr) {
			axios.post('rest/trening/setSelected', tr)
			 .then((response) => {
				 window.location.href = 'http://localhost:8080/WebShopREST/izmenaTreninga.html';
			 })
		 }
	}
});