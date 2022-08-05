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
        $.ajax().done().fail(); //ajax통신을 이용해서 3개의 파라 데이터를 json으로 변경후 insert 요청
        
    }
}

index.init();