import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
    state: () => ({
        token: null as string | null,
        username: null as string | null,
    }),
    getters: {
        // 토큰 존재 여부로 로그인 상태 판단
        isLoggedIn: (state) => !!state.token,
    },

    actions: {
        async login(username: string, password: string){
            try {
                const response = await fetch('http://localhost:8080/api/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({username, password})
                })

                if(response.ok) {
                    const user = await response.json();

                    this.token = user.token;
                    this.username = user.username;

                    return { isSuccess: true, message: "done" };
                }
                else {
                    const errorText = await response.text();
                    return { isSuccess: false, message: errorText };
                }
            }
            catch (error) {
                console.log(error)
                return { isSuccess: false, message: 'network error' };
            }
        },
        logout() {
            this.token = null;
            this.username = null;
        },
    },
    persist: true,
});
