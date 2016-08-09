var widgets = [];
var paramsForWidget = { cloud_name: 'gnom-7-cloud', upload_preset: 'sample_7eb3c60c0a',
    thumbnails: '#uploadedImages', theme: 'white'};
var callbackOfWidget = function(error, result) {
    if (!result) return;
    $('img').each(function(){
        var img = $(this);
        var src = img.attr('src').replace('/c_limit,h_60,w_90', '');
        if(src == result[0].url){
            img.closest('li').append($('<button onclick="$(this).closest(\'ul\')' +
                '.remove()" class="btn btn-danger btn-xs">&times;</button>'));
        }
        img.prop('src', img.attr('src').replace('/c_limit,h_60,w_90', '/c_limit,h_200,w_300'));
    });
};
