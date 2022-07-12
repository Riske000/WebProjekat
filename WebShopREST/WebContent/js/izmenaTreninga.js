var app = new Vue({
	 el: '#izmenaTreninga',
	 data: {
		 trening: {},
		 error: '',
		 treneri: [],
		 izabraniTrener: {}
	 },
	 mounted() {
		 axios.get('rest/trening/getSelected')
			 .then((response) => {
				 this.trening = response.data
				 axios.get('rest/korisnik1/' + this.trening.trenerIntId)
					 .then((response) => {
						 this.izabraniTrener = response.data;
					 })
			 })

		 axios.get('rest/korisnik1/getTreneri')
			 .then((response) => {
				 this.treneri = response.data;
			 })
	 },
	 methods: {
		 izmeniTrening: function(event) {
			this.trening.trenerIntId = this.izabraniTrener.intId;
			axios.put('rest/trening/', this.trening)
			 .then((response) => {
				 alert('Uspesno ste izmenili trening!')
			 })
			 event.preventDefault()
		 },
		  uploadImage: function() {
			 var fileData = event.target.files[0];
			 this.trening.slika = fileData.name;
			 
		 }
	 }
 });