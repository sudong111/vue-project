<script setup lang="ts">
import {ref} from "vue";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";

const previewImage = ref<string | null>(null)
const selectedFile = ref<File | null>(null)
const fileInput = ref<HTMLInputElement | null>(null)

const handleFileUpload = (event: Event) => {
  const target = event.target as HTMLInputElement
  const file = target.files?.[0]

  if (!file) return

  if (!file.type.startsWith('image/')) {
    alert('이미지 파일만 업로드 가능합니다.')
    return
  }

  const reader = new FileReader()
  reader.onload = (e) => {
    previewImage.value = e.target?.result as string
    selectedFile.value = file
  }
  reader.readAsDataURL(file)

  target.value = '' // 초기화
}

const removeImage = () => {
  previewImage.value = null
  selectedFile.value = null
}
</script>

<template>
  <div
      class="flex w-full h-full items-center justify-center border-2 border-dashed border-gray-300 rounded-lg cursor-pointer hover:border-blue-400 overflow-hidden"
      @click="fileInput?.click()"
  >
    <input
        type="file"
        accept="image/*"
        class="hidden"
        @change="handleFileUpload"
        ref="fileInput"
    />

    <template v-if="previewImage">
      <img :src="previewImage" alt="Preview" class="object-contain h-full w-full" />
    </template>
    <template v-else>
      <div class="text-center">
        <p class="text-sm text-gray-500">클릭하여 기타 이미지를 업로드하세요</p>
        <p class="text-sm text-gray-400">(jpg/png, 최대 1장)</p>
      </div>
    </template>
  </div>

  <!-- 삭제 버튼 -->
  <div v-if="previewImage" class="mt-2 text-right">
    <button
        @click="removeImage"
        class="text-sm text-red-500 hover:underline"
    >
      이미지 제거
    </button>
  </div>
</template>