class SlideProduct {
    constructor(section) {
        this.products = [];
        this.startIndex = -1;
        this.template = Handlebars.templates["precompile/slide_product_template"];
        this.slideSection = $(`#${section}`);
        this.left = null;
        this.middle = null;
        this.right = null;

        this.TOTAL_PRODUCTS = 12;
        this.MOVE_LEFT = -100;
        this.MOVE_LEFT_TO_RIGHT = 200;
        this.MOVE_RIGHT = 100;
        this.MOVE_RIGHT_TO_LEFT = -200;
        this.SIDE_DISHES = 1;
        this.MAIN_DISHES = 3;

        this.init();
    }

    requestProducts(categoryid) {
        getManager({
            url: `/categories/${categoryid}/products`,
            callback: this.handleProducts.bind(this)
        })
    }

    requestSideDishes() {
        this.requestProducts(this.SIDE_DISHES);
    }

    requestMainDishes() {
        this.requestProducts(this.MAIN_DISHES);
    }

    handleProducts(products) {
        this.products = products;
        this.drawProducts();
    }

    drawProducts() {
        [this.left, this.middle, this.right].forEach((box) => {
            box.drawProductsInBox(this.template, this.products, this.prevBtnClickIndexStrategy.bind(this))
        });

    }

    init() {
        const contents = this.slideSection.querySelectorAll(".content-box");
        this.left = new ContentBox(contents[0]);
        this.middle = new ContentBox(contents[1]);
        this.right = new ContentBox(contents[2]);

        this.slideSection.querySelector(".btn.prev").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.handlePrevBtnClick()
        });

        this.slideSection.querySelector(".btn.next").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.handleNextBtnClick();
        });

    }

    handlePrevBtnClick() {
        this.left.moveContentBox(this.MOVE_LEFT_TO_RIGHT, false);
        this.middle.moveContentBox(this.MOVE_LEFT, true);
        this.right.moveContentBox(this.MOVE_LEFT, true);

        const left = this.left;
        this.left = this.middle;
        this.middle = this.right;
        this.right = left;

        this.right.drawProductsInBox(this.template, this.products, this.prevBtnClickIndexStrategy.bind(this))
    }

    handleNextBtnClick() {

        this.left.moveContentBox(this.MOVE_RIGHT, true);
        this.middle.moveContentBox(this.MOVE_RIGHT, true);
        this.right.moveContentBox(this.MOVE_RIGHT_TO_LEFT, false);

        const right = this.right;
        this.right = this.middle;
        this.middle = this.left;
        this.left = right;

        this.left.drawProductsInBox(this.template, this.products, this.nextBtnClickIndexStrategy.bind(this))
    }

    prevBtnClickIndexStrategy() {
        this.startIndex = (this.startIndex + 1) % this.products.length;
        return this.startIndex;
    }

    nextBtnClickIndexStrategy() {
        this.startIndex = (this.startIndex + this.products.length - 1) % this.products.length;
        // this.products.length * 2 는 index 값이 음수가 되지 않게 하기 위한 조치이다.
        return (this.startIndex - this.TOTAL_PRODUCTS + 1 + (this.products.length * 2)) % this.products.length;
    }


}