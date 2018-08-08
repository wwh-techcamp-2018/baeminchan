const KEY_UP = 38;
const KEY_DOWN = 40;

class SearchBar {
    constructor() {
        addEvent('#searching_text', 'input', this.search.bind(this));
    }

    search({target}) {
        clearTimeout(this.shootTimer);
        this.shootTimer = setTimeout(() => {
            this.shootHandler(target.value)
        }, 100);
    }

    renderProducts(products) {
        let resultContainer = $('#search-result>ul');
        resultContainer.innerText = "";
        const templates = Array.from(products.data).map(p => this.createDomElement(p));
        resultContainer.insertAdjacentHTML('afterbegin', templates.join(""));
    }

    createDomElement(p) {
        return `<li>${p.title}</li>`.replace(this.keyword, `<span class="highlight-keyword">${this.keyword}</span>`);
    }

    shootHandler(text) {
        this.keyword = text;
        new FetchManager({
            url: `/api/search/${text}`,
            method: "GET",
        })
            .onSuccess(response => {
                this.renderProducts(response);
            })
            .doFetch();
    };
}

document.addEventListener('DOMContentLoaded', () => {
    let searchBar = new SearchBar();
    addEvent('.search-form', 'keyup', evt => {
        const focused = $('.search-form li.on');

        if (!focused) {
            $('.search-form li').classList.toggle('on');
        }

        if (evt.keyCode === KEY_DOWN) {
            focused.classList.toggle('on');
            focused.nextElementSibling.classList.toggle('on');
        }

        if (evt.keyCode === KEY_UP) {
            focused.classList.toggle('on');
            focused.previousElementSibling.classList.toggle('on');
        }
    });
});
