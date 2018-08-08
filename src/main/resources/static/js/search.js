const KEY_UP = 38;
const KEY_DOWN = 40;
const KEY_ENTER = 13;

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

    selectInput() {
        $('#searching_text').value = $('li[class="on"]').textContent;
        $('#search-result>ul').innerText = "";
    }

    down() {
        const focused = $('.search-form li.on');
        focused ? (() => {
            focused.classList.toggle('on');
            focused.nextElementSibling.classList.toggle('on');
        })() : $('.search-form li').classList.toggle('on');
    }

    up() {
        const focused = $('.search-form li.on');
        focused ? (() => {
            focused.classList.toggle('on');
            focused.previousElementSibling.classList.toggle('on');
        })() : $All('.search-form li').pop().classList.toggle('on');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    let searchBar = new SearchBar();
    addEvent('.search-form', 'keyup', evt => {
        switch (evt.keyCode) {
            case KEY_DOWN:
                searchBar.down();
                break;
            case KEY_UP:
                searchBar.up();
                break;
            case KEY_ENTER:
                searchBar.selectInput();
                break;
        }
    });
});
