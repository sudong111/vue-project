<script setup lang="ts">
import {computed, ref} from 'vue'
import {Card} from '@/components/ui/card'
import {Input} from '@/components/ui/input'
import {Label} from '@/components/ui/label'
import {Button} from "@/components/ui/button";
import ListBox from '@/components/list-box.vue'
import ImageUpload from "@/components/image-upload.vue";

</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <div class="flex justify-center mt-20">
        <Card class="form-card">
          <form class="flex flex-col w-full h-full gap-6">
            <div class="flex justify-between">
              <div class="flex flex-col gap-3">
                <div class="flex flex-col gap-3">
                  <Label>제품 이름</Label>
                  <Input></Input>
                </div>
                <div>


                </div>
              </div>
              <div class="lg:w-[7.5rem] lg:h-[10rem]">
                <ImageUpload/>
              </div>
            </div>
          </form>
        </Card>
      </div>
    </div>
  </div>

</template>