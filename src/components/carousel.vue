<script setup lang="ts">

import {Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious} from "@/components/ui/carousel";
import { useNavigation } from "@/utils/navigation"
import ElectricBanner from '@/assets/banners/electric-guitar-1.jpg'
import AcousticBanner from '@/assets/banners/acoustic-guitar-1.jpg'
import BaseBanner from '@/assets/banners/bass-guitar-1.jpg'

const { handleViewChanged } = useNavigation();
</script>

<template>
  <Carousel class="carousel">
    <CarouselContent class="h-full">
      <CarouselItem>
        <div
            class="carousel-banner"
            :style="{backgroundImage: `url(${ElectricBanner})`}"
            @click="handleViewChanged('electric')"
        >
          <div class="carousel-banner-text">
            <p class="carousel-banner-title text-xl sm:text-4xl lg:text-6xl"
               style="font-family: 'Playwrite AU NSW', sans-serif;">
              PEOZ Smart Electric Guitar TR3
            </p>
            <p class="carousel-banner-desc"
               style="font-family: 'Nanum Brush Script', sans-serif;">
              혁신적인 일렉기타
            </p>
          </div>
        </div>
      </CarouselItem>
      <CarouselItem>
        <div
            class="carousel-banner"
            :style="{backgroundImage: `url(${AcousticBanner})`}"
            @click="handleViewChanged('acoustic')"
        >
          <div class="carousel-banner-text">
            <p class="carousel-banner-title text-2xl sm:text-6xl lg:text-8xl"
               style="font-family: 'Amatic SC', sans-serif;">
              YAMAHA Acoustic Guitar F400
            </p>
            <p class="carousel-banner-desc"
               style="font-family: 'Nanum Brush Script', sans-serif;">
              풍부한 음색, 합리적인 가격, 입문 어쿠스틱 기타
            </p>
          </div>
        </div>
      </CarouselItem>
      <CarouselItem>
        <div
            class="carousel-banner"
            :style="{backgroundImage: `url(${BaseBanner})`}"
            @click="handleViewChanged('base')"
        >
          <div class="carousel-banner-text">
            <p class="carousel-banner-title text-2xl sm:text-6xl lg:text-8xl"
               style="font-family: 'Bebas Neue', sans-serif;">
              Vintage V495
            </p>
            <p class="carousel-banner-desc"
               style="font-family: 'Nanum Brush Script', sans-serif;">
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