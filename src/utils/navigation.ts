import { useRouter } from "vue-router"

export function useNavigation() {
    const router = useRouter()
    function handleViewButtonClicked(view: string) {
        router.push(`/${view}`)
    }

    return { handleViewButtonClicked }
}