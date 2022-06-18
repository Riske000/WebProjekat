var app = new Vue({
	el: '#sportskiObjekti',
	data: {
		sportObjects: null
	},
	mounted() {
		axios.get('rest/sportskiObjekti')
			.then(response => (this.sportObjects = response.data))
	},
	methods: {
		
		
		
	}
});
