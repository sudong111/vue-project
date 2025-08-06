<script setup lang="ts">
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

import { computed } from 'vue'

const props = defineProps<{
  type: string
}>()

const emit = defineEmits<{
  (e: 'select', value: string): void
}>()

const menuItems = computed(() => {
  switch (props.type) {
    case 'acoustic':
      return [
        { label: 'Steel', value: 'acoustic-steel' },
        { label: 'Nylon', value: 'acoustic-nylon' },
      ]
    case 'electric':
      return [
        { label: 'Solid Body', value: 'electric-solid' },
        { label: 'Montreal', value: 'electric-montreal' },
        { label: '5th', value: 'electric-5th' },
      ]
    case 'etc':
      return [
        { label: 'Amp', value: 'etc-amp' },
        { label: 'pick', value: 'etc-pick' },
      ]
    default:
      return []
  }
})
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
          @click="$emit('select', item.value)"
      >
        {{ item.label }}
      </DropdownMenuItem>
    </DropdownMenuContent>
  </DropdownMenu>
</template>