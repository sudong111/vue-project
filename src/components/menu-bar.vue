<script setup lang="ts">
import {
  Menubar,
  MenubarContent,
  MenubarItem,
  MenubarMenu,
  MenubarTrigger,
  MenubarSeparator,
} from "@/components/ui/menubar"
import {Button} from "@/components/ui/button"
import { useNavigation } from "@/utils/navigation"
import { useUserStore } from "@/stores/user"

const { handleViewChanged } = useNavigation();
const userStore = useUserStore();
const handleLogout = () => {
  userStore.logout();
  handleViewChanged('/');
};
</script>

<template>
  <div class="menu-bar">
    <Menubar>
      <MenubarMenu>
        <MenubarTrigger>menu</MenubarTrigger>
        <MenubarContent>
          <MenubarItem>
            <Button
                v-if="!userStore.isLoggedIn"
                variant="ghost"
                @click="handleViewChanged('login')">
              <img class="header-img" src="@/assets/icons/login-icon.png" alt=""/>
              <p>login</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewChanged('acoustic')">
              <img class="header-img" src="@/assets/icons/acoustic-icon.png" alt=""/>
              <p>acoustic</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewChanged('electric')">
              <img class="header-img" src="@/assets/icons/electric-icon.png" alt=""/>
              <p>electric</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewChanged('base')">
              <img class="header-img" src="@/assets/icons/base-icon.png" alt=""/>
              <p>base</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewChanged('etc')">
              <img class="header-img" src="@/assets/icons/peek-icon.png" alt=""/>
              <p>etc</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button
                v-if="userStore.isLoggedIn"
                variant="ghost"
                @click=handleLogout>
              <img class="header-img" src="@/assets/icons/logout-icon.png" alt=""/>
              <p>logout</p>
            </Button>
          </MenubarItem>
        </MenubarContent>
      </MenubarMenu>
    </Menubar>
  </div>
</template>