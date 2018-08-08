const UP    = 38;
const DOWN  = 40;
const ENTER = 13;
const CLASS_INVISIBLE = "invisible";

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
                errCallback: this.alertSearchError
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

    alertSearchError() {
        alert("search Error!");
    }
}

export { AutoSearching }