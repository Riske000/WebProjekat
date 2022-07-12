var app = new Vue({
	 el: '#dodavanjeTreninga',
	 data: {
		 trening: {},
		 error: '',
		 treneri: [],
		 izabraniTrener: {}
	 },
	 mounted() {
		 
		 axios.get('rest/korisnik1/getTreneri')
			 .then((response) => {
				 this.treneri = response.data;
			 })
	 },
	 methods: {
		 dodajTrening: function(event) {
			this.trening.trenerIntId = this.izabraniTrener.intId;
			axios.post('rest/trening/', this.trening)
			 .then((response) => {
				 alert('Uspesno ste dodali trening!')
			 }).catch(() =>{
					alert('Takav naziv vec postoji!')
					event.preventDefault();
					return;
				})
			 event.preventDefault()
		 },
		  uploadImage: function() {
			 var fileData = event.target.files[0];
			 this.trening.slika = fileData.name;
			 
		 }
	 }
 });