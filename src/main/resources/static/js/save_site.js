var site = {
    images: [],
};

setTimeout(function () {
    var container = $('.my-container');
    console.log(container);
    for(var i = 0; i < container.find('.my-content').length; i++){
        var elements = $('.my-content').eq(i).children('.my-element');
        if(elements.length == 0) {continue;}
        for(var j = 0; j < elements.length; j++){
            var currentElement = elements.eq(j);
            if(currentElement.find('.glyphicon-camera').length != 0){
                currentElement.find('img').each(function (index, image) {
                    site.images.push({url: image.src, position: i, site: $('#site-name').val()});
                })
            }
            console.log(currentElement);
        }


    }
}, 15000);
