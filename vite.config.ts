import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import path from "path"
import tailwindcss from "tailwindcss"
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
})
