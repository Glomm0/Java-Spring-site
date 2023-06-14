$(document).ready(function () {
    console.log(root);
    $.getJSON(root+"books/json/findall",function (data) {
        let tbl = $("#tablebody");
        $.each(data,function (i,item) {
            let tr = $("<tr>");
            let td = $("<td>");
            td.append($("<a>",{
                'href':root+"books/"+item.id,
                'text':item.title
            }));
            tr.append(td);
            tr.append($("<td>",{'text':item.authors}));
            tr.append($("<td>",{'text':item.year}));
            tbl.append(tr);
        })
    })
});

