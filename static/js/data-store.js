/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
export const dataStore = Vuex.createStore({

	state () {
		// signed in customer
		customer: null;
		
		// the shopping cart items
		items: null;
	},

	mutations: {

		// user signs in
		signIn(state, customer) {
			state.customer = customer;
			state.items = new Array();
		}

	},

	// add session storage persistence
	plugins: [window.createPersistedState({storage: window.sessionStorage})]

});

