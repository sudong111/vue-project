<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious } from "@/components/ui/carousel"
import { useNavigation } from "@/utils/navigation"

import ElectricBanner from '@/assets/banners/electric-guitar-1.jpg'
import AcousticBanner from '@/assets/banners/acoustic-guitar-1.jpg'
import BaseBanner from '@/assets/banners/bass-guitar-1.jpg'

const { handleViewChanged } = useNavigation()

const banners = [
  {
    image: ElectricBanner,
    title: "PEOZ Smart Electric Guitar TR3",
    desc: "혁신적인 일렉기타",
    font: "'Playwrite AU NSW', sans-serif",
    type: 'electric'
  },
  {
    image: AcousticBanner,
    title: "YAMAHA Acoustic Guitar F400",
    desc: "풍부한 음색, 합리적인 가격, 입문 어쿠스틱 기타",
    font: "'Amatic SC', sans-serif",
    type: 'acoustic'
  },
  {
    image: BaseBanner,
    title: "Vintage V495",
    desc: `" This is Base "`,
    font: "'Bebas Neue', sans-serif",
    type: 'base'
  }
]

const carouselPreviousRef = ref<HTMLElement | null>(null)
const carouselNextRef = ref<HTMLElement | null>(null)
const currentIndex = ref(0)
let intervalId: ReturnType<typeof setInterval> | null = null

const isHovered = ref(false)

function startInterval() {
  stopInterval()
  intervalId = setInterval(() => {
    if (isHovered.value) return

    const nextIndex = (currentIndex.value + 1) % banners.length
    currentIndex.value = nextIndex

    const btn = (carouselNextRef.value as any)?.$el as HTMLElement | undefined
    if (btn) {
      if (nextIndex !== 0) {
        btn.click()
      } else {
        const prevBtn = (carouselPreviousRef.value as any)?.$el as HTMLElement | undefined
        if (prevBtn) {
          for (let i = 0; i < banners.length; i++) {
            prevBtn.click()
          }
        }
      }
    }
  }, 5000)
}

function stopInterval() {
  if (intervalId) clearInterval(intervalId)
}

onMounted(() => {
  startInterval()
})

onBeforeUnmount(() => {
  stopInterval()
})
</script>

<template>
  <Carousel>
    <CarouselContent data-carousel-content class="h-full">
      <CarouselItem v-for="(banner, index) in banners" :key="index">
        <div
            class="carousel-banner"
            :style="{ backgroundImage: `url(${banner.image})` }"
            @click="handleViewChanged(banner.type)"
            @mouseenter="isHovered = true"
            @mouseleave="isHovered = false"
        >
          <div class="carousel-banner-text py-3">
            <p class="carousel-banner-title text-sm sm:text-xl lg:text-3xl"
               :style="{ fontFamily: banner.font }">
              {{ banner.title }}
            </p>
            <p class="carousel-banner-desc" style="font-family: 'Nanum Brush Script', sans-serif;">
              {{ banner.desc }}
            </p>
          </div>
        </div>
      </CarouselItem>
    </CarouselContent>
    <CarouselPrevious ref="carouselPreviousRef" class="hidden" />
    <CarouselNext ref="carouselNextRef" class="hidden" />
  </Carousel>
</template>
