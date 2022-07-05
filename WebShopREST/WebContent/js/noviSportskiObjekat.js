var app = new Vue({
	el: '#noviSportskiObjekat',
	data: {
		noviSportskiObjekat : {} ,
		novaLokacija: {},
		error: '',
		slobodniMenadzeri: null,
		imaSlobodnih: 'true',
		izabraniMenadzer: null, 
		prikaziFormu: false,
		userToRegister: {}
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

			this.izabraniMenadzer.sportskiObjekat = this.noviSportskiObjekat;
			axios.put('rest/korisnik1/', this.izabraniMenadzer)
			.then((response) => {
				alert('Sport object added to manager')
			}).catch(() => {
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
			if (!this.userToRegister.korisnickoIme || !this.userToRegister.sifra || !this.userToRegister.ime || !this.userToRegister.prezime
			 			|| !this.userToRegister.pol || !this.userToRegister.datumRodjenja) {
				this.error = "Sva polja moraju biti uneta!";
				return;
			}
			axios.post('rest/korisnik1', this.userToRegister)
				.then((response) => {
					alert('Uspesno ste registrovali novog menadzera!')
				}).catch(() =>{
					alert('Korisnicko ime vec postoji!')
					return;
				})
			this.izabraniMenadzer = this.userToRegister;
			return;
	   }
	}
});