/**
 * 사용자 정보
 */
export interface User {
    id: number;
    username: string;
    authority: string;
    email: string;
}

/**
 *  유효성 검사 정보
 */
export interface Validation {
    isValid: boolean;
    message: string;
}

