import { useRouter } from "vue-router"

export function useNavigation() {
    const router = useRouter()
    function handleViewChanged(view: string) {
        router.push(`/${view}`)
    }

    return { handleViewChanged }
}