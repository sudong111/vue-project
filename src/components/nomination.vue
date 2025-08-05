<script setup lang="ts">
  import {Card} from "@/components/ui/card"
</script>

<template>
  <div class="flex flex-col items-center gap-4 lg:gap-8 mx-10">
    <p class="text-xl sm:text-2xl lg:text-4xl font-semibold">어떤 제품을 찾으시나요?</p>
    <div class="flex gap-4 sm:gap-8 lg:gap-16 lg:w-[40rem]">
      <div>
        <img class="" src="@/assets/icons/newbie-icon.png" alt="" />
        <div class="flex flex-col flex-nowrap justify-center items-center">
          <p class="text-xs sm:text-base lg:text-xl">입문</p>
        </div>
      </div>
      <div>
        <img class="" src="@/assets/icons/enjoy-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="text-xs sm:text-base lg:text-xl">취미</p>
        </div>
      </div>
      <div>
        <img class="" src="@/assets/icons/university-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="text-xs sm:text-base lg:text-xl">입시생</p>
        </div>
      </div>
      <div>
        <img class="" src="@/assets/icons/pro-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="text-xs sm:text-base lg:text-xl">프로</p>
        </div>
      </div>
      <div>
        <img class="" src="@/assets/icons/money-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="text-xs sm:text-base lg:text-xl">고가</p>
        </div>
      </div>
    </div>
  </div>

</template>