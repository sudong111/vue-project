export default {
    mounted(el: HTMLElement) {
        el.classList.add('bounce-fade-hidden');

        const handleScroll = () => {
            const rect = el.getBoundingClientRect();
            const windowHeight = window.innerHeight || document.documentElement.clientHeight;

            const isVisible = rect.top < windowHeight && rect.bottom > 0;

            if (isVisible) {
                if (!el.classList.contains('bounce-fade-visible')) {
                    el.classList.remove('bounce-fade-hidden');
                    el.classList.add('bounce-fade-visible');
                }
            } else {
                if (el.classList.contains('bounce-fade-visible')) {
                    el.classList.remove('bounce-fade-visible');
                    el.classList.add('bounce-fade-hidden');
                }
            }
        };

        window.addEventListener('scroll', handleScroll);
        handleScroll();

        (el as any)._scrollBounceFadeHandler = handleScroll;
    },
    unmounted(el: HTMLElement) {
        window.removeEventListener('scroll', (el as any)._scrollBounceFadeHandler);
    },
};