<script setup lang="ts">
import { ref, computed } from 'vue'
import Banner from '@/components/banner.vue'

const guitarType = ref('');
const options = {
  Acoustic: ['Yamaha', 'Taylor', 'Martin'],
  Electric: ['Gibson', 'Fender', 'Ibanez'],
  Base: ['Music Man', 'Fender Jazz', 'Rickenbacker'],
  Etc: ['Custom 1', 'Custom 2']
}

const guitarModels = computed(() => {
  return options[guitarType.value as keyof typeof options] || []
})
</script>

<template>
  <div class="view">
    <div class="product-wrapper">
      <Banner />
      <div class="m-5 sm:m-10 lg:m-20 px-5 border-l-2 border-blue-200">
        <p class="title mb-4">어떤 제품을 찾으시나요?</p>
        <p class="text">저희 제품은 항상 최고급만 유지합니다.</p>
        <p class="text">모든 지역 무료 배송 및 당일 배송을 원칙으로 합니다.</p>
        <p class="text">부담없이 선택하시고 골라보세요!</p>
      </div>
          <v-select
              label="Guitar"
              :items="['Acoustic', 'Electric', 'Base', 'Etc']"
              v-model="guitarType"
              variant="underlined"
          ></v-select>
          <v-select
              label="Category"
              :items="guitarModels"
              :disabled="!guitarType || guitarType == 'Base' "
              variant="underlined"
          ></v-select>
    </div>
  </div>

</template>