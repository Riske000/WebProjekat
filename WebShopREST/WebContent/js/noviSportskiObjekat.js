var app = new Vue({
	el: '#noviSportskiObjekat',
	data: {
		noviSportskiObjekat : {} ,
		novaLokacija: {},
		error: '',
		slobodniMenadzeri: null,
		imaSlobodnih: 'true',
		izabraniMenadzer: {}, 
		prikaziFormu: false,
		userToRegister: {},
		pravimoNovogMenadzera: false
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
		this.userToRegister = {intId: '', korisnickoIme: null, sifra: null, ime: null, prezime: null, pol: null, datumRodjenja: null, uloga: 'menadzer'}
	},
	methods: {
		createSportskiObjekat: function(event) {
			this.error = ""
			if (!this.noviSportskiObjekat.ime || !this.noviSportskiObjekat.tipObjekta) {
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

			this.noviSportskiObjekat.prosecnaOcena = 1
			this.noviSportskiObjekat.lokacija = this.novaLokacija
			axios.post('rest/sportskiObjekti', this.noviSportskiObjekat)
				.then((response) => {
					alert('Uspesno ste kreirali sportski objekat!')
					this.noviSportskiObjekat = response.data;
					this.izabraniMenadzer.sportskiObjekat = this.noviSportskiObjekat;
					axios.put('rest/korisnik1/', this.izabraniMenadzer)
						.then((response) => {
							alert('Sportski objekat dodat menadzeru')
						}).catch(() => {
							alert('Korisnicko ime vec postoji');
							this.error = "This sport object already exists.";
							event.preventDefault();
							return;
						})
				}).catch(() =>{
					alert('This sport object already exists.')
					 this.error = "This sport object already exists.";
					 event.preventDefault();
					 return;
				})

			event.preventDefault();
		},
		selectManager: function(menadzer) {
			this.izabraniMenadzer = menadzer;
		},
		showForm: function(){
		   this.prikaziFormu = true;
	   },
	   createUser: function() {
		this.error = ""
			if (!this.izabraniMenadzer.korisnickoIme || !this.izabraniMenadzer.sifra || !this.izabraniMenadzer.ime || !this.izabraniMenadzer.prezime
			 			|| !this.izabraniMenadzer.pol || !this.izabraniMenadzer.datumRodjenja) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			this.izabraniMenadzer.uloga = 'menadzer';
			axios.post('rest/korisnik1', this.izabraniMenadzer)
				.then((response) => {
					alert('Uspesno ste registrovali novog menadzera!')
					this.izabraniMenadzer = response.data
				}).catch(() =>{
					alert('Korisnicko ime vec postoji!')
					event.preventDefault();
					return;
				})
			event.preventDefault();
	   },
	   uploadImage: function() {
			 var fileData = event.target.files[0];
			 this.noviSportskiObjekat.logoObjekta = fileData.name;
			 
		 }
	}
});