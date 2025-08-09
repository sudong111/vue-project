<script setup lang="ts">
import {DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger} from "@/components/ui/dropdown-menu"
import {computed, onMounted, ref} from 'vue'
import {useNavigation} from "@/utils/navigation.ts";
import type {Category, CategorySubtypeQuery, Subtype} from "@/types/interfaces.ts";
import {useGuitarStore} from "@/stores/guitar.ts";

const { handleViewChangedWithQuery } = useNavigation()
const guitarStore = useGuitarStore();
const subtypeList = ref<Subtype[]>([]);
const queryList = ref<CategorySubtypeQuery[]>([]);

const props = defineProps<{
  category: Category
}>()

const menuItems = computed(() => {
  return subtypeList.value
      .filter(subtype => subtype.category_id === props.category.id)
      .map(subtype => ({
        label: subtype.name,
        value: subtype.id || subtype.name.toLowerCase()
      }))
});

const handleClicked = (subtype : Subtype) => {
  queryList.value = [{
    category: props.category,
    subtype: subtype
  }]
  handleViewChangedWithQuery('products', queryList.value);
}


onMounted(async () => {
  await guitarStore.selectAllSubtype();
  subtypeList.value = guitarStore.subtypes;
});
</script>

<template>
  <DropdownMenu>
    <DropdownMenuTrigger as-child>
      <slot name="trigger" />
    </DropdownMenuTrigger>
    <DropdownMenuContent>
      <DropdownMenuItem
          v-for="item in menuItems"
          :key="item.value"
          @click="handleClicked(subtypeList.find(s => s.id === item.value) || { id: 0, name: '', category_id: 0 })"
      >
        {{ item.label }}
      </DropdownMenuItem>
    </DropdownMenuContent>
  </DropdownMenu>
</template>