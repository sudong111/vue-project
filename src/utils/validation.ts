import { ref, computed } from 'vue';
import type { Validation } from '@/types/interfaces'

/**
 * 유효성 검사 로직을 위한 컴포저블 함수.
 * 다양한 유효성 규칙을 적용하고 검사 결과를 반환합니다.
 */
export function useValidation() {
    const isFormValid = ref(true);
    const validationErrors = ref<Record<string, string>>({});

    /**
     * 입력 필드에 대한 유효성 검사 규칙을 실행합니다.
     * @param value 검사할 입력 값
     * @param rules 적용할 유효성 검사 규칙 배열
     * @param fieldName 오류 메시지에 사용할 필드 이름
     */
    const validateField = (value: string, rules: Function[], fieldName: string) => {
        let errorMessage = '';
        let isValid = true;

        for (const rule of rules) {
            const result: Validation = rule(value, fieldName);
            if (!result.isValid) {
                errorMessage = result.message;
                isValid = false;
                break;
            }
        }

        // 유효성 검사 결과 업데이트
        if (errorMessage) {
            validationErrors.value[fieldName] = errorMessage;
        } else {
            delete validationErrors.value[fieldName];
        }

        isFormValid.value = Object.keys(validationErrors.value).length === 0;
        return isValid;
    };

    const isFormValidComputed = computed(() => Object.keys(validationErrors.value).length === 0);

    return {
        validateField,
        validationErrors,
        isFormValid: isFormValidComputed,
    };
}

// 필드가 비어있는지 확인하는 규칙
export const required = (value: string, fieldName: string): Validation => {
    if (!value || value.trim() === '') {
        return { isValid: false, message: `${fieldName}은(는) 필수 입력 항목입니다.` };
    }
    return { isValid: true, message: '' };
};

// 최소 길이 규칙
export const minLength = (min: number) => (value: string, fieldName: string): Validation => {
    if (value.length < min) {
        return { isValid: false, message: `${fieldName}은(는) 최소 ${min}자 이상이어야 합니다.` };
    }
    return { isValid: true, message: '' };
};

// 최대 길이 규칙
export const maxLength = (max: number) => (value: string, fieldName: string): Validation => {
    if (value.length > max) {
        return { isValid: false, message: `${fieldName}은(는) 최대 ${max}자를 초과할 수 없습니다.` };
    }
    return { isValid: true, message: '' };
};

// 비밀번호 복잡성 규칙 (영문, 숫자, 특수문자 조합)
export const isStrongPassword = (value: string, fieldName: string): Validation => {
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{6,32}$/;
    if (!passwordRegex.test(value)) {
        return {
            isValid: false,
            message: `${fieldName}은(는) 6~32자의 영문, 숫자, 특수문자를 조합하여 입력해야 합니다.`
        };
    }
    return { isValid: true, message: '' };
};

// 이메일 형식 규칙
export const isEmail = (value: string, fieldName: string): Validation => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(value)) {
        return { isValid: false, message: `유효한 ${fieldName} 을 입력해주세요.` };
    }
    return { isValid: true, message: '' };
};

