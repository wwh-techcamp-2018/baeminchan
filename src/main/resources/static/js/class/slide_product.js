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
        let template = Handlebars.templates["precompile/slide_product_template"];

        [this.left, this.middle, this.right].forEach((box) => {
            let productsHtml = "";
            const products = this.products.slice(start, start + this.BOX_CONTENTS_SIZE);

            for (const product of products) {
                productsHtml += template(product);
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

        console.log(this.left, this.middle, this.right);

        $("#side-dish").querySelector(".btn.prev").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.handlePrevBtnClick()
        });

        $("#side-dish").querySelector(".btn.next").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.handleNextBtnClick();
        });
    }

    handlePrevBtnClick() {
        this.leftTranslate += 200;
        this.middleTranslate -= 100;
        this.rightTranslate -= 100;
        this.translateSlide(this.left, this.leftTranslate);
        this.translateSlide(this.middle, this.middleTranslate);
        this.translateSlide(this.right, this.rightTranslate);

        const left = this.left;
        this.left = this.middle;
        this.middle = this.right;
        this.right = left;

        const leftTranslate = this.leftTranslate;
        this.leftTranslate = this.middleTranslate;
        this.middleTranslate = this.rightTranslate;
        this.rightTranslate = leftTranslate;
    }

    handleNextBtnClick() {
        this.leftTranslate += 100;
        this.middleTranslate += 100;
        this.rightTranslate -= 200;
        this.translateSlide(this.left, this.leftTranslate);
        this.translateSlide(this.middle, this.middleTranslate);
        this.translateSlide(this.right, this.rightTranslate);

        const right = this.right;
        this.right = this.middle;
        this.middle = this.left;
        this.left = right;

        const rightTranslate = this.rightTranslate;
        this.rightTranslate = this.middleTranslate;
        this.middleTranslate = this.leftTranslate;
        this.leftTranslate = rightTranslate;
    }

    translateSlide(slide, translate) {
        slide.style.transform = this.buildTranslate(translate);
    }

    buildTranslate(translateValue) {
        return "translateX(" + translateValue + "%)";
    }
}