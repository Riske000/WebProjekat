var app = new Vue({
	el: '#noviSportskiObjekatForm',
	data: {
		noviSportskiObjekat : {
			intId: '', ime: null, tipObjekta: null, status: "ne radi", lokacija: {geografskaSirina: '', geografskaDuzina: '', ulica: null, broj: '', mesto: null, postanskiBroj: null},
			logoObjekta:" ", pocetakRadnogVremena: null, krajRadnogVremena: null
		} ,
		error: '',
		slobodniMenadzeri: null,
		imaSlobodnih: 'true'
	},
	mounted() {
		this.noviSportskiObjekat = {
			intId: '', ime: null, tipObjekta: null, status: "ne radi", lokacija: {geografskaSirina: '', geografskaDuzina: '', ulica: null, broj: '', mesto: null, postanskiBroj: null},
			logoObjekta:" ", pocetakRadnogVremena: null, krajRadnogVremena: null
		} 
		axios.get('rest/korisnik1/freeManagers')
			 .then((response) => {this.slobodniMenadzeri = response.data;})
		if(this.slobodniMenadzeri === null){
			this.imaSlobodnih = 'false';
		}
	},
	methods: {
		createSportskiObjekat: function(event) {
			this.error = ""
			if (!this.noviSportskiObjekat.ime || !this.noviSportskiObjekat.tipObjekta || !this.noviSportskiObjekat.lokacija.geografskaSirina
			 			|| !this.noviSportskiObjekat.lokacija.geografskaDuzina || !this.noviSportskiObjekat.lokacija.ulica || !this.noviSportskiObjekat.lokacija.broj 
			 			|| !this.noviSportskiObjekat.lokacija.mesto || !this.noviSportskiObjekat.lokacija.postanskiBroj) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			axios.post('rest/sportskiObjekti', this.noviSportskiObjekat)
				.then((response) => {
					alert('Uspesno ste kreirali sportski objekat!')
				}).catch(() =>{
					alert('Korisnicko ime vec postoji!')
					event.preventDefault();
					return;
				})
			event.preventDefault();
			return;
		}
	}
});