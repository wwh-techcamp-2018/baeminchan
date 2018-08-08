

class EventManager {
    constructor(templateManager) {
        this.templateManager = templateManager;
        templateManager.getSearchTextElement().addEventListener('focusout', this.autoCompleteFocusOutHandler.bind(this));
        templateManager.getSearchTextElement().addEventListener('keyup', this.autoCompleteKeyDownHandler.bind(this));
    }

    autoCompleteFocusOutHandler(evt) {
        evt.preventDefault();
        const autoCompleteEl = this.templateManager.getAutoCompleteParent();
        autoCompleteEl.style.display = "none";
    }

    keyUpMoveAutoComplete(autoCompleteEl, activeElement) {
        if(!activeElement) {
           activeElement = autoCompleteEl.lastElementChild;
           activeElement.classList.toggle('focus');
           return;
        }
        activeElement.classList.toggle('focus');
        const previousElement = activeElement.previousElementSibling;
        if(previousElement) {
            previousElement.classList.toggle('focus');
            return;
        }
        autoCompleteEl.lastElementChild.classList.toggle('focus');
    }
    
    keyDownMoveAutoComplete(autoCompleteEl, activeElement) {
        if(!activeElement) {
           activeElement = autoCompleteEl.firstElementChild;
           activeElement.classList.toggle('focus');
           return;
        }
        activeElement.classList.toggle('focus');
        const nextElement = activeElement.nextElementSibling;
        if(nextElement) {
            nextElement.classList.toggle('focus');
            return;
        }
        autoCompleteEl.firstElementChild.classList.toggle('focus');
    }
    
    autoCompleteKeyDownHandler(evt) {
        const searchingTextEl = this.templateManager.getSearchTextElement();
        const autoCompleteEl = this.templateManager.getAutoCompleteParent();
        let activeElement = $('.auto-complete > .focus');
        if(document.activeElement === searchingTextEl && autoCompleteEl.style.display === 'block'){
            if(evt.keyCode == 38) { // up
                this.keyUpMoveAutoComplete(autoCompleteEl, activeElement);
            }
            else if(evt.keyCode == 40) { // down
                this.keyDownMoveAutoComplete(autoCompleteEl, activeElement);
            } else {
                fetchManager({
                     url: '/api/products?searchText=' + this.templateManager.getSearchTextElement().value,
                     method: 'GET',
                     headers: {'content-type': 'application/json'},
                     onSuccess: (autoCompleteTexts) => {
                        console.log(autoCompleteTexts);
                         this.templateManager.appendAutoCompletes(autoCompleteTexts);
                     },
                     onFailure: () => {}
                 });
            }
            if(evt.keyCode == 13) { // enter
                if(activeElement) {
                    searchingTextEl.value = activeElement.innerHTML;
                }
            }
        }
//
//        if((evt.keyCode >= 48 && evt.keyCode <= 57) || (evt.keyCode >= 65 && evt.keyCode <= 90)) {
//            autoCompleteEl.style.display = "block";
//        }
        autoCompleteEl.style.display = "block";
    }

}

class AutoComplete {
    constructor() {
        this.index = 0;
        this.length = 0;
    }

    keyUp() {
        if(--index < 0) {
            index = length-1;
        }
    }

    keyDown() {
        if(++index === length) {
            index = 0;
        }
    }

}

class TemplateManager {
    constructor() {
    }

    getAutoCompleteParent() {
        return $('.auto-complete');
    }
    getSearchTextElement() {
        return $('#searching_text');
    }

    clearAutoComplete() {
//        const parent = this.getAutoCompleteParent();
//        if(parent.children.length !== 0) {
//            parent.children.forEach((el) => {
//                console.log(parent.removeChild(el));
//            });
//        }

        const children = $All('.auto-complete > li');
        children.forEach((el) => {
            el.parentElement.removeChild(el);
        });
    }

    appendAutoCompletes(autoCompleteTexts) {
        this.clearAutoComplete();
        const autoCompleteEl = this.getAutoCompleteParent();
        autoCompleteTexts.forEach((text) => {
            autoCompleteEl.insertAdjacentHTML('beforeend', `<li>${text.name}</li>`)
        });
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const templateManager = new TemplateManager();
    const eventManager = new EventManager(templateManager);

    console.log(eventManager);

});