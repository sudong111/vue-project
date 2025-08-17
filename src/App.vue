<script setup lang="ts">
import Header from "@/components/header.vue";
import Footer from "@/components/footer.vue";
</script>

<template>
  <Header />
  <div class="mt-[4rem]">
    <div class="flex flex-col">
      <router-view />
    </div>
  </div>
  <Footer />
</template>

<style scoped></style>
