<script setup lang="ts">

</script>

<template>
  <div class="flex flex-col items-center gap-4 lg:gap-8 mx-10">
    <p v-scroll-fade class="text-xl sm:text-2xl lg:text-4xl font-semibold">가장 많이 팔린 제품들이에요</p>
    <div class="flex items-center gap-4 sm:gap-8 lg:gap-16 lg:w-[40rem]">
      <div class="flex flex-col">
        <img src="@/assets/acoustic/49622_acoustic_Godin-Nylon.jpg" alt="" />
        <div class="nomination-text-container">
          <p class="product-text">[acoustic]</p>
          <p class="product-text">Godin-Nylon</p>
        </div>
      </div>
      <div>
        <div class="flex flex-col">
          <img src="@/assets/acoustic/50147_acoustic_Metropolis-LR.jpg" alt="" />
          <div class="nomination-text-container">
            <p class="product-text">[acoustic]</p>
            <p class="product-text">Fairmount-CN</p>
          </div>
        </div>
      </div>
      <div class="flex flex-col">
        <img src="../assets/acoustic/51793_acoustic_Arena-EQ.jpg" alt="" />
        <div class="nomination-text-container">
          <p class="product-text">[acoustic]</p>
          <p class="product-text">Fairmount-CN</p>
        </div>
      </div>
    </div>
  </div>
</template>