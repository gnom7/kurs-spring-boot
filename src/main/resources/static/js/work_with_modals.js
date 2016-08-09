var modal = $('.modal');
var modalInvoker;
var currentModal;

$(modal).on('show.bs.modal', function (e) {
    modalInvoker = $(e.relatedTarget.parentNode);
    currentModal = $(this);
    if(modalInvoker.children('iframe').length != 0){
        var iframe = modalInvoker.children('iframe');
        var sourceLink = iframe.attr('src');
        currentModal.find('#video-url').val(sourceLink);
        currentModal.find('#video-width').val(iframe.attr('width'));
        currentModal.find('#video-height').val(iframe.attr('height'));
        currentModal.find('#video-autoplay').prop('checked', sourceLink.indexOf('autoplay=') != -1);
        currentModal.find('#video-loop').prop('checked', sourceLink.indexOf('loop=') != -1);
    }
    if(modalInvoker.children('.markdown').length != 0){
        // populate modal for text
        var element = modalInvoker.find('input');
        currentModal.find('textarea').val(element.val());
    }
});

$(modal).on('hidden.bs.modal', function () {
    if($(this).attr('id') == 'site-opts-modal') {return;}
    $.each($(this).find('input'), function (index, item) {
        item.checked = false;
        item.value = '';
    });
    $.each($(this).find('textarea'), function (index, item) {
        item.value = '';
    })
});

function saveModalData() {
    setTimeout(function () {
        fncs[currentModal.attr('id')]();
    }, 100)
}

fncs = {'film': function() {
    var videoUrl = $('#video-url').val().replace('watch?v=', 'embed/');
    videoUrl += '?';
    var videoAutoplay = $('#video-autoplay').is(':checked');
    var videoLoop = $('#video-loop').is(':checked');
    var videoWidth = $('#video-width').val() || 300;
    var videoHeight = $('#video-height').val() || 200;

    if(videoAutoplay){
        videoUrl += '&autoplay=1'
    }
    if(videoLoop){
        videoUrl += '&loop=1'
    }

    if(modalInvoker.children('.my-tool').length != 0) {
        modalInvoker.children('.my-tool').remove();
        modalInvoker.prepend('<iframe width="' + videoWidth + '" height="' + videoHeight + '" src="' + videoUrl +
            '" frameborder="0" allowfullscreen="allowfullscreen"></iframe>');
    } else {
        var iframe = modalInvoker.children('iframe');
        iframe.prop('width', videoWidth);
        iframe.prop('height', videoHeight);
        iframe.prop('src', videoUrl)
    }
},

    'font': function () {
        var text = $('#text').val();

        function markdownToHtml(text) {
            var converter = new showdown.Converter();
            var element = converter.makeHtml(text);
            var wrapper = $('<div class="markdown"></div>');
            wrapper.append(element);
            wrapper.append($('<input type="hidden"/>').val(text));
            return wrapper;
        }

        if(modalInvoker.children('.my-tool').length != 0) {
            modalInvoker.children('.my-tool').remove();
            modalInvoker.prepend(markdownToHtml(text));
        } else {
            modalInvoker.children('.markdown').remove();
            modalInvoker.prepend(markdownToHtml(text));
        }
    },
    'camera': function () {

    }};

$(document).ready(function () {

    // config
    var layout =  [[12], [6, 6], [12]];

    var toolbar = $('.my-toolbar');

    $('.my-tool').draggable({
        revert: 'invalid',
        helper: 'clone',
        containment: 'body',
        cancel: false
    });

    createGrid(layout);

});

function createGrid(rows) {
    var container = $('.my-container');
    container.empty();
    rows.forEach(function (row) {
        var rowTemplate = $('<div class="row"></div>');
        row.forEach(function (col) {
            rowTemplate.append('<div class="my-content col-md-' + col + '"></div>');
        });
        container.append(rowTemplate);
    }) ;

    var editField = $('.my-content');

    editField.droppable({
        accept: '.my-tool',
        drop: function (event, ui) {
            addElement(ui.draggable, $(this));
        }
    });

    var shouldDelete = false;
    editField.sortable({
        cursor: 'move',
        connectWith: editField,
        containment: 'body',
        tolerance: 'pointer',
        over: function () {
            shouldDelete = false;
        },
        out: function () {
            shouldDelete = true;
        },
        beforeStop: function (event, ui) {
            if (shouldDelete == true) {
                ui.item.remove();
            }
        }
    });
}

function addElement(item, container) {
    var id = item.attr('id');
    container.append(createElement(id.substr(5)));
}

function createElement(text) {
    var wrapper = $('<div class="my-element"></div>');
    wrapper.append('<button type="button" class="my-tool btn btn-default btn-lg ' +
        'tn-lg"><span class="glyphicon glyphicon-' + text + '"></span></button>');
    if(text == 'camera'){
        paramsForWidget['thumbnails'] = '#uploadedImages' + widgets.length;
        widgets.push(cloudinary.createUploadWidget(paramsForWidget, callbackOfWidget));
        wrapper.append($('<input type="hidden" />').val(widgets.length - 1));
        wrapper.append('<button onclick="' +
            'widgets[$(this).siblings(\'input\').val()].open()" class="btn btn-primary btn-xs">' +
            '<span class="glyphicon glyphicon-cog"></span></button>');
        wrapper.prepend('<div id="uploadedImages' + (widgets.length - 1) + '"></div>');
    } else {
        wrapper.append('<button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#' +
            text + '"><span class="glyphicon glyphicon-cog"></span></button>');
    }
    wrapper.append('<button onclick="parentNode.remove()" class="btn btn-danger btn-xs">' +
        '<span class="glyphicon glyphicon-remove"></span></button>');

    return wrapper;
}
