function customAjax(u,d) {
    return $.ajax({
                type: 'post',
                data: JSON.stringify(d),
                url: u,
                dataType : 'json',
                contentType : 'application/json',
    });
}