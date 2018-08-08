const UP    = 38;
const DOWN  = 40;
const ENTER = 13;
const CLASS_INVISIBLE = "invisible";
const CLASS_NAME_NOW  = "now";
const SIDE_DISHES_CACHE = {};

document.addEventListener("DOMContentLoaded", () => {
    const autoSearching = new AutoSearching();
    requestCategories();
    addEventListeners(autoSearching);
});

function addEventListeners(autoSearching) {
    document.onkeydown = function(event) {
        if ($("." + CLASS_INVISIBLE) == null && event.keyCode === ENTER) event.preventDefault();
    };
    document.onclick = function({target}) {
        if(target.parentNode.id === "searching_box") {
            $("#searching_text").value = autoSearching.removeSpanTag(target.innerHTML);
        }
        $("#searching_box").classList.add(CLASS_INVISIBLE);
    };
    $("#best-categories").addEventListener("click", onClickBestCategory);
    $("#searching_text").addEventListener("keyup", autoSearching.onKeyUpSearchForm.bind(autoSearching));
}

class AutoSearching {
    constructor() {
        this.index = -1;
        this.indexLimit = -1;
    }

    onKeyUpSearchForm({target, keyCode}) {
        if(!this.checkEnableAjax(target)) return;

        switch (keyCode) {
            case UP:    this.moveUp(); break;
            case DOWN:  this.moveDown(); break;
            case ENTER: this.pressEnter(); break;
            default   : fetchManager({
                url: '/api/sidedishes/' + target.value,
                method: 'GET',
                headers: { 'content-type': 'application/json'},
                callback: this.onKeyUpCallBack.bind(this),
                errCallback: alertError
            });
        }
    }

    checkEnableAjax(target) {
        const pattern = /^[가-힇|A-Za-z|0-9]{0,20}$/;
        const currentText = target.value.trim();

        if (currentText === "" || !pattern.test(currentText)) {
            this.toggleInvisible(true);
            return false;
        }
        return true;
    }

    moveUp() {
        if(this.index < 0){
            return;
        }
        this.removeBackground();
        this.index--;
        this.addBackground();
    }

    moveDown() {
        if(this.index >= 0) {
            this.removeBackground();
        }
        this.index = (this.index + 1) % this.indexLimit;
        this.addBackground();
    }

    pressEnter() {
        if ($("." + CLASS_INVISIBLE) != null) return;

        let rawText = $("#searching_box").children[this.index].innerHTML;
        $("#searching_text").value = this.removeSpanTag(rawText);
        this.index = -1;
        this.toggleInvisible(true);
    }

    removeSpanTag(rawText) {
        rawText = rawText.replace('<span class=\"highlight-search\">', "");
        return rawText.replace("</span>", "");
    }

    onKeyUpCallBack(response) {
        response.json().then((sideDishes) => {
            this.toggleInvisible(sideDishes.length === 0);
            this.index = -1;
            this.indexLimit = sideDishes.length;

            const searchingHTML = sideDishes
                .map((sideDishName) => {
                    return this.highlightWord(sideDishName, $("#searching_text").value);
                })
                .reduce(this.makeSearchingHTML, ``);
            $("#searching_box").innerHTML = '';
            $("#searching_box").insertAdjacentHTML("beforeend", searchingHTML);
        })
    }

    addBackground() {
        if(this.index === -1) return;
        $("#searching_box").children[this.index].classList.add("highlight-background");
    }

    removeBackground() {
        $(".highlight-background").classList.remove("highlight-background");
    }

    toggleInvisible(isInvisible) {
        if(isInvisible) {
            $("#searching_box").classList.add(CLASS_INVISIBLE);
            return;
        }

        $("#searching_box").classList.remove(CLASS_INVISIBLE);
    }

    highlightWord(word, searchingText) {
        return word.replace(searchingText, `<span class="highlight-search">${searchingText}</span>`);
    }

    makeSearchingHTML(prev, curr) {
        return prev + `<li>${curr}</li>`;
    }
}

function requestCategories() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccess,
        errCallback: alertError
    });

    fetchManager({
        url: '/api/bestCategories',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccessBestCategories,
        errCallback: alertError
    });
}

function onSuccess(response) {
    response.json().then((response) => {
        response.forEach((category) => {
            // 추가
            const html1 = `<li>
                              <a href="./side-dishs.html">` + category.name + `</a>
                              <ul class="sub-menu">`;

            const html2 = category.subCategories.reduce((prevSubCategory, nextSubCategory) => { return prevSubCategory + `<li><a href="#">` + nextSubCategory.name + `</a></li>`}, ``);

            const html3 = `</ul></li>`;
            $("#menu").insertAdjacentHTML("beforeend", html1 + html2 + html3);
        });
    });
}

function onSuccessBestCategories(response) {
    response.json().then((result) => {
        const html = result.reduce((prev, next) => {
            return prev + `<li><a class="best-category-a" data-category-id=${next.id}>${next.name}</a></li>`;
        }, ``);

        $("#best-categories").insertAdjacentHTML("beforeend", html);

        const randomNumber = (generateRandomNumber(1, result.length));
        loadSideDishes(randomNumber);
        $("#best-categories").children[randomNumber - 1].querySelector("a").classList.toggle(CLASS_NAME_NOW);
    });
}

function onClickBestCategory(evt) {
    let {"target" : target} = evt;
    $(".now").classList.toggle(CLASS_NAME_NOW);
    target.classList.toggle(CLASS_NAME_NOW);
    if(!SIDE_DISHES_CACHE[target.dataset["categoryId"]])
        loadSideDishes(target.dataset["categoryId"]);
    else
        updateSideDishes(SIDE_DISHES_CACHE[target.dataset["categoryId"]]);
}

function loadSideDishes(categoryId) {
    fetchManager({
        url: '/api/bestCategories/' + categoryId,
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccessBestSideDishes,
        errCallback: alertError
    });
}

function onSuccessBestSideDishes(response) {
    response.json().then((result) => {
        SIDE_DISHES_CACHE[$(".now").dataset["categoryId"]] = result;
        updateSideDishes(result);
    });
}

function updateSideDishes(result) {
    const html = getSideDishesTemplate(result);
    $("#best-side-dish-box").innerHTML = '';
    $("#best-side-dish-box").insertAdjacentHTML("beforeend", html);
}

function getSideDishesTemplate(sideDishes) {
    return sideDishes.reduce((prev, next) => {
        return prev + `<li>
                  <a class="thumbnail-box" href="#">
                    <div class="thumbnail">
                      <img src="./img/img-best-dish.jpg" alt=[${next.brand.name}]${next.name} />
                      <div class="overlay">
                        <p class="txt">새벽배송</p>
                        <p class="txt">전국택배</p>
                      </div>
                      <div class="badge-wrapper">
                        <i class="bm-icon badge-best">베스트</i>
                      </div>
                    </div>

                    <dl class="content">
                      <dt class="title">[${next.brand.name}]${next.name}</dt>
                      <dd class="desc">${next.description}</dd>
                      <dd class="price-wrapper">
                        <span class="original-price">${next.price}</span>
                        <span class="final-price">
                          <span class="number">${next.salePrice}</span>
                          <span class="unit">원</span>
                        </span>
                      </dd>
                    </dl>
                  </a>
                </li>`;
    }, ``);
}

function alertError() {
    alert("요기요를 이용해주세요...죄송합니다.");
}

function generateRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}