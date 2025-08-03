<script setup lang="ts">
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert"
import {Button} from "@/components/ui/button";

const props = defineProps({
  variant: {
    type: String,
    default: 'default',
  },
  title: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
})
const emit = defineEmits(['close']);

const closeAlert = () => {
  emit('close');
}
</script>

<template>
  <Alert :variant="props.variant">
    <div class="grid grid-cols-[80%_20%] items-center">
      <div>
        <AlertTitle>{{props.title}}</AlertTitle>
        <AlertDescription>
          {{props.description}}
        </AlertDescription>
      </div>
    <Button variant="ghost" @click="closeAlert">닫기</Button>
    </div>
  </Alert>
</template>