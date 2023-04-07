function getAllCustomer() {
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/api/customer/list",
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            console.log(data);
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
            <td>${data[i].firstName}</td>
            <td><button onclick="customerDetail(${data[i].id})">${data[i].lastName}</button></td>
            <td><button onclick="deleteCustomerById(${data[i].id})">Delete</button></td>
        </tr>`
            }
            document.getElementById("content").innerHTML = content;
        }
    });
}
getAllCustomer();
function createCustomer(){
    let firstName = $('#firstName').val();
    let lastName = $('#lastName').val();
    let newCustomer = {
        "firstName": firstName,
        "lastName": lastName
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/customer/create-customer",
       data: JSON.stringify(newCustomer),
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        success:function (){
            alert("ok");
            getAllCustomer();
        }
    })
    event.preventDefault();
}
function deleteCustomerById(id) {
    $.ajax(
        {
            type: "DELETE",
            url: "http://localhost:8080/api/customer/delete/" + id,
            success: function () {
                alert("deleted successfully");
                getAllCustomer();
            }
        }
    )

}
function customerDetail(id){
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/customer/find/"+ id,
        success:function (data){
            console.log(data)
            let find=""
            find+= `<tr>
            <td>${data.firstName}</td>
            <td>${data.lastName}</td>
        </tr>`
            document.getElementById("find").innerHTML=find;
        }
    })


}

