document.addEventListener('DOMContentLoaded', () => {
    loadMainCatetories();
});

function loadMainCatetories() {
    new FetchManager({
        url: '/api/categories',
        method: 'GET'
    })
    .onSuccess(response => {
        response.data.forEach(mainCategory => {
            createMainCategoryElement(mainCategory);
        });
    })
    .onFailed(errors => {
        
    })
    .doFetch();
}

function createMainCategoryElement(mainCategory) {
    const mainCategoryComponent = `
        <li id="category-${mainCategory.id}" data-id="${mainCategory.id}">
            <a href="#">${mainCategory.title}</a>
            <ul class="sub-menu">
            </ul>
        </li>
    `;
    $('#gnb .inner-wrapper .menu').insertAdjacentHTML('beforeend', mainCategoryComponent);
    addEvent(`#category-${mainCategory.id}`, 'mouseover', loadSubCategories);
}

function loadSubCategories(evt) {
    let mainCategory = evt.target;

    if (mainCategory.getAttribute('data-id') === null) {
        mainCategory = evt.target.parentElement;
    }

    mainCategory.removeEventListener('mouseover', loadSubCategories);

    new FetchManager({
        url: `/api/categories/${mainCategory.getAttribute('data-id')}`,
        method: 'GET'
    })
    .onSuccess(response => {
        response.data.forEach(subCategory => {
            createSubCategoryElement(subCategory, mainCategory);
        })
    })
    .onFailed(errors => {
        mainCategory.addEventListener('mouseover', loadSubCategories);  
    })
    .doFetch();
}

function createSubCategoryElement(subCategory, mainCategory) {
    const subCategoryComponent = `
        <li>
            <a href="#">${subCategory.title}</a>
        </li>
    `;
    mainCategory.querySelector('.sub-menu').insertAdjacentHTML('beforeend', subCategoryComponent);
}