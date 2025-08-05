<script setup lang="ts">
</script>

<template>
  <div class="home-content-wrapper">
    <div class="flex flex-col items-center gap-6">
      <p v-scroll-fade class="title">어떤 제품을 찾으시나요?</p>
      <p v-scroll-fade class="desc">원하는 제품 종류를 선택해보세요</p>
    </div>
    <div class="home-content-container">
      <div v-scroll-fade>
        <div class="img-div hover-up">
          <img src="@/assets/icons/newbie-icon.png" alt="" />
          <p class="img-text">입문</p>
        </div>
      </div>
      <div v-scroll-fade>
        <div class="img-div hover-up">
          <img class="" src="@/assets/icons/enjoy-icon.png" alt="" />
          <p class="img-text">취미</p>
        </div>
      </div>
      <div v-scroll-fade>
        <div class="img-div hover-up">
          <img class="" src="@/assets/icons/university-icon.png" alt="" />
          <p class="img-text">입시생</p>
        </div>
      </div>
      <div v-scroll-fade>
        <div class="img-div hover-up">
        <img class="" src="@/assets/icons/pro-icon.png" alt="" />
          <p class="img-text">프로</p>
        </div>
      </div>
      <div v-scroll-fade>
        <div class="img-div hover-up">
          <img class="" src="@/assets/icons/money-icon.png" alt="" />
          <p class="img-text">고가</p>
        </div>
      </div>
    </div>
  </div>
</template>