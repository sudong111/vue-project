import { computed } from 'vue';
import { defineStore } from 'pinia';
import { useStorage } from '@vueuse/core'

export const useUserStore = defineStore('user', () => {
    const token = useStorage('user-token', '')
    const username = useStorage('user-username', '')

    const isLoggedIn = computed(() => !!token.value);

    const login = async (inputUsername: string, password: string) => {
        try {
            const response = await fetch('http://localhost:8080/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username: inputUsername, password })
            });

            if (response.ok) {
                const user = await response.json();
                token.value = user.token;
                username.value = user.username;
                return { isSuccess: true, message: "로그인 성공" };
            } else {
                const errorText = await response.text();
                return { isSuccess: false, message: errorText };
            }
        } catch (error) {
            console.log(error);
            return { isSuccess: false, message: '네트워크 오류' };
        }
    };

    const logout = () => {
        token.value = null;
        username.value = null;
    };

    const signup = async (inputUsername: string, password: string, email: string) => {
        try {
            const response = await fetch('http://localhost:8080/api/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({username: inputUsername, password, email})
            })

            if(response.ok) {
                const text = await response.text();
                return {isSuccess: true, message: text}
            }
            else {
                const errorText = await response.text();
                return { isSuccess: false, message: errorText };
            }
        }
        catch (error) {
            console.log(error);
            return { isSuccess: false, message: '네트워크 오류' };
        }
    }

    const userDuplicationChecked = async (inputUsername: string) => {
        try {
            const response = await fetch('http://localhost:8080/api/user/duplication', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({username: inputUsername})
            })

            if(response.ok) {
                const text = await response.text();
                return {isSuccess: true, message: text}
            }
            else {
                const errorText = await response.text();
                return { isSuccess: false, message: errorText };
            }

        }
        catch (error) {
            console.log(error);
            return { isSuccess: false, message: '네트워크 오류' };
        }
    }

    return { token, username, isLoggedIn, login, logout, signup, userDuplicationChecked };
});
