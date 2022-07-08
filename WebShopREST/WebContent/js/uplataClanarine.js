var app = new Vue({
	el: '#uplataClanarine',
	data: {
		clanarine: [],
		loggedUser: {}
	},
	mounted() {
		axios.get('rest/korisnik1/currentUser')
			.then((response) => {
				this.loggedUser = response.data;
                clanarina1 = {intId: 1, id: "1", tipClanarine:"dnevna", datumPlacanja: null, pocetniDatumVazenja: null, krajnjiDatumVazenja: null, punaCena:500, kupac: null, status: 'aktivna', brojTermina:1}
                clanarina2 = {intId: 2, id: "2", tipClanarine:"mesecna", datumPlacanja: null, pocetniDatumVazenja: null, krajnjiDatumVazenja: null, punaCena:2500, kupac: null, status: 'aktivna', brojTermina:30}
                clanarina3 = {intId: 3, id: "3", tipClanarine:"godisnja", datumPlacanja: null, pocetniDatumVazenja: null, krajnjiDatumVazenja: null, punaCena:12000, kupac: null, status: 'aktivna', brojTermina:365}
                this.clanarine.push(clanarina1);
                this.clanarine.push(clanarina2);
                this.clanarine.push(clanarina3);
            })
	},
	methods: {
		izabratiClanarinu: function(c) {
			axios.post('rest/clanarina/setSelected', c).then(response => {
				 window.location.href = 'http://localhost:8080/WebShopREST/clanarina.html';
			 })
		}


	}
});