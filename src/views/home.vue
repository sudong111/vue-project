<script setup lang="ts">
  import Carousel from "@/components/carousel.vue"
  import Nomination from "@/components/nomination.vue";
  import Suggestion from "@/components/suggestion.vue";
</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <Carousel />
      <Nomination />
      <Suggestion />
    </div>
  </div>
</template>