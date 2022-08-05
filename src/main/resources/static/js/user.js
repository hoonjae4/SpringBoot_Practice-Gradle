
let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },
    save: function() {
        //alert('user의 save함수');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        //console.log(data);
        $.ajax({
            type : "POST",
            url : "/blog/api/user",
            //user라는 table에 data를 넣을것이기에 api/user까지만 적자.
            data : JSON.stringify(data), //데이터를 json으로 변경
            contentType: "application/json; charset=utf-8",
            dataType : "json", // 요청에 대한 응답이 왔을때의 데이터가 string인데 이걸 javascript objcet로 저장
            //회원가입 수행 요청
        }).done(function(resp){
            //성공시 done
            alert("회원가입이 완료 되었습니다.")
            location.href = "/blog";
        }).fail(function(error){
            //실패시 fail
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 3개의 파라 데이터를 json으로 변경후 insert 요청
        
    }
}

index.init();