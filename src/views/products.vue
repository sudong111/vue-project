<script setup lang="ts">
import {computed, onMounted, ref, watch} from 'vue'
import { useRoute } from 'vue-router'
import Banner from '@/components/banner.vue'
import ListBox from '@/components/list-box.vue'
import {Button} from "@/components/ui/button";
import {useGuitarStore} from "@/stores/guitar.ts";
import type {Category, Subtype, Guitar} from "@/types/interfaces.ts";
import {useNavigation} from "@/utils/navigation.ts";

const guitarStore = useGuitarStore();
const { handleDetailView } = useNavigation()
const route = useRoute();
const guitarList = ref<Guitar[]>([]);
const categoryList = ref<Category[]>([]);
const subtypeList = ref<Subtype[]>([]);
const selectedCategory = ref<Category | null>(null);
const selectedSubtype = ref<Subtype | null>(null);

const filteredSubtypes = computed(() => {
  if (!selectedCategory.value) return [];
  return [
    { id: 0, name: 'All', category_id: selectedCategory.value.id },
    ...subtypeList.value.filter(item => item.category_id === selectedCategory.value!.id)
  ];
});

const setFilter = (query: Record<string, any>) => {
  const categoryId = Number(query.category_id);
  const subtypeId = Number(query.subtype_id);

  const category = categoryList.value.find(c => c.id === categoryId) || undefined;
  const subtype = subtypeList.value.find(s => s.id === subtypeId) || undefined;

  if(category == undefined) {
    selectedCategory.value = {id: 0, name: 'All'};
    selectedSubtype.value = {id: 0, name: 'All', category_id: 0};
  }
  else if(subtype == undefined) {
    selectedCategory.value = category;
    selectedSubtype.value = {id: 0, name: 'All', category_id: 0};
  }
  else {
    selectedCategory.value = category;
    selectedSubtype.value = subtype;
  }
}

const setGuitar = async () => {
  console.log(selectedCategory.value, selectedSubtype.value)
  await guitarStore.selectGuitar(selectedCategory.value!.name, selectedSubtype.value!.id);

  console.log(guitarStore.guitars);
}

const onCategorySelected = () => {
  selectedSubtype.value = null;
}

watch(() => route.query, (newQuery) => {
  setFilter(newQuery);
});

watch([selectedCategory, selectedSubtype], ([newCategory, newSubtype]) => {
  setFilter({
    category_id: newCategory?.id ?? 0,
    subtype_id: newSubtype?.id ?? 0
  });
});

onMounted(async () => {
  await guitarStore.selectAllCategory();
  await guitarStore.selectAllSubtype();

  categoryList.value = [{ id: 0, name: 'All' }, ...guitarStore.categories];
  subtypeList.value = [{ id: 0, name: 'All', category_id: 0 }, ...guitarStore.subtypes];
  setFilter(route.query);
  await setGuitar();
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
                @update:modelValue="onCategorySelected"
            />
          </div>
          <div>
            <p>세부 종류</p>
            <ListBox
                v-model="selectedSubtype"
                :items="filteredSubtypes"
                :disabled="!selectedCategory || selectedCategory.id === 3 || selectedCategory.id === 0"
            />
          </div>
        </div>
        <div class="flex items-end">
          <Button
            variant="submit"
            @click="setGuitar()"
          >검색</Button>
        </div>
      </div>
      <div class="flex w-full flex-wrap gap-3 sm:gap-5 lg:gap-10">
        <div
            v-for="guitar in guitarStore.guitars"
            :key="guitar.id"
            class="product-img hover-up"
            @click="handleDetailView('product-detail', guitar.id)"
        >
          <img
              :src="guitar.image_url"
              alt=""
          />
          <div class="product-text-container">
            <p class="product-title">{{ guitar.name }}</p>
            <p class="product-title">{{ guitar.price.toLocaleString() }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>