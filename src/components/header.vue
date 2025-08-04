<script setup lang="ts">
import {Button} from '@/components/ui/button'
import {Input} from '@/components/ui/input'
import MenuBar from '@/components/menu-bar.vue'
import { useNavigation } from "@/utils/navigation"
import { useUserStore } from "@/stores/user"

const { handleViewChanged } = useNavigation()
const userStore = useUserStore();

const handleLogout = () => {
  userStore.logout();
  handleViewChanged('/');
};

</script>

<template>
  <div class="header">
    <div class="header-wrapper">
      <div class="flex gap-6">
        <Button variant="ghost" @click="handleViewChanged('')">
          <img class="header-img" src="@/assets/icons/home-icon.png" alt=""/>
          <p class="header-button-text">home</p>
        </Button>
        <div class="menu">
          <Button variant="ghost" @click="handleViewChanged('acoustic')">
            <img class="header-img" src="@/assets/icons/acoustic-icon.png" alt=""/>
            <p class="header-button-text">acoustic</p>
          </Button>
          <Button variant="ghost" @click="handleViewChanged('electric')">
            <img class="header-img" src="@/assets/icons/electric-icon.png" alt=""/>
            <p class="header-button-text">electric</p>
          </Button>
          <Button variant="ghost" @click="handleViewChanged('base')">
            <img class="header-img" src="@/assets/icons/base-icon.png" alt=""/>
            <p class="header-button-text">base</p>
          </Button>
          <Button variant="ghost" @click="handleViewChanged('etc')">
            <img class="header-img" src="@/assets/icons/peek-icon.png" alt=""/>
            <p class="header-button-text">etc</p>
          </Button>
        </div>
      </div>
      <div class="flex gap-4 sm:w-[15rem] md:w-[25rem] lg:w-[30rem]">
        <Input class="w-full" placeholder="search"></Input>
        <div class="menu">
          <Button  v-if="!userStore.isLoggedIn"
                   variant="ghost"
                   @click="handleViewChanged('login')">login
          </Button>
          <Button
              v-if="userStore.isLoggedIn"
              variant="ghost"
              @click="handleViewChanged('user')">
            <img class="header-img" src="@/assets/icons/user-icon.png" alt=""/>
            <p>{{ userStore.username }}</p>
          </Button>
          <Button
              v-if="userStore.isLoggedIn"
              variant="ghost"
              @click="handleLogout">
            logout
          </Button>
          <Button
              v-if="!userStore.isLoggedIn"
              variant="outline"
              @click="handleViewChanged('sign-up')"
          >
            sign-up
          </Button>
        </div>
        <MenuBar/>
      </div>
    </div>
  </div>
</template>
