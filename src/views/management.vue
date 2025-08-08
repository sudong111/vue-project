<script setup lang="ts">
import {computed, ref, watch} from 'vue'
import type {Category, Subtype, Guitar} from '@/types/interfaces.ts'
import {Card} from '@/components/ui/card'
import {Input} from '@/components/ui/input'
import {Label} from '@/components/ui/label'
import {Button} from "@/components/ui/button";
import ListBox from '@/components/list-box.vue'
import ImageUpload from "@/components/image-upload.vue";
import {Textarea} from "@/components/ui/textarea";
import { onMounted } from "vue";
import { useGuitarStore } from "@/stores/guitar.ts";
import {useValidation} from "@/utils/validation.ts";

const guitarStore = useGuitarStore();
const name = ref('');
const brand = ref('');
const price = ref(0);
const stock = ref(0);
const description = ref('');
const imageFile = ref<File | null>(null)
const categoryList = ref<Category[]>([]);
const subtypeList = ref<Subtype[]>([]);
const selectedCategory = ref<Category | null>(null);
const selectedSubtype = ref<Subtype | null>(null);

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

const handleFileSelected = (file: File) => {
  imageFile.value = file
}

const insert = async () => {
  if(selectedSubtype.value?.id == null) {
    return;
  }
  const guitarData: Guitar = {
    name: name.value,
    subtype_id: selectedSubtype.value.id,
    brand: brand.value,
    price: price.value,
    description: description.value,
    stock: stock.value,
  };

  const formData = new FormData();
  formData.append(
      "guitar",
      new Blob([JSON.stringify(guitarData)], { type: "application/json" })
  );
  formData.append("image", imageFile.value!);

  await guitarStore.insertGuitar(formData);
}

</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <div class="flex flex-col items-center justify-center mt-20">
        <Card class="form-card">
          <form class="flex flex-col relative w-full h-full gap-10" @submit.prevent="insert">
            <div class="flex relative">
                <div class="flex flex-col gap-3 w-full mr-[10rem]">
                  <Label>제품 이름<span class="text-red-500"> *</span></Label>
                  <Input v-model="name"></Input>
                  <Label>브랜드 명<span class="text-red-500"> *</span></Label>
                  <Input v-model="brand"></Input>
                </div>
              <div class="absolute top-0 right-0 w-[7.5rem] h-[10rem]">
                <ImageUpload
                    @file-selected="handleFileSelected"
                />
              </div>
            </div>
            <div class="flex gap-4">
              <div>
                <Label>종류<span class="text-red-500"> *</span></Label>
                <ListBox
                    v-model="selectedCategory"
                    :items="categoryList"
                />
              </div>
              <div>
                <Label>세부 종류<span class="text-red-500"> *</span></Label>
                <ListBox
                    v-model="selectedSubtype"
                    :items="filteredSubtypes"
                    :disabled="!selectedCategory || selectedCategory.id === 3"
                />
              </div>
            </div>
            <div class="flex w-full gap-4">
              <div class="flex w-full flex-col gap-2">
                <Label>가격<span class="text-red-500"> *</span></Label>
                <Input type="number" v-model="price"></Input>
              </div>
              <div class="flex w-full flex-col gap-2">
                <Label>수량</Label>
                <Input type="number" placeholder="0" v-model="stock"></Input>
              </div>
            </div>
            <div>
              <Label>설명</Label>
              <Textarea v-model="description" />
            </div>
            <Button variant="submit" type="submit">저장하기</Button>
          </form>
        </Card>
      </div>
    </div>
  </div>

</template>