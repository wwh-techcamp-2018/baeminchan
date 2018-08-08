let autoSearchList;

function autoSearchCallback(response) {

    response.json().then(res => {
        autoSearchList = new AutoSearchList(res.data);
    })
}

function autoSearchFocusHandler(evt) {

    evt.preventDefault();
    if (!autoSearchList) {
        getData("/api/product/search", autoSearchCallback);
    }
}

function autoSearchInputHandler(evt) {

    if (!autoSearchList) {
        return;
    }
    autoSearchList.removeAutoSearchView($(".auto_search_ul"));
    if (evt.target.value === "") {
        return;
    }
    autoSearchList.appendAutoSearchView($(".auto_search_div"), evt.target.value.trim())
}

function autoSearchKeyDownHandler(evt) {
    const evtCode = evt.code;
    const funtionKey = ["Enter", "ArrowUp", "ArrowDown"]
    if (funtionKey.includes(evtCode)) {
        evt.preventDefault();
        if (evtCode === "Enter") {
            autoSearchList.enterHandler();
            return;
        }
        autoSearchList.arrowBtnHandler(evtCode);
    }
}

function autoSearchBtnHandler(evt) {

    evt.preventDefault();
    autoSearchList.removeAutoSearchView($(".auto_search_ul"));
}

class AutoSearchList {
    constructor(data) {
        this.searchList = [];
        for (const searchItem of data) {
            this.searchList.push(new SearchItem(searchItem.id, searchItem.keyWord));
        }
    }

    makeAutoSearchView(matchedSearchItems, searchValue) {
        let autoSearchHTML = `<ul class="auto_search_ul">`
        for (const matchedSearchItem of matchedSearchItems) {
            autoSearchHTML += matchedSearchItem.makeSearchItemLi(searchValue);
        }
        autoSearchHTML += `</ul>`;
        return autoSearchHTML;
    }

    appendAutoSearchView(target, searchValue) {
        const matchedSearchItems = this.getMatchedSearchItems(searchValue);
        if (matchedSearchItems.length === 0)
            return;
        target.insertAdjacentHTML("afterbegin", this.makeAutoSearchView(matchedSearchItems, searchValue));
    }

    removeAutoSearchView(target) {
        if (!target)
            return;
        target.parentElement.removeChild(target);
    }

    getMatchedSearchItems(searchValue) {
        return this.searchList.filter(item => item.isMatchedSearchItem(searchValue));
    }

    arrowBtnHandler(evtCode) {
        const parent = $(".auto_search_ul");
        if (!parent)
            return;
        const on = $(".auto_search_ul .on");
        if (!on) {
            parent.firstElementChild.classList.add("on");
            return;
        }
        this.moveOnClass(on, evtCode)
    }

    moveOnClass(target, dir) {
        target.classList.remove("on");
        if (dir === "ArrowUp") {
            getPrevElement(target).classList.add("on");
            return;
        }
        if (dir === "ArrowDown") {
            getNextElement(target).classList.add("on");
            return;
        }
    }

    enterHandler() {
        const autoSearchUl = $(".auto_search_ul");
        if (!autoSearchUl)
            return;
        const on = $(".auto_search_ul .on");
        if (on) {
            $("#searching_text").value = on.textContent;
        }
        this.removeAutoSearchView(autoSearchUl);
    }
}

class SearchItem {
    constructor(id, keyWord) {
        this.id = id;
        this.keyWord = keyWord;
    }

    makeSearchItemLi(searchValue) {
        return `<li id = "search_item_${this.id}">${this.getHighlightKeyWord(searchValue)}</li>`
    }

    getHighlightKeyWord(searchValue) {
        const matchedIndex = Hangul.rangeSearch(this.keyWord, searchValue);
        const slicedWord = this.keyWord.slice(matchedIndex[0][0], matchedIndex[0][1] + 1)
        if (searchValue === slicedWord)
            return this.keyWord.replace(slicedWord, `<span class = "highlight">${slicedWord}</span>`)
        return this.keyWord;
    }

    isMatchedSearchItem(searchValue) {
        return Hangul.search(this.keyWord, searchValue) >= 0 ? true : false;
    }
}

window.addEventListener("DOMContentLoaded", () => {
    $("#searching_text").addEventListener("focus", autoSearchFocusHandler);
    $("#searching_text").addEventListener("input", autoSearchInputHandler);
    $("#searching_text").addEventListener("keydown", autoSearchKeyDownHandler);
    $("#searching_btn").addEventListener("click", autoSearchBtnHandler);
});
