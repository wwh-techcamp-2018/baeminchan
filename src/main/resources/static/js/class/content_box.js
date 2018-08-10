class ContentBox {
    constructor(node) {
        this.BOX_CONTENTS_SIZE = 4;
        this.node = node;
        this.translate = -100;
        this.moveContentBox(0);
    }

    moveContentBox(translate, animation) {
        this.translate += translate;
        if (animation)
            this.node.style.transition = "transform 0.7s";
        else
            this.node.style.transition = "";
        this.node.style.transform = `translateX(${this.translate}%)`;
    }

    drawProductsInBox(template, products, indexStrategy) {
        let productsHtml = "";
        for (let i = 0; i < this.BOX_CONTENTS_SIZE; i++) {
            productsHtml += template(products[indexStrategy()]);
        }
        this.node.innerHTML = productsHtml;
    }
}