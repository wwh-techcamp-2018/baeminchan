function setPromotion(index) {
  const current = $(".img-box .current");
  current.classList.remove("current");
  current.parentElement.children[index].classList.add("current");
}

function synchronizeDotAndPromotion(dot) {
  const dotIndex = [...dot.parentElement.children].indexOf(dot);
  setPromotion(dotIndex);
}

function promotionArrowBtnHandler(evt) {
  evt.preventDefault();
  let onDot = $(".dot-btn-box .dot.on");
  onDot.classList.remove("on");
  onDot = evt.target.classList.contains("next")
    ? getNextElement(onDot)
    : getPrevElement(onDot);
  onDot.classList.add("on");
  synchronizeDotAndPromotion(onDot);
}

function addPromotionNextBtn() {
  const btn = $(".direction-btn-box .next");
  if (btn === null) return;
  btn.addEventListener("click", promotionArrowBtnHandler);
}

function addPromotionPrevBtn() {
  const btn = $(".direction-btn-box .prev");
  if (btn === null) return;
  btn.addEventListener("click", promotionArrowBtnHandler);
}

function dotBtnHandler(evt) {
  evt.preventDefault();
  if (evt.target.classList.contains("dot-btn-box")) return;
  let onDot = $(".dot-btn-box .dot.on");
  if (onDot !== null) onDot.classList.remove("on");
  onDot = evt.target;
  onDot.classList.add("on");
  synchronizeDotAndPromotion(onDot);
}

function addDotBtn() {
  const btn = $("#main-visual .dot-btn-box");
  if (btn === null) return;
  btn.addEventListener("click", dotBtnHandler);
}

function addArrowBtn() {
  addPromotionNextBtn();
  addPromotionPrevBtn();
}

function addPromotionEventHandlers() {
  addArrowBtn();
  addDotBtn();
}
