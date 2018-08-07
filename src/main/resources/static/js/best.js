document.addEventListener('DOMContentLoaded', () =>{
    renderBestCategory();
    registClickEvent($('.tab-btn-box'), changeBestBanchan);
}, false);

const changeBestBanchan = () => {
        const targetLi = event.target.tagName !== 'LI'? event.target.closest('li') :  event.target ;
        const targetIndex = getIndex(targetLi);
        changeActiveBox('tab-btn-box', targetIndex);
        changeActiveBox('tab-content-group-box', targetIndex);

        if(!targetLi.getAttribute("data-load")){
            loadBestBanchan(targetIndex);
        }
}

const renderBestCategory = async () =>{
    await initBestCategory();
    const randomIndex = Math.floor(Math.random()* $('.tab-btn-box').childElementCount);
    await loadBestBanchan(randomIndex);

     $('.tab-btn-box').children[randomIndex].classList.add('on');
     $('.tab-content-group-box').children[randomIndex].classList.add('on');

}

const getBestBanchanData = (index) => {
    index = index || "";
    return fetchAsync({
        url:  '/api/banchan/best/' + index,
        method: 'GET',
        headers: { 'content-type': 'application/json'},
    });
}

const changeActiveBox = (target, index) => {
    $('.' + target + ' .on').classList.remove('on');
    $('.' + target).children[index].classList.add('on');
}

const initBestCategory = async ( ) =>{
    const bestCategoryData = await getBestBanchanData();
    initCategory(bestCategoryData);
};

const loadBestBanchan = async (index) => {
     const targetId = $('.tab-btn-box :nth-child('+ (index + 1) +') a').getAttribute('data-category-id')
     const bestBanchanData = await getBestBanchanData(targetId);

     initBanchan(bestBanchanData, index);
     $('.tab-btn-box').children[index].setAttribute("data-load", "loaded");
};

const initBanchan = (data, id) => {
    appendHtmlFromData(data, banchanBoxHTML, $All('.tab-content-box')[id]);
}

const initCategory = (data) => {
    appendHtmlFromData(data, categoryHtml, $('.tab-btn-box'));
    appendHtmlFromData(data, banchanOuterHtml, $('.tab-content-group-box'));
}

const appendHtmlFromData = (data, templateFun, parentElement) => {
    const html = data.reduce( (accum, cur) => {
                    return accum + templateFun(cur);
            }, '');
    parentElement.insertAdjacentHTML('beforeend', html);
}

const banchanOuterHtml = () => `<li> <ul class="tab-content-box"></ul></li>`;
const banchanBoxHTML = ( {description, title, imgUrl, originalPrice, realPrice}) => {
    const priceHTML = (originalPrice !== realPrice)? `<span class="original-price" >${ formatMoney(originalPrice) }</span>`:"";
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
                                               <span class="number">${ formatMoney(realPrice) }</span>
                                               <span class="unit">원</span>
                                             </span>
                                         </dd>
                                     </dl>
                                 </a>
                             </li>`;
                             // todo 뱃지 나중에 추가해야함.
}
const categoryHtml = ( {id, title} ) => `<li>
    <a data-category-id="${id}" href="#">${title}</a>
    </li>`;