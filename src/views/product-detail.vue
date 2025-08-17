<script setup lang="ts">
import { useRoute } from 'vue-router';
import { onMounted, ref } from "vue";
import { useGuitarStore } from "@/stores/guitar.ts";
import { Button } from '@/components/ui/button'

const route = useRoute();
const guitarStore = useGuitarStore();
const id = Number(route.query.id);

const scale = 3;
const posX = ref(50);
const posY = ref(50);
const isHover = ref(false);

function handleMouseMove(e: MouseEvent) {
  const { offsetX, offsetY, currentTarget } = e as any;
  const { offsetWidth, offsetHeight } = currentTarget;
  posX.value = (offsetX / offsetWidth) * 100;
  posY.value = (offsetY / offsetHeight) * 100;
}

onMounted(async () => {
  await guitarStore.selectGuitarDetail(id);
});
</script>

<template>
  <div class="view">
    <div class="flex flex-col lg:flex-row mt-10 mx-[10rem]">
      <div
          class="w-full h-[800px] overflow-hidden relative"
          @mousemove="handleMouseMove"
          @mouseenter="isHover = true"
          @mouseleave="isHover = false"
      >
        <img
            :src="guitarStore.guitar.image_url"
            alt="기타 이미지"
            class="w-full h-full object-cover transition-transform duration-300"
            :style="isHover ?
            { transform: `scale(${scale})`, transformOrigin: `${posX}% ${posY}%` }
            : {}"
        />
      </div>

      <div class="flex flex-col gap-5 pl-[10rem]">
        <p class="font-bold text-[3rem]">{{ guitarStore.guitar.name }}</p>
        <p class="text-[2rem]">{{ guitarStore.guitar.brand }}</p>
        <p>가격 : {{ guitarStore.guitar.price }} 원</p>
        <div class="mr-40">
          <p>{{ guitarStore.guitar.description }}</p>
        </div>
        <div class="mt-40">
          <Button class="bg-blue-700 mr-10">구매하기</Button>
          <Button class="bg-gray-200 text-black">장바구니</Button>
        </div>
      </div>
    </div>
  </div>
</template>
