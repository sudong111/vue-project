<script setup lang="ts">
  import {Card} from '@/components/ui/card'
  import {Input} from '@/components/ui/input'
  import {Checkbox} from '@/components/ui/checkbox'
  import {Label} from '@/components/ui/label'
  import {Button} from "@/components/ui/button";

</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <Card class="login">
        <div class="flex flex-col w-full gap-6">
          <Label class="font-bold text-xl">Log-in</Label>
          <Input placeholder="id"></Input>
          <Input type="password" placeholder="password"></Input>
          <div class="flex justify-between w-full">
            <div class="flex gap-1 items-center">
              <Checkbox></Checkbox>
              <Label>아이디 기억하기</Label>
            </div>
            <div class="hidden gap-4 text-gray-400 sm:flex lg:flex">
              <Label class="hover:underline">아이디 찾기</Label>
              <Label class="hover:underline">비밀번호 찾기</Label>
            </div>
          </div>
          <div class="flex gap-4 text-gray-400 sm:hidden lg:hidden">
            <Label class="hover:underline">아이디 찾기</Label>
            <Label class="hover:underline">비밀번호 찾기</Label>
          </div>
          <Button class="bg-blue-500">Log-in</Button>
        </div>
      </Card>
    </div>
  </div>
</template>