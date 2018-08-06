document.addEventListener('DOMContentLoaded', () =>{
   //templateInit();

   templateInit2();


}, false);

const templateInit = () => {
    const randomIdx = Math.floor(Math.random()*6);
    bestCategory(randomIdx);

}
const templateInit2 = async () => {
    //call bestCategory

    const bestCategoryData = await fetchAsync({
                      url:  '/api/banchan/best',
                      method: 'GET',
                      headers: { 'content-type': 'application/json'},
                  });
    categoryInit(bestCategoryData);

    const randomIdx = Math.floor(Math.random()*6);

    const bestBanchanData = await fetchAsync({
                url:  '/api/banchan/best/' + (randomIdx + 1),
                method: 'GET',
                headers: { 'content-type': 'application/json'},
                });

     banchanInit(bestBanchanData, randomIdx);

     $('.tab-btn-box').children[randomIdx].classList.add('on');
     $('.tab-content-group-box').children[randomIdx].classList.add('on');

}


const banchanInit = (data, id) => {
    const innerList = data.reduce((accum, cur) => {
        return accum + banchanBoxHTML(cur);
    }, '');

    $All('.tab-content-box')[id].insertAdjacentHTML('beforeend', innerList);
}
/*
const bestBanchan = (id) => {
    fetchManager({
            url:  '/api/banchan/best/' + id,
            method: 'GET',
            headers: { 'content-type': 'application/json'},
            callback: (data) => banchanInit(data, id),

        });
}
*/
/*
const bestCategory = (onIndex) => {
    fetchManager({
        url:  '/api/banchan/best',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: (data) => {
            categoryInit(data);
            $('.tab-btn-box').children[onIndex].classList.add('on');
            bestBanchan( onIndex + 1);
            $('.tab-btn-box').children[onIndex].classList.add('on');
        }
    });
}
*/

const insertHtml = ( {id, title} ) => `<li>
    <a data-category-id="${id}" href="#">${title}</a>
    </li>`;

const categoryInit = (data) => {
    const innerList = data.reduce( (accum, cur) => {
        return accum + insertHtml(cur);
    }, '');
    $('.tab-btn-box').insertAdjacentHTML('beforeend', innerList);

    const boxList = data.reduce( (accum) => {
                return accum + banchanOuterHtml;
        }, '');
    $('.tab-content-group-box').insertAdjacentHTML('beforeend', boxList);
}
const banchanOuterHtml =`<li> <ul class="tab-content-box"></ul></li>`;
const banchanBoxHTML = ( {description, title, imgUrl, originalPrice, realPrice} ) => `<li>
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
                                             <span class="original-price">${originalPrice}</span>
                                             <span class="final-price">
                                               <span class="number">${realPrice}</span>
                                               <span class="unit">원</span>
                                             </span>
                                         </dd>
                                     </dl>
                                 </a>
                             </li>`;

//const moveBanchanBox = (id) => {
//    $('.tab-content-group-box')
//}