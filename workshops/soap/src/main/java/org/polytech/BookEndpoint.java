package org.polytech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetBookRequest;
import io.spring.guides.gs_producing_web_service.GetBookResponse;

@Endpoint
public class BookEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private final BookRepository bookRepository;

	@Autowired
	public BookEndpoint(final BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
	@ResponsePayload
	public GetBookResponse getCountry(@RequestPayload final GetBookRequest request) {
		final GetBookResponse response = new GetBookResponse();
		response.setBook(bookRepository.findBook(request.getId()));

		return response;
	}
}