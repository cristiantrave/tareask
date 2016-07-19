function getConversation(idConversation) {

    $("#conversationlite" + idConversation).toggle("fast");
    $.ajax({
        url: 'http://localhost:8080/tareask/getconversation',
        data: "idConversation=" + idConversation,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            $('#dinamicreply').html(
                    '<div id="replymail" class="modal fade" role="dialog">\
                        <div class="modal-dialog" >\
                        <!-- Modal content-->\
                        <div class="modal-content" >\
                        <div class="modal-header">\
                        <button type="button" class="close" data-dismiss="modal">&times;</button>\
                        <h4 class="modal-title">Modal Header</h4>\
                        </div>\
                        <div class="modal-body">\
                        <form method="POST" action="addemail.html">\
                        <input value="' + idConversation + '" name="conversation" type="hidden" required="required"/>\
                        <div class="form-group">\
                        <label class="col-lg-2 control-label">Subject</label>\
                        <div class="col-lg-10">\
                        <input placeholder="" name="subject" class="form-control" type="text" required="required"/>\
                        </div>\
                        </div>\
                        <div class="form-group">\
                        <label class="col-lg-2 control-label">Message</label>\
                        <div class="col-lg-10">\
                        <textarea rows="10" cols="30" class="form-control" id="" name="content" required="required"> </textarea>\
                        </div>\
                        </div>\
                        <div class="form-group" >\
                        <div class="col-lg-offset-2 col-lg-10">\
                        <button class="btn btn-send" type="submit">Send</button>\
                        </div>\
                        </div>\
                        </form>\
                        </div>\
                        <div class="modal-footer">\
                        <button type="button" class="btn btn-default" data-dismiss="modal"> Close </button>\
                        </div>\
                        </div>\
                        </div>\
                        </div>\
                        </div>'

                    );

            $('#viewconversation' + idConversation).append('\
            <div class=row>\
                <div class=col-xs-12>\
                    <a style="color:inherit;text-decoration: none;display:inline" href="javascript:;" onclick="closeConversation(' + idConversation + ');">\
                        <span class="label label-primary" style="font-size: 14px;float: left;margin: 14px;margin-right: -8px"><span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span><span class="hidden-xs"> Hide</span></span>\
                    </a>\
                    <a href="#" style="color:inherit;text-decoration: none;display:inline" data-toggle="modal" data-target="#replymail">\
                        <span class="label label-success" style="font-size: 14px;float: left;margin: 14px;margin-right: -8px"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><span class="hidden-xs"> Reply</span></span>\
                    </a>\
                </div>\
            </div>\
            ');

            $.each(data, function (index, obj)
            {
                $('#viewconversation' + idConversation).append(
                        '<div style="margin:20px;padding:10px" class="list-group-item">\
                <span style="font-weight: bold;color: lightslategrey">Remitente: </span><span>' + obj.name + '</span><br/>\
                <span style="font-weight: bold;color: lightslategrey">Destinatarios: </span><span>' + obj.receptors + '</span><br/>\
                <span style="font-weight: bold;color: lightslategrey">Recibido: </span><span>' + obj.date + '</span><br/>\
                <hr style="margin-top:8px;margin-bottom: 8px"/>\
                <div>\
                ' + obj.content + '\
                </div>\
                </div>'
                        );
            });
        },
        error: function (data) {
            alert("Ups, Problem!");
        }
    });
    $("#viewconversation" + idConversation).toggle("fast");
}

function closeConversation(idConversation) {
    $('#viewconversation' + idConversation).html("");
    $('#dinamicreply').html("");
    $("#conversationlite" + idConversation).toggle("fast");
    $("#viewconversation" + idConversation).toggle("fast");
}

function addReceptor(receptor, id) {

    $('#receptors').val($('#receptors').val() + receptor + ',');
    $('#' + id).html("");
}


$(document).ready(function () {
    $('#emailSent').modal('show');
    $('#editProfile').modal('show');
});



