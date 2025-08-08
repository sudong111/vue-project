<script setup lang="ts">
import {computed, ref} from 'vue'
import {Card} from '@/components/ui/card'
import {Input} from '@/components/ui/input'
import {Label} from '@/components/ui/label'
import {Button} from "@/components/ui/button";
import ListBox from '@/components/list-box.vue'
import ImageUpload from "@/components/image-upload.vue";
import {Textarea} from "@/components/ui/textarea";

</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <div class="flex justify-center mt-20">
        <Card class="form-card">
          <form class="flex flex-col w-full h-full gap-10">
            <div class="block relative">
                <div class="flex flex-col gap-3 mr-[10rem]">
                  <Label>제품 이름</Label>
                  <Input></Input>
                  <Label>브랜드 명</Label>
                  <Input></Input>
                </div>
              <div class="absolute top-0 right-0 w-[7.5rem] h-[10rem]">
                <ImageUpload/>
              </div>
            </div>
            <div class="flex gap-4">
              <div>
                <p>기타 종류</p>
                <ListBox
                    v-model="selectedGuitar"
                    :items="guitar"
                />
              </div>
              <div>
                <p>세부 종류</p>
                <ListBox
                    v-model="selectedSubType"
                    :items="subTypeOptions"
                    :disabled="!selectedGuitar"
                />
              </div>
            </div>
            <div class="flex w-full gap-4">
              <div class="flex w-full flex-col gap-2">
                <Label>가격</Label>
                <Input></Input>
              </div>
              <div class="flex w-full flex-col gap-2">
                <Label>수량</Label>
                <Input></Input>
              </div>
            </div>
            <div>
              <Label>설명</Label>
              <Textarea />
            </div>
            <Button variant="submit">저장하기</Button>
          </form>
        </Card>
      </div>
    </div>
  </div>

</template>