import {defineStore} from "pinia";
import {useStorage} from "@vueuse/core";
import type {Category, Subtype, Guitar} from "@/types/interfaces.ts";


export const useGuitarStore = defineStore('guitar', () => {

    const defaultGuitar: Guitar = {
        id: 0,
        name: '',
        subtype_id: 0,
        brand: '',
        price: 0,
        image_url: '',
        description: '',
        stock: 0
    };

    const guitars = useStorage<Guitar[]>('guitar', []);
    const guitar = useStorage<Guitar>('guitar-detail', defaultGuitar);
    const categories = useStorage<Category[]>('guitar-category', []);
    const subtypes = useStorage<Subtype[]>('guitar-subtype', []);

    const selectGuitar = async (category: string, subtype_id: number) => {
        try {
            const params = new URLSearchParams({
                category,
                subtype_id: String(subtype_id)
            });

            const response = await fetch(
                `http://localhost:8080/api/guitar/select?${params.toString()}`,
                { method: "GET" }
            );

            if (response.ok) {
                const result = await response.json();
                guitars.value = result.data;
            } else {
                console.log(await response.text());
            }
        } catch (error) {
            console.log(error);
        }
    };

    const selectGuitarDetail = async (id: number) => {
        try {
            const params = new URLSearchParams({
                id: String(id)
            });

            const response = await fetch(
                `http://localhost:8080/api/guitar/detail?${params.toString()}`,
                { method: "GET" }
            );

            if (response.ok) {
                const result = await response.json();
                guitar.value = result.data;
            } else {
                console.log(await response.text());
            }
        } catch (error) {
            console.log(error);
        }
    };

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

    const insertGuitar = async (data: FormData ) => {
        try {
            const response = await fetch('http://localhost:8080/api/guitar/insert', {
                method: 'POST',
                body: data
            });

            if(response.ok) {
                const text = await response.text();
                return {isSuccess: true, message: text}
            }
            else {
                const errorText = await response.text();
                return { isSuccess: false, message: errorText };
            }

        } catch (error) {
            console.log(error);
            return { isSuccess: false, message: '네트워크 오류' };
        }
    }


    return { guitar, guitars, categories, subtypes, selectGuitarDetail, selectGuitar, selectAllCategory, selectAllSubtype, insertGuitar };
})