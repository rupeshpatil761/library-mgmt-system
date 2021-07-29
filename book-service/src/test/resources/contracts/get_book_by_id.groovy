import org.springframework.cloud.contract.spec.Contract;

Contract.make {

    description("get book by id")

    request {
        method("GET")
        urlPath("/books/12")
    }

    response {
        status OK()
        headers {
            header "Content-Type":"application/json"
        }
        body(file("get_book_by_id_response.json"))
    }
}