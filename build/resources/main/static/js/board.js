
let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });
        $("#btn-update").on("click",()=>{
            this.updateById();
        });
        $("#btn-reply-save").on("click",()=>{
            this.replySave();
        })
    },
    save: function() {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        $.ajax({
            type : "POST",
            url : "/board",
            data : JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            alert("글 작성 완료.")
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    },
    deleteById : function(){
        let id = $("#boardId").text();
        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            dataType: "json"
        }).done(function(resp){
            alert("삭제 완료");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        })
    },
    updateById: function() {
        let id = $("#updateId").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        console.log(id,data);
        $.ajax({
            type : "PUT",
            url : "/api/board/"+id,
            data : JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            alert("글 수정 완료.")
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    },
    replySave: function() {
        let data = {
            boardId:$("#reply-boardId").val(),
            userId: $("#reply-userId").val(),
            content: $("#reply-content").val()
        }
        $.ajax({
            type : "POST",
            url : `/api/board/${data.boardId}/reply`,
            data : JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            alert("댓글 작성 완료.")
            location.href = `/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    }
}

index.init();