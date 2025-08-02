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
            <Button variant="ghost" @click="handleViewButtonClicked('acoustic')">acoustic</Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewButtonClicked('electric')">electric</Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewButtonClicked('base')">base</Button>
          </MenubarItem>
          <MenubarItem>
            <Button variant="ghost" @click="handleViewButtonClicked('etc')">etc</Button>
          </MenubarItem>
        </MenubarContent>
      </MenubarMenu>
    </Menubar>
  </div>
</template>