class Slider {
    constructor(sliderElement, categoryId, viewSize) {
        this.sliderElement = sliderElement;
        this.categoryId = categoryId;
        this.elementList = [];
        this.startIndex = 0;
        this.viewSize = viewSize;
        this.init();
    }

    init() {
        this.contentGroup = this.sliderElement.querySelector(".content-group");

        this.sliderElement.querySelector("a.btn.prev").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.direction = -1;
            this.setTemporaryTranslate();
        });
        this.sliderElement.querySelector("a.btn.next").addEventListener("click", (evt) => {
            evt.preventDefault();
            this.direction = +1;
            this.setTemporaryTranslate();
        });

        this.sliderElement.querySelector(".content-group").addEventListener("transitionend", (evt) => {
            if(!evt.target.classList.contains("on")) return false;
            this.updateStartIndex();
            if (this.direction == 1) {
                let nextText = this.getSliderElementHolder(this.getStartRange(1));

                this.removeFirstContentBox();

                this.sliderElement.querySelector(".content-box.on").classList.toggle("on");
                this.sliderElement.querySelector(".content-box:last-child").classList.toggle("on");

                this.contentGroup.innerHTML = this.contentGroup.innerHTML + nextText;

                this.setPermanentTranslate();
            } else {
                let prevText = this.getSliderElementHolder(this.getStartRange(-1));

                this.removeLastContentBox();

                this.sliderElement.querySelector(".content-box.on").classList.toggle("on");
                this.sliderElement.querySelector(".content-box:first-child").classList.toggle("on");

                this.contentGroup.innerHTML = prevText + this.contentGroup.innerHTML;

                this.setPermanentTranslate();
            }

        });

        getManager({
            url: "/api/categories/category/" + this.categoryId,
            method: "GET",
            headers: {"Content-type": "application/json"},
            callback : this.setProducts.bind(this)
        })
    }

    removeFirstContentBox() {
        this.sliderElement.querySelector(".content-box:first-child").remove();
    }

    removeLastContentBox() {
        this.sliderElement.querySelector(".content-box:last-child").remove();
    }

    setProducts(results) {
        this.products = results;
        const template = Handlebars.templates["precompile/slider_element_template"];
        for (const product of this.products) {
            this.elementList.push(template(product));
        }
        this.renderInitialState();
    }

    renderInitialState() {
        let htmlText = "";
        for (let i = this.startIndex; i < this.viewSize; i++) {
            htmlText += this.elementList[i];
        }
        this.sliderElement.querySelector(".content-group .content-box").innerHTML = htmlText;
        this.sliderElement.querySelector(".content-box").classList.toggle("on");

        let prevText = this.getSliderElementHolder(this.getStartRange(-1));

        let nextText =this.getSliderElementHolder(this.getStartRange(1));

        this.contentGroup.innerHTML = prevText + this.contentGroup.innerHTML + nextText;

    }

    setTemporaryTranslate() {
        let translateXValue = -100 + (this.direction * -100);
        let contentBoxes = this.sliderElement.querySelectorAll(".content-box");
        for(const contentBox of contentBoxes) {
            contentBox.style.transform = "translateX(" + translateXValue + "%)";
            contentBox.style.transition = "transform 0.2s ease";
        }
    }

    setPermanentTranslate() {
        const contentBoxes = this.sliderElement.querySelectorAll(".content-box");
        for(const contentBox of contentBoxes) {
            contentBox.style.transform = "translateX(-100%)";
            contentBox.style.transition = "transform 0s";
        }
    }

    updateStartIndex() {
        this.startIndex = (this.startIndex + this.direction * this.viewSize + this.products.length) % this.products.length;
        return this.startIndex;
    }

    getStartRange(direction) {
        let startIndex = (this.startIndex + direction * this.viewSize + this.products.length) % this.products.length;
        const ret = [];
        let count = 0;
        while(count < this.viewSize) {
            ret.push(startIndex);
            startIndex++;
            startIndex %= this.products.length;
            count++;
        }
        return ret;
    }

    getSliderElementHolder(startRange) {
        let contents = "";
        for (let i of startRange) {
            contents += this.elementList[i];
        }
        return `<ul class="content-box">` + contents + `</ul>`
    }


}