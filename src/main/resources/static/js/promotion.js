document.write("<script src='/js/common.js'></script>");

window.addEventListener("DOMContentLoaded", () => {
  initEventsList(all(".bm-icon.spr-btn-arrow-main-slide"), handler);
  initEventsList(all(".dot"), changePicByDot);
  $(".img-item.img-1").classList.toggle("current");
});

function handler(evt) {
    let current = $(".img-item.current");
    current.classList.toggle("current");
    evt.target.classList.contains("next") ? changeCurrent(current.nextElementSibling,0) : changeCurrent(current.previousElementSibling,all(".img-item").length-1);
}

function changeCurrent(newCurrent,index){
    newCurrent !== null ? classNameToggle(newCurrent,index) : imageListToggle(index);        
}

function classNameToggle(newCurrent,index){
    newCurrent.classList.toggle("current");
    dotToggle(index);
}

function imageListToggle(index){
    offDot()
    all(".img-item")[index].classList.toggle("current");
    all(".dot")[index].classList.toggle("on");
}

function dotToggle(index){
    let tempDot = $('.dot.on');
    offDot()
    index === 0 ? tempDot.nextElementSibling.classList.toggle("on") : tempDot.previousElementSibling.classList.toggle("on");
}

function changePicByDot(evt){
    offDot()
    $(".img-item.current").classList.toggle("current");
    
    let dot = evt.target;
    dot.classList.toggle("on");
    all(".img-item")[dot.dataset.index].classList.toggle("current");
}

function offDot(){
    $('.dot.on').classList.toggle("on");
}