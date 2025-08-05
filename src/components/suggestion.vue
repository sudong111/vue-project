<script setup lang="ts">

</script>

<template>
  <div class="home-content-wrapper">
    <p v-scroll-fade class="title">가장 많이 팔린 제품들이에요</p>
    <div class="home-content-container">
      <div v-scroll-fade>
        <div class="img-div hover-up">
          <img src="@/assets/acoustic/49622_acoustic_Godin-Nylon.jpg" alt="" />
          <div class="product-text-container">
            <p class="product-category">[acoustic]</p>
            <p class="product-title">Godin-Nylon</p>
            <p class="product-title">324,000</p>
          </div>
        </div>
      </div>
      <div>
        <div v-scroll-fade>
          <div class="img-div hover-up">
            <img src="@/assets/acoustic/50147_acoustic_Metropolis-LR.jpg" alt="" />
            <div class="product-text-container">
              <p class="product-category">[acoustic]</p>
              <p class="product-title">Fairmount-CN</p>
              <p class="product-title">520,000</p>
            </div>
          </div>
        </div>
      </div>
      <div v-scroll-fade>
        <div class="img-div hover-up">
          <img src="../assets/acoustic/51793_acoustic_Arena-EQ.jpg" alt="" />
          <div class="product-text-container">
            <p class="product-category">[acoustic]</p>
            <p class="product-title">Fairmount-CN</p>
            <p class="product-title">168,000</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>