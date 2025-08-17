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
    id: number;
    name: string;
    subtype_id: number;
    brand: string;
    price: number;
    image_url: string;
    description: string;
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

export interface CategorySubtypeQuery {
    category : Category;
    subtype : Subtype;
}

