/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
/* global Vue, axios */
var customersUsernameApi = ({username}) => `/api/customers/${username}`;

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            customer: new Object()
            
        };
    },
        
    mounted() {
        // semicolon separated statements


    },
        
    methods: {
        // comma separated function declarations
        signIn() {

            // send GET request
            axios.get(customersUsernameApi({'username':this.customer.username}))
                    .then(response => {
                        // store response in students model
                        this.customer = response.data;
                        dataStore.commit("signIn", this.customer);
                        window.location = 'index.html';
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred - check the console for details.");
                    });

        }

    },
        // other modules
        mixins: []

});

// other component imports go here

// import navigation  menu component
import { navigationMenu } from './navigation-menu.js';
        app.component('navmenu', navigationMenu);

// import data store
import { dataStore } from './data-store.js'
        app.use(dataStore);

// mount the page - this needs to be the last line in the file
        app.mount("main");

