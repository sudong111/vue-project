<script setup lang="ts">

import {Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious} from "@/components/ui/carousel";
import { useNavigation } from "@/utils/navigation"
import ElectricBanner from '@/assets/banners/electric-guitar-1.jpg'
import AcousticBanner from '@/assets/banners/acoustic-guitar-1.jpg'
import BaseBanner from '@/assets/banners/bass-guitar-1.jpg'
const { handleViewButtonClicked } = useNavigation();
</script>

<template>
  <Carousel class="carousel">
    <CarouselContent class="h-full">
      <CarouselItem>
        <div
            class="flex w-full h-full bg-cover bg-center cursor-pointer"
            :style="{backgroundImage: `url(${ElectricBanner})`}"
            @click="handleViewButtonClicked('electric')"
        >
          <div class="flex flex-col w-full justify-center items-center select-none">
            <p class="text-5xl font-extralight text-white" style="font-family: 'Playwrite AU NSW', sans-serif;">
              PEOZ Smart Electric Guitar TR3
            </p>
            <p class="text-3xl mt-5 text-white" style="font-family: 'Nanum Brush Script', sans-serif;">
              혁신적인 일렉기타
            </p>
          </div>
        </div>
      </CarouselItem>
      <CarouselItem>
        <div
            class="flex w-full h-full bg-cover bg-center cursor-pointer"
            :style="{backgroundImage: `url(${AcousticBanner})`}"
            @click="handleViewButtonClicked('acoustic')"
        >
          <div class="flex flex-col w-full justify-center items-center select-none">
            <p class="text-8xl font-extralight text-white" style="font-family: 'Amatic SC', sans-serif;">
              YAMAHA Acoustic Guitar F400
            </p>
            <p class="text-3xl mt-5 text-white" style="font-family: 'Nanum Brush Script', sans-serif;">
              풍부한 음색, 합리적인 가격, 입문 어쿠스틱 기타
            </p>
          </div>
        </div>
      </CarouselItem>
      <CarouselItem>
        <div
            class="flex w-full h-full bg-cover bg-center cursor-pointer"
            :style="{backgroundImage: `url(${BaseBanner})`}"
            @click="handleViewButtonClicked('base')"
        >
          <div class="flex flex-col w-full justify-center items-center select-none">
            <p class="text-8xl font-extralight text-white" style="font-family: 'Bebas Neue', sans-serif;">
              Vintage V495
            </p>
            <p class="text-3xl mt-5 text-white" style="font-family: 'Nanum Brush Script', sans-serif;">
              " This is Base "
            </p>
          </div>
        </div>
      </CarouselItem>
    </CarouselContent>
    <CarouselPrevious />
    <CarouselNext />
  </Carousel>
</template>

<style scoped>

</style>