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
 * response 데이터 정보
 */
export interface ResponseResult {
    isSuccess: boolean;
    message: string;
}

/**
 *  유효성 검사 정보
 */
export interface Validation {
    isValid: boolean;
    message: string;
}

export interface Guitar {
    name: String;
    subtype_id: number;
    brand: String;
    price: number;
    description: String;
    stock: number;
}

export interface Category {
    id: number;
    name: string;
}

export interface Subtype {
    id: number;
    category_id: number;
    name: string;
}

