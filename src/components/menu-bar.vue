<script setup lang="ts">
import {
  Menubar,
  MenubarContent,
  MenubarItem,
  MenubarMenu,
  MenubarTrigger,
} from "@/components/ui/menubar"
import {Button} from "@/components/ui/button";
import { useNavigation } from "@/utils/navigation"

const { handleViewButtonClicked } = useNavigation()
</script>

<template>
  <div class="menu-bar">
    <Menubar>
      <MenubarMenu>
        <MenubarTrigger>menu</MenubarTrigger>
        <MenubarContent>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewButtonClicked('acoustic')">
              <img class="img" src="@/assets/acoustic-icon.png" alt=""/>
              <p>acoustic</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewButtonClicked('electric')">
              <img class="img" src="@/assets/electric-icon.png" alt=""/>
              <p>electric</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewButtonClicked('base')">
              <img class="img" src="@/assets/base-icon.png" alt=""/>
              <p>base</p>
            </Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewButtonClicked('etc')">
              <img class="img" src="@/assets/peek-icon.png" alt=""/>
              <p>etc</p>
            </Button>
          </MenubarItem>
        </MenubarContent>
      </MenubarMenu>
    </Menubar>
  </div>
</template>