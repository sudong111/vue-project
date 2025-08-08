<script setup lang="ts">
import {computed, onMounted, ref, watch} from 'vue'
import Banner from '@/components/banner.vue'
import ListBox from '@/components/list-box.vue'
import {Button} from "@/components/ui/button";
import {useGuitarStore} from "@/stores/guitar.ts";
import type {Category, Subtype} from "@/types/interfaces.ts";

const guitarStore = useGuitarStore();
const categoryList = ref<Category[]>([]);
const subtypeList = ref<Subtype[]>([]);
const selectedCategory = ref<Category | null>(null);
const selectedSubtype = ref(null);

const filteredSubtypes = computed(() => {
  if (!selectedCategory.value) return [];
  return subtypeList.value.filter(
      (item) => item.category_id === selectedCategory.value!.id
  );
});

watch(selectedCategory, () => {
  selectedSubtype.value = null
});


onMounted(async () => {
  await guitarStore.selectAllCategory();
  await guitarStore.selectAllSubtype();
  categoryList.value = guitarStore.categories;
  subtypeList.value = guitarStore.subtypes;
});
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
                v-model="selectedCategory"
                :items="categoryList"
            />
          </div>
          <div>
            <p>세부 종류</p>
            <ListBox
                v-model="selectedSubtype"
                :items="filteredSubtypes"
                :disabled="!selectedCategory || selectedCategory.id === 3"
            />
          </div>
        </div>
        <div class="flex items-end">
          <Button
            variant="submit"
            @click=""
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