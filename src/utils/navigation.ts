import { useRouter } from "vue-router"

/**
 * 페이지 이동 네비게이션
 * 입력된 페이지(/acoustic, /electric ..)로 이동시키는 함수
 */
export function useNavigation() {
    const router = useRouter()

    /**
     * 입력 필드에 따라 페이지를 라우팅한다.
     * @param view 이동할 페이지 주소
     */
    function handleViewChanged(view: string) {
        router.push(`/${view}`)
    }

    function handleViewChangedWithQuery(view: string, query: Array<any>) {
        router.push({
            name: `${view}`,
            query: {
                category_id: query[0]?.category.id,
                subtype_id: query[0]?.subtype.id,
            }
        })
    }

    function handleDetailView(view: string, id: number) {
        router.push({
            name: `${view}`,
            query: {
                id: id
            }
        })
    }

    return { handleViewChanged, handleViewChangedWithQuery, handleDetailView }
}