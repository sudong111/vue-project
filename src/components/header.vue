<script setup lang="ts">
  import {Button} from '@/components/ui/button'
  import { useRouter } from 'vue-router'

  const router = useRouter()

  function handleButtonClicked(view : string) {
    router.push(`/${view}`)
  }

</script>

<template>
  <div class="header">this is header
  <Button @click="handleButtonClicked('')">home</Button>
    <Button @click="handleButtonClicked('member')">member</Button>
  </div>
</template>