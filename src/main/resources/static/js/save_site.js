var site = {
    images: [],
    texts: [],
    videos: []
};

$('.grid-layout').on('click', function () {
    site.grid = this.value;
});

$('.save-site').on('click', function () {
    var siteName = $('#site-name').get(0);
    if(!siteName.checkValidity()){
        var errorDiv = $('.save-site-error').get(0);
        errorDiv.style.display = 'inline-block';
        errorDiv.innerHTML = '<p>Please, enter site name</p>';
        return;
    }
    // $.post('checkSiteName', {
    //     siteName: siteName.value,
    //     "[[${_csrf.parameterName}]]":"[[${_csrf.token}]]"
    // }, function (data) {
    //     console.log(data);
    // });
    // $.ajax({
    //     url: '/savesite',
    //     type: 'POST',
    //     data: {jsons: site},
    //     error: function () {
    //         alert('not working')
    //     },
    //     dataType: 'json',
    //     success: function () {
    //         alert('working');
    //     }
    // })
    site.siteName = siteName.value;
    site.theme = $('input[name = "theme"]:checked').val();
    site.allowRating = $('input[name = "allowRating"]').is(':checked');
    site.allowComments = $('input[name = "allowComments"]').is(':checked');
    if(!site.grid) {site.grid = [[12],[6,6],[12]]} // grid which is rendered by default
    console.log(site);
});

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
            if(currentElement.find('.markdown').length != 0){
                site.texts.push({text: currentElement.find('input').val(), position: i});
            }
            if(currentElement.find('iframe').length != 0){
                var iframe = currentElement.find('iframe');
                site.videos.push({src: iframe.attr('src'), height: iframe.attr('height'),
                    width: iframe.attr('width'), position: i});
            }
        }


    }
}, 10000);
