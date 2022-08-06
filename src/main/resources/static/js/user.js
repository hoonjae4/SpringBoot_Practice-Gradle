
let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },
    save: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        $.ajax({
            type : "POST",
            url : "/auth/joinProc",
            //user라는 table에 data를 넣을것이기에 api/user까지만 적자.
            data : JSON.stringify(data), //데이터를 json으로 변경
            contentType: "application/json; charset=utf-8",
            dataType : "json"
        }).done(function(resp){
            alert("회원가입이 완료 되었습니다.")
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
        
    }
}

index.init();