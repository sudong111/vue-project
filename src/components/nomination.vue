<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import newbieIcon from '@/assets/icons/newbie-icon.png';
import enjoyIcon from '@/assets/icons/enjoy-icon.png';
import universityIcon from '@/assets/icons/university-icon.png';
import proIcon from '@/assets/icons/pro-icon.png';
import moneyIcon from '@/assets/icons/money-icon.png';

const items = ref([
  {
    content: `어떤 제품을 찾으시나요?`,
    type: 'title',
    visible: false,
  },
  {
    content: `자신에게 맞는 기타 종류를 선택해보세요.`,
    type: 'desc',
    visible: false,
  },
  {
    imageCards: [
      { src: newbieIcon, label: '입문' },
      { src: enjoyIcon, label: '취미' },
      { src: universityIcon, label: '입시생' },
      { src: proIcon, label: '프로' },
      { src: moneyIcon, label: '고가' },
    ],
    visible: false,
  },
]);

const animatedElements = ref<(HTMLElement | null)[]>([]);

const handleScroll = () => {
  const viewportHeight = window.innerHeight;

  animatedElements.value.forEach((element, index) => {
    if (element) {
      const elementTop = element.getBoundingClientRect().top;

      if (elementTop < viewportHeight) {
        if (!items.value[index].visible) {
          items.value[index].visible = true;
        }
      } else {
        if (items.value[index].visible) {
          items.value[index].visible = false;
        }
      }
    }
  });
};

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});
</script>

<template>
  <div class="flex flex-col items-center gap-4 lg:gap-8 mx-10">
      <div v-for="(item, index) in items" :key="index">
        <div
            :class="{
            'opacity-100 translate-y-0': item.visible,
            'opacity-0 translate-y-16': !item.visible,
          }"
            class="transition-all duration-1000 ease-out"
            :data-index="index"
            ref="animatedElements"
        >
          <div v-if="item.imageCards" class="flex justify-center items-center gap-4 sm:gap-8 lg:gap-32 mt-10">
            <div v-for="(card, cardIndex) in item.imageCards" :key="cardIndex" class="flex flex-col items-center">
              <img :src="card.src" :alt="card.label" class="w-16 h-16 sm:w-20 sm:h-20 object-contain" />
              <p class="mt-2 text-base font-medium">{{ card.label }}</p>
            </div>
          </div>
          <div v-else>
              <p v-if="item.type === 'title'"
                 :class="{
                  'text-xl sm:text-2xl lg:text-4xl font-semibold': item.type === 'title'
                }">
                {{ item.content }}
              </p>
              <p v-else
                 :class="{
                  'text-sm sm:text-xl lg:text-2xl': item.type !== 'title'
                }">
                {{ item.content }}
              </p>
          </div>
        </div>
      </div>
  </div>
</template>