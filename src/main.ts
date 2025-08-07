import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import scrollFade from './directives/scrollFade';
import scrollBounce from './directives/bounce';
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

import App from './App.vue'
import router from './router'
import './global.css'

const vuetify = createVuetify({
    components,
    directives,
})

const app = createApp(App)

const pinia = createPinia()

pinia.use(piniaPluginPersistedstate)

app.use(vuetify)
app.use(pinia)
app.use(router)
app.directive('scroll-fade', scrollFade)
app.directive('scroll-bounce', scrollBounce)

app.mount('#app')
