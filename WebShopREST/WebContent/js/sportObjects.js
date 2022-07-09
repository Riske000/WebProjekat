var app = new Vue({
	el: '#sportskiObjekti',
	data: {
		sportObjects: [],
		tmp: [],
		searchIme: "",
		searchTip: "",
		searchLokacija: "",
		searchOcena: "",
		loggedUser: {},
		logovan: false,
		error: "",
		intId: "",
		daLiRadi: "ne radi",
		filterStatus: ""
	},
	mounted() {
		axios.get('rest/sportskiObjekti')
			.then((response) => {
				this.tmp = response.data; 
				for(let s of this.tmp){
					if(s.status === 'radi'){
						this.sportObjects.push(s);
					}
				}
				for(let s of this.tmp){
					if(s.status === 'ne radi'){
						this.sportObjects.push(s);
					}
				}
			})
		axios.get('rest/korisnik1/currentUser').then((response) => {
			this.loggedUser = response.data
			this.logovan = true
			})
	},
	methods: {
		pretraziOjekte: function() {
			this.error = " ";

			if(this.searchOcena == ""){
				this.searchOcena = "1";
			}
			if(this.searchOcena < 1 || this.searchOcena > 5){
				this.error = "Vrednost mora biti izmedju 1 i 5!";
				return;
			}
			axios.get('rest/sportskiObjekti/search', { params: { searchIme: this.searchIme, searchTip: this.searchTip, searchLokacija: this.searchLokacija, 
				searchOcena: this.searchOcena,  daLiRadi: this.daLiRadi, filterStatus: this.filterStatus} })
				.then(response => (this.sportObjects = response.data))
		},
		
		Selected: function(sp) {
			axios.post('rest/sportskiObjekti/setSelected', { intId: sp.intId } ).then((response)=>{
				window.location.href = 'http://localhost:8080/WebShopREST/sportskiObjekat.html';
			})
			
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
