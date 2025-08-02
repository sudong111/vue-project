<script setup lang="ts">

import {Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious} from "@/components/ui/carousel";
</script>

<template>
  <Carousel class="carousel">
    <CarouselContent class="h-full">
      <CarouselItem>
        <div class="carousel-item bg-gray-200">
          test 1
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="carousel-item bg-blue-200">
          test 2
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="carousel-item bg-red-200">
          test 3
        </div>
      </CarouselItem>
    </CarouselContent>
    <CarouselPrevious />
    <CarouselNext />
  </Carousel>
</template>

<style scoped>

</style>