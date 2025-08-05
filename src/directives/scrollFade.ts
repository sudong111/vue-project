import type { DirectiveBinding } from 'vue';

export default {
    mounted(el: HTMLElement, binding: DirectiveBinding) {
        const baseOpacity = '0';
        const baseTransform = 'translateY(60px)';
        const visibleOpacity = '1';
        const visibleTransform = 'translateY(0)';

        el.style.opacity = baseOpacity;
        el.style.transform = baseTransform;
        el.style.transition = 'opacity 0.8s ease-out, transform 0.8s ease-out';

        const handleScroll = () => {
            const rect = el.getBoundingClientRect();
            const windowHeight = window.innerHeight || document.documentElement.clientHeight;

            const isVisible = rect.top < windowHeight && rect.bottom > 0;

            if (isVisible) {
                el.style.opacity = visibleOpacity;
                el.style.transform = visibleTransform;
            } else {
                el.style.opacity = baseOpacity;
                el.style.transform = baseTransform;
            }
        };

        window.addEventListener('scroll', handleScroll);
        handleScroll();

        (el as any)._scrollFadeHandler = handleScroll;
    },
    unmounted(el: HTMLElement) {
        window.removeEventListener('scroll', (el as any)._scrollFadeHandler);
    },
};