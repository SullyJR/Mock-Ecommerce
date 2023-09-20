/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
"use strict";

export const navigationMenu = {

    computed: {
        signedIn() {
            return this.customer != null;
        },
        ...Vuex.mapState({
                customer: 'customer'
        })
    },

    template:
         // v-if="signedIn" this goes in view products after debugging
            `
	<nav>
		<p><a href="." class="navbtn">Home</a></p>
		<p><a href="view-products.html" v-if="signedIn" class="navbtn">Browse Products</a></p>
		<p><a href="cart.html" v-if="signedIn" class="navbtn">View Cart</a></p>
		<p><a href="#" v-if="signedIn" @click="signOut()" class="navbtn">Sign Out</a></p>
		<p><a href="sign-in.html" v-if="!signedIn" class="navbtn">Sign In</a></p>
                <p v-if="signedIn" class="welcome">Welcome, {{customer.firstName}}</p>
	</nav>
	`,

    methods: {
        signOut() {
            sessionStorage.clear();
            window.location = '.';
        }
    }
};	