<script setup lang="ts">
import {Card} from "@/components/ui/card"
import {Label} from "@/components/ui/label";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {ref} from "vue";
import {
  isStrongPassword,
  maxLength,
  minLength,
  required,
  isEmail,
  useValidation,
  isSameValue
} from "@/utils/validation.ts";
import {useNavigation} from "@/utils/navigation.ts";
import {useUserStore} from "@/stores/user.ts";
import Alert from "@/components/alert.vue";
import { type AlertVariants } from '@/components/ui/alert';

const username = ref('')
const isDuplicationChecked = ref(false);
const password = ref('')
const password_check = ref('')
const email = ref('')
const { handleViewChanged } = useNavigation();
const userStore = useUserStore();
const { validateField, validationErrors, isFormValid } = useValidation();
const duplicationMessage = ref({
  text: '',
  color: ''
});

const alertInfo = ref<{
  show: boolean;
  variant: AlertVariants['variant'];
  title: string;
  description: string;
}>({
  show: false,
  variant: 'default',
  title: '',
  description: '',
});

const hideAlert = () => {
  alertInfo.value.show = false;
};

const duplication = async () => {
  const isUsernameValid = validateField(username.value, [required, minLength(4), maxLength(50)], 'id');

  if(!isUsernameValid) {
    return;
  }

  const result = await userStore.userDuplicationChecked(username.value);

  if(result.isSuccess) {
    duplicationMessage.value = {
      text: result.message,
      color: 'text-green-500'
    }
    isDuplicationChecked.value = true;
    clearValidationError('id');
  }
  else {
    duplicationMessage.value = {
      text: result.message,
      color: 'text-red-500'
    }
    isDuplicationChecked.value = false;
    clearValidationError('id');
  }
}

const signUp = async () => {
  const isUsernameValid = validateField(username.value, [required, minLength(4), maxLength(50)], 'id');
  const isPasswordValid = validateField(password.value, [required, isStrongPassword], 'password');
  const isPasswordCheckValid = validateField(password_check.value, [isSameValue(password.value, 'password')], 'passwordCheck');
  const isEmailValid = validateField(email.value, [required, isEmail], 'email');

  if (!isUsernameValid || !isPasswordValid || !isPasswordCheckValid || !isEmailValid ) {
    return;
  }

  if(!isDuplicationChecked.value) {
    duplicationMessage.value = {
      text: "아이디 중복을 수행하지 않았습니다.",
      color: 'text-red-500'
    }
    clearValidationError('id');
    return;
  }

  const result = await userStore.signup(username.value, password.value, email.value);

  if(result.isSuccess) {
    handleViewChanged("/");
  }
  else {
    alertInfo.value = {
      show: true,
      variant: 'destructive',
      title: '회원가입 실패',
      description: result.message,
    };
  }
}

const clearValidationError = (fieldName: string) => {
  delete validationErrors.value[fieldName];
  hideAlert();
};

const clearDuplication = () => {
  duplicationMessage.value = {
    text: '',
    color: ''
  };
  isDuplicationChecked.value = false;
}
</script>

<template>
  <div class="view">
    <div class="view-wrapper">
      <div class="flex justify-center mt-20">
        <Card class="form-card">
          <form class="flex flex-col w-full gap-6" @submit.prevent="signUp" novalidate>
            <Label class="font-bold text-xl">회원가입</Label>
            <Alert
                v-if="alertInfo.show"
                :variant="alertInfo.variant"
                :title="alertInfo.title"
                :description="alertInfo.description"
                @close="hideAlert"
            />
            <div>
              <p class="text-sm">아이디</p>
              <div class="flex">
                <Input
                    placeholder="id"
                    v-model="username"
                    :class="{ 'border-destructive': validationErrors.id }"
                    @focus="clearValidationError('id'); clearDuplication()"
                    @input="clearValidationError('id'); clearDuplication()">
                </Input>
                <Button type="button" variant="outline" class="ml-2" @click="duplication">중복 확인</Button>
              </div>
              <p v-if="validationErrors.id" class="text-red-500 text-sm">{{ validationErrors.id }}</p>
              <p v-if="duplicationMessage.text" :class="`${duplicationMessage.color} text-sm`">
                {{ duplicationMessage.text }}
              </p>
            </div>
            <div>
              <p class="text-sm">비밀번호</p>
              <Input
                  type="password"
                  placeholder="password"
                  v-model="password"
                  :class="{ 'border-destructive': validationErrors.password }"
                  @focus="clearValidationError('password')"
                  @input="clearValidationError('password')">
              </Input>
              <p v-if="validationErrors.password" class="text-red-500 text-sm">{{ validationErrors.password }}</p>
            </div>
            <div>
              <p class="text-sm">비밀번호 확인</p>
              <Input
                  type="password"
                  placeholder="password"
                  v-model="password_check"
                  :class="{ 'border-destructive': validationErrors.passwordCheck }"
                  @focus="clearValidationError('passwordCheck')"
                  @input="clearValidationError('passwordCheck')">
              </Input>
              <p v-if="validationErrors.passwordCheck" class="text-red-500 text-sm">{{ validationErrors.passwordCheck }}</p>
            </div>
            <div>
              <p class="text-sm">이메일</p>
              <Input
                  type="email"
                  placeholder="email"
                  v-model="email"
                  :class="{ 'border-destructive': validationErrors.email }"
                  @focus="clearValidationError('email')"
                  @input="clearValidationError('email')"
              >
              </Input>
              <p v-if="validationErrors.email" class="text-red-500 text-sm">{{ validationErrors.email }}</p>
            </div>
            <Button variant="submit" type="submit">회원 가입</Button>
          </form>
        </Card>
      </div>
    </div>
  </div>

</template>