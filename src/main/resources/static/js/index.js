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

window.addEventListener('load', callMenuApi);

document.addEventListener("DOMContentLoaded", function(event) {
  $('.dot-btn-box').firstElementChild.classList.add('on');
  $('.img-box').firstElementChild.classList.add('current');
  $('.spr-btn-arrow-main-slide.prev').addEventListener('click', arrowMove);
  $('.spr-btn-arrow-main-slide.next').addEventListener('click', arrowMove);
  $('.dot-btn-box').addEventListener('click', dotMove);
});


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