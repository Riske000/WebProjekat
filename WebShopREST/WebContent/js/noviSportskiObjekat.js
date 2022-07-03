var app = new Vue({
	el: '#noviSportskiObjekatForm',
	data: {
		noviSportskiObjekat : {} ,
		novaLokacija: {},
		error: '',
		slobodniMenadzeri: null,
		imaSlobodnih: 'true',
		izabraniMenadzer: null, 
		prikaziFormu: false
	},
	mounted() {
		 
		axios.get('rest/korisnik1/freeManagers')
			 .then((response) => {console.log(response.data)
								  this.slobodniMenadzeri = response.data
								})
		this.noviSportskiObjekat = {
			intId: '', ime: null, tipObjekta: null, sadrzajObjekta: null,  status: "ne radi", lokacija: null,
			logoObjekta: null, prosecnaOcena: null, pocetakRadnogVremena: null, krajRadnogVremena: null}
		this.novaLokacija = {intId: '', geografskaSirina: null, geografskaDuzina: null, ulica: null, broj: null, mesto: null, postanskiBroj: null}
	},
	methods: {
		createSportskiObjekat: function(event) {
			this.error = ""
			if (!this.noviSportskiObjekat.ime || !this.noviSportskiObjekat.tipObjekta || !this.noviSportskiObjekat.logoObjekta) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			if(!this.novaLokacija.geografskaDuzina || !this.novaLokacija.geografskaSirina || !this.novaLokacija.ulica || !this.novaLokacija.broj || !this.novaLokacija.mesto || !this.novaLokacija.postanskiBroj){
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			if(this.izabraniMenadzer === null){
				this.izabraniMenadzer = this.slobodniMenadzeri[0]
			}

			this.noviSportskiObjekat.lokacija = this.novaLokacija
			axios.post('rest/sportskiObjekti', this.noviSportskiObjekat)
				.then((response) => {
					alert('Uspesno ste kreirali sportski objekat!')
				}).catch(() =>{
					alert('This sport object already exists.')
					 this.error = "This sport object already exists.";
					 event.preventDefault();
					 return;
				})
			event.preventDefault();
			return;
		}
	}
});