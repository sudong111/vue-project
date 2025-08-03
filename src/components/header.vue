<script setup lang="ts">
  import {Button} from '@/components/ui/button'
  import {Input} from '@/components/ui/input'
  import MenuBar from '@/components/menu-bar.vue'
  import { useNavigation } from "@/utils/navigation"

  const { handleViewButtonClicked } = useNavigation()

</script>

<template>
  <div class="header">
    <div class="header-wrapper">
      <div class="flex gap-6">
        <Button class="header-button" variant="ghost" @click="handleViewButtonClicked('')">
          <img class="img" src="@/assets/icons/home-icon.png" alt=""/>
          <p class="button-text">home</p>
        </Button>
        <div class="menu">
          <Button variant="ghost" @click="handleViewButtonClicked('acoustic')">
            <img class="img" src="@/assets/icons/acoustic-icon.png" alt=""/>
            <p class="button-text">acoustic</p>
          </Button>
          <Button variant="ghost" @click="handleViewButtonClicked('electric')">
            <img class="img" src="@/assets/icons/electric-icon.png" alt=""/>
            <p class="button-text">electric</p>
          </Button>
          <Button variant="ghost" @click="handleViewButtonClicked('base')">
            <img class="img" src="@/assets/icons/base-icon.png" alt=""/>
            <p class="button-text">base</p>
          </Button>
          <Button variant="ghost" @click="handleViewButtonClicked('etc')">
            <img class="img" src="@/assets/icons/peek-icon.png" alt=""/>
            <p class="button-text">etc</p>
          </Button>
        </div>
      </div>
      <div class="flex sm:w-[15rem] md:w-[25rem] lg:w-[30rem]">
        <Input class="w-full" placeholder="search"></Input>
        <Button variant="ghost" @click="handleViewButtonClicked('login')">login</Button>
        <MenuBar/>
      </div>
    </div>
  </div>
</template>