<script setup lang="ts">
  import {Button} from '@/components/ui/button'
  import HomeIcon from '@/assets/home-icon.vue'
  import {Input} from '@/components/ui/input'
  import MenuBar from '@/components/menu-bar.vue'
  import { useNavigation } from "@/utils/navigation"

  const { handleViewButtonClicked } = useNavigation()

</script>

<template>
  <div class="header">
    <div class="header-wrapper">
      <div class="flex">
        <Button variant="ghost" @click="handleViewButtonClicked('')"><HomeIcon/></Button>
        <div class="menu">
          <Button variant="ghost" @click="handleViewButtonClicked('acoustic')">acoustic</Button>
          <Button variant="ghost" @click="handleViewButtonClicked('electric')">electric</Button>
          <Button variant="ghost" @click="handleViewButtonClicked('base')">base</Button>
          <Button variant="ghost" @click="handleViewButtonClicked('etc')">etc</Button>
        </div>
      </div>
      <div class="flex">
        <Input placeholder="search"></Input>
        <Button variant="ghost" @click="handleViewButtonClicked('login')">login</Button>
        <MenuBar/>
      </div>
    </div>
  </div>
</template>