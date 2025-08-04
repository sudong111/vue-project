import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home.vue'
import Acoustic from '@/views/acoustic.vue'
import Electric from '@/views/electric.vue'
import Base from '@/views/base.vue'
import Etc from "@/views/etc.vue";
import Login from '@/views/login.vue'
import SignUp from '@/views/sign-up.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/acoustic',
      name: 'acoustic',
      component: Acoustic
    },
    {
      path: '/electric',
      name: 'electric',
      component: Electric
    },
    {
      path: '/base',
      name: 'base',
      component: Base
    },
    {
      path: '/etc',
      name: 'etc',
      component: Etc
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/sign-up',
      name: 'sign-up',
      component: SignUp
    }
  ],
})

export default router
