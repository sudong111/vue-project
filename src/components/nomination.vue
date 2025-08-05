<script setup lang="ts">
</script>

<template>
  <div class="flex flex-col items-center gap-8 lg:gap-16 mx-10">
    <div class="flex flex-col items-center gap-6">
      <p v-scroll-fade class="title">어떤 제품을 찾으시나요?</p>
      <p v-scroll-fade class="desc">원하는 제품 종류를 선택해보세요.</p>
    </div>
    <div class="flex gap-4 sm:gap-8 lg:gap-16 lg:w-[60rem]">
      <div v-scroll-fade class="img-div">
        <img class="" src="@/assets/icons/newbie-icon.png" alt="" />
        <p class="img-text">입문</p>
      </div>
      <div v-scroll-fade class="img-div">
        <img class="" src="@/assets/icons/enjoy-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="img-text">취미</p>
        </div>
      </div>
      <div v-scroll-fade class="img-div">
        <img class="" src="@/assets/icons/university-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="img-text">입시생</p>
        </div>
      </div>
      <div v-scroll-fade class="img-div">
        <img class="" src="@/assets/icons/pro-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="img-text">프로</p>
        </div>
      </div>
      <div v-scroll-fade class="img-div">
        <img class="" src="@/assets/icons/money-icon.png" alt="" />
        <div class="flex flex-col justify-center items-center">
          <p class="img-text">고가</p>
        </div>
      </div>
    </div>
  </div>
</template>