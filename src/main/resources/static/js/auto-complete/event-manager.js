class EventManager {
    constructor(autoComplete, templateManager) {
        this.autoComplete = autoComplete;
        this.templateManager = templateManager;
        templateManager.getSearchTextElement().addEventListener('focus', this.autoCompleteFocusHandler.bind(this));
        templateManager.getSearchTextElement().addEventListener('focusout', this.autoCompleteFocusOutHandler.bind(this));
        templateManager.getSearchTextElement().addEventListener('keyup', this.autoCompleteKeyUpHandler.bind(this));
        templateManager.getSearchTextElement().addEventListener('keydown', (evt) => {
            if(evt.keyCode === 13) {
                evt.preventDefault();
            }
        });
        templateManager.getSearchTextElement().addEventListener('input', this.inputSearchTextHandler.bind(this));
    }

    autoCompleteFocusHandler(evt) {
        const autoCompleteEl = this.templateManager.getAutoCompleteParent();
        autoCompleteEl.style.display = "block";
    }

    autoCompleteFocusOutHandler(evt) {
        evt.preventDefault();
        const autoCompleteEl = this.templateManager.getAutoCompleteParent();
        autoCompleteEl.style.display = "none";
    }

    autoCompleteKeyUpHandler({keyCode}) {
        if (keyCode === 38 || keyCode === 40 || keyCode === 13) {
            this.autoComplete.handleKeyUp(keyCode);
        }
    }

    inputSearchTextHandler() {
        fetchManager({
             url: '/api/products?searchText=' + this.templateManager.getSearchTextElement().value,
             method: 'GET',
             headers: {'content-type': 'application/json'},
             onSuccess: (autoCompleteTexts) => {
                 this.templateManager.appendAutoCompletes(autoCompleteTexts);
             },
             onFailure: () => {console.log("error")}
         });
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const templateManager = new TemplateManager();
    const autoComplete = new AutoComplete(templateManager);
    const eventManager = new EventManager(autoComplete, templateManager);
});