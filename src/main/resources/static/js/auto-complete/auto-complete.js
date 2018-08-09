class AutoComplete {
    constructor(templateManager) {
        this.templateManager = templateManager;
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

    handleKeyUp(keyCode) {
        const searchingTextEl = this.templateManager.getSearchTextElement();
        const autoCompleteEl = this.templateManager.getAutoCompleteParent();
        let activeElement = $('.auto-complete > .focus');

        if(keyCode === 38) { // up
            this.keyUpMoveAutoComplete(autoCompleteEl, activeElement);
        }
        else if(keyCode === 40) { // down
            this.keyDownMoveAutoComplete(autoCompleteEl, activeElement);
        }
        if(keyCode === 13) { // enter
            if(activeElement) {
                searchingTextEl.value = activeElement.textContent;

                fetchManager({
                     url: '/api/products?searchText=' + searchingTextEl.value,
                     method: 'GET',
                     headers: {'content-type': 'application/json'},
                     onSuccess: (autoCompleteTexts) => {
                         this.templateManager.appendAutoCompletes(autoCompleteTexts);
                     },
                     onFailure: () => {console.log("error")}
                 });
            }
        }
    }
}