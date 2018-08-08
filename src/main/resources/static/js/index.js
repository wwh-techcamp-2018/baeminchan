const callMenuApi = () => {
    fetchManager({
        url : 'api/menu',
        method : 'GET',
        callback : setMenu,
    });
}

const setMenu = (status, data) => {
    const root = initList('menu');
    root.setAttribute('id', 'category-menu');

    data.children.forEach(child => {
        const node = setElement(child, './side-dishs.html');
        if(child.childCount !== 0) {
            const childList = initList('sub-menu');
            child.children.forEach(child => {
                const node = setElement(child, '#');
                childList.appendChild(node);
            });
            node.appendChild(childList);
        }
        root.appendChild(node);
    });

    getElement($, idString, 'category-wrapper').appendChild(root);
    root.lastChild.className = 'brand';
};

const initList = (className) => {
    const node = document.createElement('ul');
    node.className = className;
    return node;
};


const setElement = (data, url) => {
    const node = document.createElement('li');
    const urlLink = document.createElement('a');
    urlLink.setAttribute('href', url);
    urlLink.innerText = data.name;
    node.appendChild(urlLink);
    return node;
}

const dotMove = (event) => {
  event.preventDefault();
  const sourceElement = event.target;
  if(sourceElement.tagName.toLowerCase() !== 'a') return;



  let index = sourceElement.getAttribute('data-index');
  const newCurrentImage = $('.img-box').children[index-1];
  move(newCurrentImage);
}

const arrowMove = (event) => {
 event.preventDefault();
  if(event.target.classList.contains('prev')){
    move( prev($('.current')) );
    return;
  }
    move( next($('.current')) );
}


const move = (newCurrentImage)=>{

  const originCurrentImage = $('.current');

  $('.dot-btn-box > .on').classList.remove('on');
  addClass('on', $('.dot-btn-box').children[newCurrentImage.getAttribute('data-index')-1]);

  removeClass('current', originCurrentImage);
  addClass('current', newCurrentImage);

  removeClass('next', next(originCurrentImage));
  addClass('next', next(newCurrentImage));

  removeClass('prev', prev(originCurrentImage));
  addClass('prev', prev(newCurrentImage));
}


let bestCategoryTasks;

const switchSelectElement = (target) => {
    const targetElement = $All('.tab-content-group-box > li')[target];
    const prevTargetElement = $('.tab-content-group-box .on');
    prevTargetElement.classList.toggle('on', false);
    targetElement.classList.toggle('on', true);
}

const selectBestCategory = (target) => {

    if(bestCategoryTasks[target] === true){
        switchSelectElement(target);
        return;
    }

    const targetElement = $('.tab-btn-box').children[target].firstElementChild;
    const bestMenuId = targetElement.getAttribute('data-category-id');

    fetchManager({
        url : `/api/eventMenu/${bestMenuId}/products`,
        method : 'GET',
        headers : ContentType.JSON,
        callback : fillBestProducts.bind(null, target)
    });
}

const initializeBestCategory = () => {
    const bestProductsContainer = `<li>
                                       <ul class="tab-content-box">

                                       </ul>
                                   </li>`
    const numOfTasks = $('.tab-btn-box').childElementCount;
    bestCategoryTasks = new Array(numOfTasks);
    for(task of bestCategoryTasks){
        task = false;
        $('.tab-box .tab-content-group-box').insertAdjacentHTML('beforeend',bestProductsContainer);
    }

    const target = getRandomInt(numOfTasks);
    $('.tab-box .tab-content-group-box').children[target].classList.toggle('on', true);
    selectBestCategory(target);
}

const listenToSelectBestCategory = (event) => {
    event.preventDefault();
    selectBestCategory(event.target.getAttribute('data-category-index')-1);
}

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

const fillBestProducts = (target, statusCode, data) => {
    const targetElement = $All('.tab-content-group-box > li')[target];

    data.forEach((product, index) => {
        const bestProductsLiterals = `<li>
                                <a class="thumbnail-box" href="#">
                                    <div class="thumbnail">
                                        <img src="${product.thumbnail}" alt="${product.title}"/>
                                        <div class="overlay">.tab-content-group-box'
                                            <p class="txt">새벽배송</p>
                                            <p class="txt">전국택배</p>
                                        </div>
                                        <div class="badge-wrapper">
                                            <i class="bm-icon badge-event">이벤트특가</i>
                                        </div>
                                    </div>

                                    <dl class="content">
                                        <dt class="title">${product.title}</dt>
                                        <dd class="desc">${product.description}</dd>
                                        <dd class="price-wrapper">
                                            <span class="original-price">${product.price}</span>
                                            <span class="final-price">
                          <span class="number">${product.price}</span>
                          <span class="unit">원</span>
                        </span>
                                        </dd>
                                    </dl>
                                </a>
                            </li>`;

        targetElement.firstElementChild.insertAdjacentHTML('beforeend', bestProductsLiterals);

    });
    switchSelectElement(target);
    bestCategoryTasks[target] = true;
}


window.addEventListener('load', callMenuApi);

document.addEventListener("DOMContentLoaded", function(event) {
  $('.dot-btn-box').firstElementChild.classList.add('on');
  $('.img-box').firstElementChild.classList.add('current');
  $('.spr-btn-arrow-main-slide.prev').addEventListener('click', arrowMove);
  $('.spr-btn-arrow-main-slide.next').addEventListener('click', arrowMove);
  $('.dot-btn-box').addEventListener('click', dotMove);
  initializeBestCategory();
});

$('.tab-box .tab-btn-box').addEventListener("click", listenToSelectBestCategory);