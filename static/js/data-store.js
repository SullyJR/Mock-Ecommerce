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
                
                //Selected item to add to cart
                selectedProduct: null;
	},

	mutations: {

		// user signs in
		signIn(state, customer) {
			state.customer = customer;
			state.items = new Array();
		},
                
                // user selects a product
                selectProduct(state, product) {
                    state.selectedProduct = product;
                },
                
                // add item to cart
                addItem(state, item) {
                    state.items.push(item);
                },
                
                // clear cart items
                clearItems(state) {
                    state.items = new Array();
                    console.log("cleared Items");
                }

	},

	// add session storage persistence
	plugins: [window.createPersistedState({storage: window.sessionStorage})]

});

