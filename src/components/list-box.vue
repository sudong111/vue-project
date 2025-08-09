<script setup lang="ts">
import { computed } from 'vue'
import {
  Listbox,
  ListboxLabel,
  ListboxButton,
  ListboxOptions,
  ListboxOption,
} from '@headlessui/vue'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/vue/20/solid'

const props = defineProps<{
  modelValue: any,
  items: any[],
  disabled?: boolean,
  class?: string | object
}>()

const emit = defineEmits(['update:modelValue'])

const selected = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})
</script>

<template>
  <Listbox v-model="selected" :disabled="props.disabled">
    <div class="relative w-[10rem]">
      <ListboxButton
          :class="[
        'relative w-full cursor-default rounded-lg py-2 pl-3 pr-10 text-left shadow-md sm:text-sm',
        props.disabled
          ? 'bg-gray-100 text-gray-400 cursor-not-allowed'
          : 'bg-white text-black focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white/75 focus-visible:ring-offset-2 focus-visible:ring-offset-blue-300',
          props.class
          ]"
      >
        <span class="block truncate">{{ selected?.name || 'All' }}</span>
        <span class="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
          <ChevronUpDownIcon class="h-5 w-5 text-gray-400" aria-hidden="true" />
        </span>
      </ListboxButton>

      <transition
          leave-active-class="transition duration-100 ease-in"
          leave-from-class="opacity-100"
          leave-to-class="opacity-0"
      >
        <ListboxOptions class="absolute mt-1 max-h-60 w-full overflow-visible rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black/5 focus:outline-none sm:text-sm">
          <ListboxOption
              v-for="item in items"
              :key="item.id"
              :value="item"
              v-slot="{ active, selected }"
              as="template"
          >
            <li
                :class="[
                active ? 'bg-blue-100 text-blue-900' : 'text-gray-900',
                'relative cursor-default select-none py-2 pl-10 pr-4',
              ]"
            >
              <span
                  :class="[
                  selected ? 'font-medium' : 'font-normal',
                  'block truncate',
                ]"
              >
                {{ item.name }}
              </span>
              <span
                  v-if="selected"
                  class="absolute inset-y-0 left-0 flex items-center pl-3 text-blue-600"
              >
                <CheckIcon class="h-5 w-5" aria-hidden="true" />
              </span>
            </li>
          </ListboxOption>
        </ListboxOptions>
      </transition>
    </div>
  </Listbox>
</template>
