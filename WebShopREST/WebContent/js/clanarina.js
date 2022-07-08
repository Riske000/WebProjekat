var app = new Vue({
	el: '#clanarina',
	data: {
		clanarina: {},
		error: '',
		min: Date.now()
	},
	mounted() {
		axios.get('rest/clanarina/getSelected')
			.then((response) => {
				this.clanarina = response.data;
			})
			this.min = new Date().toISOString().split("T")[0];
	},
	methods: {
		platiClanarinu: function() {
			axios.post('rest/clanarina/', this.clanarina)
			.then((response) => {
				this.clanarina = response.data;
			})
			event.preventDefault();
		}
	}
});