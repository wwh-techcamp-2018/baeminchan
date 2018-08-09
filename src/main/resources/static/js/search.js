function debounce(callback, wait) {
    let timer;

    return function(...args) {
        if (timer) {
            clearTimeout(timer);
        }
        timer = setTimeout(() => {
            callback(...args)
        }, wait);
    };
}

class Search {

    constructor() {
        this.searchKeyword = debounce(this.searchKeyword.bind(this), 300);
        this.renderSearchResults = this.renderSearchResults.bind(this);
        this.focusOnContent = this.focusOnContent.bind(this);
        this.focusOutContent = this.focusOutContent.bind(this);
        this.searchItemTemplate = this.searchItemTemplate.bind(this);
        this.highlightKeyword = this.highlightKeyword.bind(this);
        this.moveRecommendationFocus = this.moveRecommendationFocus.bind(this);
        this.insertSearchingText = this.insertSearchingText.bind(this);
    }

    setSearchForm(element) {
        this.searchForm = element;
        return this;
    }

    setSearchingText(element) {
        this.searchingText = element;
        return this;
    }

    setRecommendationList(element) {
        this.recommendations = element;
        return this;
    }

    build() {
        this.searchForm.addEventListener('submit', e => e.preventDefault());
        this.searchingText.addEventListener('input', this.searchKeyword);
        this.searchingText.addEventListener('keyup', this.moveRecommendationFocus);
        this.recommendations.addEventListener('mouseover', this.focusOnContent);
        this.recommendations.addEventListener('mouseout', this.focusOutContent);
    }

    focusOnContent(e) {
        const focusList = e.target.closest('li');
        focusList && focusList.classList.add('on');
    }

    focusOutContent() {
        const focusedList = this.recommendations.querySelector("li.on");
        focusedList && focusedList.classList.remove('on');
    }

    searchKeyword(e) {
        const { value: keyword } = e.target;
        this.keyword = keyword;
        this.recommendations.innerHTML = '';

        fetch(`/products/search?keyword=${keyword}`)
            .then(response => response.json())
            .then(this.renderSearchResults);
    }

    renderSearchResults({data}) {
        this.recommendations.style.display = data.length ? 'block' : 'none';
        this.recommendations.innerHTML += data.map(this.searchItemTemplate).join('');
    }

    searchItemTemplate({productId: id, title}) {
        return `<li id="search-product-${id}"><a href="#">${this.highlightKeyword(title)}</a></li>`;
    }

    highlightKeyword(title) {
        return title.replace(this.keyword, `<strong>${this.keyword}</strong>`);
    }

    moveRecommendationFocus(e) {
        if (!this.recommendations.children.length) {
            return;
        }

        const ENTER = 13;
        const UP_ARROW = 38;
        const DOWN_ARROW = 40;
        const currentFocus = this.recommendations.querySelector("li.on");

        currentFocus && currentFocus.classList.remove("on");
        const upFocus = (currentFocus && currentFocus.previousElementSibling) || this.recommendations.querySelector('li:last-child');
        const downFocus = (currentFocus && currentFocus.nextElementSibling) || this.recommendations.querySelector('li:first-child');

        switch (e.keyCode) {
            case UP_ARROW:
                upFocus.classList.add("on");
                break;
            case DOWN_ARROW:
                downFocus.classList.add("on");
                break;
            case ENTER:
                this.insertSearchingText(currentFocus);
                break;
        }
    }

    insertSearchingText(focus) {
        this.searchingText.value = focus.innerText;
        this.recommendations.innerHTML = '';
    }

}

new Search()
    .setSearchForm($('.search-form'))
    .setSearchingText($("#searching_text"))
    .setRecommendationList($('.search-recommendation-list'))
    .build();