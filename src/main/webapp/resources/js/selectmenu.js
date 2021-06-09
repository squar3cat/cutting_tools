jQuery(document).ready(function () {
    $('select[name="toolType"] option, select[name="type"] option, select[name="parentId"] option').each(function () {
        var text = String($(this).text());
        var count = Number(text.match(/\d+/));

        text = text.replace(/\d+/, '- '.repeat(count));

        $(this).text(text);
    });


});