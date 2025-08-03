<script setup lang="ts">
import { ref } from 'vue'
import {Card} from '@/components/ui/card'
import {Input} from '@/components/ui/input'
import {Checkbox} from '@/components/ui/checkbox'
import {Label} from '@/components/ui/label'
import {Button} from "@/components/ui/button";
import {useNavigation} from "@/utils/navigation.ts";
import {useUserStore} from "@/stores/user.ts";
import { useValidation, required, minLength, maxLength, isStrongPassword } from "@/utils/validation.ts";
import Alert from "@/components/alert.vue";

const username = ref('')
const password = ref('')
const rememberId = ref(false)
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
  alertInfo.value.show = false;
};

const login = async () => {
  const isUsernameValid = validateField(username.value, [required, minLength(4), maxLength(50)], 'id');
  const isPasswordValid = validateField(password.value, [required, isStrongPassword], 'password');

  if (!isUsernameValid || !isPasswordValid) {
    return;
  }


  const loginPayload = {
    username: username.value,
    password: password.value
  }

  try {
    // fetch API를 사용해 백엔드에 POST 요청을 보냅니다.
    const response = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(loginPayload)
    })

    if (response.ok) {
      // 로그인이 성공한 경우
      const userData = await response.json();
      userStore.login(userData);
      handleViewChanged('/');
    } else {
      // 로그인이 실패한 경우
      const errorText = await response.text()
      alertInfo.value = {
        show: true,
        variant: 'destructive',
        title: '로그인 실패',
        description: errorText,
      };
    }
  } catch (error) {
    // 네트워크 오류 등의 예외 처리
    console.error('Login failed:', error)
    alertInfo.value = {
      show: true,
      variant: 'destructive',
      title: '로그인 실패',
      description: '네트워크 오류로 인해 로그인에 실패하였습니다. ' + error,
    };
  }
}
</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <div class="mt-20">
        <Card class="login">
          <form class="flex flex-col w-full gap-6" @submit.prevent="login">
            <Label class="font-bold text-xl">Log-in</Label>
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
                <Checkbox v-model="rememberId"></Checkbox>
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
            <Button class="bg-blue-500" type="submit">Log-in</Button>
          </form>
        </Card>
      </div>
    </div>
  </div>
</template>