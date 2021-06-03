
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import RoomManager from "./components/RoomManager"

import BookingManager from "./components/BookingManager"

import PaymentManager from "./components/PaymentManager"

import MsgManager from "./components/MsgManager"


import Mypage from "./components/mypage"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/Room',
                name: 'RoomManager',
                component: RoomManager
            },

            {
                path: '/Booking',
                name: 'BookingManager',
                component: BookingManager
            },

            {
                path: '/Payment',
                name: 'PaymentManager',
                component: PaymentManager
            },

            {
                path: '/Msg',
                name: 'MsgManager',
                component: MsgManager
            },


            {
                path: '/mypage',
                name: 'mypage',
                component: mypage
            },


    ]
})
