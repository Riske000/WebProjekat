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
		prijavaNaTrening: function(training){
			
		}
	}
});