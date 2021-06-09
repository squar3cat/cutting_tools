$(document).mouseup(function (e)
{
    var container = $("#sidebarMenuWrapper");

    if (!container.is(e.target) && container.has(e.target).length === 0)
        $("#openSidebarMenu").prop( "checked", false );
});