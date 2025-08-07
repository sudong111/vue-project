<script setup lang="ts">
import {computed, ref} from 'vue'
import Banner from '@/components/banner.vue'
import ListBox from '@/components/list-box.vue'
import {Button} from "@/components/ui/button";

const guitar = [
  { id: 1, name: 'Acoustic', unavailable: false },
  { id: 2, name: 'Electric', unavailable: false },
  { id: 3, name: 'Base', unavailable: false },
  { id: 4, name: 'Etc', unavailable: true },
]
const guitarTypesMap: Record<string, { id: number; name: string }[]> = {
  Acoustic: [
    { id: 1, name: 'Nylon' },
    { id: 2, name: 'Steel' },
  ],
  Electric: [
    { id: 3, name: 'Stratocaster' },
    { id: 4, name: 'Les Paul' },
  ],
  Base: [
    { id: 5, name: 'Jazz Bass' },
    { id: 6, name: 'Precision Bass' },
  ],
  Etc: [
    { id: 7, name: 'Ukulele' },
    { id: 8, name: 'Mandolin' },
  ],
}

const selectedGuitar = ref<typeof guitar[0] | null>(null)
const selectedSubType = ref<any>(null)
const subTypeOptions = computed(() => {
  if (!selectedGuitar.value) return []
  return guitarTypesMap[selectedGuitar.value.name] || []
})

const handleSearchClicked = () => {
  console.log(selectedGuitar.value?.name ?? 'all', selectedSubType.value?.name ?? 'all')
}
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
      <div class="flex flex-col sm:flex-row lg:flex-row gap-4 mx-5 sm:mx-10 lg:mx-20">
        <div class="flex gap-4">
          <div>
            <p>기타 종류</p>
             <ListBox
                 v-model="selectedGuitar"
                 :items="guitar"
             />
          </div>
          <div>
            <p>세부 종류</p>
            <ListBox
                v-model="selectedSubType"
                :items="subTypeOptions"
                :disabled="!selectedGuitar"
            />
          </div>
        </div>
        <div class="flex items-end">
          <Button
            variant="submit"
            @click="handleSearchClicked"
          >검색</Button>
        </div>
      </div>
      <div class="flex w-full flex-wrap gap-3 sm:gap-5 lg:gap-10">
        <div>
          <div class="product-img hover-up">
            <img src="../assets/acoustic/nylon/1_acoustic_nylon_Godin-Nylon.jpg" alt="" />
            <div class="product-text-container">
              <p class="product-category">[acoustic]</p>
              <p class="product-title">Godin-Nylon</p>
              <p class="product-title">324,000</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>