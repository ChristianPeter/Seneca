$(function() {
    $('.selectpicker').selectpicker();
});


/*
function uploadFiles(id) {
    var file = document.getElementById(id).files[0];

    var filename = escape(file.name);
    var filetype = escape(file.type);

    $.ajax({
        url: "/Seneca/webresources/api/document?filename=" + filename + "&type=" + filetype,
        type: "PUT",
        data: file,
        processData: false,
        contentType: "application/octet-stream"
    });
}
*/

function uploadFiles(id) {
    var files = document.getElementById(id).files;

    _uploadFiles(files, function() {
        console.log('done.');
    }, function() {
    });
}

function _uploadFiles(files, successCB, errorCB, currentIndex) {
    if (!files)
        return errorCB(); // if no files given
    if (!currentIndex)
        currentIndex = 0; // init the index if not given
    if (files.length === currentIndex)
        return successCB();

    var file = files[currentIndex];
    var filename = escape(file.name);
    var filetype = escape(file.type);

    $.ajax({
        url: "/Seneca/webresources/api/document?filename=" + filename + "&type=" + filetype,
        type: "PUT",
        data: file,
        processData: false,
        contentType: "application/octet-stream"
    }).success(function() {
        _uploadFiles(files, successCB, errorCB, currentIndex + 1);
    });


}