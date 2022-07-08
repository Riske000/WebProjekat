var app = new Vue({
	el: '#viewSO',
	data: {
		so: {},
		error: '',
		treninzi:[]
	},
	mounted() {
		axios.get('rest/sportskiObjekti/getSelected')
		.then((response) => {this.so = response.data;
			axios.get('rest/trening/getTreninzi', { params: { idSportskogObjekta: this.so.intId } }).
			then((response) => {
				this.treninzi = response.data;
			})
			})
		},
	methods: {
		prijavaNaTrening: function(t){
			axios.put('rest/korisnik1/cekirajSe')
			.then((response) => {
				axios.post('rest/istorijaTreninga/cekirajSe', { intId: t.intId })
				.then((response) => {
					alert('Uspesno ste se cekirali za trening!')
				})
			}).catch(() => {
				alert('Istekla Vam je clanarina ili ste potrosili sve termine!')
				return;
			})
		}
	}
});