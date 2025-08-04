<script setup lang="ts">
import { ref } from 'vue'
import {Card} from '@/components/ui/card'
import {Input} from '@/components/ui/input'
import {Label} from '@/components/ui/label'
import {Button} from "@/components/ui/button";
import {useNavigation} from "@/utils/navigation.ts";
import {useUserStore} from "@/stores/user.ts";
import { useValidation, required, minLength, maxLength, isStrongPassword } from "@/utils/validation.ts";
import Alert from "@/components/alert.vue";

const username = ref('')
const password = ref('')
const { handleViewChanged } = useNavigation();
const userStore = useUserStore();
const { validateField, validationErrors, isFormValid } = useValidation();
const alertInfo = ref({
  show: false,
  variant: 'default',
  title: '',
  description: '',
});

const hideAlert = () => {
  alertInfo.value.show = false;
};

const clearValidationError = (fieldName: string) => {
  delete validationErrors.value[fieldName];
  hideAlert();
};

const login = async () => {
  const isUsernameValid = validateField(username.value, [required, minLength(4), maxLength(50)], 'id');
  const isPasswordValid = validateField(password.value, [required, isStrongPassword], 'password');

  if (!isUsernameValid || !isPasswordValid) {
    return;
  }

  const result = await userStore.login(username.value, password.value);

  if (result.isSuccess) {
    handleViewChanged('/');
  }
  else {
    alertInfo.value = {
      show: true,
      variant: 'destructive',
      title: '로그인 실패',
      description: result.message,
    };
  }
}


</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <div class="mt-20">
        <Card class="form-card">
          <form class="flex flex-col w-full gap-6" @submit.prevent="login">
            <Label class="font-bold text-xl">로그인</Label>
            <Alert
                v-if="alertInfo.show"
                :variant="alertInfo.variant"
                :title="alertInfo.title"
                :description="alertInfo.description"
                @close="hideAlert"
            />
            <Input
                placeholder="id"
                v-model="username"
                :class="{ 'border-destructive': validationErrors.id }"
                @focus="clearValidationError('id')"
                @input="clearValidationError('id')"
            ></Input>
            <p v-if="validationErrors.id" class="text-red-500 text-sm">{{ validationErrors.id }}</p>
            <Input
                type="password"
                placeholder="password"
                v-model="password"
                :class="{ 'border-destructive': validationErrors.password }"
                @focus="clearValidationError('password')"
                @input="clearValidationError('password')"
            ></Input>
            <p v-if="validationErrors.password" class="text-red-500 text-sm">{{ validationErrors.password }}</p>
            <div class="flex justify-between w-full">
              <div class="flex gap-1 items-center">
                <Label class="hover:underline" @click="handleViewChanged('sign-up')">회원 가입</Label>
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
            <Button variant="submit" type="submit">Log-in</Button>
          </form>
        </Card>
      </div>
    </div>
  </div>
</template>