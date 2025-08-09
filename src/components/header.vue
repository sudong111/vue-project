<script setup lang="ts">
import {onMounted, ref} from 'vue'
import {Button} from '@/components/ui/button'
import {Input} from '@/components/ui/input'
import MenuBar from '@/components/menu-bar.vue'
import Dropdown from '@/components/dropdown.vue'
import { useNavigation } from "@/utils/navigation"
import { useUserStore } from "@/stores/user"
import {useGuitarStore} from "@/stores/guitar.ts";
import type {Category} from "@/types/interfaces.ts";
import acousticIcon from '@/assets/icons/acoustic-icon.png';
import electricIcon from '@/assets/icons/electric-icon.png';
import baseIcon from '@/assets/icons/base-icon.png';
import etcIcon from '@/assets/icons/pick-icon.png';

const icons = {
  acoustic: acousticIcon,
  electric: electricIcon,
  base: baseIcon,
  etc: etcIcon,
};

const { handleViewChanged, handleViewChangedWithQuery } = useNavigation()
const userStore = useUserStore();
const guitarStore = useGuitarStore();
const categoryList = ref<Category[]>([]);

const handleLogout = () => {
  userStore.logout();
  handleViewChanged('/');
};

const getIconPath = (name: string) => {
  return icons[name as keyof typeof icons];
}

onMounted(async () => {
  await guitarStore.selectAllCategory();
  categoryList.value = guitarStore.categories;
});

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
          <template v-for="category in categoryList" :key="category.id">
            <Dropdown
                v-if="category.name !== 'base'"
                :category="category"
            >
              <template #trigger>
                <Button variant="ghost">
                  <img
                      class="header-img"
                      :src="getIconPath(category.name)"
                      alt=""
                  />
                  <p class="header-button-text">{{ category.name }}</p>
                </Button>
              </template>
            </Dropdown>

            <Button
                v-else
                variant="ghost"
                @click="handleViewChangedWithQuery('products', [{
                  category: {id: 3, name: 'base'},
                  subtype: {id: 0, name: 'All', category_id: 3}
                }])"
            >
              <img
                  class="header-img"
                  :src="getIconPath(category.name)"
                  alt=""
              />
              <p class="header-button-text">{{ category.name }}</p>
            </Button>
          </template>
        </div>
      </div>
      <div class="flex gap-4">
        <Input class="w-full" placeholder="search"></Input>
        <div class="menu">
          <Button
              v-if="userStore.isAdmin"
              variant="submit"
              @click="handleViewChanged('management')">제품 관리
          </Button>
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
