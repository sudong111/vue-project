import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home.vue'
import Login from '@/views/login.vue'
import SignUp from '@/views/sign-up.vue'
import Products from '@/views/products.vue'
import ProductDetail from "@/views/product-detail.vue";
import Management from "@/views/management.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/products',
      name: 'products',
      component: Products,
    },
    {
      path: '/products/detail',
      name: 'product-detail',
      component: ProductDetail,
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
    },
    {
      path: '/management',
      name: 'management',
      component: Management
    }
  ],
})

export default router
