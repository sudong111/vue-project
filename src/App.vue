<script setup lang="ts">
import Header from "@/components/header.vue";
import Footer from "@/components/footer.vue";
</script>

<template>
  <Header />
  <div class="my-[3rem]">
    <router-view />
  </div>
  <Footer />
</template>

<style scoped></style>
