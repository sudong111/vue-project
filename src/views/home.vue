<script setup lang="ts">
  import Carousel from "@/components/carousel.vue"
  import Nomination from "@/components/nomination.vue";
  import Suggestion from "@/components/suggestion.vue";
</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <Carousel />
      <img v-scroll-bounce class="w-[3rem] lg:w-[5rem]" src="@/assets/icons/arrow-icon.png" alt="" />
      <Nomination />
      <Suggestion />
    </div>
  </div>
</template>