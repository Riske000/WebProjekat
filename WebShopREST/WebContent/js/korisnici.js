var app = new Vue({
	el: '#korisnici',
	data: {
		korisnici: null,
		searchIme: "",
		searchPrezime: "",
		searchKorisnickoIme: "",
		loggedUser: {},
		tipKorisnika: ""
	},
	mounted() {
		axios.get('rest/korisnik1')
			.then(response => (this.korisnici = response.data))
		axios.get('rest/korisnik1/currentUser').then((response) => {
			this.loggedUser = response.data
			})
	},
	methods: {
		pretraziKorisnike: function() {
			axios.get('rest/korisnik1/search', { params: { searchIme: this.searchIme, searchPrezime: this.searchPrezime, searchKorisnickoIme: this.searchKorisnickoIme, 
				tipKorisnika: this.tipKorisnika } })
				.then(response => (this.korisnici = response.data))
		},
		Sortiraj: function(n) {
			//function found on site: https://www.w3schools.com/howto/howto_js_sort_table.asp
			  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
			  table = document.getElementById("tabela");
			  switching = true;
			  dir = "asc";
			  while (switching) {
			    switching = false;
			    rows = table.rows;
			    for (i = 1; i < (rows.length - 1); i++) {
			      shouldSwitch = false;
			      x = rows[i].getElementsByTagName("TD")[n];
			      y = rows[i + 1].getElementsByTagName("TD")[n];
			      if (dir == "asc") {
			        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
			          shouldSwitch = true;
			          break;
			        }
			      } else if (dir == "desc") {
			        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
			          shouldSwitch = true;
			          break;
			        }
			      }
			    }
			    if (shouldSwitch) {
			      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			      switching = true;
			      switchcount ++;
			    } else {
			      if (switchcount == 0 && dir == "asc") {
			        dir = "desc";
			        switching = true;
			      }
			    }
			  }
		}


	}
});