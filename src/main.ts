import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import scrollFade from './directives/scrollFade';
import scrollBounce from './directives/bounce';

import App from './App.vue'
import router from './router'
import './global.css'

const app = createApp(App)

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

app.use(pinia)
app.use(router)
app.directive('scroll-fade', scrollFade)
app.directive('scroll-bounce', scrollBounce)

app.mount('#app')
