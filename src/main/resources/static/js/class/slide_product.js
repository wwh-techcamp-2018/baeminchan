class SlideProduct {
    constructor() {
        this.products = [];

        this.startIndex = 0;
        this.BOX_CONTENTS_SIZE = 4;
        this.BOX_COUNT = 3;

        this.left = null;
        this.middle = null;
        this.right = null;

        this.leftTranslate = -100;
        this.middleTranslate = -100;
        this.rightTranslate = -100;

        this.productTemplate = null;

        this.init();
    }

    requestProducts() {
        getManager({
            url: "/categories/1/products",
            callback: this.handleProducts.bind(this)
        })
    }

    handleProducts(products) {
        this.products = products;
        this.drawProducts();
    }

    drawProducts() {
        let start = this.startIndex;
        [this.left, this.middle, this.right].forEach((box) => {
            let productsHtml = "";
            const products = this.products.slice(start, start + this.BOX_CONTENTS_SIZE);

            for (const product of products) {
                productsHtml += this.productTemplate(product);
                start = (start + 1) % this.products.length;
            }
            box.innerHTML += productsHtml;
        });

        this.left.style.transform = this.buildTranslate(this.leftTranslate);
        this.middle.style.transform = this.buildTranslate(this.middleTranslate);
        this.right.style.transform = this.buildTranslate(this.rightTranslate);
    }

    init() {
        const contents = $_all(".content-box.side-dish-box");
        this.left = contents[0];
        this.middle = contents[1];
        this.right = contents[2];

        this.productTemplate = Handlebars.templates["precompile/slide_product_template"];

        const sideDishSection = $("#side-dish");
        sideDishSection.querySelector(".btn.prev").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.handlePrevBtnClick()
        });

        sideDishSection.querySelector(".btn.next").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.handleNextBtnClick();
        });
    }

    rotateNext() {
        this.leftTranslate += 200;
        this.middleTranslate -= 100;
        this.rightTranslate -= 100;
        this.translateSlideWithoutAnimation(this.left, this.leftTranslate);
        this.translateSlideWithAnimation(this.middle, this.middleTranslate);
        this.translateSlideWithAnimation(this.right, this.rightTranslate);

        const left = this.left;
        this.left = this.middle;
        this.middle = this.right;
        this.right = left;

        const leftTranslate = this.leftTranslate;
        this.leftTranslate = this.middleTranslate;
        this.middleTranslate = this.rightTranslate;
        this.rightTranslate = leftTranslate;
    }

    refreshNextItems() {
        let productsHtml = "";
        const totalBoxSize = this.BOX_CONTENTS_SIZE * this.BOX_COUNT;
        let start = this.startIndex + totalBoxSize;
        range(start, start + this.BOX_CONTENTS_SIZE).forEach((rawIndex) => {
            productsHtml += this.productTemplate(this.products[rawIndex % this.products.length]);
        });
        this.right.innerHTML = productsHtml;
        this.startIndex = (this.startIndex + this.BOX_CONTENTS_SIZE) % this.products.length;
    }

    rotatePrev() {
        this.leftTranslate += 100;
        this.middleTranslate += 100;
        this.rightTranslate -= 200;
        this.translateSlideWithAnimation(this.left, this.leftTranslate);
        this.translateSlideWithAnimation(this.middle, this.middleTranslate);
        this.translateSlideWithoutAnimation(this.right, this.rightTranslate);

        const right = this.right;
        this.right = this.middle;
        this.middle = this.left;
        this.left = right;

        const rightTranslate = this.rightTranslate;
        this.rightTranslate = this.middleTranslate;
        this.middleTranslate = this.leftTranslate;
        this.leftTranslate = rightTranslate;
    }

    refreshPrevItems() {
        let productsHtml = "";
        let start = (this.startIndex + this.products.length - this.BOX_CONTENTS_SIZE) % this.products.length;
        range(start, start + this.BOX_CONTENTS_SIZE).forEach((rawIndex) => {
            productsHtml += this.productTemplate(this.products[rawIndex % this.products.length]);
        });
        this.left.innerHTML = productsHtml;
        this.startIndex = (this.startIndex + this.products.length - this.BOX_CONTENTS_SIZE) % this.products.length;
    }

    handleNextBtnClick() {
        this.rotateNext();
        this.refreshNextItems();
    }

    handlePrevBtnClick() {
        this.rotatePrev();
        this.refreshPrevItems();
    }

    translateSlideWithAnimation(slide, translate) {
        slide.style.transition = "transform 0.5s";
        this.translateSlide(slide, translate);
    }

    translateSlideWithoutAnimation(slide, translate) {
        slide.style.transition = "";
        this.translateSlide(slide, translate);
    }

    translateSlide(slide, translate) {
        slide.style.transform = `translateX(${translate}%)`;
    }
}