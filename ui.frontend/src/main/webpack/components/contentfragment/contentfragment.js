const showImages = () => {
    const imageElements = document.querySelectorAll("div.cmp-contentfragment__element--parkImage > dd");
    imageElements.forEach(element => {
        const url = element.innerText.trim();
        element.innerText = '';
        element.innerHTML = `<img src="${url}">`;
    });
};
showImages();
