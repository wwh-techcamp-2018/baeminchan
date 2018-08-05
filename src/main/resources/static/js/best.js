document.addEventListener('DOMContentLoaded', () =>{
    templateInit();
    //click event listener -- 베스트만찬 로드 및 출력 완료후
    //콟백 - remove on class, cached/ loaded? ajax, add 'on' class
    //1. cached[] = [0] //변수로 저장 유지 --
    //2. ul data-loaded="loaded" -- html node 속성으로 관리 --
    //3. childElementCount -
    //+ loaded 여부를 알수있게?
}, false);

const templateInit = async () => {
    //call bestCategory
    const randomIdx = Math.floor(Math.random()*6);
    await bestCategory();
    //call bestBanchan
    await bestBanchan(randomIdx + 1);
    $('.tab-btn-box').children[randomIdx].classList.add('on');
    $('.tab-content-group-box').children[id-1].classList.add('on');
}

const categoryInit = (data) => {
    const innerList = data.reduce( (accum, cur) => {
        return accum + insertHtml(cur);
    }, '');
    const boxList = data.reduce( (accum) => {
        return accum + banchanOuterHtml;
    }, '');

    $('.tab-btn-box').insertAdjacentHTML('beforeend', innerList);
    $('.tab-content-group-box').insertAdjacentHTML('beforeend', boxList);
}

const banchanInit = (data, id) => {
    const innerList = data.reduce((accum, cur) => {
        return accum + banchanBoxHTML(cur);
    }, '');

    $All('.tab-content-box')[id-1].insertAdjacentHTML('beforeend', innerList);
}

const bestBanchan = (id) => new Promise(resolve => {
    fetchManager({
        url:  '/api/banchan/best/' + id,
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: data => {
            resolve(banchanInit(data, id));
        },

    });
});

const bestCategory = () => new Promise(resolve => {
    fetchManager({
        url:  '/api/banchan/best',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: data => {
            resolve(categoryInit(data));
        },
    });
});


const banchanOuterHtml =`<li> <ul class="tab-content-box"></ul></li>`;
const banchanBoxHTML = ( {description, title, imgUrl, originalPrice, realPrice}) => {
    const priceHTML = (originalPrice !== realPrice)? `<span class="original-price" >${originalPrice}</span>`:"";
    return `<li>
                                 <a class="thumbnail-box" href="#">
                                     <div class="thumbnail">
                                         <img src="${imgUrl}" alt="${title}" />
                                         <div class="badge-wrapper">
                                         </div>
                                     </div>

                                     <dl class="content">
                                         <dt class="title">${title}</dt>
                                         <dd class="desc">${description}</dd>
                                         <dd class="price-wrapper">
                                             ${priceHTML}
                                             <span class="final-price">
                                               <span class="number">${realPrice}</span>
                                               <span class="unit">원</span>
                                             </span>
                                         </dd>
                                     </dl>
                                 </a>
                             </li>`;
    // todo 뱃지 나중에 추가해야함.
}

const insertHtml = ( {id, title} ) => `<li>
    <a data-category-id="${id}" href="#">${title}</a>
    </li>`;