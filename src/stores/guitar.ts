import {defineStore} from "pinia";
import {useStorage} from "@vueuse/core";
import type {Category, Subtype} from "@/types/interfaces.ts";


export const useGuitarStore = defineStore('guitar', () => {

    const categories = useStorage<Category[]>('guitar-category', []);
    const subtypes = useStorage<Subtype[]>('guitar-subtype', []);

    const selectAllCategory = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/category/selectAll', {
                method: 'GET'
            });

            if (response.ok) {
                const result = await response.json();
                categories.value = result.data;

            } else {
                console.log(response.text())
            }

        } catch (error) {
            console.log(error);
        }
    }

    const selectAllSubtype = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/subtype/selectAll', {
                method: 'GET'
            });

            if (response.ok) {
                const result = await response.json();
                subtypes.value = result.data;

            } else {
                console.log(response.text())
            }

        } catch (error) {
            console.log(error);
        }
    }


    return { categories, subtypes, selectAllCategory, selectAllSubtype };
})