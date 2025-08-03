import { defineStore } from 'pinia';
import type { User } from '@/types/interfaces';

export const useUserStore = defineStore('user', {
    state: () => ({
        isLoggedIn: false,
        user: null as User | null,
    }),

    actions: {
        login(userData: User) {
            this.isLoggedIn = true;
            this.user = userData;
        },
        logout() {
            this.isLoggedIn = false;
            this.user = null;
        },
    },
});
