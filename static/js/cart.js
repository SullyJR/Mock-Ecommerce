/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
"use strict";

var SalesApi = "/api/sales";

class SaleItem {
	constructor(product, quantityPurchased) {
		this.product = product;
		this.quantityPurchased = quantityPurchased;
		this.salePrice = product.listPrice;
	}
}

class Sale {
	constructor(customer, items) {
		this.customer = customer;
		this.items = items;
	}
}


const app = Vue.createApp({

    data() {
        return {
            // models (comma separated key/value pairs)

        };
    },

    computed: Vuex.mapState({
	    product: 'selectedProduct',
	    items: 'items',
	    customer: 'customer'
    }),


    mounted() {
        // semicolon separated statements


    },

    methods: {
        // comma separated function declarations
        addToCart(){
            dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
            window.location = 'view-products.html';
        },
        
        getItemTotal(item){
            const price = item.product.listPrice * item.quantityPurchased;
            return this.formatCurrency(price);
        },
        
        getCartTotal(){
            let total = 0.00;
            this.items.forEach((item) => {
                total = total + (item.product.listPrice * item.quantityPurchased);
            });
            return this.formatCurrency(total);
        },
        
        checkout() {
            let sale = new Sale(this.customer, this.items);
            axios.post(SalesApi, sale)
                    .then(() => {
                        dataStore.commit("clearItems");
                        window.location = 'order-confirmed.html';
                    })
                    .catch(error => {
                        console.log(sale);
                        console.error(error);
                    });
        }

    },
    
    // other modules
    mixins: [NumberFormatter, BasicAccessAuthentication] 

});

/* other component imports go here */

// import navigation  menu component
import { navigationMenu } from './navigation-menu.js';
app.component('navmenu', navigationMenu);

// import data store
import { dataStore } from './data-store.js'
app.use(dataStore);	

// import number formatter
import { NumberFormatter } from './number-formatter.js';

// import authentication module
import { BasicAccessAuthentication } from './authentication.js';

// mount the page - this needs to be the last line in the file
app.mount("main");

